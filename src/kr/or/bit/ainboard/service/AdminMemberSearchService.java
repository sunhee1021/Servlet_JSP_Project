package kr.or.bit.ainboard.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.ainboard.dao.AinBoardDao;
import kr.or.bit.member.dto.MemberDto;
import net.sf.json.JSONArray;

public class AdminMemberSearchService implements Action {
	
	
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		AinBoardDao dao = new AinBoardDao();
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		
		List<MemberDto> list = new ArrayList<MemberDto>();
		
		if (email != null && nickname == null) {
			list = dao.searchEmail(email);
		}else {
			list = dao.searchNickname(nickname);
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
