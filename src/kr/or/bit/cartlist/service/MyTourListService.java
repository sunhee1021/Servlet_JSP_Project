package kr.or.bit.cartlist.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.cartlist.dao.cartlistDAO;
import kr.or.bit.member.dto.MemberDto;

public class MyTourListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		MemberDto loginUser = (MemberDto) request.getSession().getAttribute("user");
		
		if(loginUser == null) {
			forward = new ActionForward();
			forward.setPath("WEB-INF/view/redirect.jsp");
			request.setAttribute("url", request.getContextPath() + "/Login.do");
			request.setAttribute("msg", "로그인 해주세요.");
			return forward;
		}
		
		
		String email = loginUser.getEmail();
		
		cartlistDAO cartdao = new cartlistDAO();
		List<String> myTourList = cartdao.myTourList(email);
		
		try {
			
			request.setAttribute("myTourList", myTourList);
			
			forward = new ActionForward();
			forward.setRedirect(false); // forward
			forward.setPath("WEB-INF/view/tourList/myTourList.jsp");
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
