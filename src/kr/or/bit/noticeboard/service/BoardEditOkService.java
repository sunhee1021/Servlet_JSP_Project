package kr.or.bit.noticeboard.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.noticeboard.dao.NoticeDao;
import kr.or.bit.utils.FileUpload;

public class BoardEditOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		MultipartRequest multi = FileUpload.getMulti(request);
		System.out.println("ok서비스안");
		
		String n_NUMBER = multi.getParameter("n_NUMBER");
		
		String msg = "";
		String url = "";
		ActionForward forward = null;
		
		try {
			NoticeDao dao = new NoticeDao();
			
			if (n_NUMBER == null || n_NUMBER.trim().equals("")) {
				msg = "글번호 입력 오류";
				url = "boardList.bo";
				
			}else {
								
				int result = dao.boardEdit(multi);
				
				if (result > 0) {
					msg = "edit success";
					url = "/boardList.bo";
				} else {
					msg = "edit fail";
					url = "boardEdit.bo?n_NUMBER=" + n_NUMBER;
				}
				
			}
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("boardList.bo");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return forward;
	}

}
