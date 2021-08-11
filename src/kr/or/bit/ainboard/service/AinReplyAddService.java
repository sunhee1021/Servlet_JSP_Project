package kr.or.bit.ainboard.service;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.ainboard.dao.AinBoardDao;
import kr.or.bit.ainboard.dto.AinReply;
import kr.or.bit.ainboard.utils.StringUtils;
import kr.or.bit.member.dto.MemberDto;
import net.sf.json.JSONArray;

public class AinReplyAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String email = ((MemberDto) request.getSession().getAttribute("user")).getEmail();
		String content = request.getParameter("reply_content");
		String cNumber = request.getParameter("cNumber");
		
		String msg="";
		String url="";
		
		System.out.println("댓글add서비스진입");
		
		List<AinReply> replyList = null;
		
		try {
			AinBoardDao dao = new AinBoardDao();
			
			int c_Number = Integer.parseInt(cNumber);
			
			int result = dao.replyAdd(c_Number, email, content);
			
			if(result > 0) {
				msg = "댓글 입력 성공";
				url = "boardContent.ain?cNumber="+c_Number;
				
				replyList = dao.replylist(cNumber);
				
				StringUtils utils = new StringUtils();
				JSONArray parsed = utils.listParseToJsonArray(replyList, new AinReply());
				
				response.setCharacterEncoding("UTF-8");
				
				PrintWriter out = response.getWriter();
				
				out.print(parsed);
				
			} else {
				msg="댓글입력 실패";
				url = "boardContent.ain?cNumber="+c_Number;
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/view/redirect.jsp");
		
		return null;
	}

}
