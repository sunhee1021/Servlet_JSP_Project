package kr.or.bit.sunboard.service;
import java.sql.Date;

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

public class BoardAddService implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		//"insert into sh_board(c_number,title,email,content,writedate,filename,"
			//	+ "refer,depth,step,filerealname,viewcount,filesize)"
		
		MultipartRequest multi = FileUpload.getMulti(request);
		
		HttpSession session =  request.getSession();
		
		String c_number = multi.getParameter("c_number");
		String title = multi.getParameter("title");
		String email = (String) session.getAttribute("email");
		String content = multi.getParameter("content");
		String filename = multi.getFilesystemName((String)multi.getFileNames().nextElement());
		String refer = multi.getParameter("refer");
		String depth = multi.getParameter("depth");
		String step = multi.getParameter("step");
		String filerealname = multi.getOriginalFileName((String)multi.getFileNames().nextElement());
		String viewcount = multi.getParameter("viewcount");
		String filesize = multi.getParameter("filesize");
	
		SunBoardDTO dto = new SunBoardDTO();
		
		dto.setTitle(title);
		dto.setEmail(email);
		dto.setContent(content);
		dto.setFilename(filename);
		dto.setRefer(1);
		dto.setDepth(0);
		dto.setStep(0);
		dto.setFilerealname(filerealname);
		dto.setViewcount(0);
		dto.setFilesize(0);
		
		
		
		int result = 0;
		
		try {
			SunBoardDAO dao = new SunBoardDAO();
			result = dao.writeOk(dto);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		// write.jsp 화면 >> writeok.jsp 처리 >> service >> dao > DB 작업 >
		// return dao > return service > writeok.jsp 결과처리 >> 이동 (공통) >> redirect.jsp
		String msg = "";
		String url = "";
		if (result > 0) {
			msg = "insert success";
			url = "boardList.sun";
		} else {
			msg = "insert fail";
			url = "boardWrite.sun";
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
		return forward;
	}
}
