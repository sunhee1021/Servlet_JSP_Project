package kr.or.bit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.qnaboard.dao.QnaBoardDao;

public class QnaBoardDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		
		String qnaNumber = request.getParameter("qnaNumber");
		
		
		
		
		String page = request.getParameter("page");
		
		QnaBoardDao dao = new QnaBoardDao();
		
		int result = dao.deleteContent(Integer.parseInt(qnaNumber));
		
		if(result > 0) {
			// 성공
			request.setAttribute("msg", "글 수정에 성공하셨습니다.");
		} else {
			// 실패
			request.setAttribute("msg", "글 수정에 실패하셨습니다.");
		}
		
		request.setAttribute("url", request.getContextPath() + "/boardList.qna?page=" + request.getParameter("page"));
		forward.setPath("WEB-INF/view/redirect.jsp");
		
		
		return forward;
	}

}
