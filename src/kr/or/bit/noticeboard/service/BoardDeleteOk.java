package kr.or.bit.noticeboard.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.noticeboard.dao.NoticeDao;

public class BoardDeleteOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String n_NUMBER = request.getParameter("n_NUMBER");
		
		String msg="";
		String url="";
		
		NoticeDao dao;
		try {
			dao = new NoticeDao();
			
			int result = dao.deleteOk(n_NUMBER);
			System.out.println(result);
			if(result > 0){
				msg="delete success";
				url="boardList.bo";
			}else{
				msg="delete fail";
				url="boardList.bo";
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("board_msg",msg);
		request.setAttribute("board_url",url);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false); // forward
		forward.setPath("boardList.bo");

		return forward;
	}

}
