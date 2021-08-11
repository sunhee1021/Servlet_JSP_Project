package kr.or.bit.ainboard.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.ainboard.dao.AinBoardDao;
import kr.or.bit.ainboard.dto.AinReply;
import kr.or.bit.ainboard.utils.StringUtils;

public class AinReplyDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String crNumber = request.getParameter("crNumber");
		String cNumber = request.getParameter("cNumber");
		
		String msg = "";
		String url = "";
		
		System.out.println("댓글delete서비스진입");
		
		List<AinReply> replyList = null;
		
		try {
			AinBoardDao dao = new AinBoardDao();
			int result = dao.replyDelete(crNumber);

			if(result > 0) {
				msg = "댓글 삭제 성공";
				url = "boardContent.ain?cNumber="+cNumber;
				
				replyList = dao.replylist(cNumber);
				
				StringUtils utils = new StringUtils();
				String parsed = utils.listParseToJavascriptArray(replyList, new AinReply());
				
				response.setCharacterEncoding("UTF-8");
				
				PrintWriter out = response.getWriter();
				
				out.print(parsed);
				
			} else {
				msg = "댓글 삭제 실패";
				url = "boardContent.ain?cNumber="+cNumber;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/view/redirect.jsp");

		return null;
	}

}
