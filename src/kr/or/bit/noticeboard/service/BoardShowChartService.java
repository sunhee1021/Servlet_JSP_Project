package kr.or.bit.noticeboard.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.noticeboard.dao.NoticeDao;
import net.sf.json.JSONArray;

public class BoardShowChartService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		NoticeDao dao;
		// 게시판을 가장 많이 이용한 회원 TOP3
		JSONArray chartList = null;
		// 가장 많이 찜한 관광지 명소 TOP3
		JSONArray chartTourList = null;
		try {
			dao = new NoticeDao();
			chartList = dao.searchTopThree();
			chartTourList = dao.searchTourListTopThree();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("chartList", chartList);
		request.setAttribute("chartTourList", chartTourList);
		System.out.println(chartTourList);
		// 경로
		forward.setPath("WEB-INF/view/BO/bbsChart.jsp");
		
		return forward;
	}

}
