package kr.or.bit.qnaboard.ajax;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.apache.tomcat.util.json.JSONParser;

import kr.or.bit.action.ActionForward;
import kr.or.bit.qnaboard.dao.QnaBoardDao;
import kr.or.bit.qnaboard.dto.QnaBoard;
import kr.or.bit.utils.StringUtils;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class QnaAjaxController
 */
@WebServlet("*.ajax")
public class QnaAjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaAjaxController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String url = request.getRequestURI().substring(request.getContextPath().length());
    	
    	
    	
    	if(url.equals("/ajaxGetContent.ajax")) {
    		
    		QnaBoardDao dao = new QnaBoardDao();
    		int currentPage = Integer.parseInt(request.getParameter("currentPage").trim());
    		int pageSize = Integer.parseInt(request.getParameter("pageSize").trim());
    		
    		String[] arr = request.getParameter("searchConditionKey").split("\\+");
    		
    		Map<String, String> searchCondition = new HashedMap();
    		
    		for(String str : arr) {
    			searchCondition.put(str.trim(), request.getParameter("searchConditionValue"));
    		}
    		
    		
    		int start = (currentPage - 1) * pageSize + 1;
    		int end = currentPage * pageSize;
    		List<QnaBoard> list = dao.searchListQnaBoard(start, end, searchCondition);
    		
    		JSONObject json = new JSONObject();
    		json.put("total", "" + dao.getTotal(searchCondition));
    		json.put("list", new StringUtils().listParseToJsonArray(list, new QnaBoard()));
    		
    		response.setCharacterEncoding("UTF-8");
    		response.getWriter().print(json);
    		
    	}
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
