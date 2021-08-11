package kr.or.bit.ainboard.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.ainboard.dao.AinBoardDao;
import kr.or.bit.ainboard.utils.FileUpload;

public class AinEditOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		MultipartRequest multi = FileUpload.getMulti(request);
		
		String cNumber = multi.getParameter("cNumber");
		/*
		 * String ps = multi.getParameter("ps"); 
		 * String cp = multi.getParameter("cp");
		 */

		String msg = "";
		String url = "";
		ActionForward forward = null;
		
		AinBoardDao dao = new AinBoardDao();
		
		if (cNumber == null || cNumber.trim().equals("")) {
			msg = "글번호 입력 오류";
			url = "boardList.ain";
			
		}else {
							
			int result = dao.boardEdit(multi);
			
			if (result > 0) {
				msg = "수정 성공";
				url = "boardList.ain";
			} else {
				msg = "edit fail";
				url = "boardEdit.ain?cNumber=" + cNumber;
			}
			
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/view/redirect.jsp");

		return forward;
	}

}