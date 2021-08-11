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
import kr.or.bit.sunboard.service.BoardAddService;
import kr.or.bit.sunboard.service.BoardContentService;
import kr.or.bit.sunboard.service.BoardDeleteOk;
import kr.or.bit.sunboard.service.BoardDeleteService;
import kr.or.bit.sunboard.service.BoardEditOk;
import kr.or.bit.sunboard.service.BoardEditService;
import kr.or.bit.sunboard.service.BoardFileDownload;
import kr.or.bit.sunboard.service.BoardListService;
import kr.or.bit.sunboard.service.BoardRewriteOk;
import kr.or.bit.sunboard.service.BoardRewriteService;
import kr.or.bit.sunboard.service.ReplyAddService;
import kr.or.bit.sunboard.service.ReplyDeleteOk;

@WebServlet("*.sun")
public class SunController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SunController() {
		super();
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String URL = requestURI.substring(contextPath.length());

		Action action = null;
		ActionForward forward = null;

		if (URL.equals("/boardList.sun")) { // 글쓰기 처리
			// UI+로직
			action = new BoardListService();
			forward = action.execute(request, response);

		} else if (URL.equals("/boardWrite.sun")) { // 만약 있다면 상세보기
			// UI 제공 ...
			// 예) /WEB-INF/views/memoview.jsp 가정
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/view/SH/board_write.jsp");
			
		} else if (URL.equals("/boardWriteOK.sun")) { // 만약 있다면 상세보기
			// UI 제공 ...
			// 예) /WEB-INF/views/memoview.jsp 가정
			System.out.println("컨트롤러 들어오니?");
			action = new BoardAddService();
			forward = action.execute(request, response);
		
		}else if(URL.equals("/boardContent.sun")) { //만약 있다 상세보기 
			  //UI 제공 ... 
			  //예)/WEB-INF/views/memoview.jsp 가정 
			  action = new BoardContentService(); 
			  forward = action.execute(request, response); 
			  
		}else if(URL.equals("/ReplyOk.sun")) { //만약 있다면 상세보기 
			//UI 제공 ... //예) /WEB-INF/views/memoview.jsp 가정 
			action = new ReplyAddService(); 
			forward = action.execute(request, response); 
			
		}else if(URL.equals("/boardEdit.sun")) { //만약 있다면 상세보기 //UI 제공 ... //예)/WEB-INF/views/memoview.jsp 가정 
			action = new BoardEditService(); 
			forward = action.execute(request, response); 
			
		}else if(URL.equals("/boardEditOk.sun")) {
	//만약 있다면 상세보기 //UI 제공 ... //예) /WEB-INF/views/memoview.jsp 가정 
			action = new BoardEditOk(); 
			forward = action.execute(request, response); 
			
		}else if(URL.equals("/boardDelete.sun")) { //만약 있다면 상세보기 //UI 제공 ... //예)/WEB-INF/views/memoview.jsp 가정 
			action = new BoardDeleteService(); 
			forward = action.execute(request, response); 
			
		}else if(URL.equals("/boardDeleteOk.sun")){ //만약 있다면 상세보기 //UI 제공 ... //예) /WEB-INF/views/memoview.jsp 가정 
			action = new BoardDeleteOk(); 
			forward = action.execute(request, response); 
			
		}else if(URL.equals("/boardRewrite.sun")) { //만약 있다면 상세보기 //UI 제공 ... //예)/WEB-INF/views/memoview.jsp 가정 
			action = new BoardRewriteService(); 
			forward = action.execute(request, response);
			
		}else if(URL.equals("/boardRewriteOk.sun")){ //만약 있다면 상세보기 //UI 제공 ... //예) /WEB-INF/views/memoview.jsp 가정 
			action = new BoardRewriteOk(); 
			forward = action.execute(request, response);
			
		}else if(URL.equals("/ReplyDeleteOk.sun")) { //만약 있다면 상세보기 //UI 제공 ... //예)/WEB-INF/views/memoview.jsp 가정 
			action = new ReplyDeleteOk(); 
			forward = action.execute(request, response); 
			
		} else if(URL.equals("/file.sun")) { 
			action = new BoardFileDownload(); 
			action.execute(request, response); 
		}

		if (forward != null) {
			if (forward.isRedirect()) { // true
				response.sendRedirect(forward.getPath());
			} else { // false (모든 자원 ) 사용
				// UI
				// UI + 로직
				// forward url 주소 변환 없어 View 내용을 받을 수 있다
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
