package kr.or.bit.sunboard.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.sunboard.dao.SunBoardDAO;

public class BoardDeleteOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String c_number = request.getParameter("c_number");
		
		String msg="";
		String url="";
		
		SunBoardDAO dao;
		try {
			dao = new SunBoardDAO();
			
			int result = dao.deleteOk(c_number);
			
			
			if(result > 0){
				msg="글이 삭제되었습니다.";
				url="boardList.sun";
			}else{
				msg="삭제 실패";
				url="boardList.sun";
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("board_msg",msg);
		request.setAttribute("board_url",url);
		
		
		ActionForward forward = new ActionForward();
		
		if (request.getSession().getAttribute("email") == null) {
			request.setAttribute("board_url", request.getContextPath() + "/Login.do");
			request.setAttribute("board_msg", "로그인 후에 이용이 가능합니다.");
			forward.setPath("WEB-INF/view/SH/redirect.jsp");
			return forward;
		}
		
		forward.setRedirect(false); // forward
		forward.setPath("/WEB-INF/view/SH/redirect.jsp");

		return forward;
	}

}
