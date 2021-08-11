package kr.or.bit.ainboard.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.ainboard.dao.AinBoardDao;
import kr.or.bit.ainboard.dto.AinBoard;
import kr.or.bit.ainboard.utils.ThePager;

public class AinBoardListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		try {
			AinBoardDao dao = new AinBoardDao();
			
			//게시물 총 건수
			int totalboardcount = dao.totalBoardCount();
			
			//다시 목록으로 넘어올 떄 현재페이지와, 페이지크기 넘겨주기 위해서 설정
			String ps = request.getParameter("ps");
			String cp = request.getParameter("cp");
			
			//List페이지 처음 호출시 기본적으로 페이지크기 5개, 현재페이지 1페이지로 설정
			if(ps==null || ps.trim().equals("")) {
				ps = "10";
			}
			if(cp==null || cp.trim().equals("")) {
				cp = "1";
			}
			
			int pagesize = Integer.parseInt(ps);
			int cpage = Integer.parseInt(cp);
			int pagecount = 0;
			
			//한페이지에 노출되는 게시글 수
			if (totalboardcount % pagesize == 0) {
				pagecount = totalboardcount / pagesize; // 20 << 100/5
			} else {
				pagecount = (totalboardcount / pagesize) + 1;
			}
			
			//전체목록 가져오기
			List<AinBoard> list = dao.list(cpage, pagesize);
			
			System.out.println("list: "+list);
			
			int pagersize = 3;
			ThePager pager = new ThePager(totalboardcount,cpage,pagesize,pagersize,"boardList.ain");
			
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("list", list);
			request.setAttribute("totalboardcount", totalboardcount);
			request.setAttribute("pager", pager);
			
			forward = new ActionForward();
			forward.setRedirect(false); // forward
			forward.setPath("/WEB-INF/view/AIN/ainBoardList.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return forward;
	}

}
