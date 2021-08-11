package kr.or.bit.ainboard.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.member.dto.MemberDto;
import kr.or.bit.ainboard.dao.AinBoardDao;
import kr.or.bit.ainboard.dto.AinBoard;
import kr.or.bit.ainboard.utils.FileUpload;

public class AinWriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		// enctype="multipart/form-data" 쓴 경우 multipartrequest로 받아야함
		@SuppressWarnings("static-access")
		MultipartRequest multi = new FileUpload().getMulti(request);
		
		
		// 이메일
		String email = ((MemberDto)request.getSession().getAttribute("user")).getEmail();
		// 글 제목
		String title = multi.getParameter("title");
		// 글 내용
		String content = multi.getParameter("content");
		
		// filename=?
		String inputFilename = (String)multi.getFileNames().nextElement();
		// 저장된 파일 실제 이름
		String realFilename = multi.getFilesystemName(inputFilename) != null ? multi.getFilesystemName(inputFilename) : "";
		// 파일 이름
		String filename = multi.getOriginalFileName(inputFilename) != null ? multi.getOriginalFileName(inputFilename) : "";
		// 파일 크기
		long filesize = multi.getFile(inputFilename) != null ? multi.getFile(inputFilename).length() : 0;
		
		AinBoardDao boardDao = new AinBoardDao();
		// 글 참조
		int referMax = boardDao.getMaxRefer();
		int refer = referMax + 1;
		
		AinBoard board = new AinBoard();
		board.setEmail(email);
		board.setTitle(title);
		board.setContent(content);
		board.setFilerealname(realFilename);
		board.setFilename(filename);
		board.setFilesize(filesize);
		board.setRefer(refer);
		
		int result = boardDao.writeok(board);
		
		if(result > 0) {
			// 글 등록 성공
			request.setAttribute("msg", "등록에 성공하셨습니다.");
		} else {
			// 글 등록 실패
			request.setAttribute("msg", "등록에 실패하셨습니다.");
		}
		request.setAttribute("url", request.getContextPath() + "/boardList.ain");
		
		return forward;
	}

	

}