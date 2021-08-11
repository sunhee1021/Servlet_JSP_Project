package kr.or.bit.qnaboard.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.member.dto.MemberDto;
import kr.or.bit.qnaboard.dao.QnaBoardDao;
import kr.or.bit.qnaboard.dto.QnaBoard;

public class QnaInputPasswordService implements Action {

	public static final String ADMIN_DIVISION = "0";
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		MemberDto user = (MemberDto)request.getSession().getAttribute("user");
		
		String division  = user.getDivision();
		String email = user.getEmail();
		
		if(division.equals(ADMIN_DIVISION) || email.equals(request.getParameter("email"))) {
			
			int qnaNumber = Integer.parseInt(request.getParameter("qnaNumber").trim());
			QnaBoardDao dao = new QnaBoardDao();
			QnaBoard qnaBoard = dao.searchContentQnaBoard(qnaNumber);
			request.setAttribute("qnaBoard", qnaBoard);
			request.setAttribute("page", request.getParameter("page"));
			forward.setPath("WEB-INF/view/qna/qnaContent.jsp");
			
			forward.setPath("WEB-INF/view/qna/qnaContent.jsp");
		} else {
			forward.setPath("WEB-INF/view/qna/qnaInputPassword.jsp");
		}
		
		
		
		return forward;
	}

}
