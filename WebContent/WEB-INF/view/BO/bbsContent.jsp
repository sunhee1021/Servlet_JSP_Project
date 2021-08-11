<%@page import="java.util.List"%>
<%@page import="kr.or.bit.noticeboard.dto.NoticeBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board_content</title>
<link rel="Stylesheet"
	href="${pageContext.request.contextPath}/style/default.css" />
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
<link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
<style>
.adminPro{
width: 36px;
height: 36px;
}

.noneBorder{
	border-top-width: 0px;
    border-right-width: 0px;
    border-left-width: 0px;
    border-bottom-width: 0px;
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
	<c:set var="board" value="${requestScope.board}" />
	<c:set var="n_NUMBER" value="${requestScope.board.n_NUMBER}" />
	<c:set var="cpage" value="${requestScope.cp}" />
	<c:set var="pagesize" value="${requestScope.ps}" />

	<jsp:include page="/include/header.jsp"></jsp:include>
	<div id="pageContainer">
		<div style="padding-top: 50px; text-align: center">
			<div class="font-back-tittle mb-10 ">
				<div class="archivment-front" style="padding-bottom: 10px">
					<h3 style="color: #EAAF24; font-size: 20px;">공지사항</h3>
					<h3 style="color: black; font-size: 30px;">${board.n_TITLE}</h3>
				</div>
			</div>
			<br>
			<center>
				<table width="80%" class="noneBorder">
					<tr>
					<td colspan="4" width="90%"><b>&nbsp;관리자&nbsp;</b></td>
					</tr>
					<tr>
						<td colspan ="2" width="25%" align="left" style="opacity: 0.6; font-size: small;"><b>&nbsp;${board.n_WRITEDATE}&nbsp;&nbsp;&nbsp;&nbsp;조회 ${board.n_VIEWCOUNT}</b></td>
						<td colspan = "2" width="20%" align="right"><span>
						<a style="opacity: 0.65; margin-right: 10px; font-size:" href="#" onclick="clip(); return false; ">URL 주소복사</a></span></td>
				</table>
			</center>
			<hr width="80%" align="center" class="hrSpace" >

			<center>
				<table width="80%" class="noneBorder">
					<%-- <tr >
						<td width="20%" align="center"><b> 글번호: </b></td>
						<td width="30%">${board.n_NUMBER}</td>
						<td width="20%" align="center"><b>작성일</b></td>
						<td>${board.n_WRITEDATE}</td>
					</tr> --%>
					
					<tr>
						<td colspan="2" width="20%" align="right">
<!-- 						<b>첨부파일&nbsp;&nbsp;&nbsp;</b> -->
<!-- 						<a  -->
<%-- 						href="FileDownloadService.do?file_name=${board.n_FILENAME}" --%>
<%-- 						>${board.n_FILENAME}&nbsp;&nbsp;&nbsp;</a> --%>
						</td>
						
					</tr>
					<tr style="height: 300px;">
						<td colspan="5" align="center" class="defaultImg">
						<img src="upload/${board.n_FILENAME}">						
						${fn:replace(board.n_CONTENT, newLineChar,"<br>")}
						
						</td>
					</tr>
				</table></center>
				<hr width="80%" align="center" class="hrSpace" >
				<%-- <table width="80%" >
				<tr>
						<td><a class=""
							href="boardList.bo?cp=${cpage}&ps=${pagesize}">목록가기</a> 
							</td>
							<td><a style="float: right"
							href="BoardRewrite.do?n_NUMBER=${n_NUMBER}&cp=${cpage}&ps=${pagesize}&n_TITLE=${board.n_TITLE}">글쓰기</a>
						</td>
							<td><a style="float: right"
							href="boardEdit.do?n_NUMBER=${n_NUMBER}&cp=${cpage}&ps=${pagesize}">편집</a>
							</td>
							<td> <a style="float: right"
							href="BoardDelete.do?n_NUMBER=${n_NUMBER}&cp=${cpage}&ps=${pagesize}">삭제</a>
							</td>
							
					</tr>
				</table> --%>
				<table width="80%" align="center">
				<tr align="right" style="opacity: 0.65;">
					<td align="left" width="20%">
					<a class="menuClip" href="boardList.bo?cp=${cpage}&ps=${pagesize}">목록가기</a>
					</td>
					<td class="admin">
					<a class="admin menuClip" href="boardWrite.bo">글쓰기</a>
					<a class="menuClip admin" href="boardEdit.bo?n_NUMBER=${n_NUMBER}">수정하기</a>
					<a class="menuClip admin" href="boardDelete.bo?n_NUMBER=${n_NUMBER}">삭제하기</a>
					</td>
				</tr>
				</table>
				<br>
		</div>
		<div style="padding-bottom: 100px; text-align: center"></div>
	</div>
	<jsp:include page="/include/footer.jsp"></jsp:include>
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
		$('.admin').css("display","none")
	
		if('<%=session.getAttribute("email")%>' != 'admin@naver.com'){
		} else {
			$('.admin').css("display","");
		}
		
		if('${board.n_FILENAME}'==''){
			$(".defaultImg").find('img').attr('src','/JYP_PROJECT/image/defalutImg.png');	
		}
		
		
		console.log("/Users/bosungbaek/Desktop/boco/bitcamp/2nd_team/project-workspace/JYP_PROJECT/WebContent/upload/${board.n_FILENAME}");
	
	function clip(){
		var url = '';
		var textarea = document.createElement("textarea");
		document.body.appendChild(textarea);
		url = window.document.location.href;
		textarea.value = url;
		textarea.select();
		document.execCommand("copy");
		document.body.removeChild(textarea);
		alert("URL이 복사되었습니다.")
	}
	</script>
</body>
</html>