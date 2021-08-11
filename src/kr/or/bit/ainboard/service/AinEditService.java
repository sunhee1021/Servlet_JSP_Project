package kr.or.bit.ainboard.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.ainboard.dao.AinBoardDao;
import kr.or.bit.ainboard.dto.AinBoard;

public class AinEditService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//수정하기
		String cNumber = request.getParameter("cNumber");
		
		String msg="";
	    String url="";

		AinBoardDao dao;
		ActionForward forward = null;
		
		try {		
			if(cNumber == null || cNumber.trim().equals("")){
				response.sendRedirect("boardList.ain"); //cpage=1 , ps=5
				return null;
			}

			dao = new AinBoardDao();
			
			//BoardService service = BoardService.getInBoardService();
			AinBoard board = dao.getEditContent(cNumber);
			
			if(board == null){
				msg ="데이터 오류";
				url ="BoardList.do";
				
				request.setAttribute("board_msg", msg);
				request.setAttribute("board_url", url);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/view/redirect.jsp");
				
			}else {
				request.setAttribute("cNumber", cNumber);
				request.setAttribute("board", board);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/view/AIN/ainBoardEdit.jsp");
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward;
	}

}