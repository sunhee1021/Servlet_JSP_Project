package kr.or.bit.sunboard.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.sunboard.dao.SunBoardDAO;
import kr.or.bit.sunboard.dto.SunBoardDTO;

public class BoardEditService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//수정하기
		String c_number = request.getParameter("c_number");
		
		String msg="";
	    String url="";

		SunBoardDAO dao;
		ActionForward forward = null;
		try {		
			if(c_number == null || c_number.trim().equals("")){
				response.sendRedirect("boardList.sun"); //cpage=1 , ps=5
				return null;
			}

			dao = new SunBoardDAO();
			
			//BoardService service = BoardService.getInBoardService();
			SunBoardDTO dto = dao.getEditContent(c_number);
			
			if(dto == null){
				msg ="데이터 오류";
				url ="boardList.sun";
				
				request.setAttribute("board_msg", msg);
				request.setAttribute("board_url", url);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/view/SH/redirect.jsp");
				
			}else {
				request.setAttribute("c_number", c_number);
				request.setAttribute("dto", dto);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/view/SH/board_edit.jsp");
				
			}
			
			if (request.getSession().getAttribute("email") == null) {
				request.setAttribute("board_url", request.getContextPath() + "/Login.do");
				request.setAttribute("board_msg", "로그인 후에 이용이 가능합니다.");
				forward.setPath("WEB-INF/view/SH/redirect.jsp");
				return forward;
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward;
	}

}
