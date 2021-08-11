package kr.or.bit.ainboard.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.ainboard.dao.AinBoardDao;

public class AdminMemberDelService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		String email = request.getParameter("email");
		String cp = request.getParameter("cp");
		String ps = request.getParameter("ps");
		
		try {
			if (email == null || email.trim().equals("")) {
				response.sendRedirect("admin.ain");
				return null;
			}
			if (cp == null || ps == null) {
				cp = "1";
				ps = "20";
			}

			
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		int pagesize = Integer.parseInt(ps);
		int cpage = Integer.parseInt(cp);
		
		request.setAttribute("pagesize", pagesize);
		request.setAttribute("cpage", cpage);
		
		AinBoardDao dao = new AinBoardDao();
		int result = dao.memberDelete(email);
		
		if(result > 0) {
			// 글 등록 성공
			request.setAttribute("msg", "회원정지처리완료 😥");
		} else {
			// 글 등록 실패
			request.setAttribute("msg", "정지시키기 실패 .......");
		}
		request.setAttribute("url", request.getContextPath() + "/admin.ain");
		
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/view/redirect.jsp");
		
		return forward;
	}

}