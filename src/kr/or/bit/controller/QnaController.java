package kr.or.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.ActionForward;
import kr.or.bit.qnaboard.service.QnaBoardContentService;
import kr.or.bit.qnaboard.service.QnaBoardFiledownloadService;
import kr.or.bit.qnaboard.service.QnaBoardListService;
import kr.or.bit.qnaboard.service.QnaBoardRegister;
import kr.or.bit.qnaboard.service.QnaBoardRewriteService;
import kr.or.bit.qnaboard.service.QnaBoardToRegisterView;
import kr.or.bit.qnaboard.service.QnaBoardUpdateService;
import kr.or.bit.qnaboard.service.QnaInputPasswordService;
import kr.or.bit.utils.StringUtils;

@WebServlet("*.qna")
public class QnaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QnaController() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String url = request.getRequestURI().substring(request.getContextPath().length());
    	
    	System.out.println(url);
    	
    	ActionForward forward = null;
    	
    	// Q&A 게시판 으로 이동한다.
    	if(url.equals("/boardList.qna")) {
    		forward = new QnaBoardListService().execute(request, response);
    		forward.setRedirect(false);
    	} 
    	// Q&A 글 등록으로 이동한다.
    	else if(url.equals("/register.qna")) {
    		forward = new QnaBoardToRegisterView().execute(request, response);
    		forward.setRedirect(false);
    	}
    	// Q&A 글 등록페이지에서 작성완료 버튼을 클린한 경우
    	else if(url.equals("/registerContent.qna")) {
    		forward = new QnaBoardRegister().execute(request, response);
    		forward.setRedirect(false);
    	}
    	// Q&A 글 수정
    	else if(url.equals("/updateContent.qna")) {
    		forward = new QnaBoardUpdateService().execute(request, response);
    		forward.setRedirect(false);
    	}
    	// Q&A 답글
    	else if(url.equals("/rewriteContent.qna")) {
    		forward = new QnaBoardRewriteService().execute(request, response);
    		forward.setRedirect(false);
    	}
    	
    	// Q&A 비밀번호 입력 페이지
    	else if(url.equals("/qnaInputPassword.qna")) {
    		
    		request.setAttribute("qnaNumber", request.getParameter("qnaNumber"));
    		
    		forward = new QnaInputPasswordService().execute(request, response);
    		forward.setRedirect(false);
    	}
    	
    	// Q&A 상세보기
    	else if(url.equals("/qnaContent.qna")) {
    		forward = new QnaBoardContentService().execute(request, response);
    		forward.setRedirect(false);
    	}
    	
    	// Q&A 게시글 삭제
    	else if(url.equals("/deleteContent.qna")) {
    		forward = new QnaBoardDeleteService().execute(request, response);
    		forward.setRedirect(false);
    	}
    	
    	// Q&A 상세보기 파일 다운로드
    	else if(url.equals("/filedownload.qna")) {
    		new QnaBoardFiledownloadService().execute(request, response);
    	}
    	
    	
    	if(forward != null) {
    		if(!forward.isRedirect()) {
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
