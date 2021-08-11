package kr.or.bit.ainboard.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.member.dto.MemberDto;

public class CheckLogin implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = new ActionForward();
		
		MemberDto user = (MemberDto) request.getSession().getAttribute("user");

		if (user == null) {
			request.setAttribute("url", request.getContextPath() + "/Login.do");
			request.setAttribute("msg", "로그인 후에 이용이 가능한 서비스입니다.");
			forward.setPath("WEB-INF/view/redirect.jsp");
			return forward;
		}
		
		return forward;
		
	}
}