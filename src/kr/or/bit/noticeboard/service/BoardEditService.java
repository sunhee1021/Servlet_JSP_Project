package kr.or.bit.noticeboard.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.noticeboard.dao.NoticeDao;
import kr.or.bit.noticeboard.dto.NoticeBoard;

public class BoardEditService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//수정하기
		String n_NUMBER = request.getParameter("n_NUMBER");
		
		String msg="";
	    String url="";

		NoticeDao dao;
		ActionForward forward = null;
		try {		
			if(n_NUMBER == null || n_NUMBER.trim().equals("")){
				response.sendRedirect("boardList.bo"); //cpage=1 , ps=5
				return null;
			}

			dao = new NoticeDao();
			
			//BoardService service = BoardService.getInBoardService();
			NoticeBoard board = dao.getEditContent(n_NUMBER);
			
			if(board == null){
				msg ="데이터 오류";
				url ="boardList.bo";
				
				request.setAttribute("board_msg", msg);
				request.setAttribute("board_url", url);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/view/redirect.jsp");
				
			}else {
				request.setAttribute("n_NUMBER", n_NUMBER);
				request.setAttribute("board", board);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/view/BO/bbsEdit.jsp");
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward;
	}

}
