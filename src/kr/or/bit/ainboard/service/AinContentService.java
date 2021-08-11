package kr.or.bit.ainboard.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.ainboard.dao.AinBoardDao;
import kr.or.bit.ainboard.dto.AinBoard;
import kr.or.bit.ainboard.dto.AinReply;

public class AinContentService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("boardcontent실행");
		
		ActionForward forward = null;
		
		String cNumber = request.getParameter("cNumber");
		String cpage = request.getParameter("cp");
		String pagesize = request.getParameter("ps");
		
		AinBoard board = new AinBoard();
		
		List<AinReply> replyList = new ArrayList<AinReply>();
		
		boolean isread = false;
		
		try {
			
			AinBoardDao dao = new AinBoardDao();
			
			//글번호 없이 넘어온경우 처리
			if (cNumber == null || cNumber.trim().equals("")) {
				response.sendRedirect("boardList.ain");
				return null;
			}
			
			cNumber = cNumber.trim();
			
			//List 페이지 처음 호출 ...
			if(cpage == null || cpage.trim().equals("")){
				//default 값 설정
				cpage = "1"; 
			}
		
			if(pagesize == null || pagesize.trim().equals("")){
				//default 값 설정
				pagesize = "10"; 
			}
			
			isread = dao.getReadNum(cNumber);
			
			if(isread) {
				board = dao.getContent(Integer.parseInt(cNumber));
				replyList = dao.replylist(cNumber);
				
				System.out.println("replyList: "+replyList);
			}
			
			request.setAttribute("board", board);
			request.setAttribute("cNumber", cNumber);
			request.setAttribute("cp", cpage);
			request.setAttribute("ps", pagesize);
			request.setAttribute("replyList", replyList);
			
			forward = new ActionForward();
			forward.setRedirect(false); // forward
			forward.setPath("/WEB-INF/view/AIN/ainBoardContent.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return forward;
	}

}
