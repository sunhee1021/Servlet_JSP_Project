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
import kr.or.bit.cartlist.ajax.InsertCartlistService;
import kr.or.bit.cartlist.ajax.deleteCartlistService;
import kr.or.bit.cartlist.service.MyTourListService;
import kr.or.bit.cartlist.service.TourListService;

@WebServlet("*.ha")
public class HaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HaController() {
		super();
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURI().substring(request.getContextPath().length());

		System.out.println(url);

		Action action = null;
		ActionForward forward = null;

		// 게시글목록 제공
		if (url.equals("/boardList.ha")) {
			System.out.println("오냐?");
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("WEB-INF/view/HJS/Community_board/board_List.jsp");
		
		// 게시글쓰기 제공
		}else if(url.equals("/boardWrite.ha")){
			System.out.println("쓰냐?");
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("WEB-INF/view/HJS/Community_board/board_Write.jsp");
		
		// 글 등록 버튼 사용시 검증사항
		}else if(url.equals("/boardWrite_ok.ha")){
			System.out.println("글쓰기 버튼을 눌렀넹?");
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("WEB-INF/view/HJS/Community_board/board_Write_ok.jsp");
		
		// 등록성공 했을 경우 메세지 띄어주는 기능과 게시글 목록으로 이동시켜주는 기능
		}else if(url.equals("/boardWrite_yes.ha")){
			System.out.println("등록 완료");
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("WEB-INF/view/HJS/Community_board/board_List.jsp");
		
		// 등록에 실패 했을경우 메세지를 띄어주는 기능과 게시글 작성으로 이동시켜주는 기능
		}else if(url.equals("/boardWrite_no.ha")){
			System.out.println("등록 실패");
			forward = action.execute(request, response);
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("WEB-INF/view/HJS/Community_board/board_Write.jsp");
		 
		// 상세보기의 게시글 번호를 가지고 오지 않았을 경우
		}else if(url.equals("/c_number_not.ha")) {
			System.out.println("c_number 가져오기 실패");
			forward = action.execute(request, response);
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("WEB-INF/view/HJS/Community_board/board_List.jsp");
		
		// content에서 목록가기 눌렀을 때
		}else if(url.equals("/c_content_golist.ha")) {
			System.out.println("목록가기");
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("WEB-INF/view/HJS/Community_board/board_List.jsp");
		
		// content에서 수정하기 눌렀을 때
		}else if(url.equals("/c_content_edit.ha")) {
			System.out.println("편집하기로 이동");
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("WEB-INF/view/HJS/Community_board/board_Edit.jsp");
		
		// content에서 삭제하기 눌렀을 때
		}else if(url.equals("/c_content_delete.ha")) {
			System.out.println("삭제하기");
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("WEB-INF/view/HJS/Community_board/board_Delete_ok.jsp");
			
		// 이전 페이지 가기
		}else if(url.equals("/board_list_before.ha")) {
			System.out.println("이전 페이지 가기");
			forward = new ActionForward();
			// forward = action.execute(request, response);
			forward.setRedirect(false);
			forward.setPath("WEB-INF/view/HJS/Community_board/board_List.jsp");
			
			
		// 콘텐츠 보여주기
		}else if(url.equals("/board_content.ha")) {
			System.out.println("리스트에서 제목 눌렀음");
			forward = new ActionForward();
			forward.setRedirect(false);
			
			
			request.setAttribute("c_number", request.getParameter("c_number"));
			request.setAttribute("cp", request.getParameter("cp"));
			request.setAttribute("ps", request.getParameter("ps"));
			
			forward.setPath("WEB-INF/view/HJS/Community_board/board_Content.jsp");
			
		// 다음버튼눌러서 페이지 가기
		}else if(url.equals("/board_list_next.ha")) {
			System.out.println("다음페이지 가기");
			forward = new ActionForward();
			// forward = action.execute(request, response);
			forward.setRedirect(false);
			forward.setPath("WEB-INF/view/HJS/Community_board/board_List.jsp");
			
//		// 페이저 설정
//		}else if(url.equals("/board_List_pager.ha")){
//			System.out.println("pager");
//			forward = new ActionForward();
//			forward = action.execute(request, response);
//			forward.setRedirect(false);
//			forward.setPath("WEB-INF/view/HJS/Community_board/board_List.jsp");
		
		// 목록으로 가는 페이지
		}else if(url.equals("/board_list.ha")) {
			System.out.println("목록으로 가즈아");
			forward.setRedirect(false);
			forward.setPath("WEB-INF/view/HJS/Community_board/board_List.jsp");	
			
		// 수정완료버튼 누르기
		}else if(url.equals("/board_editok.ha")) {
			System.out.println("수정완료 버튼 클릭!");
			
			/* request.setAttribute("c_number", request.getParameter("c_number")); */
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("WEB-INF/view/HJS/Community_board/board_Edit_ok.jsp");
		}
		
			if(forward != null) {
	    		if(forward.isRedirect()) { //true 
	    			response.sendRedirect(forward.getPath());
	    		}else { 
	    			RequestDispatcher dis  = request.getRequestDispatcher(forward.getPath());
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
