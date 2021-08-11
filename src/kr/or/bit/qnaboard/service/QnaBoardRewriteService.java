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

public class QnaBoardRewriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		MemberDto loginUser = (MemberDto)request.getSession().getAttribute("user");
		
		// enctype="multipart/form-data" 쓴 경우 multipartrequest로 받아야함
		MultipartRequest multi = new FileUpload().getMulti(request);
		
		int qnaNumber = Integer.parseInt(multi.getParameter("qnaNumber"));
		
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
		
		QnaBoard qnaboard = new QnaBoard();
		qnaboard.setQnaNumber(qnaNumber);
		qnaboard.setEmail(email);
		qnaboard.setQnaPassword(qnaPassword);
		qnaboard.setQnaTitle(qnaTitle);
		qnaboard.setQnaContent(qnaContent);
		qnaboard.setQnaRealFilename(qnaRealFilename);
		qnaboard.setQnaFilename(qnaFilename);
		qnaboard.setQnaFilesize(qnaFilesize);
		// 글 참조
		setReferDepthStep(qnaboardDao.getReferDepthStep(qnaNumber), qnaboard);
		
		// 관리자가 답글을 달 때는 0 : 답변완료
		// 일반 회원이 질문할 때는 1 : 접수완료 상태가 된다.
		if(loginUser.getDivision().equals("0")) {
			qnaboard.setQnaStatus(0);
		} else {
			qnaboard.setQnaStatus(1);
		}
		
		int result2 = qnaboardDao.updateQnaRewrite(qnaboard);
		int result1 = qnaboardDao.insertQnaContent(qnaboard);
		
		if(result1 > 0) {
			// 성공
			request.setAttribute("msg", "글 답글에 성공하셨습니다.");
		} else {
			// 실패
			request.setAttribute("msg", "글 답글에 실패하셨습니다.");
		}
		
		request.setAttribute("url", request.getContextPath() + "/boardList.qna?page=" + multi.getParameter("page"));
		forward.setPath("WEB-INF/view/redirect.jsp");
		
		return forward;
	}
	
	public void setReferDepthStep(QnaBoard refdepstep, QnaBoard qnaboard) {
		qnaboard.setQnaRefer(refdepstep.getQnaRefer());
		qnaboard.setQnaDepth(refdepstep.getQnaDepth() + 1);
		qnaboard.setQnaStep(refdepstep.getQnaStep() + 1);
	}

}
