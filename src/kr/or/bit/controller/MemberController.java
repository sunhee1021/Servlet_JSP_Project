package kr.or.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.member.service.InfoEditOkService;
import kr.or.bit.member.service.LogOutOkService;
import kr.or.bit.member.service.LoginOkService;
import kr.or.bit.member.service.CheckService;
import kr.or.bit.member.service.RegisterOkService;


@WebServlet("*.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public MemberController() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	request.setCharacterEncoding("UTF-8");
    	
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String urlcommand = requestURI.substring(contextPath.length());
    	
    	//System.out.println("requestURI : " + requestURI);
    	//System.out.println("contextPath : " + contextPath);
    	//System.out.println("command : " + urlcommand);
    	
    	Action action = null;
    	ActionForward forward = null;
    	
    	if(urlcommand.equals("/Register.do")){ 
    		forward = new ActionForward();
    		forward.setRedirect(false); 
    		forward.setPath("/WEB-INF/view/Member/register.jsp");
    		
    	}else if(urlcommand.equals("/RegisterOk.do")) {
    		action = new RegisterOkService();
    		forward = action.execute(request, response);  
    		
    	}else if(urlcommand.equals("/Login.do")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/view/Member/login.jsp");
    		
    	}else if(urlcommand.equals("/LoginOk.do")) {
    		System.out.println("로그인OK");
    		action = new LoginOkService();
    		forward = action.execute(request, response);
    		
    	}else if(urlcommand.equals("/LogOutOk.do")) {
    		System.out.println("로그아웃OK");
    		action = new LogOutOkService();
    		forward = action.execute(request, response);
    		
    	}else if(urlcommand.equals("/main.do")) {
    		System.out.println("메인페이지");
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/view/main.jsp");
    		
    	}else if(urlcommand.equals("/InfoEdit.do")) {
    		System.out.println("개인정보수정 페이지만");
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/view/Member/myInfo.jsp");
    		
    	}else if(urlcommand.equals("/InfoEditOk.do")) {
    		System.out.println("개인정보수정");
    		action = new InfoEditOkService();
    		forward = action.execute(request, response);
    		
    	}else if(urlcommand.equals("/access_terms.do")) {
    		System.out.println("약관동의 페이지, 지워버릴까?");
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/view/Member/access_terms.jsp");
    		
    	}else if(urlcommand.equals("/emailCheck.do")) {
    		System.out.println("이메일 중복 체크");
    		action = new CheckService();
    		forward = action.execute(request, response);
    		
    	}else if(urlcommand.equals("/nickCheck.do")) {
    		System.out.println("닉네임 중복 체크");
    		action = new CheckService();
    		forward = action.execute(request, response);
    	}
    	
    	//회사소개페이지 UI제공
    	else if(urlcommand.equals("/introduce.do")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/view/AIN/introducePage.jsp");
    	}
    	//이용약관페이지 UI제공
    	else if(urlcommand.equals("/terms.do")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/view/AIN/termsPage.jsp");
    	}
    	//개인정보처리방침페이지 UI제공
    	else if(urlcommand.equals("/personalInfo.do")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/view/AIN/InfomationPage.jsp");
    	}
    	if(forward != null) {
    		if(forward.isRedirect()) {    
    			response.sendRedirect(forward.getPath());   
    		}else {	
    			//false
    			RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doProcess(request, response);
	}

}

