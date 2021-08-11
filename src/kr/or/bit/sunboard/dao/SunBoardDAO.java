package kr.or.bit.sunboard.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;

import kr.or.bit.sunboard.dto.SunBoardDTO;
import kr.or.bit.sunboard.dto.SunReplyDTO;
import kr.or.bit.utils.FileUpload;

public class SunBoardDAO {
	DataSource ds = null;
	
	public SunBoardDAO() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	/////////////////////////////////////////////////////////////////////
	
	public int writeOk(SunBoardDTO sunboard_dto) {		//글쓰기
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "insert into sh_board(c_number,title,email,content,writedate,filename,"
					+ "refer,depth,step,filerealname,filesize,viewcount)"
					+ "values(sh_board_c_number.nextval,?,?,?,sysdate,?,?,?,?,?,?,?)";
			
			//insert into sh_board(c_number,title,content,writedate,filename,refer,depth,step,viewcount,email,filerealname,filesize)
	        //values(16,'동행구함','같이 여행가실래요?','2021-04-24',1,1,1,1,1,'bit01@naver.com','photo',10);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sunboard_dto.getTitle());
			pstmt.setString(2, sunboard_dto.getEmail());
			pstmt.setString(3, sunboard_dto.getContent());
			pstmt.setString(4, sunboard_dto.getFilename());
			
			
			int refermax = getMaxRefer();
			int refer = refermax + 1;
			
			pstmt.setInt(5, refer);
			pstmt.setInt(6, sunboard_dto.getDepth());
			pstmt.setInt(7, sunboard_dto.getStep());
			pstmt.setString(8, sunboard_dto.getFilerealname());
			pstmt.setLong(9, sunboard_dto.getFilesize());
			pstmt.setInt(10, 0);
			
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return row;
		
	}

	/////////////////////////////////////////////////////////////////////
	
	private int getMaxRefer() {  //원본글 refer값 생성 method
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int maxrefer = 0;
		
		try {
			conn = ds.getConnection();
			String sql = " select nvl(max(refer), 0) as refer_max from sh_board ";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				maxrefer = rs.getInt("refer_max");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
				
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			} 
		}
		
