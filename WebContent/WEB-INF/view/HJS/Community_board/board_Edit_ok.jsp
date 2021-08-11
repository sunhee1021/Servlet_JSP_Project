<%@page import="kr.or.bit.communityboard.service.CommunityBoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   
  request.setCharacterEncoding("UTF-8");
  String c_number = request.getParameter("c_number");
  
  if(c_number == null || c_number.trim().equals("")){
	  out.print("<script>");
	  	out.print("alert('글번호 입력 오류')");
	  	out.print("location.href='board_list.ha'");
	  out.print("</script>");
  }
  
  CommunityBoardService service = CommunityBoardService.getInBoardService();
  int result = service.board_Edit(request);
  
	String msg="";
	String url="";
	if(result > 0){
		msg="edit success";
		url="board_list_next.ha";
	}else{
		msg="edit fail";
		url="c_content_edit.ha?c_number="+c_number;
	}
	
	request.setAttribute("board_msg",msg);
	request.setAttribute("board_url",url);

%>
<jsp:forward page="redirect.jsp"></jsp:forward>