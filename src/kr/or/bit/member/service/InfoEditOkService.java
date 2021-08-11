package kr.or.bit.member.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.member.dao.MemberDao;
import kr.or.bit.member.dto.MemberDto;
public class InfoEditOkService implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		/*
		 * HttpSession session = request.getSession();
			session.setAttribute("ID", "abcd");
		 * - 세션 생성
			HttpSession session = request.getSession();
			session.setAttribute("ID", "abcd");
		   - 세션 정보
			HttpSession session = request.getSession();
			String str = (String) session.getAttribute("ID");
		   - 세션 종료
			session.invalidate();
 */
		String email = (String) request.getSession().getAttribute("email");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		
		int result = 0;
		
		MemberDto memberdto = new MemberDto(email, password, nickname);
		
		System.out.println("회원수정 여기까지 오나?(InfoEditOkService 43번째줄)");
		System.out.println("멤바 : "+ memberdto.toString());
		
		try {
			MemberDao memberdao = new MemberDao();
			
			result = memberdao.editInfoOk(memberdto);
			
			String msg="";
			String url="";
			
			
			if(result > 0) {
				msg="정보수정 완료";
				url="main.bo";
				
			}else {
				msg="정보수정 실패";
				url="/JYP_PROJECT/InfoEdit.do";

			}
			
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			
			
			forward.setPath("/WEB-INF/view/SH/redirect.jsp");
			
		} catch (Exception e) {
			System.out.println("정보수정 에러 : "+ e.getMessage());
		}
		return forward;
	}
}