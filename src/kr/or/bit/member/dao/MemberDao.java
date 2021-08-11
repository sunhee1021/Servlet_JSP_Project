package kr.or.bit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import kr.or.bit.member.dto.MemberDto;



public class MemberDao {
	
	DataSource ds = null;
	
	
	public MemberDao() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");				
		
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e.getMessage());
			return;
		}
	}
	
	//회원가입
	public int RegisterMember(MemberDto memberDto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "insert into member values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberDto.getEmail());
			pstmt.setString(2, memberDto.getPassword());
			pstmt.setString(3, "1");
			pstmt.setString(4, memberDto.getNickname());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("RegisterMember 에러(memberDao 52번째줄) : " + e.getMessage());
		
		}finally {
			if(pstmt!=null) try{pstmt.close();}catch(Exception e){}
			if(conn!=null) try{conn.close();}catch(Exception e){}
		}
		return result;
	}
	
	//로그인
	public MemberDto loginMember(MemberDto memberDto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberDto user = null;
		
		
		try {
			conn = ds.getConnection();
			String sql = "select email, password, division, nickname from member where email=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDto.getEmail());
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				if (rs.getString("password").equals(memberDto.getPassword())) {
					user = new MemberDto();
					//일치
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					user.setDivision(rs.getString("division"));
					user.setNickname(rs.getString("nickname"));
					
				} else {
					//불일치
				}
			}
		} catch (Exception e) {
			System.out.println("로그인 에러 : " + e.getMessage());
		} finally {
			
			 
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;  
	}
	
	//로그인 완료 후 처리
	public boolean loginOkMember(String email , String pwd) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "select password from member where email = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("password").equals(pwd)) {
					result = true;
				} else {
					result = false;
				}
			}
		} catch(Exception e) {
			e.getMessage();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e2) {
				
			}
		}
		return result;
		
	}
	
	/*
	 * //개인정보수정 public boolean editInfo(MemberDto memberdto){
	 * 
	 * Connection conn = null; PreparedStatement pstmt = null; boolean result =
	 * false;
	 * 
	 * try { conn = ds.getConnection(); String sql =
	 * "select email,password,nickname from member where email=?"; //String sql =
	 * "UPDATE member SET nickname=?, password=?, where email=?";
	 * 
	 * pstmt = conn.prepareStatement(sql); pstmt.setString(1, memberdto.getEmail());
	 * 
	 * pstmt.executeQuery();
	 * 
	 * System.out.println("memberDao 156번째줄");
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * System.out.println("개인정보수정 에러(memberDao 159번째줄) : " + e.getMessage()); }
	 * finally { try { pstmt.close(); conn.close(); } catch (SQLException e) {
	 * e.printStackTrace(); } } return result;
	 * 
	 * }
	 */

	//회원 이메일검색
		public List<MemberDto> emailCheck(String email) {
			List<MemberDto> list = new ArrayList<MemberDto>();
			
			String sql =  "select email, password, division, nickname "
					+ "from member where email like ?";
			
			
			try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
				
				pstmt.setString(1, "%" + email + "%");

				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					MemberDto dto = new MemberDto();
					dto.setEmail(rs.getString("email"));
					dto.setPassword(rs.getString("password"));
					dto.setDivision(rs.getString("division"));
					dto.setNickname(rs.getString("nickname"));
					
					list.add(dto);
				}
				
			} catch (Exception e) {
				System.out.println("오류 :" + e.getMessage());
			}
			
			
			return list;
		}
		
	//회원 닉네임 검색
		public List<MemberDto> nickCheck(String nickname) {
			List<MemberDto> list = new ArrayList<MemberDto>();
			
			String sql =  "select email, password, division, nickname "
					+ "from member where nickname like ?";
			
			
			try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
				
				pstmt.setString(1, "%" + nickname + "%");

				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					MemberDto dto = new MemberDto();
					dto.setEmail(rs.getString("email"));
					dto.setPassword(rs.getString("password"));
					dto.setDivision(rs.getString("division"));
					dto.setNickname(rs.getString("nickname"));
					
					list.add(dto);
				}
				
			} catch (Exception e) {
				System.out.println("오류 :" + e.getMessage());
			}
			
			
			return list;
		}
	
	public int editInfoOk(MemberDto memberdto){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
			//String sql = "select email,password,nickname from member where email=?";
			String sql = "UPDATE member SET division = ?, nickname=?, password=?"
					+ 		"where email=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberdto.setDivision("1"));
			pstmt.setString(2, memberdto.getNickname());
			pstmt.setString(3, memberdto.getPassword());
			pstmt.setString(4, memberdto.getEmail());

			result = pstmt.executeUpdate();
			
			System.out.println("업데이트된 result 값 : " + memberdto.getPassword());
			System.out.println("업데이트된 result 값 : " +result);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("개인정보수정 에러(memberDao 159번째줄) : " + e.getMessage());
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

}