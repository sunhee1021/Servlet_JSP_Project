package kr.or.bit.ainboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;

import kr.or.bit.ainboard.dto.AinBoard;
import kr.or.bit.ainboard.dto.AinReply;
import kr.or.bit.member.dto.MemberDto;

public class AinBoardDao {
	DataSource ds = null;
	
	public AinBoardDao(){
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//게시물 총 건수 구하기
	public int totalBoardCount() {
		
		int totalcount = 0;
		
		String sql="select count(*) cnt from ain_board";
		
		try (Connection conn = ds.getConnection();
			 PreparedStatement selectpstmt = conn.prepareStatement(sql);){
			ResultSet rs = selectpstmt.executeQuery();
			
			if(rs.next()) {
				totalcount = rs.getInt("cnt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalcount;
	}
	
	//게시물 목록보기
	public List<AinBoard> list(int cpage, int pagesize) {
		List<AinBoard> list = null;
		
		String sql =  "select * from (select rownum rn, c_number, title, content, writedate, filename, refer, depth, step, viewcount, a.email, nickname, filerealname, filesize"
				+ "	from ( SELECT * FROM ain_board ORDER BY refer DESC , step ASC) a"
				+ " join member m on a.email = m.email"
				+ "	where rownum <= ?) where rn >= ?";
		
		int start = cpage * pagesize - (pagesize -1); //1 * 5 - (5 - 1) >> 1
		int end = cpage * pagesize; // 1 * 5 >> 5
		
		
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);

			ResultSet rs = pstmt.executeQuery();
			
			list = new ArrayList<AinBoard>();
			
			while(rs.next()) {
				AinBoard board = new AinBoard();
				board.setcNumber(rs.getInt("c_number"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWritedate(rs.getDate("writedate"));
				board.setFilename(rs.getString("filename"));
				board.setViewcount(rs.getInt("viewcount"));
				board.setEmail(rs.getString("email"));
				
				board.setNickname(rs.getString("nickname"));
				
				board.setFilerealname(rs.getString("filerealname"));
				board.setFilesize(rs.getInt("filesize"));
				
				board.setRefer(rs.getInt("refer"));
				board.setStep(rs.getInt("step"));
				board.setDepth(rs.getInt("depth"));
				
				list.add(board);
			}
			
		} catch (Exception e) {
			System.out.println("오류 :" + e.getMessage());
		}
		
		return list;
	}
	
	//게시글 조회수 증가
	public boolean getReadNum(String cNumber) {
		boolean result = false;
		
		String sql = "update ain_board set viewcount = viewcount + 1 where c_Number=?";
		
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			pstmt.setString(1, cNumber);
			
			int row = pstmt.executeUpdate();
			if(row > 0) {
				result = true;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//게시글 상세보기
	public AinBoard getContent(int cNumber) {
		AinBoard board = null;
		//  "select c_number, title, content, writedate, filename, "
		//		+ "refer, depth, step, viewcount, email "
		//		+ "from ain_board where c_Number=?";
		
		
		String sql = "select c_number, title, content, writedate, filename, "
				+ "refer, depth, step, viewcount, a.email, nickname, filerealname, filesize "
				+ "from ain_board a join member m on a.email = m.email where c_Number=?";
		
			try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			pstmt.setInt(1, cNumber);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				java.sql.Date writedate = rs.getDate("writedate");
				String filename = rs.getString("filename");
				String email = rs.getString("email");
				int viewcount = rs.getInt("viewcount");
				
				String nickname = rs.getString("nickname");
				
				String filerealname = rs.getString("filerealname");
				int filesize = rs.getInt("filesize");
				
				//계층형
				int refer = rs.getInt("refer");
				int step = rs.getInt("step");
				int depth = rs.getInt("depth");
				
				board = new AinBoard(cNumber, title, content, writedate, filename, refer, depth, step, viewcount, email, nickname, filerealname, filesize);
			}
			
		}catch (Exception e) {
			System.out.println("오류 :" + e.getMessage());
		}
		
		return board;
	}
	
	//댓글 조회하기
	public List<AinReply> replylist(String cNumber_fk){
		List<AinReply> list = null;
		
		/*
		 * String sql = "select cr_number, content, writedate, email, c_number " +
		 * "from ain_reply_board where c_number=? order by cr_number desc";
		 */
		
		String sql = "select cr_number, content, writedate, a.email, c_number, nickname "
				+ "from ain_reply_board a join member m on a.email = m.email "
				+ "where c_Number=? order by cr_number desc";
		
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			pstmt.setString(1, cNumber_fk);
			
			ResultSet rs = pstmt.executeQuery();
			
			list = new ArrayList<AinReply>();
			
			while(rs.next()) {
				int crNumber = rs.getInt("cr_number");
				String email = rs.getString("email");
				String content = rs.getString("content");
				java.sql.Date writedate = rs.getDate("writedate");
				int cNumber = rs.getInt("c_number");
				
				String nickname = rs.getString("nickname");
				
				AinReply replydto = new AinReply(crNumber, content, writedate, email, cNumber, nickname);
				
				list.add(replydto);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("댓글불러오는중list: "+list);
		
		return list;
	}
	
	//글쓰기-원본글
	public int writeok(AinBoard board) {
		int result = 0;
		String sql = "insert into ain_board(c_Number, title, filename, filesize, "
				+ "filerealname, content, writedate, refer, depth, step, email)"+ 
				   " values(ainsequence.NEXTVAL,?,?,?,?,?,sysdate,?,?,?,?)";
		
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
				
				pstmt.setString(1, board.getTitle());
				pstmt.setString(2, board.getFilename());
				pstmt.setLong(3, board.getFilesize());
				pstmt.setString(4, board.getFilerealname());
				pstmt.setString(5, board.getContent());
				pstmt.setInt(6, board.getRefer());
				pstmt.setInt(7, board.getDepth());
				pstmt.setInt(8, board.getStep());
				pstmt.setString(9, board.getEmail());
				
				result = pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return result;
	}
	
	//새 글 등록 시 최대참조 수 조회하기 
	public int getMaxRefer() {
		
		String sql = " select nvl(max(refer), 0) as refer_max from ain_board ";
		
		int referMax = 0;
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();) {
			
			if(rs.next()) {
				referMax = rs.getInt("refer_max");
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return referMax;
	}
	
	//게시글 수정하기
	public AinBoard getEditContent(String cNumber) {
		return this.getContent(Integer.parseInt(cNumber));
	}
	
	//게시글 수정 처리
	public int boardEdit(MultipartRequest boarddata) {
		int row = 0;
		
		String cNumber= boarddata.getParameter("cNumber");
		String title= boarddata.getParameter("title");
		String content= boarddata.getParameter("content");
		
		String sql_cnumber = "select c_number from ain_board where c_number=?";
		String sql_udpate = "update ain_board set title=? , content=? where c_number=?";
		
		try(Connection conn = ds.getConnection();
			PreparedStatement selectpstmt = conn.prepareStatement(sql_cnumber);
			PreparedStatement updatepstmt = conn.prepareStatement(sql_udpate);) {
				
			selectpstmt.setString(1, cNumber);
			
			ResultSet rs = selectpstmt.executeQuery();
			
			//데이터가 없다면 수정 불가능
			if(rs.next()) {
				updatepstmt.setString(1, title);
				updatepstmt.setString(2, content);
				updatepstmt.setString(3, cNumber);
				
				row = updatepstmt.executeUpdate();
			}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		
		return row;
	}
	
	//답글쓰기 처리
	@SuppressWarnings("resource")
	public int reWriteok(AinBoard board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			conn = ds.getConnection();
			
			int cNumber = board.getcNumber(); 
			String title = board.getTitle();
			String filename = board.getFilename();
			long filesize = board.getFilesize();
			String filerealname = board.getFilerealname();
			String content = board.getContent();
			String email = board.getEmail();
			
			System.out.println("cNumber: " + cNumber);
			
			//1. 답글 
			//현재 내가 읽은 글의 refer , depth , step (원본글 ,답글)
			String refer_depth_step_sal ="select refer , depth , step from ain_board where c_Number=?";
			
			//2. 위치
			//step (순서) : 나중에 쓴 답글이 위로 올라오게 하겠다
			//내가 읽은 글의 step 보다 큰 값은 +1 해서 증가 시켜 놓는다
			String step_update_sql = "update ain_board set step=step+1 where step > ? and refer =? ";
			
			//3. 답글 insert
			String rewrite_sql = "insert into ain_board(c_Number, title, filename, filesize, "
								+ "filerealname, content, writedate, refer, depth, step, email)"
								+ " values(ainsequence.NEXTVAL,?,?,?,?,?,sysdate,?,?,?,?)";
			
			pstmt = conn.prepareStatement(refer_depth_step_sal);
			pstmt.setInt(1, cNumber);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { //데이터가 있다면 ... 원본글의  refer , step , depth 존재
				int refer = rs.getInt("refer");
				int step = rs.getInt("step");
				int depth = rs.getInt("depth");
				
				pstmt = conn.prepareStatement(step_update_sql); //컴파일
				//기존 step + 1 >> update 구문 실행
				pstmt.setInt(1, step);
				pstmt.setInt(2, refer);
				pstmt.executeUpdate();
				
				//filename,filesize,refer,depth,step
				pstmt = conn.prepareStatement(rewrite_sql); //컴파일 
				pstmt.setString(1, title);
				pstmt.setString(2, filename);
				pstmt.setLong(3, filesize);
				pstmt.setString(4, filerealname);
				pstmt.setString(5, content);
				
				//답변
				pstmt.setInt(6, refer);
				pstmt.setInt(7, depth+1); // 규칙 현재 읽은 글에 depth + 1
				pstmt.setInt(8, step+1); // 순서 update 통해서  자리 확보 + 1
				
				pstmt.setString(9, email);
				
				int row = pstmt.executeUpdate();
				if(row > 0) {
					result = row;
				}else {
					result = -1;
				}

			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환
			}catch (Exception e) {
				
			}
		}
		
		return result;
		}
	
	//게시글 삭제하기
	public int boardDelete(String cNumber) {
		//해당 게시글의 글번호, refer, depth, step 조회 쿼리문
		String sql1 = "select c_number, refer, depth, step from ain_board where c_number = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			int c_number = Integer.parseInt(cNumber);
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, c_number);
			
			conn.setAutoCommit(false);
			
			rs = pstmt.executeQuery();
			
			//삭제하려는 게시글의 들여쓰기
			int beforeDepth = 0;
			//삭제하려는 게시글 다음 답글의 들여쓰기
			int afterDepth = 0;
			//글의순서
			int step = 0;
			//참조 게시글의 값
			int refer = 0;
			
			if(rs.next()) {
				beforeDepth = rs.getInt("depth");
				afterDepth = rs.getInt("depth");
				step = rs.getInt("step") + 1;
				
				refer = rs.getInt("refer");
				
				
				
				//해당 글(원본글) 댓글 삭제
				String replyDel_sql = "delete from ain_reply_board where c_Number = "+ rs.getInt("c_number");
				PreparedStatement deleteReply = conn.prepareStatement(replyDel_sql);
				deleteReply.executeQuery();
				
				//해당글 삭제
				String deletesql = " delete from ain_board where c_number = " + rs.getInt("c_number");
				PreparedStatement delete = conn.prepareStatement(deletesql);
				delete.executeUpdate();
			}
			
			do {
				// 같은 참조값을 가지는 다음 순서 답글의 들여쓰기, 순서, 글번호를 조회
				String sql2 = " select depth, step, c_number, refer from ain_board where step = ? and refer = ?";
				
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				
				pstmt2.setInt(1, step);
				pstmt2.setInt(2, refer);
				
				rs = pstmt2.executeQuery();
				
				if(rs.next()) {
					
					beforeDepth = afterDepth;
					afterDepth = rs.getInt(1);
					step = rs.getInt(2) + 1;
					
					c_number = rs.getInt(3);
					
					if(!(beforeDepth < afterDepth)) {
						break;
					}
					
					//해당 글(답글) 댓글 삭제
					String replyDel_sql = "delete from ain_reply_board where c_Number = "+ rs.getInt("c_number");
					PreparedStatement deleteReply = conn.prepareStatement(replyDel_sql);
					deleteReply.executeQuery();
					
					//답글 삭제
					String deletesql = " delete from ain_board where c_number = " + rs.getInt(3);
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
	
	//댓글쓰기
	public int replyAdd(int c_Number, String email, String content) {
		int result = 0;
		
		
		String sql = "insert into ain_reply_board (cr_number, content, writedate, email, c_number)"
				+ "VALUES (ainReplyseq.NEXTVAL, ?, sysdate, ?, ?)";
		
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
				
			pstmt.setString(1, content);
			pstmt.setString(2, email);
			pstmt.setInt(3, c_Number);
			
			result = pstmt.executeUpdate();
					
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		return result;
	}
	
	//댓글삭제
	public int replyDelete(String crNumber) {
		int row = 0;
		
		String sql = "delete from ain_reply_board where cr_Number=?";
		
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
				
			pstmt.setString(1, crNumber);
				
			row = pstmt.executeUpdate();
						
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		return row;
	}
	
	//관리자페이지용
	
	//전체회원수
	public int totalMemberCount() {
		int result = 0;
		
		String sql = "select count(*) cnt from member";
		
		try (Connection conn = ds.getConnection();
			PreparedStatement selectpstmt = conn.prepareStatement(sql);){
			ResultSet rs = selectpstmt.executeQuery();
				
				if(rs.next()) {
					result = rs.getInt("cnt");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
		return result;
	}
	
	//전체 회원 목록
	public List<MemberDto> memberList(int cpage, int pagesize) {
		List<MemberDto> list = null;
		
		String sql =  "select * from (select rownum rn, email, password, division, nickname"
				+ "	from member"
				+ "	where rownum <= ?) where rn >= ?";
		
		int start = cpage * pagesize - (pagesize -1); //1 * 5 - (5 - 1) >> 1
		int end = cpage * pagesize; // 1 * 5 >> 5
		
		
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);

			ResultSet rs = pstmt.executeQuery();
			
			list = new ArrayList<MemberDto>();
			
			while(rs.next()) {
				MemberDto member = new MemberDto();
				member.setEmail(rs.getString("email"));
				member.setPassword(rs.getString("password"));
				member.setDivision(rs.getString("division"));
				member.setNickname(rs.getString("nickname"));
				
				list.add(member);
			}
			
		} catch (Exception e) {
			System.out.println("오류 :" + e.getMessage());
		}
		return list;
	}
	
	//회원 정지처리
		public int memberDelete(String email) {
			
			int result = 0;
			
			//해당 이메일을 가진 멤버 division 정보 변경
			String sql = "update member set division=99"
						+ "where email=?";
			
			try(Connection conn = ds.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);) {
					
					pstmt.setString(1, email);
					
					result = pstmt.executeUpdate();
						
				}catch (Exception e) {
					e.printStackTrace();
				}
			return result;
		}
		
	//회원 복구처리
		public int memberReJoin(String email) {
			
			int result = 0;
			
			//해당 이메일을 가진 멤버 division 정보 변경
			String sql = "update member set division=1"
						+ "where email=?";
			
			try(Connection conn = ds.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);) {
					
					pstmt.setString(1, email);
					
					result = pstmt.executeUpdate();
						
				}catch (Exception e) {
					e.printStackTrace();
				}
			return result;
		}

	//회원 이메일검색
		public List<MemberDto> searchEmail(String email) {
			List<MemberDto> list = new ArrayList<MemberDto>();
			
			String sql =  "select email, password, division, nickname "
					+ "from member where email like ?";
			
			
			try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
				
				pstmt.setString(1, "%" + email + "%");

				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					MemberDto member = new MemberDto();
					member.setEmail(rs.getString("email"));
					member.setPassword(rs.getString("password"));
					member.setDivision(rs.getString("division"));
					member.setNickname(rs.getString("nickname"));
					
					list.add(member);
				}
				
			} catch (Exception e) {
				System.out.println("오류 :" + e.getMessage());
			}
			
			
			return list;
		}
		
	//회원 닉네임 검색
		public List<MemberDto> searchNickname(String nickname) {
			List<MemberDto> list = new ArrayList<MemberDto>();
			
			String sql =  "select email, password, division, nickname "
					+ "from member where nickname like ?";
			
			
			try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
				
				pstmt.setString(1, "%" + nickname + "%");

				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					MemberDto member = new MemberDto();
					member.setEmail(rs.getString("email"));
					member.setPassword(rs.getString("password"));
					member.setDivision(rs.getString("division"));
					member.setNickname(rs.getString("nickname"));
					
					list.add(member);
				}
				
			} catch (Exception e) {
				System.out.println("오류 :" + e.getMessage());
			}
			
			
			return list;
		}

}
