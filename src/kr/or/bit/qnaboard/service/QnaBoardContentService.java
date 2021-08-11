package kr.or.bit.qnaboard.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.member.dto.MemberDto;
import kr.or.bit.qnaboard.dao.QnaBoardDao;
import kr.or.bit.qnaboard.dto.QnaBoard;

public class QnaBoardContentService implements Action {

	
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		int qnaNumber = Integer.parseInt(request.getParameter("qnaNumber").trim());
		String password = request.getParameter("password");
		QnaBoardDao dao = new QnaBoardDao();
		QnaBoard qnaBoard = dao.searchContentQnaBoard(qnaNumber);
		
		
		
		if(qnaBoard.getQnaPassword().equals(password)) {
			request.setAttribute("qnaBoard", qnaBoard);
			request.setAttribute("page", request.getParameter("page"));
			forward.setPath("WEB-INF/view/qna/qnaContent.jsp");
		} else {
			request.setAttribute("url", "boardList.qna");
			request.setAttribute("msg", "비밀번호가 일치하지 않습니다");
			forward.setPath("WEB-INF/view/redirect.jsp");
		}
		
		return forward;
	}

}
