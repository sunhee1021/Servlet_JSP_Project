package kr.or.bit.ainboard.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

public class AinFileDownload implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 다운로드 할 파일경로 가져오기
		String filename = request.getParameter("file_name");
		
		// 물리적 경로 얻기
		// String uploadpath = "/Users/bosungbaek/Desktop/boco/bitcamp/2nd_team/project-workspace/JYP_PROJECT/WebContent/upload";
		String filePath = "/Users/bosungbaek/Desktop/boco/bitcamp/2nd_team/project-workspace/JYP_PROJECT/WebContent/upload"+"/"+filename;
		
		// 파일을 읽어서 출력
		byte[] byteArray = new byte[4096];
		
		try(FileInputStream in = new FileInputStream(filePath);
			ServletOutputStream out = response.getOutputStream();) {
			
			String sMimeType = request.getSession().getServletContext().getMimeType(filePath); //파일의 타입 정보
			
			//application이 인지할 수 없는 확장자 파일은 null 경우에 형식을 잡아준다
			if(sMimeType == null) {
				sMimeType = "application/octet-stream";
			}
			
			//2. client 전달되는 형식을 지정(Type)
			response.setContentType(sMimeType);
			
			response.setHeader("Content-Disposition", "attachment;filname=" + new String(filename.getBytes(), "ISO8859_1"));
			//문서를 읽어 들여서 ISO8859_1 형식으로 변환 ......
			
			//5. 파일 출력하기
			int numread = 0;
			while( (numread = in.read(byteArray, 0, byteArray.length) ) != -1) {
				out.write(byteArray, 0, numread);
			}
			
			out.flush();
			out.close();
			in.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		return null;
	}

}
