package kr.or.bit.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

public class LogOutOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response){
		
		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		System.out.println("로그아웃 오니?");
		forward.setRedirect(false);
   		forward.setPath("main.bo");
   		return forward;
   		
		//return new ActionForward();
	}

}
