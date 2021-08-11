package kr.or.bit.sunboard.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

public class BoardDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String msg="";
	    String url="";
		
		//삭제글 처리 (글번호 받기)
		String c_number = request.getParameter("c_number");
		String cpage = request.getParameter("cp"); // current page
		String pagesize = request.getParameter("ps"); // pagesize
		String referer = (String)request.getHeader("Referer");
		
		ActionForward forward = null;
		
		if(c_number == null || c_number.trim().equals("")){
			msg ="글번호가 넘어오지 않았습니다";
			url = "boardContent.sun?c_number=" + c_number;
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/view/SH/redirect.jsp");
			
		} else {
			request.setAttribute("c_number", c_number);
			request.setAttribute("cp", cpage);
			request.setAttribute("ps", pagesize);
			
			forward = new ActionForward();
			
			if (request.getSession().getAttribute("email") == null) {
				request.setAttribute("board_url", request.getContextPath() + "/Login.do");
				request.setAttribute("board_msg", "로그인 후에 이용이 가능합니다.");
				forward.setPath("WEB-INF/view/SH/redirect.jsp");
				return forward;
			}
			
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/view/SH/board_delete.jsp");
		}
		
		return forward;
	}

}
