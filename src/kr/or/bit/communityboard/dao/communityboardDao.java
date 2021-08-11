package kr.or.bit.communityboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import kr.or.bit.communityboard.dto.CommunityBoard;
import kr.or.bit.communityboard.dto.CommunityReplyBoard;
import kr.or.bit.communityboard.service.CommunityBoardService;

public class communityboardDao {
	DataSource ds = null;

	public communityboardDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}

	// 글쓰기
	public int board_Write(CommunityBoard boarddata) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			conn = ds.getConnection();
			String sql = "insert into community_board(c_number, c_title, c_content, c_writedate, c_file, c_viewcount, email)"
					+ " values (community_board_seq.nextval,       ?,        ?,     sysdate,        ?,        0         ,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, boarddata.getcTitle());
			pstmt.setString(2, boarddata.getcContent());
			pstmt.setString(3, boarddata.getcFilename());
			pstmt.setString(4, boarddata.getEmail()); 

			row = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {

			}
		}
		return row;
	}
	

	//게시물 목록보기
		public List<CommunityBoard> list(int cpage , int pagesize){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<CommunityBoard> list = null;
			try {
				conn = ds.getConnection();
				String sql = "select * from " +
                        "(select rownum rn,c_number, email, c_title, c_content, c_writedate, c_viewcount "+
                        " from ( SELECT * FROM community_board order by c_number desc ) "+
	                       " where rownum <= ?" +  //endrow
	         ") where rn >= ?"; //startrow
				pstmt = conn.prepareStatement(sql);
				//공식같은 로직
				int start = cpage * pagesize - (pagesize -1); //1 * 5 - (5 - 1) >> 1
				int end = cpage * pagesize; // 1 * 5 >> 5
				//
				pstmt.setInt(1, end);
				pstmt.setInt(2, start);
				
				rs = pstmt.executeQuery();
				list = new ArrayList<CommunityBoard>();
				while(rs.next()) {
					CommunityBoard board = new CommunityBoard();
					board.setcNumber(rs.getInt("c_number"));
					board.setcTitle(rs.getString("c_title"));
					board.setEmail(rs.getString("email"));
					board.setcWritedate(rs.getDate("c_writedate"));
					board.setcViewcount(rs.getInt("c_viewcount"));
					
					list.add(board);
				}
				
			}catch (Exception e) {
				System.out.println("오류 :" + e.getMessage());
			}finally {
				try {
					pstmt.close();
					rs.close();
					conn.close();//반환
				} catch (Exception e2) {
					
				}
			}
				
			return list;
		}
		
		//게시물 총 건수 구하기
		public int totalBoardCount() {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				int totalcount = 0;
				try {
					conn = ds.getConnection(); //dbcp 연결객체 얻기
					String sql="select count(*) cnt from community_board";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						totalcount = rs.getInt("cnt");
					}
				}catch (Exception e) {
					
				}finally {
					try {
						pstmt.close();
						rs.close();
						conn.close();//반환  connection pool 에 반환하기
					}catch (Exception e) {
						
					}
				}
				return totalcount;
			}

		//게시물 상세보기
		public CommunityBoard getContent(int c_number) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			CommunityBoard board= null;
			
			try {
				conn = ds.getConnection();
				String sql=" select * from community_Board where c_number=? "; //* 하지 말자
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, c_number);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					String email = rs.getString("email");
					String c_title = rs.getString("c_title");
					String c_content = rs.getString("C_CONTENT");
					String c_file = rs.getString("c_file");
					
					java.sql.Date c_writedate = rs.getDate("c_writedate");
					int c_viewcount = rs.getInt("c_viewcount");
					
					
					board = new CommunityBoard();
					board.setcContent(c_content);
					board.setcFilename(c_file);
					board.setcNumber(c_number); 
					board.setcTitle(c_title);
					board.setcViewcount(c_viewcount);
					board.setcWritedate(c_writedate);
					board.setEmail(email);
					
				}
				
			} catch (Exception e) {
				System.out.println("c_content: " + e.getMessage());
			}finally {
				try {
					pstmt.close();
					rs.close();
					conn.close();//반환하기
				} catch (Exception e2) {
					
				}
			}
			
			return board;
		}

		//게시글 조회수 증가
		public boolean getReadNum(String c_number) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				boolean result = false;
				try {
					conn = ds.getConnection();
					String sql="update community_board set c_viewcount = c_viewcount + 1 where c_number=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, c_number);
					
					int row = pstmt.executeUpdate();
					if(row > 0 ) {
						result = true;
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					try {
						pstmt.close();
						conn.close();//반환
					}catch (Exception e) {
						
					}
				}
				return result;
			}

		//게시글 삭제하기
		public int deleteOk(int c_number) { // 이메일정보 입력시 글 삭제 가능
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			int row = 0;
			try {
					conn = ds.getConnection();
					
					
					
					//게시글 삭제
					String sql_board="delete from community_board where c_number=?";
					
					pstmt = conn.prepareStatement(sql_board);
					pstmt.setInt(1, c_number);
					row = pstmt.executeUpdate();
					
					
					
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					pstmt.close();
					conn.close();//반환
				} catch (Exception e2) {
					
				}
			}
			return row;
		}
		public CommunityBoard getEditContent(String idx) {
			return this.getContent(Integer.parseInt(idx));
			//조회화면 동일 (기존에 있는 함수 재활용)
		}
		
		//게시글 수정하기 처리
		//public int boardEdit(Board boarddata){}
		public int boardEdit(HttpServletRequest boarddata) {
			String c_number= boarddata.getParameter("c_number");
			String email= boarddata.getParameter("email");
			String c_title= boarddata.getParameter("c_title");
			String c_content= boarddata.getParameter("c_content");
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int row = 0;
			
			try {
				conn = ds.getConnection();
				String sql_idx = "select c_number  from community_board where c_number=? ";
				String sql_udpate = "update community_board set email=? ,"+
				                    " c_title=? , c_content=? where c_number=?";
				pstmt = conn.prepareStatement(sql_idx);
				pstmt.setString(1, c_number);
				
				rs = pstmt.executeQuery();
				//판단 (데이터 있다며 : 수정가능 , 없다면 : 수정불가
				if(rs.next()) {
					//경고
					pstmt.close();
					//업데이트
					pstmt = conn.prepareStatement(sql_udpate);
					pstmt.setString(1, email);
					pstmt.setString(2, c_title);
					pstmt.setString(3, c_content);
					pstmt.setString(4, c_number);
					row = pstmt.executeUpdate();
					//System.out.println("row : " + row);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					pstmt.close();
					rs.close();
					conn.close();//반환
				} catch (Exception e2) {
					
				}
			}
		
			return row;
		}
}

		










