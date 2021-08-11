package kr.or.bit.qnaboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.or.bit.qnaboard.dto.QnaBoard;
import kr.or.bit.utils.StringUtils;

public class QnaBoardDao {

	DataSource ds = null;

	public QnaBoardDao() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");

		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e.getMessage());
			return;
		}
	}

	/**
	 * Q&A 게시판 글 목록 조회
	 * 
	 * @param start    - 해당 페이지 시작 글 번호
	 * @param end      - 해당 페이지 끝 글 번호
	 * @param title    - 글제목
	 * @param content  - 그내용
	 * @param nickname - 작성자
	 * @param email    - 회원아이디
	 * @return Q&A 리스트
	 */
	public List<QnaBoard> searchListQnaBoard(int start, int end, Map<String, String> searchCondition) {

		List<QnaBoard> list = new ArrayList<>();

		StringBuilder searchBuilder = new StringBuilder()
				.append("	select *	")
				.append("	from	")
				.append("	        (select rownum as rn, r.*	")
				.append("	        from 	")
				.append("	                (select  	")
				.append("	                        QNA_NUMBER,	")
				.append("	                        QNA_TITLE,	")
				.append("	                        QNA_FILENAME,	")
				.append("	                        QNA_FILESIZE,	")
				.append("	                        QNA_REAL_FILENAME,	")
				.append("	                        QNA_CONTENT,	")
				.append("	                        QNA_WRITEDATE,	")
				.append("	                        QNA_REFER,	")
				.append("	                        QNA_DEPTH,	")
				.append("	                        QNA_STEP,	")
				.append("	                        QNA_VIEWCOUNT,	")
				.append("	                        q.EMAIL,	")
				.append("	                        QNA_PASSWORD,	")
				.append("	                        QNA_STATUS,	")
				.append("	                        m.DIVISION,	")
				.append("	                        m.NICKNAME	")
				.append("	                from    	")
				.append("	                        qna_board q	")
				.append("	                join 	")
				.append("	                        member m	")
				.append("	                on      	")
				.append("	                        q.email = m.email	")
				.append("	                order by 	")
				.append("	                        qna_refer desc, qna_step asc) r	")
				.append("	                                                        ) a	")
				.append("	where a.rn between ? and ?	");

		// 조건 절 채우기
		StringUtils util = new StringUtils();
		searchBuilder.append(util.setSearchCondition(searchCondition));

		String searchsql = searchBuilder.toString();

		try (Connection conn = ds.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(searchsql);) {

			int count = 0;

			pstmt.setInt(++count, start);
			pstmt.setInt(++count, end);
			
			util.setPstmt(count, searchCondition, pstmt);
			
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				QnaBoard board = new QnaBoard();
				board.setQnaNumber(rs.getInt("QNA_NUMBER"));
				board.setQnaTitle(rs.getString("QNA_TITLE"));
				board.setQnaFilename(rs.getString("QNA_FILENAME"));
				board.setQnaFilesize(rs.getInt("QNA_FILESIZE"));
				board.setQnaRealFilename(rs.getString("QNA_REAL_FILENAME"));
				board.setQnaContent(rs.getString("QNA_CONTENT"));
				board.setQnaWritedate(rs.getDate("QNA_WRITEDATE"));
				board.setQnaRefer(rs.getInt("QNA_REFER"));
				board.setQnaDepth(rs.getInt("QNA_DEPTH"));
				board.setQnaStep(rs.getInt("QNA_STEP"));
				board.setQnaViewcount(rs.getInt("QNA_VIEWCOUNT"));
				board.setEmail(rs.getString("EMAIL"));
				board.setQnaPassword(rs.getString("QNA_PASSWORD"));
				board.setQnaStatus(rs.getInt("QNA_STATUS"));
				board.setDivision(rs.getString("DIVISION"));
				board.setNickname(rs.getString("NICKNAME"));

				list.add(board);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<QnaBoard> searchListQnaBoard(int start, int end) {
		return searchListQnaBoard(start, end, new HashMap<String, String>());
	}
	
	public int getTotal(Map<String, String> searchCondition) {
		int result = 0;

		String sql = " select count(*) as cnt from qna_board where 1 = 1 ";

		StringUtils util = new StringUtils();
		
		sql += util.setSearchCondition(searchCondition);
		
		try (Connection conn = ds.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			util.setPstmt(0, searchCondition, pstmt);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public int getTotal() {
		return getTotal(new HashMap<String, String>());
	}

	public QnaBoard searchContentQnaBoard(int qnaNumber) {

		QnaBoard qnaBoard = null;

		StringBuilder searchBuilder = new StringBuilder()
				.append("	select  	")
				.append("	        QNA_NUMBER,	")
				.append("	        QNA_TITLE,	")
				.append("	        QNA_FILENAME,	")
				.append("	        QNA_FILESIZE,	")
				.append("	        QNA_REAL_FILENAME,	")
				.append("	        QNA_CONTENT,	")
				.append("	        QNA_WRITEDATE,	")
				.append("	        QNA_REFER,	")
				.append("	        QNA_DEPTH,	")
				.append("	        QNA_STEP,	")
				.append("	        QNA_VIEWCOUNT,	")
				.append("	        q.EMAIL,	")
				.append("	        QNA_PASSWORD,	")
				.append("	        QNA_STATUS,	")
				.append("	        m.DIVISION,	")
				.append("	        m.NICKNAME	")
				.append("	from    	")
				.append("	        qna_board q	")
				.append("	join 	")
				.append("	        member m	")
				.append("	on      	")
				.append("	        q.email = m.email	")
				.append("	where 	1 = 1	")
				.append(" 			and QNA_NUMBER = ? ");

		String searchsql = searchBuilder.toString();

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(searchsql);) {
			
			// 조회수 증가
			updateViewCount(qnaNumber);
			
			pstmt.setInt(1, qnaNumber);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				QnaBoard board = new QnaBoard();
				board.setQnaNumber(rs.getInt("QNA_NUMBER"));
				board.setQnaTitle(rs.getString("QNA_TITLE"));
				board.setQnaFilename(rs.getString("QNA_FILENAME"));
				board.setQnaFilesize(rs.getInt("QNA_FILESIZE"));
				board.setQnaRealFilename(rs.getString("QNA_REAL_FILENAME"));
				board.setQnaContent(rs.getString("QNA_CONTENT"));
				board.setQnaWritedate(rs.getDate("QNA_WRITEDATE"));
				board.setQnaRefer(rs.getInt("QNA_REFER"));
				board.setQnaDepth(rs.getInt("QNA_STEP"));
				board.setQnaViewcount(rs.getInt("QNA_VIEWCOUNT"));
				board.setEmail(rs.getString("EMAIL"));
				board.setQnaPassword(rs.getString("QNA_PASSWORD"));
				board.setQnaStatus(rs.getInt("QNA_STATUS"));
				board.setDivision(rs.getString("DIVISION"));
				board.setNickname(rs.getString("NICKNAME"));

				qnaBoard = board;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return qnaBoard;
	}

	/**
	 * Q&A 글 등록 페이지에서 작성 완료 버튼 클릭 시 QNA_BOARD 테이블에 입력받은 데이터를 삽입한다.
	 * 
	 * @param qnaboard - DB TABLE QNA_BOARD DB컬럼
	 * @return 결과 1이면 성공 결과 0이면 실패
	 */
	public int insertQnaContent(QnaBoard qnaboard) {
		int result = 0;

		String insertsql = new StringBuilder()
				.append(" insert into qna_board ")
				.append("(	qna_number, ")
				.append("	qna_title, ")
				.append(" 	qna_filename, ")
				.append(" 	qna_filesize, ")
				.append(" 	qna_real_filename, ")
				.append(" 	qna_content, ")
				.append(" 	qna_writedate, ")
				.append(" 	qna_refer, ")
				.append(" 	qna_depth, ")
				.append(" 	qna_step, ")
				.append(" 	email, ")
				.append(" 	qna_password, ")
				.append(" 	qna_status	) ")

				.append(" values ")
				.append(" (	qna_seq.nextval, ")
				.append(" 	?, ")
				.append(" 	?, ")
				.append(" 	?, ")
				.append(" 	?, ")
				.append(" 	?, ")
				.append(" 	sysdate, ")
				.append(" 	?, ")
				.append(" 	?, ")
				.append(" 	?, ")
				.append(" 	?, ")
				.append(" 	?, ")
				.append(" 	?	) ").toString();
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			conn = ds.getConnection(); 
			pstmt = conn.prepareStatement(insertsql);
			
			
			pstmt.setString(1, qnaboard.getQnaTitle());
			pstmt.setString(2, qnaboard.getQnaFilename());
			pstmt.setLong(3, qnaboard.getQnaFilesize());
			pstmt.setString(4, qnaboard.getQnaRealFilename());
			pstmt.setString(5, qnaboard.getQnaContent());
			pstmt.setInt(6, qnaboard.getQnaRefer());
			pstmt.setInt(7, qnaboard.getQnaDepth());
			pstmt.setInt(8, qnaboard.getQnaStep());
			pstmt.setString(9, qnaboard.getEmail());
			pstmt.setString(10, qnaboard.getQnaPassword());
			pstmt.setInt(11, qnaboard.getQnaStatus());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public int updateQnaContent(QnaBoard qnaboard) {
		int result = 0;

		String insertsql = new StringBuilder().append("	update	qna_board 			")
				.append("	set 						")
				.append("		qna_title	   		= ?,	")
				.append("		qna_filename	   	= ?,	")
				.append("		qna_filesize	  	= ?,	")
				.append("		qna_real_filename	= ?,	")
				.append("		qna_content	   		= ?,	")
				.append("		qna_writedate	   	= sysdate,	")
				.append("		email	   			= ?,	")
				.append("		qna_password	   	= ?,	")
				.append("		qna_status	   		= ?	")
				.append("	where						")
				.append("		qna_number	   		= ?	")

				.toString();

		try (Connection conn = ds.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(insertsql);) {

			pstmt.setString(1, qnaboard.getQnaTitle());
			pstmt.setString(2, qnaboard.getQnaFilename());
			pstmt.setLong(3, qnaboard.getQnaFilesize());
			pstmt.setString(4, qnaboard.getQnaRealFilename());
			pstmt.setString(5, qnaboard.getQnaContent());
			pstmt.setString(6, qnaboard.getEmail());
			pstmt.setString(7, qnaboard.getQnaPassword());
			pstmt.setInt(8, qnaboard.getQnaStatus());
			pstmt.setInt(9, qnaboard.getQnaNumber());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 새 글 등록 시 최대참조 수 조회하기
	 * 
	 * @return 참조 수 중 제일 높은 수
	 */
	public int getMaxRefer() {

		String sql = " select max(qna_refer) as refer_max from qna_board ";

		int referMax = 0;
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {

			if (rs.next()) {
				referMax = rs.getInt("refer_max");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return referMax;
	}

	/**
	 * 조회수 증가
	 * 
	 * @param qnaNumber qna_board primary key
	 */
	public void updateViewCount(int qnaNumber) {
		String sql = " update qna_board set qna_viewcount = (select qna_viewcount + 1 from qna_board where qna_number = "
				+ qnaNumber + ") where qna_number = "
				+ qnaNumber + " ";
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 참조 글 번호, 들여쓰기, 글 순서 조회
	 * 
	 * @param qnaNumber
	 * @return
	 */
	public QnaBoard getReferDepthStep(int qnaNumber) {
		
		String sql = " select qna_refer as refer, qna_depth as depth, qna_step as step from qna_board where qna_number = ? ";
		
		QnaBoard board = new QnaBoard();
		
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
				
				pstmt.setInt(1, qnaNumber);
			
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					board.setQnaRefer(rs.getInt(1));
					board.setQnaDepth(rs.getInt(2));
					board.setQnaStep(rs.getInt(3));
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		
		return board;
	}

	public int updateQnaRewrite(QnaBoard qnaboard) {
		
		String sql = " update qna_board set qna_step = qna_step + 1 where qna_refer = ? and qna_step > (select qna_step from qna_board where qna_number = ?) ";
		String sql2 = " update qna_board set qna_status = 0 where qna_number = ? ";
		
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		try{
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
		
			pstmt.setInt(1, qnaboard.getQnaRefer());
			pstmt.setInt(2, qnaboard.getQnaNumber());
			
			pstmt2.setInt(1, qnaboard.getQnaNumber());
			
			pstmt.executeUpdate();
			result = pstmt2.executeUpdate();
				
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
			try {
				pstmt2.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
	
	/**
	 * 하위답글을 포함해서 삭제
	 * 
	 * @param qnaNumber
	 */
	public int deleteContent(int qnaNumber) {
		
		String sql1 = " select qna_depth, qna_step, qna_number, qna_refer from qna_board where qna_number = ? ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, qnaNumber);
			
			conn.setAutoCommit(false);
			
			rs = pstmt.executeQuery();
			
			int beforeDepth = 0;
			int afterDepth = 0;
			int step = 0;
			int refer = 0;
			
			if(rs.next()) {
				beforeDepth = rs.getInt(1);
				afterDepth = rs.getInt(1);
				step = rs.getInt(2) + 1;
				refer = rs.getInt(4);
				
				String deletesql = " delete from qna_board where qna_number = " + rs.getInt(3);
				PreparedStatement delete = conn.prepareStatement(deletesql);
				delete.executeUpdate();
			}
			
			do {
				String sql2 = " select qna_depth, qna_step, qna_number from qna_board where qna_step = ? and qna_refer = ?";
				
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				
				pstmt2.setInt(1, step);
				pstmt2.setInt(2, refer);
				
				rs = pstmt2.executeQuery();
				
				if(rs.next()) {
					
					//beforeDepth = afterDepth;
					afterDepth = rs.getInt(1);
					step = rs.getInt(2) + 1;
					
					if(!(beforeDepth < afterDepth)) {
						break;
					}
					
					// 삭제
					String deletesql = " delete from qna_board where qna_number = " + rs.getInt(3);
					PreparedStatement delete = conn.prepareStatement(deletesql);
					delete.executeUpdate();
					
				} else {
					break;
				}
				
			} while(beforeDepth < afterDepth);
			
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return 1;
		
	}

}
