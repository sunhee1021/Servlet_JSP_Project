package kr.or.bit.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileUpload {
	public static MultipartRequest getMulti(HttpServletRequest request) {
		
		//String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		String uploadpath = "/Users/bosungbaek/Desktop/boco/bitcamp/2nd_team/project-workspace/JYP_PROJECT/WebContent/upload";
		System.out.println(uploadpath);

		int size = 1024 * 1024 * 10; // (10M)

		MultipartRequest multi = null;
		try {
			multi = new MultipartRequest(
					request,
					uploadpath,
					size,
					"UTF-8",
					new DefaultFileRenamePolicy()
					);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return multi;
	}
}
