package kr.or.bit.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.action.ActionForward;
import kr.or.bit.member.dto.MemberDto;

/**
 * Servlet Filter implementation class CheckLogin
 */
@WebFilter(description = "등록된 url 패턴은 로그인 상태에서 이용이 가능한 서비스다.", urlPatterns = { "*.qna", "/InfoEdit.do"})
public class CheckLogin implements Filter {

    
    public CheckLogin() {}

	
	public void destroy() {}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpreq = (HttpServletRequest) request;
		// getSession을 false로 지정하지 않으면 session을 새로 생성 (기본값 true)
		HttpSession session = httpreq.getSession(false);
		// 로그인 후 -> true
		// 로그인 전 -> false
		boolean login = false;
		if (session != null) {
			// session 객체에 로그인 시 담았던 유저정보가 있다면 다음 필터 혹은 자원으로 이동
			if(session.getAttribute("user") != null) {
				login = true;
			}
		}
		if (login) {
			// 다음 필터 혹은 자원(servlet, jsp)으로 이동
			chain.doFilter(request, response);
		} else {
			// 로그인이 되어있지 않은 경우에는
			// '로그인 후에 이용이 가능한 서비스입니다.'
			// 경고창을 띄우고 로그인 화면으로 포워딩한다.
			request.setAttribute("url", httpreq.getContextPath() + "/Login.do");
			request.setAttribute("msg", "로그인 후에 이용이 가능한 서비스입니다.");
			RequestDispatcher dis = httpreq.getRequestDispatcher("WEB-INF/view/redirect.jsp");
			dis.forward(request, response);
		}
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Filter init : login");
	}

}
