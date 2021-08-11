package kr.or.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.noticeboard.service.BoardAddService;
import kr.or.bit.noticeboard.service.BoardContentService;
import kr.or.bit.noticeboard.service.BoardDeleteOk;
import kr.or.bit.noticeboard.service.BoardEditOkService;
import kr.or.bit.noticeboard.service.BoardEditService;
import kr.or.bit.noticeboard.service.BoardListService;
import kr.or.bit.noticeboard.service.BoardShowChartService;
import kr.or.bit.noticeboard.service.BoardWriteService;

@WebServlet("*.bo")
public class boController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public boController() {
        super();
    }

private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	
    	String url = request.getRequestURI().substring(request.getContextPath().length());
    	
    	System.out.println(url);

		ActionForward forward = null;
		Action action=null;
		
		if (url.equals("/boardWrite.bo")) {
			forward = new BoardWriteService().execute(request, response);
			forward.setRedirect(false); 
			forward.setPath("WEB-INF/view/BO/bbsWrite.jsp");
		} else if (url.equals("/boardList.bo")) {
			action = new BoardListService();
    		forward = action.execute(request, response);
		} else if (url.equals("/boardWriteOk.bo")) {
			action = new BoardAddService();
    		forward = action.execute(request, response);
		} else if (url.equals("/boardContent.bo")) {
			action = new BoardContentService();
    		forward = action.execute(request, response);
		} else if(url.equals("/boardDelete.bo")) {
			action = new BoardDeleteOk();
    		forward = action.execute(request, response);
		}else if(url.equals("/boardEdit.bo")) {
			action = new BoardEditService();
    		forward = action.execute(request, response);
		}else if(url.equals("/boardEditOk.bo")) {
			action = new BoardEditOkService();
    		forward = action.execute(request, response);
		}else if(url.equals("/chart.bo")) {
				action = new BoardShowChartService();
				forward = action.execute(request, response);
				forward.setRedirect(false); 
		} else if(url.equals("/main.bo")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("WEB-INF/view/BO/bopage.jsp");
		}	
		
		
		if (forward != null) {
			if (!forward.isRedirect()) {
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
}
    	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
