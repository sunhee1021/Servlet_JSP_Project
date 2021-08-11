<%@page import="kr.or.bit.ainboard.utils.ThePager"%>
<%@page import="kr.or.bit.ainboard.dto.AinBoard"%>

<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">
<title>아인게시판</title>

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
   <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
<%-- <link rel="Stylesheet" href="${pageContext.request.contextPath}/style/default.css" /> --%>

<!-- 폰트 -->
   <link rel="preconnect" href="https://fonts.gstatic.com">
   <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">

<!-- sweet alert -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css" />


<style type="text/css">
 *{
	font-family: 'Nanum Gothic', sans-serif !important;
}
 
 .ainboard-margin {
 	width: 100%;
 	height: 50px;
 }
 
 .font-back-tittle .archivment-front h3 {
 	color: #587D4E;
 }
 
 .a, button {
    color: #587D4E;
    outline: medium none;
}

/* 테이블 */
table.aintable {
    border: 5px solid white;
    border-top: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
}
/* 
tr:nth-child(even) {
	background-color: #FAECCC;
} */


.genric-btn.success {
	background: #EAAF24;
}

</style>

</head>
<body>
	<c:import url="/include/header.jsp" />

	<c:set var="pagesize" value="${requestScope.pagesize}" />
	<c:set var="cpage" value="${requestScope.cpage}" />
	<c:set var="pagecount" value="${requestScope.pagecount}" />
	<c:set var="list" value="${requestScope.list}" />
	<c:set var="totalboardcount" value="${requestScope.totalboardcount}" />
	<c:set var="pager" value="${requestScope.pager}" />

	<div class="ainboard-margin"></div>

	<div class="font-back-tittle mb-50">
		<div class="archivment-front">
			<h3>🍊 Ain-Board 🍊 </h3>
		</div>
		<h3 class="archivment-back">AinBoard</h3>
	</div>

	<div id="pagecontainer" style="margin-bottom: 200px;">
		<div style="padding-top: 30px; text-align: cetner">
			<table width="80%" border="1" cellspacing="0" align="center" class="aintable">
				<tr>
					<td colspan="5" style="text-align: right;">
						<form name="list" >
							<select name="ps" onchange="submit()">
							   <c:forEach var="i" begin="5" end="20" step="5">
							   		<c:choose>
							   			<c:when test="${pagesize == i}">
							   				<option value="${i}" selected>${i}개씩</option>
							   			</c:when>
						   				<c:otherwise>
						   					<option value="${i}">${i}개씩</option>
						   				</c:otherwise>
							   		</c:choose>
							   </c:forEach>
		   					</select>
						</form>
					</td>
				</tr>
				<tr style="text-align: center; background: #F2CF7C;">
					<th width="10%">순번</th>
					<th width="40%">제목</th>
					<th width="20%">글쓴이</th>
					<th width="20%">날짜</th>
					<th width="10%">조회수</th>
				</tr>
				<!-- 데이터가 한건도 없는 경우  -->

				<!-- forEach()  목록 출력하기  -->
				<c:forEach var="board" items="${list}">
					<tr onmouseover="this.style.backgroundColor='#FAECCC'" onmouseout="this.style.backgroundColor='white'">
						<td align="center">${board.cNumber}</td>
						<td align="left">
							<c:forEach var="i" begin="1" end="${board.depth}" step="1">
								&nbsp;&nbsp;&nbsp;
							</c:forEach>
							<c:if test="${board.depth > 0}">
								<img src="${pageContext.request.contextPath}/image/ain/re.gif">
							</c:if>
							<a href="boardContent.ain?cNumber=${board.cNumber}&cp=${cpage}&ps=${pagesize}">
								<c:choose>
									<c:when test="${board.title != null && fn:length(board.title) > 15}">
										${fn:substring(board.title,0,15)}...
									</c:when>
									<c:otherwise>
										${board.title}
									</c:otherwise>
								</c:choose>
							</a>
							
						</td>
						<td align="center">${board.nickname}</td>
						<td align="center">${board.writedate}</td>
						<td align="center">${board.viewcount}</td>
					</tr>
				</c:forEach>
				<!-- forEach()  -->
				<tr>
					<c:if test="${sessionScope.email != null}">
					<td colspan="5" style="text-align: left;">
						<a href="boardWrite.ain" class="genric-btn success medium">글쓰기</a>
					</td>
					</c:if>
				</tr>
				<tr>
					<td colspan="5" align="center">
					${pager}
					</td>
				</tr>
				
			</table>
		</div>
	</div>
	
	<div class="ainboard-margin"></div>
	
	<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>