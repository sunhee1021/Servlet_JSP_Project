package kr.or.bit.qnaboard.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.member.dto.MemberDto;
import kr.or.bit.qnaboard.dao.QnaBoardDao;
import kr.or.bit.qnaboard.dto.QnaBoard;
import kr.or.bit.utils.FileUpload;
import kr.or.bit.utils.StringUtils;

public class QnaBoardRegister implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		
		MemberDto loginUser = (MemberDto)request.getSession().getAttribute("user");
		
		// enctype="multipart/form-data" 쓴 경우 multipartrequest로 받아야함
		MultipartRequest multi = new FileUpload().getMulti(request);
		
		// 이메일
		String email = loginUser.getEmail();
		// 비밀번호
		String qnaPassword = multi.getParameter("password");
		// 글 제목
		String qnaTitle = multi.getParameter("title");
		// 글 내용
		String qnaContent = multi.getParameter("content");
		
		// filename=?
		String inputFilename = (String)multi.getFileNames().nextElement();
		// 저장된 파일 실제 이름
		String qnaRealFilename = multi.getFilesystemName(inputFilename) != null ? multi.getFilesystemName(inputFilename) : "";
		// 파일 이름
		String qnaFilename = multi.getOriginalFileName(inputFilename) != null ? multi.getOriginalFileName(inputFilename) : "";
		// 파일 크기
		long qnaFilesize = multi.getFile(inputFilename) != null ? multi.getFile(inputFilename).length() : 0;
		
		QnaBoardDao qnaboardDao = new QnaBoardDao();
		
		// 글 참조
		int referMax = qnaboardDao.getMaxRefer();
		int qnaRefer = referMax + 1;
		
		
		QnaBoard qnaboard = new QnaBoard();
		qnaboard.setEmail(email);
		qnaboard.setQnaPassword(qnaPassword);
		qnaboard.setQnaTitle(qnaTitle);
		qnaboard.setQnaContent(qnaContent);
		qnaboard.setQnaRealFilename(qnaRealFilename);
		qnaboard.setQnaFilename(qnaFilename);
		qnaboard.setQnaFilesize(qnaFilesize);
		qnaboard.setQnaRefer(qnaRefer);
		
		// 관리자가 답글을 달 때는 0 : 답변완료
		// 일반 회원이 질문할 때는 1 : 접수완료 상태가 된다.
		if(loginUser.getDivision().equals("0")) {
			qnaboard.setQnaStatus(0);
		} else {
			qnaboard.setQnaStatus(1);
		}
		
		int result = qnaboardDao.insertQnaContent(qnaboard);
		
		if(result > 0) {
			// 글 등록 성공
			request.setAttribute("msg", "등록에 성공하셨습니다.");
		} else {
			// 글 등록 실패
			request.setAttribute("msg", "등록에 실패하셨습니다.");
		}
		request.setAttribute("url", request.getContextPath() + "/boardList.qna");
		forward.setPath("WEB-INF/view/redirect.jsp");
		return forward;
	}

	

}
