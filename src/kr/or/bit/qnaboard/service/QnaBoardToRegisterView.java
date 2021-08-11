package kr.or.bit.qnaboard.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.qnaboard.dao.QnaBoardDao;
import kr.or.bit.qnaboard.dto.QnaBoard;
import kr.or.bit.utils.StringUtils;

public class QnaBoardToRegisterView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		String qnaNumber = request.getParameter("qnaNumber");
		
		// 글 등록 버튼 클릭 시
		if(qnaNumber == null) {
			
		} 
		// 답글 달기 버튼 클릭 한 경우
		else {
			
			String update = request.getParameter("updateContent");
			
			QnaBoardDao dao = new QnaBoardDao();
			QnaBoard qnaboard = dao.searchContentQnaBoard(Integer.parseInt(qnaNumber));
			request.setAttribute("qnaboard", qnaboard);
			request.setAttribute("update", update);
		}
		
		forward.setPath("WEB-INF/view/qna/qnaRegister.jsp");
		
		
		
		return forward;
	}

}
