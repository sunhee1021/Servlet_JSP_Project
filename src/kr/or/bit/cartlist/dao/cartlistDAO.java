package kr.or.bit.cartlist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.or.bit.cartlist.dto.CartList;

public class cartlistDAO {
	
	DataSource ds = null;
	
	public cartlistDAO() {
		
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 로그인한 유저가 담기버튼을 클릭할 시
	 * 찜목록으로 데이터를 추가한다.
	 * 
	 * @param cartlist - 찜목록으로 추가할 객체
	 * @return
	 */
	public int insertCartlist(CartList cartlist) {
		int result = 0;
		
		//selectsql : 이미 담아져있는 관광지는 담아지지 않도록 중복정보가 있는지 확인하기 위함
		String selectsql = " SELECT COUNT(*) AS cnt FROM CARTLIST WHERE 1 = 1 AND EMAIL LIKE ? AND CONTENT_ID LIKE ? "; 
		//insertsql : 정보가 없다면 insert
		String insertsql = " INSERT INTO CARTLIST(EMAIL, CONTENT_ID) VALUES(?, ?) ";
		
		try(Connection conn = ds.getConnection();
			PreparedStatement selectpstmt = conn.prepareStatement(selectsql);
			PreparedStatement insertpstmt = conn.prepareStatement(insertsql);){
			
			selectpstmt.setString(1, cartlist.getEmail());
			selectpstmt.setString(2, cartlist.getContentId());
			
			ResultSet rs = selectpstmt.executeQuery();
			
			if(rs.next() && rs.getInt("cnt") > 0) {
				// nothing
			} else {
				insertpstmt.setString(1, cartlist.getEmail());
				insertpstmt.setString(2, cartlist.getContentId());
				
				result = insertpstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//내 관광지 목록에서 유저가 취소버튼 클릭시, 데이터 삭제
	public int deleteCartlist(CartList cartlist) {
		int result = 0;
		
		String deletesql = "DELETE FROM CARTLIST WHERE EMAIL LIKE ? AND CONTENT_ID LIKE ?";
		try(Connection conn = ds.getConnection();
				PreparedStatement deletepstmt = conn.prepareStatement(deletesql);){
			
			deletepstmt.setString(1, cartlist.getEmail());
			deletepstmt.setString(2, cartlist.getContentId());
			
			result = deletepstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//사용자가 담은 관광지의 목록 불러오기
	public List<String> myTourList(String Email) {
		List<String> cidList = new ArrayList<String>();
		
		String myTourListsql = "select CONTENT_ID from CARTLIST where email= ? ";
		
		try(Connection conn = ds.getConnection();
			PreparedStatement myTourListpstmt = conn.prepareStatement(myTourListsql);) {
			
			myTourListpstmt.setString(1, Email);
			
			ResultSet rs = myTourListpstmt.executeQuery();
			
			while(rs.next()) {
				String cid = rs.getString("CONTENT_ID");
				cidList.add(cid);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cidList;
	}
	
}
