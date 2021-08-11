<%@page import="kr.or.bit.communityboard.utils.ThePager"%>
<%@page import="kr.or.bit.communityboard.dto.CommunityBoard"%>
<%@page import="kr.or.bit.communityboard.service.CommunityBoardService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 목록페이지</title>
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
	<!-- 헤더부분 추가하기-->
	<c:import url="/include/header.jsp" />
	<br><br><br><br>
	<div class="font-back-tittle mb-50">
		<div class="archivment-front">
			<h1>⌨ Ha-Board</h1>
		</div>
		<h3 class="archivment-back">Ha-Board</h3>
	</div>
	
	<br>
	<%
		CommunityBoardService service = CommunityBoardService.getInBoardService();
		//CommunityBoardService service = CommunityBoardService.getInBoardService(); // 보더서비스에서 가져오겠다
		
		//게시물 총 건수
		int totalboardcount = service.totalBoardCount(); // 서비스단에서 총 게시물 수를 가져오겠다
		
		//상세보기 >> 다시  LIST 넘어올때  >> 현재 페이지 설정
		String ps = request.getParameter("ps"); //pagesize
		String cp = request.getParameter("cp"); //current page
		
		//List 페이지 처음 호출 ...
		if(ps == null || ps.trim().equals("")){
			//default 값 설정
			ps = "5"; //5개씩 
		}
	
		if(cp == null || cp.trim().equals("")){
			//default 값 설정
			cp = "1"; // 1번째 페이지 보겠다 
		}
		
		int pagesize = Integer.parseInt(ps);
		int cpage = Integer.parseInt(cp);
		int pagecount=0;
		
		if(totalboardcount % pagesize == 0){
			pagecount = totalboardcount / pagesize; //  20 << 100/5
		}else{
			pagecount = (totalboardcount / pagesize) + 1; 
		}
		
		//102건 : pagesize=5 >> pagecount=21페이지
		
		//전체 목록 가져오기
		List<CommunityBoard> list = service.list(cpage, pagesize); //list >> 1 , 20
		
    %>
	<c:set var="pagesize" value="<%=pagesize%>" />
	<c:set var="cpage" value="<%=cpage%>" />
	<c:set var="pagecount" value="<%=pagecount%>" />
	
	<div id="pagecontainer">
		<div style="padding-top: 30px;">
			<table width="80%" border="1" cellspacing="0" align="center">
				<tbody class="text-center">
				<tr class="text-left">
					<td colspan="5">
						<form name="list" >
						 페이지당 나타낼 게시글 수 : 
							<select name="ps" onchange="submit()">
							   <c:forEach var="i" begin="5" end="20" step="5">
							   		<c:choose>
							   			<c:when test="${pagesize == i}">
							   				<option value="${i}" selected>${i}건</option>
							   			</c:when>
						   				<c:otherwise>
						   					<option value="${i}">${i}건 </option>
						   				</c:otherwise>
							   		</c:choose>
							   </c:forEach>
		   					</select>
						</form>
					</td>
				</tr>
				<tr>
					<th width="10%">글번호</th>
					<th width="40%">제목</th>
					<th width="20%">글쓴이</th>
					<th width="20%">날짜</th>
					<th width="10%">조회수</th>
				</tr>
				<!-- 데이터가 한건도 없는 경우  -->
				<%
		     		if(list == null || list.size() == 0){
		     			out.print("<tr><td colspan='5'>등록된 글이 없습니다.</td></tr>");
		     		}
		        %>
				<!-- forEach()  목록 출력하기  -->
				<c:forEach var="board" items="<%=list%>">
					<tr onmouseover="this.style.backgroundColor='#EAAF24'" onmouseout="this.style.backgroundColor='white'">
						<td align="center">${board.cNumber}</td>
						<td align="left">
						
							<a href="board_content.ha?c_number=${board.cNumber}&cp=${cpage}&ps=${pagesize}">
								<c:choose> 
									<c:when test="${board.cTitle != null && fn:length(board.cTitle) > 10}">
										${fn:substring(board.cTitle,0,10)}...
									</c:when>
									<c:otherwise>
										${board.cTitle}
									</c:otherwise>
								</c:choose>
							</a>
						</td>
						<td align="center">${board.email}</td>
						<td align="center">${board.cWritedate}</td>
						<td align="center">${board.cViewcount}</td>
					</tr>
				</c:forEach>
				<!-- forEach()  -->
				<tr>
					<td colspan="5" align="center">
						<a href="boardWrite.ha">[글쓰기]</a>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<!--이전 링크 --> 
						<c:if test="${cpage > 1}">
							<a href="board_list_before.ha?cp=${cpage-1}&ps=${pagesize}">이전</a>
						</c:if>
						<!-- page 목록 나열하기 -->
						<c:forEach var="i" begin="1" end="${pagecount}" step="1">
							<c:choose>
								<c:when test="${cpage==i}">
										<font color="#EAAF24" >[${i}]</font>
								</c:when>
								<c:otherwise>
									<a href="board_list_next.ha?cp=${i}&ps=${pagesize}">[${i}]</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<!--다음 링크 --> 
						<c:if test="${cpage < pagecount}">
							<a href="board_list_next.ha?cp=${cpage+1}&ps=${pagesize}">다음</a>
						</c:if>
					</td>
					<td colspan="2" align="center">총 게시물 수 : <%= totalboardcount %>
					</td>
				</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div id="emptyspace" style="margin-bottom: 180px;"></div>
	<%
		pageContext.include("/include/footer.jsp");
	%>
</body>
</html>





