package kr.or.bit.ainboard.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

public class AinRewriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String cNumber = request.getParameter("cNumber");
		String cpage = request.getParameter("cp");
		String pagesize = request.getParameter("ps");
		String title = request.getParameter("title"); // 답글의 제목으로 사용

		try {
			if (cNumber == null || title == null || cNumber.trim().equals("") || title.trim().equals("")) {
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
		
		request.setAttribute("cNumber", cNumber);
		request.setAttribute("cp", cpage);
		request.setAttribute("ps", pagesize);
		request.setAttribute("title", title);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/view/AIN/ainRewrite.jsp");
		
		return forward;
	}
}