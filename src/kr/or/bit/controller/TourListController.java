package kr.or.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.ActionForward;
import kr.or.bit.cartlist.ajax.InsertCartlistService;
import kr.or.bit.cartlist.ajax.deleteCartlistService;
import kr.or.bit.cartlist.service.MyTourListService;
import kr.or.bit.cartlist.service.TourListService;

@WebServlet("*.tourlist")
public class TourListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TourListController() {
		super();
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURI().substring(request.getContextPath().length());

		System.out.println(url);

		ActionForward forward = null;

		//관광지페이지 제공
		if (url.equals("/tourList.tourlist")) {
			forward = new TourListService().execute(request, response);
			forward.setRedirect(false);
			forward.setPath("WEB-INF/view/tourList/tourList.jsp");
		} 
		// 담기버튼 클릭시 카트리스트에 담기 로직
		else if(url.equals("/insertcartlist.tourlist")) {
			new InsertCartlistService().execute(request, response);
		}
		// 취소버튼 클릭시 카트리스트에 담기 로직
		else if(url.equals("/deletecartlist.tourlist")) {
			new deleteCartlistService().execute(request, response);
		}
		//내 관광지 페이지 제공
		else if (url.equals("/mytourList.tourlist")) {
			forward = new MyTourListService().execute(request, response);
			forward.setRedirect(false);
		}

		if (forward != null) {

			if (!forward.isRedirect()) {
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
