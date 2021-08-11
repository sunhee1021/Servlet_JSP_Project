package kr.or.bit.qnaboard.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

public class QnaBoardFiledownloadService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String realfilename = request.getParameter("qnaRealFilename");
		
		//다운로드할 파일명 얻기
		String filename = realfilename;
		
		//물리적 경로 얻기
//		String savepath = "upload";
//		String downloadpath = request.getRealPath(savepath);
//		String FilePath = downloadpath + "\\" + filename;
		String FilePath="/Users/bosungbaek/Desktop/boco/bitcamp/2nd_team/project-workspace/JYP_PROJECT/WebContent/upload/"+filename;
		System.out.println(FilePath);
		//IO작업 하기
		
		//파일을 읽어서 출력
		byte[] b = new byte[4096]; //4kb  //요기는 필요에 따라 조정 가능
		FileInputStream in = null;
		try {
			in = new FileInputStream(FilePath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //실 저장 경로에서
		
		//실제 인지할수 있는 타입의 파일들이면 자신의 => contentType
		//application이 인지할 수 없는 확장자 파일은 null 경우에 형식을 잡아준다
		String sMimeType = request.getSession().getServletContext().getMimeType(FilePath); //파일의 타입 정보
		if(sMimeType == null){
			 //알수 없는 형식의 파일은 
			 //application/octet-stream 기본값으로 (알수 없는 파일 형식)
			 sMimeType = "application/octet-stream";
		}
		
		
		//2. client 전달되는 형식을 지정(Type)
		//response.setContentType("text/html;charset=UTF-8");  html 파일인 경우
		response.setContentType(sMimeType);
		
		//3. 인코딩 처리(한글 : 파일이 가지고 있는 한글에 대한 처리)
		String SEncoding = null;
		try {
			SEncoding = new String(filename.getBytes("utf-8"),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//out.println("SEncoding : " + SEncoding);
		
		//4. 다운로드(기본 설정) 구현
		response.setCharacterEncoding("euc-kr");
		response.setHeader("Content-Disposition","attachment;filename="+SEncoding);
		//파일 다운로드를 위한 헤더 정보 수정 (파일이름 문자열 다시 인코딩)
		
		//Content-Disposition: attachment;filename=1.gif
		//Content-Type: image/gif;charset=UTF-8
		
		
		try {
			response.setHeader("Content-Disposition", 
			       "attachment;filename="+new String(filename.getBytes(),"ISO8859_1"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   
		//filename.getBytes(),"ISO8859_1")
		//문서를 읽어 들여서 ISO8859_1 형식으로 변환 ......
		// 서버에서 만들어주는 코드
		
		
		
		//5. 파일 출력하기
		ServletOutputStream out2 = null;
		int numread;
		try {
			
			out2 = response.getOutputStream();
			
			while((numread = in.read(b,0,b.length)) != -1){
			   out2.write(b,0,numread);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			try {
				out2.flush();
				out2.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return null;
	}

}