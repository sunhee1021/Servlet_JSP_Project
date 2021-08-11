package kr.or.bit.communityboard.service;

import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import kr.or.bit.communityboard.dao.communityboardDao;
import kr.or.bit.communityboard.dto.CommunityBoard;
import kr.or.bit.communityboard.dto.CommunityReplyBoard;

public class CommunityBoardService {
	private static CommunityBoardService instance = new CommunityBoardService();
	private CommunityBoardService() {}
	public static CommunityBoardService getInBoardService() {
		return instance; 
	} 
	
	//서비스 요청(글쓰기)
		public int writeOk(CommunityBoard boarddata) throws Exception {
			communityboardDao dao = new communityboardDao();
			int result = dao.board_Write(boarddata);
			return result;
		}
		
		//서비스 요청(글목록 보여주기)
		public List<CommunityBoard> list(int cpage, int pagesize) throws Exception{
			communityboardDao dao = new communityboardDao();
			return dao.list(cpage, pagesize);
		}
		
		//서비스 요청(글목록 게시물 총 건수)
		public int totalBoardCount() throws Exception {
			communityboardDao dao = new communityboardDao();
			return dao.totalBoardCount();
		}
		
		//서비스 요청(글 상세보기)
		public CommunityBoard content(int c_number) throws NamingException {
			return new communityboardDao().getContent(c_number);
		}
		
		//서비스 요청 (글 상세보기 시 조회수 증가하기)
		public boolean  addReadNum(String c_number) throws NamingException {
			return new communityboardDao().getReadNum(c_number);
		}
		
		//서비스 요청(게시글 삭제하기) : jspboard , reply 
		public int board_Delete(String c_number) throws NamingException {
			return new communityboardDao().deleteOk( Integer.parseInt(c_number.trim()));
		}
		
		//서비스  요청(수정 데이터 조회 )
		public CommunityBoard board_EditContent(String c_number) throws NamingException {
			return new communityboardDao().getEditContent(c_number);
		}
		
		//다른 테스트 (CommunityBoard DTO Parameter 사용)
		//request 요청 객체를 Parameter 사용 (장점 : view 코드 감소)
		public int board_Edit(HttpServletRequest req) throws NamingException {
			
			return new communityboardDao().boardEdit(req);
		}
} 	