<%@page import="kr.or.bit.member.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.bit.communityboard.dto.CommunityBoard"%>
<%@page import="kr.or.bit.communityboard.service.CommunityBoardService"%>
<%@page import="kr.or.bit.communityboard.dto.CommunityReplyBoard"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board_content</title>
<link rel="icon" type="image/png" href="http://example.com/myicon.png">
	<link rel="manifest" href="site.webmanifest">
	<link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">

	<!-- CSS here -->
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/owl.carousel.min.css">
	<link rel="stylesheet" href="assets/css/gijgo.css">
	<link rel="stylesheet" href="assets/css/slicknav.css">
	<link rel="stylesheet" href="assets/css/animate.min.css">
	<link rel="stylesheet" href="assets/css/magnific-popup.css">
	<link rel="stylesheet" href="assets/css/fontawesome-all.min.css">
	<link rel="stylesheet" href="assets/css/themify-icons.css">
	<link rel="stylesheet" href="assets/css/slick.css">
	<link rel="stylesheet" href="assets/css/nice-select.css">
	<link rel="stylesheet" href="assets/css/style.css">
	<link rel="stylesheet" href="assets/css/responsive.css">
	<link rel="stylesheet" href="css/bopage.css">
</head>
<body>


	<%
		String c_number = request.getParameter("c_number"); //글번호 받기
		
		System.out.println(request.getParameter("c_number"));
		System.out.println(request.getParameter("cp"));
		System.out.println(request.getParameter("ps"));
		request.getParameter("c_number");
		
		//글 번호를 가지고 오지  않았을 경우 예외처리
		if(c_number == null || c_number.trim().equals("")){
			response.sendRedirect("c_number_not.ha");
			return; //더 이상 아래 코드가 실행되지 않고 클라이언트에게 바로 코드 전달
		}
		
		c_number = c_number.trim();
		String cpage = request.getParameter("cp"); //current page
		String pagesize = request.getParameter("ps"); //pagesize
		
		//List 페이지 처음 호출 ...
		if(cpage == null || cpage.trim().equals("")){
			//default 값 설정
			cpage = "1"; 
		}
	
		if(pagesize == null || pagesize.trim().equals("")){
			//default 값 설정
			pagesize = "5"; 
		}
		
		//상세보기 내용
		CommunityBoardService service = CommunityBoardService.getInBoardService();
		
		//옵션
		//조회수 증가
		boolean isread = service.addReadNum(c_number);
		if(isread)System.out.println("조회증가 : " + isread);
		
		
		//데이터 조회 (1건 (row))
		CommunityBoard board = service.content(Integer.parseInt(c_number));
	
	%>
	<%
		pageContext.include("/include/header.jsp");
	%>
	<div id="pageContainer">
		<div style="padding-top: 30px; text-align: center">
			<center>
				<b>게시판 글내용</b>
				<table width="80%" border="1">
					<tr>
						<td width="20%" align="center"><b> 글번호 </b></td>
						<td width="30%"><%=c_number%></td>
					</tr>
					<tr>
						<td width="20%" align="center"><b>글쓴이</b></td>
						<td width="30%"><%=board.getEmail()%></td>
						<td width="20%" align="center"><b>조회수</b></td>
						<td><%=board.getcViewcount()%></td>
					</tr>
					<tr>
						<td width="20%" align="center"><b>제목</b></td>
						<td colspan="3"><%=board.getcTitle()%></td>
					</tr>
					<tr height="100">
						<td width="20%" align="center"><b>글내용</b></td>
						<td colspan="3">
							<%
							String content = board.getcContent();
								if(content != null){
									content = content.replace("\n", "<br>");
								}
								out.print(content);
							%>

						</td>
					</tr>
					<tr>
						<td colspan="4" align="center"><a
							href="c_content_golist.ha?cp=<%=cpage%>&ps=<%=pagesize%>">목록가기</a> |<a
							href="c_content_edit.ha?c_number=<%=c_number%>&cp=<%=cpage%>&ps=<%=pagesize%>">편집</a>
							<c:set var="user" value='<%=(MemberDto)request.getSession().getAttribute("user") %>' />
							<c:set var="email" value="<%=board.getEmail() %>"/>
							
							<c:if test="${(not empty user  && user.email eq email) || user.division eq 0}">
							| <a href="c_content_delete.ha?c_number=<%=c_number%>&cp=<%=cpage%>&ps=<%=pagesize%>">삭제</a>	
							</c:if>
							
							
							
						</td>
					</tr>
				</table>
			</center>
		</div>
	</div>
	<div id="emptyspace" style="margin-bottom: 180px;"></div>
	<%
		pageContext.include("/include/footer.jsp");
	%>
</body>
</html>





