package kr.or.bit.ainboard.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.ainboard.dao.AinBoardDao;
import kr.or.bit.ainboard.utils.ThePager;
import kr.or.bit.member.dto.MemberDto;

public class AdminMainService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		AinBoardDao dao = new AinBoardDao();
		
		String ps = request.getParameter("ps"); //pagesize
		String cp = request.getParameter("cp");
		
		
		if (cp == null || cp.trim().equals("")) {
			cp = "1";
		}
		if (ps == null || ps.trim().equals("")) {
			ps = "10";
		}
		
		int pagesize = Integer.parseInt(ps);
		int cpage = Integer.parseInt(cp);
		int pagecount = 0;
		
		//전체 회원 수
		int totalcount = dao.totalMemberCount();
		
		if (totalcount % pagesize == 0) { 
			pagecount = totalcount / pagesize;
		} else {
			pagecount = (totalcount / pagesize) + 1;
		}
		
		//전체 회원 목록
		List<MemberDto> memberList = dao.memberList(cpage, pagesize);
		
		//페이징처리
		int pagersize = 5;
		ThePager pager = new ThePager(totalcount,cpage,pagesize,pagersize,"admin.ain");
		
		request.setAttribute("memberList", memberList);
		request.setAttribute("pagesize", pagesize);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("cpage", cpage);
		request.setAttribute("pager", pager);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/WEB-INF/view/AIN/adminMain.jsp");

		return forward;
	}

}
