package kr.or.bit.cartlist.ajax;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.cartlist.dao.cartlistDAO;
import kr.or.bit.cartlist.dto.CartList;
import kr.or.bit.member.dto.MemberDto;

public class InsertCartlistService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		String contentId = request.getParameter("contentId");
		
		MemberDto loginUser = (MemberDto) request.getSession().getAttribute("user");
		
		PrintWriter out = null;
		
		try {
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(loginUser == null) {
			out.print("회원만 이용가능한 서비스입니다.\n");
			out.print("로그인하고 다시 시도 해주세요.");
			return null;
		}
		
		String email = loginUser.getEmail();
		
		CartList cartlist = new CartList();
		cartlist.setEmail(email);
		cartlist.setContentId(contentId);
		
		cartlistDAO cartdao = new cartlistDAO();
		int result = cartdao.insertCartlist(cartlist);
		
		
		
		if(result > 0) {
			out.print("✨ 찜목록에 추가 되었습니다 ✨");
		} else {
			out.print("이미 추가된 관광지입니다 😊");
		}
		
		return null;
	}

}
