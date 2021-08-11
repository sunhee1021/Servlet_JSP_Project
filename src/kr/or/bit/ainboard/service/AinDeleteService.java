package kr.or.bit.ainboard.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.ainboard.dao.AinBoardDao;

public class AinDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		System.out.println("deleteService 진입");
		
		String cNumber = request.getParameter("cNumber");
		String cpage = request.getParameter("cp");
		String pagesize = request.getParameter("ps");
		
		try {
			if (cNumber == null || cNumber.trim().equals("")) {
				response.sendRedirect("boardList.ain");
				return null;
			}
			if (cpage == null || pagesize == null) {
				cpage = "1";
				pagesize = "5";
			}

			
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		AinBoardDao dao = new AinBoardDao();
		int result = dao.boardDelete(cNumber);
		
		int cp = Integer.parseInt(cpage);
		int ps = Integer.parseInt(pagesize);
		
		
		if(result > 0) {
			// 글 등록 성공
			request.setAttribute("msg", "삭제되었습니다 😥");
		} else {
			// 글 등록 실패
			request.setAttribute("msg", "삭제 실패 .......");
		}
		request.setAttribute("url", request.getContextPath() + "/boardList.ain");
		
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/view/redirect.jsp");
		
		return forward;
	}

}
