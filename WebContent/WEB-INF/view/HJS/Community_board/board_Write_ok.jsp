<%@page import="kr.or.bit.member.dto.MemberDto"%>
<%@page import="kr.or.bit.communityboard.service.CommunityBoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="board" class="kr.or.bit.communityboard.dto.CommunityBoard" scope="page">
	<jsp:setProperty property="*" name="board" />
</jsp:useBean>

<%	

	board.setcTitle(request.getParameter("title"));
	board.setcContent(request.getParameter("content"));
	
	MemberDto user =  (MemberDto)request.getSession().getAttribute("user");
	
	if(user == null) {
		request.setAttribute("board_msg", "로그인 이후에 이용해주세요.");
	    request.setAttribute("board_url", "Login.do");
	    
	} else {
		board.setEmail(user.getEmail());
		CommunityBoardService service = CommunityBoardService.getInBoardService();
		int result = service.writeOk(board);
		
		//list 이동시 현재 pagesize , cpage
		String cpage = request.getParameter("cp"); //current page
		String pagesize = request.getParameter("ps"); //pagesize
		//코드는 필요에 따라서  url ="board_list.jsp?cp=<%=cpage";
		String msg="";
	    String url="";
	    if(result > 0){
	    	msg ="게시글 등록 성공";
	    	url ="boardWrite_yes.ha";
	    	
	    }else{
	    	msg="게시글 등록 실패";
	    	url="boardWrite_no.ha";
	    }
	    
	    request.setAttribute("board_msg",msg);
	    request.setAttribute("board_url", url);	
	}
	
	
	
%> 
<jsp:forward page="redirect.jsp"></jsp:forward>