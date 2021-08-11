package kr.or.bit.qnaboard.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.qnaboard.dao.QnaBoardDao;
import kr.or.bit.qnaboard.dto.QnaBoard;
import kr.or.bit.utils.StringUtils;

public class QnaBoardListService implements Action {

	public static final int START = 1;
	public static final int END = 10;
	
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		int currentPage = Integer.parseInt(request.getParameter("currentPage") == null ? "1" : request.getParameter("currentPage").trim());
		int pageSize = Integer.parseInt(request.getParameter("pageSize") == null ? "10" : request.getParameter("pageSize").trim());
		
		int start = (currentPage - 1) * pageSize + 1;
		int end = currentPage * pageSize;
		
		QnaBoardDao dao = new QnaBoardDao();
		List<QnaBoard> list = dao.searchListQnaBoard(start, end);
		request.setAttribute("list", list);
		request.setAttribute("total", dao.getTotal());
		
		forward.setPath("WEB-INF/view/qna/qnaList.jsp");
		
		return forward;
	}

}
