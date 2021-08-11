package kr.or.bit.member.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.member.dao.MemberDao;
import kr.or.bit.member.dto.MemberDto;
import net.sf.json.JSONArray;

public class CheckService implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDao dao = new MemberDao();
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		
		List<MemberDto> list = new ArrayList<MemberDto>();
		
		if(email != null && nickname == null) {
			list = dao.emailCheck(email);
		}else {
			list = dao.nickCheck(nickname);
		}
		
		JSONArray jsonArr = JSONArray.fromObject(list);
		
		response.setContentType("application/x-json; charset=UTF-8");
		try {
			response.getWriter().print(jsonArr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