		return maxrefer;
	}
	
	/////////////////////////////////////////////////////////////////////
	
	public List<SunBoardDTO> list(int cpage, int pagesize){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<SunBoardDTO> list = null;
		System.out.println("dao 에러 114번째 줄");
		
		try {
			conn = ds.getConnection();
			String sql = "select * from (select rownum rn, c_number, title, content, writedate, filename, refer, depth, step, viewcount, s.email, nickname, filerealname, filesize"
					+ "         from ( SELECT * FROM sh_board ORDER BY refer DESC , step ASC) s join member m on s.email = m.email"
					+ "         where rownum <= ?) where rn >= ?";
			
			pstmt = conn.prepareStatement(sql);
			
			System.out.println("dao 에러 126번째 줄");
			
			int start = cpage * pagesize - (pagesize-1);
			int end = cpage * pagesize;
			
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<SunBoardDTO>();
			
			
			while(rs.next()) {
				SunBoardDTO sunboard_dto = new SunBoardDTO();
				
				sunboard_dto.setC_number(rs.getInt("c_number"));
				sunboard_dto.setTitle(rs.getString("title"));
				sunboard_dto.setContent(rs.getString("content"));
				sunboard_dto.setWritedate(rs.getDate("writedate"));
				sunboard_dto.setFilename(rs.getString("filename"));
				sunboard_dto.setRefer(rs.getInt("refer"));
				sunboard_dto.setStep(rs.getInt("step"));
				sunboard_dto.setDepth(rs.getInt("depth"));
				sunboard_dto.setViewcount(rs.getInt("viewcount"));
				sunboard_dto.setEmail(rs.getString("email"));
				sunboard_dto.setNickname(rs.getString("nickname"));
				sunboard_dto.setFilerealname("filerealname");
				
				list.add(sunboard_dto);
			}
			
		} catch (Exception e) {
			System.out.println("이문제니?" + e.getMessage());
		} finally {
			try {
				
				rs.close();
				pstmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		return list;
		
	}

	/////////////////////////////////////////////////////////////////////
	
	public int totalBoardCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalcount = 0;
		
		System.out.println("dao 에러 181번째 줄");
		try {
			conn = ds.getConnection(); 
			String sql="select count(*) cnt from sh_board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			System.out.println("dao 에러 188번째 줄");

			if(rs.next()) {
				totalcount = rs.getInt("cnt");
			}
		}catch (Exception e) {
			
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		return totalcount;
	}

	/////////////////////////////////////////////////////////////////////
	
		public SunBoardDTO getContent(int c_number) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			SunBoardDTO sunboard_dto= null;
			
			try {
				conn = ds.getConnection();
				
				/*
				 * String sql="select c_number,title,content,writedate," +
				 * "filename,viewcount,email," + "refer,step,depth" +
				 * "from sh_board where c_number=?";
				 */
				 
				String sql = "select c_number, title, content, writedate, filename, "
						+ "refer, depth, step, viewcount, s.email, nickname, filerealname, filesize "
						+ "from sh_board s join member m on s.email = m.email where c_number=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, c_number);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					
					System.out.println("헤이헤이");
					String title = rs.getString("title");
					String content = rs.getString("content");
					java.sql.Date writedate = rs.getDate("writedate");
					
					String filename = rs.getString("filename");
					int viewcount = rs.getInt("viewcount");
					String email = rs.getString("email");
					
					//계층형
					int refer = rs.getInt("refer");
					int step = rs.getInt("step");
					int depth = rs.getInt("depth");
					
					String nickname = rs.getString("nickname");
					String filerealname = rs.getString("filerealname");
					long filesize = rs.getLong("filesize");
					
					sunboard_dto = 
							new SunBoardDTO(c_number,title,content,writedate,filename,viewcount,email,refer,depth,step,nickname,filerealname,filesize);
					
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
			
			return sunboard_dto;
		}

		/////////////////////////////////////////////////////////////////////
		
		public boolean getReadNum(String c_number) {
			
				Connection conn = null;
				PreparedStatement pstmt = null;
				boolean result = false;
				
				String sql="update sh_board set viewcount = viewcount + 1 where c_number=?";

				
				try {
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, c_number);
					
					int row = pstmt.executeUpdate();
					
					if(row > 0 ) {
						result = true;
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
				
			}
		
		public int deleteOk(String c_number) {
			//일반게시판 : 삭제 ...
			
			//계층형 게시판 : 답글 
			/*
			 1. 원본글 (답글이 있는 경우)
			 2. 원본글 (답글이 없는 경우) : 그냥 삭제
			 
			원본글 (답글이 있는 경우)
			case 1:  원본글이 삭제시 답변글 있으면 다 삭제 (같은 refer delete)
			case 2: (네이버)원본글만 삭제 -> 나머지 처리 (텍스트 형태 (원본글 삭제 표시) (step, depth)
			case 3: 삭제시 삭제되었을 표시 ( 게시판 설계 (delok :삭제여부 컬럼 :1) >> 삭제 : 0 >> update .. 0
			case 4: 삭제 못하게 한다 (답글이 있으면) refer count > 1
			*/
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int row = 0;
			try {
					conn = ds.getConnection();
					//비인증 ..
					//삭제 > 비번 
					//처리 > 글번호 ,비번
					
					//비번검증
					//String sql_pwd="select password from sh_board where email=?";
					
					//두개의 테이블 (FK) : 자식부터 삭제 , 부모 삭제
					//jspboard(pk) , reply(fk:idx)
					//reply idx_fk=1 delete, jspboard idx=1 delete
					String sql_reply = "delete from sh_reply_board where c_number=?";
					
					//게시글 삭제
					String sql_board="delete from sh_board where c_number=?";
					
					PreparedStatement pstmt1 = conn.prepareStatement(sql_reply);
					PreparedStatement pstmt2 = conn.prepareStatement(sql_board);
					
					pstmt1.setString(1, c_number);
					pstmt2.setString(1, c_number);
				
					pstmt1.executeUpdate();
					
					row = pstmt2.executeUpdate();
						
					
					}catch (Exception e) {
					e.printStackTrace();
				}
				
			
			return row;
		}
		

		//댓글 입력하기 (Table reply : fk(jspboard idx) )
		public int replywrite(int c_number ,String content ,String email) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				int row = 0;
				
				String sql="insert into sh_reply_board(cr_number,content,writedate,email,c_number) "+
				           " values(sh_reply_board__c_number.nextval,?,sysdate,?,?)";
				
				
				try {
					conn = ds.getConnection();
					
					pstmt =conn.prepareStatement(sql);
					
					pstmt.setString(1, content);
					pstmt.setString(2, email);
					pstmt.setInt(3, c_number);
					
					
					row = pstmt.executeUpdate();
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					try {
						pstmt.close();
						conn.close();//반환
					}catch (Exception e) {
						
					}
				}
				
				return row;
			}
			
		//댓글 조회하기
		public List<SunReplyDTO> replylist(String c_number_fk){
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				List<SunReplyDTO> list = null;
				
				String reply_sql = "select c_number, content, writedate, s.email, nickname, cr_number "
						+ "from sh_reply_board s join member m on s.email = m.email "
						+ "where c_number=? order by cr_number desc";
				
				try {
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(reply_sql);
					pstmt.setString(1, c_number_fk);
					
					//System.out.println(reply_sql);
					rs =pstmt.executeQuery();
					
					/*
					 * String sql="insert into sh_reply_board(c_number,content,email) "+
					 * " values(reply_no.nextval,?,?)";
					 */
					
					list = new ArrayList<SunReplyDTO>();
					
					while(rs.next()) {
						
						
						int c_number = rs.getInt("c_number");
						String email = rs.getString("email");
						String content = rs.getString("content");
						java.sql.Date writedate = rs.getDate("writedate");
						int cr_number = rs.getInt("cr_number");
						
						String nickname = rs.getString("nickname");
						
						SunReplyDTO replydto = new SunReplyDTO(cr_number, content, writedate, email, c_number, nickname);
						
						list.add(replydto);
						
					}
					
					
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					try {
						pstmt.close();
						rs.close();
						conn.close();//반환
					}catch (Exception e) {
						
					}
				}
				
				return list;
			}
			
		//댓글 삭제하기
		public int replyDelete(String cr_number) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				int row = 0;
				
				try {
					
					String replydelete = "delete from sh_reply_board where cr_number=?";
					
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(replydelete);
					pstmt.setString(1, cr_number);
					row = pstmt.executeUpdate();
						
				}catch(Exception e) {
					
				}finally {
					try {
						pstmt.close();
						conn.close();//반환
					}catch (Exception e) {
						
					}
				}
				
				return row;
			}

		//게시글 상세 (답글 쓰기) 
		public int reWriteOk(SunBoardDTO dto) {
			//content.jsp ->(답글)-> rewrite.jsp(입력) -> submit() -> rewriteok.jsp 
			//게시물 글쓰기(INSERT > 답글 ....) : refer , step , depth
			//내가 답글을 달려하는 하는 글의  글번호가 필요해요
			
			//refer , step , depth 설정을 하려면 기존 정보(read 글)
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int result = 0;
			
			try {
				
				conn = ds.getConnection();
				
				int c_number = dto.getC_number(); //현재 읽은 글의 글번호
				
				String title = dto.getTitle();
				String content = dto.getContent();
				String filename = dto.getFilename();
				String email = dto.getEmail();
				String filerealname = dto.getFilerealname();
				//int viewcount = dto.getViewcount();
				long filesize = dto.getFilesize();
			
				System.out.println("cNumber: " + c_number);
				//1. 답글 
				//현재 내가 읽은 글의 refer , depth , step (원본글 ,답글)
				String refer_depth_step_sal ="select refer , depth , step from sh_board where c_number=?";
				
				//2. 위치
				//step (순서) : 나중에 쓴 답글이 위로 올라오게 하겠다
				//내가 읽은 글의 step 보다 큰 값은 +1 해서 증가 시켜 놓는다
				//refer 값으로 판단
				//ex)   원본글                 refer=1 , step=0 , depth=0 
			    //      원본글답글           refer=1 , step=1+1 >2 >3,  depth=1
				//	        원본글답글           refer=1 , step=1>2 ,       depth=1
				//      원본글답글           refer=1 , step=0+1>1    
				String step_update_sql = "update sh_board set step = step+1 where step > ? and refer =? ";
				// "update jspboard set step= step+1 where step  > 0 and refer =1 ";
				
				//답글  insert 
				String rewrite_sql="insert into sh_board(c_number,title,content, writedate,"
								 + "email,filename,filerealname,filesize,refer,depth,step) "
								 + "values(sh_board_c_number.nextval,?,?,sysdate,?,?,?,?,?,?,?)";
				
				pstmt = conn.prepareStatement(refer_depth_step_sal);
				pstmt.setInt(1, c_number);
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
					
					pstmt = conn.prepareStatement(rewrite_sql); //컴파일 
					pstmt.setString(1, title);
					pstmt.setString(2, content);
					pstmt.setString(3, email);
					
					pstmt.setString(4, filename);
					pstmt.setString(5, filerealname);
					pstmt.setLong(6, filesize);
					
					//답변
					pstmt.setInt(7, refer);
					pstmt.setInt(8, depth+1); // 규칙 현재 읽은 글에 depth + 1
					pstmt.setInt(9, step+1); // 순서 update 통해서  자리 확보 + 1
					
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

		//게시글 수정하기 화면(답글)
		public SunBoardDTO getEditContent(String c_number) {
			return this.getContent(Integer.parseInt(c_number));
			//조회화면 동일 (기존에 있는 함수 재활용)
		}
		
		//게시글 수정하기 처리
				//public int boardEdit(Board boarddata){}
				public int boardEdit(MultipartRequest boarddata) {
					String c_number= boarddata.getParameter("c_number");
					String title= boarddata.getParameter("title");
					String content= boarddata.getParameter("content");
					String filename = boarddata.getParameter("filename");
					/*
					 * String filename= boarddata.getParameter("filename"); String viewcount=
					 * boarddata.getParameter("viewcount"); String email =
					 * boarddata.getParameter("email"); String filerealname =
					 * boarddata.getParameter("filerealname"); St
					 ring filesize = boarddata.getParameter("filesize");
					*/
					Connection conn = null;
					PreparedStatement pstmt = null;
					PreparedStatement pstmt2 = null;
					ResultSet rs = null;
					int row = 0;
					try {
						conn = ds.getConnection();
						String sql_cNum = "select c_number from sh_board where c_number=?";
						String sql_udpate = "update sh_board set title=? , content=?, filename=? where c_number=?";
						pstmt = conn.prepareStatement(sql_cNum);
						pstmt2 = conn.prepareStatement(sql_udpate);
						pstmt.setString(1, c_number);
						rs = pstmt.executeQuery();
						//판단 (데이터 있다며 : 수정가능 , 없다면 : 수정불가
						if(rs.next()) {
							//업데이트
							pstmt2 = conn.prepareStatement(sql_udpate);
							pstmt2.setString(1, title);
							pstmt2.setString(2, content);
							pstmt2.setString(3, filename);
							pstmt2.setString(4, c_number);
							row = pstmt.executeUpdate();
							System.out.println("row : " + row);
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					return row;
				}

}





















