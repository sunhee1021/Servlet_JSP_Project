package kr.or.bit.member.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.member.dao.MemberDao;
import kr.or.bit.member.dto.MemberDto;
public class RegisterOkService implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		MemberDto memberdto = new MemberDto(email, password, nickname);
		System.out.println("여기까지 오나?");
		try {
			MemberDao memberdao = new MemberDao();
			
			int result = memberdao.RegisterMember(memberdto);
			System.out.println("회원가입?????");
			
			String msg = "";
			String url = "";
			
			if(result > 0){
				msg = "반갑습니다 회원님, 로그인을 진행해주세요";
				url = "/JYP_PROJECT/Login.do";
				/*HttpSession session = request.getSession();
				session.setAttribute("email", email);
				 */
			}else {
				msg = "회원가입 실패, 다시 시도해주세요.";
				url = "/JYP_PROJECT/Register.do";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
		} catch (Exception e) {
			// TODO: handle exception
		}
		forward.setPath("/WEB-INF/view/redirect.jsp");
		return forward;
	}
}