package kr.or.bit.noticeboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;

import kr.or.bit.noticeboard.dto.NoticeBoard;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


//CRUD 함수 > ConnectionPool > 함수단위 연결 ,받환 
public class NoticeDao {
	DataSource ds = null;
	
	public NoticeDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
//	//글쓰기(원본글)
	public int writeok(NoticeBoard boarddata) {
		Connection conn = null; 
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			conn = ds.getConnection();
			System.out.println("쿼리문 앞");
			String sql="insert into notice_board (n_number, n_title, n_filename, n_filesize, n_real_filename, n_viewcount, email, n_content, n_writedate)"+ 
					   " values(n_number_seq.nextval,?,?,?,?,0,'admin@naver.com',?,sysdate)";
//			System.out.println("쿼리문 뒤");
			
//			INSERT INTO notice_board (n_number, n_title, n_filename, n_filesize, n_real_filename, n_viewcount, email, N_CONTENT, N_WRITEDATE)
//			VALUES (1, '비양도에 대한 정보가 추가될 예정입니다.', null, null, null, 5, 'admin@naver.com', 'ㅎㅇㅎㅇ', sysdate);
			pstmt = conn.prepareStatement(sql);
			System.out.println("쿼리문 뒤");
			pstmt.setString(1, boarddata.getN_TITLE());
			pstmt.setString(2, boarddata.getN_FILENAME());
			pstmt.setLong(3, boarddata.getN_FILESIZE());
			pstmt.setString(4, boarddata.getN_REAL_FILENAME());
			pstmt.setString(5, boarddata.getN_CONTENT());
			System.out.println("여기오니 제발");
			System.out.println(boarddata.getN_TITLE());
			System.out.println( boarddata.getN_FILENAME());
			System.out.println(boarddata.getN_FILESIZE());
			System.out.println( boarddata.getN_REAL_FILENAME());
			System.out.println(boarddata.getN_CONTENT());
			row = pstmt.executeUpdate();
			System.out.println("여기오니 제발2");
			
		}catch(Exception e) {
			
		}finally {
			try {
				pstmt.close();
				conn.close();//반환하기
			} catch (Exception e2) {
			
			}
		}
		
		
		return row;
	}


	//게시물 목록보기
	public List<NoticeBoard> list(int cpage , int pagesize){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<NoticeBoard> list = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from " +
			                           "(select rownum rn, N_NUMBER,N_TITLE,N_FILENAME,N_FILESIZE,N_REAL_FILENAME,N_VIEWCOUNT,EMAIL,N_CONTENT,N_WRITEDATE" +
			                           " from (SELECT * FROM NOTICE_BOARD ORDER BY N_NUMBER DESC)"+
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
			list = new ArrayList<NoticeBoard>();
			while(rs.next()) {
				NoticeBoard board = new NoticeBoard();
				board.setN_NUMBER(rs.getInt("N_NUMBER"));
				board.setN_TITLE(rs.getString("N_TITLE"));
				board.setN_FILENAME(rs.getString("N_FILENAME"));
				board.setN_FILESIZE(rs.getLong("N_FILESIZE"));
				board.setN_REAL_FILENAME(rs.getString("N_REAL_FILENAME"));
				board.setN_VIEWCOUNT(rs.getInt("N_VIEWCOUNT"));
				board.setEMAIL(rs.getString("EMAIL"));
				board.setN_CONTENT(rs.getString("N_CONTENT"));
				board.setN_WRITEDATE(rs.getDate("N_WRITEDATE"));
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
				String sql="select count(*) cnt from NOTICE_BOARD";
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

// 게시물 상세보기
	public NoticeBoard getContent(int n_number) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeBoard board= null;
		
		try {
			conn = ds.getConnection();
			String sql="select * from notice_board where n_number=?"; //* 하지 말자
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, n_number);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String N_TITLE = rs.getString("N_TITLE");
				String N_FILENAME = rs.getString("N_FILENAME");
				long N_FILESIZE = rs.getLong("N_FILESIZE");
				String N_REAL_FILENAME = rs.getString("N_REAL_FILENAME");
				int N_VIEWCOUNT = rs.getInt("N_VIEWCOUNT");
				String EMAIL = rs.getString("EMAIL");
				String N_CONTENT = rs.getString("N_CONTENT");
				java.sql.Date N_WRITEDATE = rs.getDate("N_WRITEDATE");
				
				
				
				
				board = new NoticeBoard(n_number,N_TITLE,N_FILENAME,N_FILESIZE,N_REAL_FILENAME,N_VIEWCOUNT,EMAIL,N_CONTENT,N_WRITEDATE);
			}
			
		} catch (Exception e) {
			System.out.println("content: " + e.getMessage());
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
	public boolean getReadNum(String n_NUMBER) {
			//update jspboard set readnum = readnum + 1 where idx=?
			Connection conn = null;
			PreparedStatement pstmt = null;
			boolean result = false;
			try {
				conn = ds.getConnection();
				String sql="update NOTICE_BOARD set N_VIEWCOUNT = N_VIEWCOUNT + 1 where N_NUMBER=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, n_NUMBER);
				
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

//	//게시글 삭제하기
	public int deleteOk(String n_NUMBER) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		try {
				conn = ds.getConnection();
				
				//게시글 삭제
				String sql_board="delete from notice_board where n_number=?";
				
				pstmt = conn.prepareStatement(sql_board);
				pstmt.setString(1, n_NUMBER);
				row = pstmt.executeUpdate();
				if(row > 0) {
				conn.commit(); //두개의 delete 실반영
				}
				else { //삭제하는 글이 존재하지 않는 경우
					row = 0;					
				}
			} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
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

//	//댓글 입력하기 (Table reply : fk(jspboard idx) )
//	public int replywrite(int idx_fk , String writer , String userid, String content,String pwd) {
//			Connection conn = null;
//			PreparedStatement pstmt = null;
//			int row = 0;
//			try {
//				conn = ds.getConnection();
//				String sql="insert into reply(no,writer,userid,content,pwd,idx_fk) "+
//				           " values(reply_no.nextval,?,?,?,?,?)";
//				pstmt =conn.prepareStatement(sql);
//				pstmt.setString(1, writer);
//				pstmt.setString(2, userid);
//				pstmt.setString(3,content);
//				pstmt.setString(4, pwd);
//				pstmt.setInt(5, idx_fk);
//				
//				row = pstmt.executeUpdate();
//			}catch (Exception e) {
//				e.printStackTrace();
//			}finally {
//				try {
//					pstmt.close();
//					conn.close();//반환
//				}catch (Exception e) {
//					
//				}
//			}
//			
//			return row;
//		}
		
//	//댓글 조회하기
//	public List<Reply> replylist(String idx_fk){
//			Connection conn = null;
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//			ArrayList<Reply> list = null;
//			
//			try {
//				conn = ds.getConnection();
//				String reply_sql = "select * from reply where idx_fk=? order by no desc";
//				
//				pstmt = conn.prepareStatement(reply_sql);
//				pstmt.setString(1, idx_fk);
//				
//				rs =pstmt.executeQuery();
//				
//				list = new ArrayList<>();
//				while(rs.next()) {
//					int no = Integer.parseInt(rs.getString("no"));
//					String writer = rs.getString("writer");
//					String userid = rs.getString("userid");
//					String pwd = rs.getString("pwd");
//					String content  =rs.getString("content");
//					java.sql.Date writedate = rs.getDate("writedate");
//					int idx = Integer.parseInt(rs.getString("idx_fk"));
//					
//					Reply replydto = new Reply(no, writer, userid, pwd, content, writedate, idx);
//					list.add(replydto);
//				}
//				
//				
//			}catch (Exception e) {
//				e.printStackTrace();
//			}finally {
//				try {
//					pstmt.close();
//					rs.close();
//					conn.close();//반환
//				}catch (Exception e) {
//					
//				}
//			}
//			
//			return list;
//		}
		
//	//댓글 삭제하기
//	public int replyDelete(String no , String pwd) {
//			Connection conn = null;
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//			int row = 0;
//			
//			try {
//				
//				String replyselect = "select pwd from reply where no=?";
//				String replydelete = "delete from reply where no=?";
//				
//				conn = ds.getConnection();
//				pstmt = conn.prepareStatement(replyselect);
//				pstmt.setString(1, no);
//				rs =pstmt.executeQuery();
//				if(rs.next()) {
//					String dbpwd = rs.getString("pwd");
//					if(pwd.equals(dbpwd)){
//						pstmt.close();
//						pstmt = conn.prepareStatement(replydelete);
//						pstmt.setString(1, no);
//						row = pstmt.executeUpdate();
//					}else {
//						row = 0;
//					}
//				}else {
//					row =-1;
//				}
//			}catch(Exception e) {
//				
//			}finally {
//				try {
//					pstmt.close();
//					rs.close();
//					conn.close();//반환
//				}catch (Exception e) {
//					
//				}
//			}
//			
//			return row;
//		}

//	//게시글 상세 (답글 쓰기) 
//	public int reWriteOk(Board boardata) {
//		//content.jsp ->(답글)-> rewrite.jsp(입력) -> submit() -> rewriteok.jsp 
//		//게시물 글쓰기(INSERT > 답글 ....) : refer , step , depth
//		//내가 답글을 달려하는 하는 글의  글번호가 필요해요
//		
//		//refer , step , depth 설정을 하려면 기존 정보(read 글)
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		int result = 0;
//		try {
//			conn = ds.getConnection();
//			
//			int idx = boardata.getIdx(); //현재 읽은 글의 글번호
//			
//			String writer = boardata.getWriter();
//			String email = boardata.getEmail();
//			String homepage = boardata.getHomepage();
//			String pwd = boardata.getPwd();
//			String subject = boardata.getSubject();
//			String content = boardata.getContent();
//			String filename = boardata.getFilename();
//			int filesize = 0;
//			
//			//1. 답글 
//			//현재 내가 읽은 글의 refer , depth , step (원본글 ,답글)
//			String refer_depth_step_sal ="select refer , depth , step from jspboard where idx=?";
//			
//			//2. 위치
//			//step (순서) : 나중에 쓴 답글이 위로 올라오게 하겠다
//			//내가 읽은 글의 step 보다 큰 값은 +1 해서 증가 시켜 놓는다
//			//refer 값으로 판단
//			//ex)   원본글                 refer=1 , step=0 , depth=0 
//		    //      원본글답글           refer=1 , step=1+1 >2 >3,  depth=1
//			//	        원본글답글           refer=1 , step=1>2 ,       depth=1
//			//      원본글답글           refer=1 , step=0+1>1    
//			String step_update_sql = "update jspboard set step= step+1 where step  > ? and refer =? ";
//			// "update jspboard set step= step+1 where step  > 0 and refer =1 ";
//			
//			//답글  insert 
//			String rewrite_sql="insert into jspboard(idx,writer,pwd,subject,content,email,homepage,writedate,readnum,filename,filesize,refer,depth,step)" + 
//				    		   " values(jspboard_idx.nextval,?,?,?,?,?,?,sysdate,0,?,0,?,?,?)";
//			
//			pstmt = conn.prepareStatement(refer_depth_step_sal);
//			pstmt.setInt(1, idx);
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) { //데이터가 있다면 ... 원본글의  refer , step , depth 존재
//				int refer = rs.getInt("refer");
//				int step = rs.getInt("step");
//				int depth = rs.getInt("depth");
//				
//				pstmt = conn.prepareStatement(step_update_sql); //컴파일
//				//기존 step + 1 >> update 구문 실행
//				pstmt.setInt(1, step);
//				pstmt.setInt(2, refer);
//				pstmt.executeUpdate();
//
//				//filename,filesize,refer,depth,step
//				pstmt = conn.prepareStatement(rewrite_sql); //컴파일 
//				pstmt.setString(1, writer);
//				pstmt.setString(2, pwd);
//				pstmt.setString(3, subject);
//				pstmt.setString(4, content);
//				pstmt.setString(5, email);
//				pstmt.setString(6, homepage);
//				pstmt.setString(7, filename);
//				
//				//답변
//				pstmt.setInt(8, refer);
//				pstmt.setInt(9, depth+1); // 규칙 현재 읽은 글에 depth + 1
//				pstmt.setInt(10, step+1); // 순서 update 통해서  자리 확보 + 1
//				
//				int row = pstmt.executeUpdate();
//				if(row > 0) {
//					result = row;
//				}else {
//					result = -1;
//				}
//
//			}
//	
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				pstmt.close();
//				rs.close();
//				conn.close();//반환
//			}catch (Exception e) {
//				
//			}
//		}
//		
//		return result;
//	}

	//게시글 수정하기 화면(답글)
	public NoticeBoard getEditContent(String n_NUMBER) {
		return this.getContent(Integer.parseInt(n_NUMBER));
		//조회화면 동일 (기존에 있는 함수 재활용)
	}
	
	//게시글 수정하기 처리
	//public int boardEdit(Board boarddata){}
	public int boardEdit(MultipartRequest boarddata) {
		int n_NUMBER=Integer.parseInt(boarddata.getParameter("n_NUMBER")); 
		String n_TITLE= boarddata.getParameter("N_TITLE");
		String n_CONTENT= boarddata.getParameter("N_CONTENT");
		System.out.println("n_TILTE입니다."+n_TITLE);
		System.out.println("n_NUMBER입니다."+n_NUMBER);
		System.out.println("n_COTENT입니다."+n_CONTENT);
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql_idx = "select n_NUMBER from notice_board where n_number=?";
			String sql_udpate = "update notice_board set n_title=?, n_content=? where n_number=?";
			pstmt = conn.prepareStatement(sql_idx);
			pstmt.setInt(1, n_NUMBER);
			
			rs = pstmt.executeQuery();
			//판단 (데이터 있다며 : 수정가능 , 없다면 : 수정불가
			if(rs.next()) {
				//경고
				pstmt.close();
				//업데이트
				pstmt = conn.prepareStatement(sql_udpate);
				pstmt.setString(1, n_TITLE);
				pstmt.setString(2, n_CONTENT);
				pstmt.setInt(3, n_NUMBER);
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
	
	// DB에서 정보불러오기
	public JSONArray searchTopThree() {
		JSONArray chartList = new JSONArray();
		StringBuilder sqlbuilder = new StringBuilder();
		sqlbuilder
		.append("	select * from 		")
		.append("	        (SELECT rownum as rn, c.*		")
		.append("	                FROM (select b.cnt, m.email, m.nickname, m.division		")
		.append("	                        from  (select email, count(*) as cnt		")
		.append("	                              from (select email from ain_board		")
		.append("	                                union all		")
		.append("	                                select email from community_board		")
		.append("	                                union all		")
		.append("	                                select email from ain_reply_board		")
		.append("	                                union all		")
		.append("	                                select email from sh_reply_board		")
		.append("	                                union all		")
		.append("	                                select email from sh_board 		")
		.append("	                                ) a		")
		.append("	                            group by email		")
		.append("	                            ) b		")
		.append("	                join member m		")
		.append("	                on m.email = b.email		")
		.append("	                where m.division not in(0)		")
		.append("	                order BY b.cnt DESC		")
		.append("	                ) c) d		")
		.append("	where rn between 1 and 3		");
		
		String sql = sqlbuilder.toString();
		try (Connection conn = ds.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);	
			 ResultSet rs = pstmt.executeQuery();){
			while(rs.next()) {
				JSONObject map = new JSONObject();
				map.put("ranking", rs.getInt("rn"));
				map.put("count", rs.getInt("cnt"));
				map.put("email", rs.getString("email"));
				map.put("nickname", rs.getString("nickname"));
				chartList.add(map);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return chartList;
	}
	
	public JSONArray searchTourListTopThree() {
		
		JSONArray jsonarr = new JSONArray();
		
		StringBuilder sqlbuilder = new StringBuilder()
		.append("	select *															")
		.append("	from    (   select rownum as rn, a.*								")
		.append("	            from        ( select      content_id, count(*) as cnt 	")
		.append("	                      from        cartlist 							")
		.append("	                      group by    content_id 						")
		.append("	                      order by    cnt  desc          ) a					")
		.append("	                                                        ) b 		")
		.append("	where rn between 1 and 10											");
		
		String sql = sqlbuilder.toString();
		
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();) {
			
			while(rs.next()) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("ranking", rs.getInt("rn"));
				jsonObj.put("count", rs.getInt("cnt"));
				jsonObj.put("cid", rs.getString("content_id"));
				jsonarr.add(jsonObj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonarr;
	}
}