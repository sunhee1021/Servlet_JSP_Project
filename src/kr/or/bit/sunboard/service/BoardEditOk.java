package kr.or.bit.sunboard.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.sunboard.dao.SunBoardDAO;
import kr.or.bit.sunboard.dto.SunBoardDTO;
import kr.or.bit.utils.FileUpload;

public class BoardEditOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		MultipartRequest multi = FileUpload.getMulti(request);
		
		//HttpSession session =  request.getSession();
		
		String c_number = multi.getParameter("c_number");
		
		System.out.println("오니?" +c_number);

		String msg = "";
		String url = "";
		int result = 0;
		ActionForward forward = null;
		
		try {
			SunBoardDAO dao = new SunBoardDAO();
			System.out.println("글번호 받니?");
			
			if (c_number == null || c_number.trim().equals("")) {
				msg = "글번호 입력 오류";
				url = "boardList.sun";
				
			}else {
				System.out.println("힘내자3");				
				result = dao.boardEdit(multi);
				
				if (result > 0) {
					msg = "수정 완료";
					url = "boardList.sun";
				} else {
					msg = "수정 실패";
					url = "boardEdit.sun?c_number=" + c_number;
				}
				
			}
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			
			forward = new ActionForward();
			
			if (request.getSession().getAttribute("email") == null) {
				request.setAttribute("board_url", request.getContextPath() + "/Login.do");
				request.setAttribute("board_msg", "로그인 후에 이용이 가능합니다.");
				forward.setPath("WEB-INF/view/SH/redirect.jsp");
				return forward;
				
			}
			
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/view/SH/redirect.jsp");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("여기에러니?" +e.getMessage());
		}

		return forward;
	}

}
