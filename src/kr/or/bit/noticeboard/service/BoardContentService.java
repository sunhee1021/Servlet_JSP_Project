package kr.or.bit.noticeboard.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.noticeboard.dao.NoticeDao;
import kr.or.bit.noticeboard.dto.NoticeBoard;

public class BoardContentService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		

		String n_number = request.getParameter("n_NUMBER");
		String cpage = request.getParameter("cp"); // current page
		String pagesize = request.getParameter("ps"); // pagesize
		
		System.out.println(n_number);
		System.out.println(cpage);
		System.out.println(pagesize);
		NoticeBoard board = new NoticeBoard();
		
		boolean isread = false;

		try {
			
			NoticeDao dao = new NoticeDao();
			// 글 번호를 가지고 오지 않았을 경우 예외처리
			if (n_number == null || n_number.trim().equals("")) {
				response.sendRedirect("boardList.bo");
				return null;
			}
			
			n_number = n_number.trim();

			//List 페이지 처음 호출 ...
			if(cpage == null || cpage.trim().equals("")){
				//default 값 설정
				cpage = "1"; 
			}
		
			if(pagesize == null || pagesize.trim().equals("")){
				//default 값 설정
				pagesize = "5"; 
			}
			
			isread = dao.getReadNum(n_number);
			System.out.println(isread);
			if(isread) {
				board = dao.getContent(Integer.parseInt(n_number));
			}
			
			request.setAttribute("board", board);
			request.setAttribute("n_number", n_number);
			request.setAttribute("cp", cpage);
			request.setAttribute("ps", pagesize);
			
			forward = new ActionForward();
			forward.setRedirect(false); // forward
			forward.setPath("/WEB-INF/view/BO/bbsContent.jsp");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return forward;
	}

}
