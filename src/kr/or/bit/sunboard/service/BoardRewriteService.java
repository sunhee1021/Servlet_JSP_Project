package kr.or.bit.sunboard.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

public class BoardRewriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String c_number = request.getParameter("c_number");
		String cpage = request.getParameter("cp");
		String pagesize = request.getParameter("ps");
		String title = request.getParameter("title"); // 답글의 제목으로 사용

		try {
			if (c_number == null || title == null || c_number.trim().equals("") || title.trim().equals("")) {
				response.sendRedirect("boardList.sun");
				return null;
			}
			if (cpage == null || pagesize == null) {
				cpage = "1";
				pagesize = "5";
			}

			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
		
		request.setAttribute("c_number", c_number);
		request.setAttribute("cp", cpage);
		request.setAttribute("ps", pagesize);
		request.setAttribute("title", title);
		
		System.out.println("프린트엘엔 : " + c_number);
		ActionForward forward = new ActionForward();
		
		if (request.getSession().getAttribute("email") == null) {
			request.setAttribute("board_url", request.getContextPath() + "/Login.do");
			request.setAttribute("board_msg", "로그인 후에 이용이 가능합니다.");
			forward.setPath("WEB-INF/view/SH/redirect.jsp");
			return forward;
		}
		
		
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/view/SH/board_rewrite.jsp");
		
		
		return forward;
	}

}
