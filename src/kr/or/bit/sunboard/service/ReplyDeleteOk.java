package kr.or.bit.sunboard.service;

import java.io.PrintWriter;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.sunboard.dao.SunBoardDAO;
import kr.or.bit.sunboard.dto.SunReplyDTO;
import kr.or.bit.utils.StringUtils;

public class ReplyDeleteOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String c_number = request.getParameter("c_number");// 댓글의 원본 게시글 번호
		String cr_number = request.getParameter("cr_number");// 댓글의 순번(PK)

		String msg = "";
		String url = "";
		
		List<SunReplyDTO> replyList = null;

		if (c_number == null || cr_number == null || cr_number.trim().equals("")) {
			msg = "잘못된 링크 입니다.";
			url = "boardContent.sun?cr_number=" + cr_number;
		} else {
			
			
			
			try {
				SunBoardDAO dao = new SunBoardDAO();
				int result = dao.replyDelete(cr_number);
				
				if (result > 0) {
					
					msg = "댓글이 삭제되었습니다.";
					url = "boardContent.sun?c_number=" + c_number;
					
					replyList = dao.replylist(c_number);
			    	
			    	StringUtils utils = new StringUtils();
			    	String parsed = utils.listParseToJavascriptArray(replyList, new SunReplyDTO());
			    	
			    	response.setCharacterEncoding("UTF-8");
			    	PrintWriter out = response.getWriter();
			    	out.print(parsed);
					
				} else {
					msg = "댓글 삭제 실패";
					url = "boardContent.sun?c_number=" + c_number;
				}

			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		request.setAttribute("board_msg", msg);
		request.setAttribute("board_url", url);
		
		ActionForward forward = new ActionForward();
		
		if (request.getSession().getAttribute("email") == null) {
			request.setAttribute("board_url", request.getContextPath() + "/Login.do");
			request.setAttribute("board_msg", "로그인 후에 이용이 가능합니다.");
			forward.setPath("WEB-INF/view/SH/redirect.jsp");
			return forward;
		}
		
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/view/SH/redirect.jsp");

		return null;
	}

}
