package kr.or.bit.sunboard.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.member.dto.MemberDto;
import kr.or.bit.sunboard.dao.SunBoardDAO;
import kr.or.bit.sunboard.dto.SunBoardDTO;
import kr.or.bit.utils.FileUpload;

public class BoardRewriteOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		MultipartRequest multi = FileUpload.getMulti(request);
		
		HttpSession session =  request.getSession();
		
		String c_number = multi.getParameter("c_number");
		String title = multi.getParameter("title");
		String email = (String) request.getSession().getAttribute("email");
		String content = multi.getParameter("content");
		
		String inputFilename = (String) multi.getFileNames().nextElement();
		// 저장된 파일 실제 이름
		String filerealname = multi.getFilesystemName(inputFilename) != null ? multi.getFilesystemName(inputFilename) : "";
		// 파일 이름
		String filename = multi.getOriginalFileName(inputFilename) != null ? multi.getOriginalFileName(inputFilename) : "";
		// 파일 크기
		long filesize = multi.getFile(inputFilename) != null ? multi.getFile(inputFilename).length() : 0;

	
		String msg="";
	    String url="";
	    
	    int result = 0;
	    
		try {
			
			SunBoardDTO dto = new SunBoardDTO();
			
			dto.setC_number(Integer.parseInt(c_number));
			dto.setTitle(title);
			dto.setEmail(email);
			dto.setContent(content);
			dto.setFilename(filename);
			dto.setFilerealname(filerealname);
			dto.setFilesize(filesize);

			SunBoardDAO dao = new SunBoardDAO();
			
			result = dao.reWriteOk(dto);
			System.out.println("대답 :" + result);

		    if(result > 0){
		    	msg ="답글이 작성되었습니다.";
		    	url ="boardList.sun";
		    	
		    }else{
		    	msg="답글 작성 실패";
		    	url="boardList.sun";
		    }
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		request.setAttribute("board_msg",msg);
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
