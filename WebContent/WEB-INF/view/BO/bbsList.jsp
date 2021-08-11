<%@page import="kr.or.bit.utils.ThePager"%>
<%@page import="kr.or.bit.noticeboard.dto.NoticeBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">

<title>공지사항</title>
<link rel="Stylesheet"
	href="${pageContext.request.contextPath}/style/default.css" />
<!-- <link rel="manifest" href="site.webmanifest"> -->
<link rel="shortcut icon" type="image/x-icon"
	href="assets/img/favicon.png">
<!-- Place favicon.ico in the root directory -->


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
<style>
[class^="ti-"], [class*=" ti-"] {
	line-height: inherit;
}

.noneBorder{
	border-top-width: 0px;
    border-right-width: 0px;
    border-left-width: 0px;
    border-bottom-width: 0px;
}

a:not([href]):not([tabindex]) {
    color: #ebb11 !important;
}

.menuClip{	
	width:80px;
	background: #FAECCC;
    border-radius: 5px;
    text-align: center;
    padding: 8px;
    font-weight: bold;
}

.hrSpace{
margin: auto; margin-top: 10px; margin-bottom: 30px;

}

a, button {
    color: #ebb11f;
    font-weight: 1000;
}

*{
	font-family: 'Nanum Gothic', sans-serif !important;
}

a:hover {
    color: #FAECCC;
    text-decoration: none;
}



</style>
</head>
<body>
	<!-- Preloader Start -->
	
	<!-- Preloader Start -->
	<c:import url="/include/header.jsp" />
	<div style="padding-top: 50px; text-align: center;">
	<br>
	<br>
		<div class="font-back-tittle">
			<div class="archivment-front">
				<h3 style="color: #EAAF24">공지사항</h3>
			</div>
			<h3 class="archivment-back">공지사항</h3>
		</div>
		<c:set var="pagesize" value="${requestScope.pagesize}" />
		<c:set var="cpage" value="${requestScope.cpage}" />
		<c:set var="pagecount" value="${requestScope.pagecount}" />
		<c:set var="list" value="${requestScope.list}" />
		<c:set var="totalboardcount" value="${requestScope.totalboardcount}" />
		<c:set var="pager" value="${requestScope.pager}" />

		<div id="pagecontainer">
			<div style="padding-top: 0px; text-align: cetner;" align="center">
			<br>
					<br>
					<hr width="80%" align="center" class="hrSpace">
					<br>
					<br>
				<table  class="noneBorder" width="80%" cellspacing="0" align="center" >
					<tr>
						<td  colspan="5" style="padding-bottom: 20px;">
							<form name="list">
								<select name="ps" onchange="submit()">
									<c:forEach var="i" begin="5" end="20" step="5">
										<c:choose>
											<c:when test="${pagesize == i}">
												<option value="${i}" selected>${i}건보기</option>
											</c:when>
											<c:otherwise>
												<option value="${i}">${i}건보기</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</form>
						</td>
					</tr>
					<!-- 데이터가 한건도 없는 경우  -->
					<!-- forEach()  목록 출력하기  -->
					<c:forEach var="board" items="${list}">
					<tr height="50px">
							<td style="color: gray;" width="20%" colspan="2" align="center">${board.n_NUMBER}번째 공지사항</td>
							<td width="60%"align="center" style="font-size: large;">
								<a style="color: #EAAF24"
 								href="boardContent.bo?n_NUMBER=${board.n_NUMBER}&cp=${cpage}&ps=${pagesize}">
									<c:choose>
										<c:when
											test="${board.n_TITLE != null && fn:length(board.n_TITLE) > 30}">
										${fn:substring(board.n_TITLE,0,30)}...
									</c:when>
										<c:otherwise>
										${board.n_TITLE}
									</c:otherwise>
									</c:choose>
							</a>
							</td>
							<td align="center" style="color: gray">${board.n_WRITEDATE}</td>
						</tr>
					</c:forEach>
					<!-- forEach()  -->
					<tr> 
						<td colspan="5" align="center">
							<hr width="100%">
						</td>
					</tr>
					<tr>
						 <!--이전 링크 -->
					<td colspan="5" align="center">
						<a class="menuClip" onclick="isPrv()" style="color: #EAAF24">이전</a>
					<!--다음 링크 -->
						<a class="menuClip" onclick="isNext()" style="color: #EAAF24">다음</a>
					</td>
					</tr>
					<tr>
				</table>
				<div style="padding-top: 50px"></div>
				<input class="btn admin" type="button" value="글쓰기"
					onclick="location.href = 'boardWrite.bo'" />
			</div>
		</div>
	</div>
	<div style="padding-top: 50px"></div>
	<footer> <jsp:include page="/include/footer.jsp"></jsp:include>
	<!-- Footer End--> </footer>
	<!-- JS here -->

	<!-- All JS Custom Plugins Link Here here -->
	<script src="./assets/js/vendor/modernizr-3.5.0.min.js"></script>

	<!-- Jquery, Popper, Bootstrap -->
	<script src="./assets/js/vendor/jquery-1.12.4.min.js"></script>
	<script src="./assets/js/popper.min.js"></script>
	<script src="./assets/js/bootstrap.min.js"></script>
	<!-- Jquery Mobile Menu -->
	<script src="./assets/js/jquery.slicknav.min.js"></script>

	<!-- Jquery Slick , Owl-Carousel Plugins -->
	<script src="./assets/js/owl.carousel.min.js"></script>
	<script src="./assets/js/slick.min.js"></script>
	<!-- Date Picker -->
	<script src="./assets/js/gijgo.min.js"></script>
	<!-- One Page, Animated-HeadLin -->
	<script src="./assets/js/wow.min.js"></script>
	<script src="./assets/js/animated.headline.js"></script>
	<script src="./assets/js/jquery.magnific-popup.js"></script>

	<!-- Scrollup, nice-select, sticky -->
	<script src="./assets/js/jquery.scrollUp.min.js"></script>
	<script src="./assets/js/jquery.nice-select.min.js"></script>
	<script src="./assets/js/jquery.sticky.js"></script>

	<!-- contact js -->
	<script src="./assets/js/contact.js"></script>
	<script src="./assets/js/jquery.form.js"></script>
	<script src="./assets/js/jquery.validate.min.js"></script>
	<script src="./assets/js/mail-script.js"></script>
	<script src="./assets/js/jquery.ajaxchimp.min.js"></script>

	<!-- Jquery Plugins, main Jquery -->
	<script src="./assets/js/plugins.js"></script>
	<script src="./assets/js/main.js"></script>
	<script type="text/javascript">
	
	var nowEmail= "<%=session.getAttribute("email")%>";
	
	$('.admin').css("display","none");
	
	if('<%=session.getAttribute("email")%>' != 'admin@naver.com'){
		} else {
			$('.admin').css("display","");
		}
	
	function isNext(){
		if("${cpage}"=="${pagecount}"){
			alert("마지막 페이지입니다.");
		} else{
			location.href = "boardList.bo?cp=${cpage+1}&ps=${pagesize}"			
		}
	}
	
	function isPrv(){
		if("${cpage}"==1){
			alert("첫 페이지입니다.");
		} else{
			location.href = "boardList.bo?cp=${cpage-1}&ps=${pagesize}"
		}
	}
	
	</script>
</body>
</html>