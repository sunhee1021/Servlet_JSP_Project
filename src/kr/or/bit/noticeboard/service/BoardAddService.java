package kr.or.bit.noticeboard.service;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.noticeboard.dao.NoticeDao;
import kr.or.bit.noticeboard.dto.NoticeBoard;

public class BoardAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		String uploadpath = "/Users/bosungbaek/Desktop/boco/bitcamp/2nd_team/project-workspace/JYP_PROJECT/WebContent/upload";
		System.out.println("uploadpath"+uploadpath);
		int size = 1024*1024*10;
		int result = 0;
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request, //javax.servlet.http.HttpServletRequest 주소값
					uploadpath, // 실 저장 경로 (배포된 경로)
					size, // 10M
					"UTF-8",
					new DefaultFileRenamePolicy() // 파일 중복 (upload > a.txt > a.txt 업로드 > a_1.txt) 
					);
			
			String N_TITLE = multi.getParameter("N_TITLE");
			String N_CONTENT = multi.getParameter("N_CONTENT");
			String inputFilename =(String)multi.getFileNames().nextElement(); 
			String N_FILENAME = multi.getOriginalFileName(inputFilename);
			long N_FILESIZE = multi.getFile(inputFilename) != null ? multi.getFile(inputFilename).length() : 0;
			
			
			
			NoticeBoard board = new NoticeBoard();

			board.setN_TITLE(N_TITLE);
			board.setN_FILENAME(N_FILENAME);
			board.setN_FILESIZE(N_FILESIZE);
			board.setN_REAL_FILENAME(inputFilename);
			board.setN_CONTENT(N_CONTENT);

			
			NoticeDao dao = new NoticeDao();

			result = dao.writeok(board);

		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println("에러발생");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("에러발생2");
			
		}

		// write.jsp 화면 >> writeok.jsp 처리 >> service >> dao > DB 작업 >
		// return dao > return service > writeok.jsp 결과처리 >> 이동 (공통) >> redirect.jsp
		String msg = "";
		String url = "";
		if (result > 0) {
			msg = "insert success";
			url = "/boardList.bo";
		} else {
			msg = "insert fail";
			url = "boardWriteOk.bo";
		}

		request.setAttribute("board_msg", msg);
		request.setAttribute("board_url", url);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("boardList.bo");

		return forward;

	}

}
