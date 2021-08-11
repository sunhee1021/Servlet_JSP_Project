package kr.or.bit.sunboard.service;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.sunboard.dao.SunBoardDAO;
import kr.or.bit.sunboard.dto.SunReplyDTO;
import kr.or.bit.utils.StringUtils;

public class ReplyAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String c_number = request.getParameter("c_number");
		String content = request.getParameter("reply_content");
		String email = (String) request.getSession().getAttribute("email");
		
		
		//String userid = "empty";
		
		//Reply reply = new Reply();
		String msg="";
	    String url="";
		
	    List<SunReplyDTO> replyList = null;
	    
	    System.out.println("오니?" + content);
	    
	    
		try {
			SunBoardDAO dao = new SunBoardDAO();
			int cNumpk = Integer.parseInt(c_number);
			
			System.out.println("cNumpk 값 : " + cNumpk);
			/*
			reply.setWriter(writer);
			reply.setContent(content);
			reply.setPwd(pwd);
			reply.setIdx_fk(idx_fk);
			reply.setUserid(userid);
			*/
			
			int result = dao.replywrite(cNumpk,content, email);
			
			System.out.println("result 값 : " + result);
			if(result > 0){
				
		    	msg ="댓글 입력 성공";
		    	url ="/boardContent.sun?c_number="+ cNumpk;
		    	
		    	replyList = dao.replylist(c_number);
		    	
		    	StringUtils utils = new StringUtils();
		    	String parsed = utils.listParseToJavascriptArray(replyList, new SunReplyDTO());
		    	
		    	response.setCharacterEncoding("UTF-8");
		    	PrintWriter out = response.getWriter();
		    	out.print(parsed);
		    	
//              JSONArray parsedJson = JSONArray.fromObject(replyList);
//             PrintWriter out = response.getWriter();
//             out.print(parsedJson);
		    	
//		    	response.setCharacterEncoding("utf-8");
//                PrintWriter out = response.getWriter();
//		    	
//		    	for(Reply reply : replyList) {
//		    	    out.print("<table width=\"80%\" border=\"1\">");
//	                out.print("<tr>");
//	                out.print("<th colspan=\"2\">REPLY LIST</th>");
//	                out.print("</tr>");
//	                out.print("<tr align=\"left\">");
//	                out.print("<td width=\"80%\">");
//	                out.print("[" + reply.getWriter() + "] : " + reply.getContent());
//	                out.print("<br> 작성일:" + reply.getWritedate());
//	                out.print("</td>");
//	                out.print("<td width=\"20%\">");
//	                out.print("<form action=\"ReplyDeleteOk.do\" method=\"POST\" name=\"replyDel\">");
//	                out.print("<input type=\"hidden\" name=\"no\" value=\"" + reply.getNo() + "\">");
//	                out.print("<input type=\"hidden\" name=\"idx\" value=\"" + idx + "\">");
//	                out.print("password :<input type=\"password\" name=\"delPwd\" size=\"4\">");
//	                out.print("<input type=\"button\" value=\"삭제\" onclick=\"reply_del(this.form)\">");
//	                out.print("</form>");
//	                out.print("</td>");
//	                out.print("</tr>");
//	                out.print("</table>");
//		    	}
		    	
		    }else{
		    	msg="댓글 입력 실패";
		    	url="/boardContent.sun?c_number="+cNumpk;
		    }
			
		} catch (Exception e) {
			e.getStackTrace();
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
