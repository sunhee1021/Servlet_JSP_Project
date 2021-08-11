package kr.or.bit.cartlist.ajax;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.cartlist.dao.cartlistDAO;
import kr.or.bit.cartlist.dto.CartList;
import kr.or.bit.member.dto.MemberDto;

public class deleteCartlistService implements Action {

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
		
		String email = loginUser.getEmail();
		
		CartList cartlist = new CartList();
		cartlist.setEmail(email);
		cartlist.setContentId(contentId);
		
		cartlistDAO cartdao = new cartlistDAO();
		int result = cartdao.deleteCartlist(cartlist);
		
		
		
		if(result > 0) {
			out.print("찜목록에서 삭제되었습니다 😎");
		} else {
			out.print("정상적으로 처리되지 않았습니다 😥");
		}
		
		return null;
	}

}
