package kr.or.bit.sunboard.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.sunboard.dao.SunBoardDAO;
import kr.or.bit.sunboard.dto.SunBoardDTO;
import kr.or.bit.sunboard.dto.SunReplyDTO;

public class BoardContentService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
				
		String c_number = request.getParameter("c_number");
		String cpage = request.getParameter("cp"); // current page
		String pagesize = request.getParameter("ps"); // pagesize
		
		SunBoardDTO board = new SunBoardDTO();
		List<SunReplyDTO> replyList = new ArrayList<>();
		
		boolean isread = false;

		try {
			
			SunBoardDAO dao = new SunBoardDAO();
			// 글 번호를 가지고 오지 않았을 경우 예외처리
			if (c_number == null || c_number.trim().equals("")) {
				response.sendRedirect("boardList.sun");
				return null;
			}
			
			c_number = c_number.trim();

			//List 페이지 처음 호출 ...
			if(cpage == null || cpage.trim().equals("")){
				//default 값 설정
				cpage = "1"; 
			}
		
			if(pagesize == null || pagesize.trim().equals("")){
				//default 값 설정
				pagesize = "5"; 
			}
			
			isread = dao.getReadNum(c_number);
			
			System.out.println("너너" +isread);
			
			if(isread) {
				board = dao.getContent(Integer.parseInt(c_number));
				replyList = dao.replylist(c_number);
				System.out.println("들어오니?" + board);
			}
			
			request.setAttribute("board", board);
			request.setAttribute("c_number", c_number);
			request.setAttribute("cp", cpage);
			request.setAttribute("ps", pagesize);
			request.setAttribute("replyList", replyList);
			
			forward = new ActionForward();
			forward.setRedirect(false); // forward
			forward.setPath("/WEB-INF/view/SH/board_content.jsp");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return forward;
	}

}
