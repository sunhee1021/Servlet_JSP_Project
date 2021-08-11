package kr.or.bit.member.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.member.dao.MemberDao;

public class InfoEditService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response){
		
		ActionForward forward = new ActionForward();
		
		String str = request.getParameter("email");
		
		MemberDao memberdao = new MemberDao();
		
		request.setAttribute(str, memberdao);
		
		forward.setPath("/WEB-INF/view/updateView.jsp");
		
		return null;
	}

}
