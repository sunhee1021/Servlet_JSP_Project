package kr.or.bit.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.member.dao.MemberDao;
import kr.or.bit.member.dto.MemberDto;

public class LoginOkService implements Action {

	@SuppressWarnings("unused")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = new ActionForward();

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		MemberDto result = null;

		MemberDto memberdto = new MemberDto();

		memberdto.setEmail(email);
		memberdto.setPassword(password);

		System.out.println("로그인 여기까지오나?(LoginOkService 30번째줄)");

		try {
			MemberDao memberdao = new MemberDao();

			result = memberdao.loginMember(memberdto);
			request.getSession().setAttribute("user", result);

			String msg = "";
			String url = "";
			if (result != null && result.getDivision().equals("99")) {
				msg = "활동이 정지된 회원입니다. 고객센터에 문의해주세요.";
				url = "Login.do";
			} else if (result == null) {
				msg = "가입하지 않은 이메일이거나, 잘못된 비밀번호입니다.";
				url = "Login.do";
			} else if (result != null) {

				msg = "로그인!";
				url = "main.bo";
				
				HttpSession session =  request.getSession();

				session.setAttribute("email", result.getEmail());
				session.setAttribute("password", result.getPassword());
				session.setAttribute("nickname", result.getNickname());

				System.out.println(session.getAttribute("email"));
				System.out.println(session.getAttribute("password"));
				System.out.println(session.getAttribute("nickname"));

			}

			request.setAttribute("msg", msg);
			request.setAttribute("url", url);

			/*
			 * HttpSession session = request.getSession(); String id = (String)
			 * session.getAttribute("email"); System.out.println(id);
			 */

			forward.setRedirect(false);
			forward.setPath("/WEB-INF/view/redirect.jsp");

		} catch (Exception e) {
			System.out.println("로그인 에러 : " + e.getMessage());
		}

		return forward;
	}

}
