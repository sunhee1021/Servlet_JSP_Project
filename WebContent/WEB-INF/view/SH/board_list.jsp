<%@page import="kr.or.bit.utils.ThePager"%>
<%@page import="kr.or.bit.sunboard.dto.SunBoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

  
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
   <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
   
   <!-- 부트스트랩 start -->
	<link rel="apple-touch-icon" href="apple-icon.png">
	<link rel="shortcut icon" href="favicon.ico">
	
	<link rel="stylesheet"
	   href="vendors/bootstrap/dist/css/bootstrap.min.css">
	<link rel="stylesheet"
	   href="vendors/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet"
	   href="vendors/themify-icons/css/themify-icons.css">
	<link rel="stylesheet"
	   href="vendors/flag-icon-css/css/flag-icon.min.css">
	<link rel="stylesheet" href="vendors/selectFX/css/cs-skin-elastic.css">
	<link rel="stylesheet" href="vendors/jqvmap/dist/jqvmap.min.css">
	
	<link rel="stylesheet"
		href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">


<link rel="stylesheet" href="assets/css/style.css">

<link
   href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800'
   rel='stylesheet' type='text/css'>
   
   <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
   
<!-- 부트스트랩 end  -->
   <style type="text/css">
	
	*{
	 font-family: 'Nanum Gothic', sans-serif;
	}
	
	.note-editable{
	height:300px;
	}
	
	.btn-primary #writeGo{
		color : #F2CF7C;
	}
	tr>th {
	   text-align: center;
	}
	body{
	padding : 3px;
	}
	
	#tbody{
	background-color:white;
	}
	
	#tbody tr{
	onmouseover:backgroundColor=gray;
	onmouseout:this.style.backgroundColor=white;
	}
	.col-11{
		margin-left : 60px;
	}
	
	

	</style>

	<%-- <link rel="Stylesheet" href="${pageContext.request.contextPath}/css/default.css" /> --%>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">

   <!-- include summernote css/js -->
   <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
   <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
   
   <script>
    $(document).ready(function() {
        $('#summernote').summernote();
    });
     </script>
	
	<style type="text/css">
	*{
	font-family: 'Nanum Gothic' !important;
	 }
	</style>
</head>
<body>
    <jsp:include page="/include/header.jsp"></jsp:include>
    
    <hr style="color : gray;">
	
	<c:set var="pagesize" value="${requestScope.pagesize}" />
	<c:set var="cpage" value="${requestScope.cpage}" />
	<c:set var="pagecount" value="${requestScope.pagecount}" />
	<c:set var="list" value="${requestScope.list}" />
	<c:set var="totalboardcount" value="${requestScope.totalboardcount}" />
	<c:set var="pager" value="${requestScope.pager}" />
	
	 <div class="main-panel">
     <div class="content-wrapper">
     <div class="row ">
     <div class="col-11">
	<div id="pagecontainer">
		<div style="padding-top: 30px; text-align: cetner">
		<br>
			<br>
			<br>
			<div class="font-back-tittle mb-10 ">
				<div class="archivment-front">
					<h3 style="color: #EAAF24; font-size: 50px;">선희 커뮤니티</h3>
				</div>
				<h3 class="archivment-back">선희 커뮤니티</h3>
			</div>
			<br>
					<br>
					<hr width="80%" align="center" class="hrSpace">
					<br>
					<br>
			<table width="80%" border="1" cellspacing="0" align="center">
				<tr>
					
						<!--  
							form 태그 action 전송 주소(목적지) >> submit()
							>> form name="list" ... action 없다면.. 
							>> [현재 URL 창에 있는 주소] 그대로  .....   
							>> board_list.jsp?ps=select 태그 값으로 .... 다시 호출 .....
							>>http://192.168.0.169:8090/WebServlet_5_Board_Model1_Sample/board/board_list.jsp?ps=10					
						-->
						 <div class="form-group d-flex align-items-center">
						 
                    	 <div class="col-sm-2" style="padding-left: 0">
						<c:set var="list" value="${requestScope.list}"></c:set>
						<form name="list">
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
						</div>
						</div>
				</tr><div class="main-panel">
				<div class="content-wrapper">
					<div class="row ">
						<div class="col-12">
							<table id="order-listing" class="table text-center">
								<thead>
								<tr class="text-center">
					<th width="10%">순번</th>
					<th width="40%">제목</th>
					<th width="20%">글쓴이</th>
					<th width="20%">날짜</th>
					<th width="10%">조회수</th>
				</tr>
								</thead>
								<tbody>

				<!-- 데이터가 한건도 없는 경우  -->

				<!-- forEach()  목록 출력하기  -->
				
				<c:forEach var="board" items="${list}">
					<tr onmouseover="this.style.backgroundColor='gray'" onmouseout="this.style.backgroundColor='white'">
						<td align="center">${board.c_number}</td>
						<td align="left">
							<c:forEach var="i" begin="1" end="${board.depth}" step="1">
								&nbsp;&nbsp;&nbsp;
							</c:forEach>
							<c:if test="${board.depth > 0}">
								<img src="${pageContext.request.contextPath}/image/re.gif">
							</c:if>
							<a href="boardContent.sun?c_number=${board.c_number}&cp=${cpage}&ps=${pagesize}">
								<c:choose>
									<c:when test="${board.title != null && fn:length(board.title) > 20}">
										${fn:substring(board.title,0,20)}...
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
					<td colspan="5" align="center">
						<hr width="100%" color="red">
					</td>
				</tr>
					<td colspan="5" align="center">
					${pager}
					</td>
					</tbody>
					
						<tr border="none" class="loginMember" >
						<td colspan="5" align="right" >
						<input type="button" class="btn btn-warning" value="글쓰기" onclick="location.href='boardWrite.sun'">
						</td>
						</tr>
			
			</table>
		</div>
		</div>
		</div>
		</div>
		</div>
		</table>
		</div>
		</div>
		</div>
		</div>
		</div>
		
	</div>
	<jsp:include page="/include/footer.jsp"></jsp:include>
	<script type="text/javascript">
	$('.loginMember').css("display","none");
	
	if('<%=session.getAttribute("email")%>' == ''){
	} else {
		$('.loginMember').css("display","");
	}
	</script>
</body>
</html>