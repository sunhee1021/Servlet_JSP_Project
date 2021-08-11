<%@page import="kr.or.bit.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="zxx">

<head>
	<meta charset="utf-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<title>Barber</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- <link rel="manifest" href="site.webmanifest"> -->
	<link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
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
   
   <!-- 폰트 -->
   <link rel="preconnect" href="https://fonts.gstatic.com">
   <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
   <!-- 맵API -->
   <script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ff92759dc421a0bf0b08eca76214da7f"></script>
   <!-- sweetalert -->
   <script src="http://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
   <!-- TourList CSS 가져오기 -->
   <link rel="stylesheet" href="css/TourList.css">
   <link rel="stylesheet" href="css/qna.css">
   
   
</head>
<body>
    <jsp:include page="/include/header.jsp" />
    <main>
    	<div class="font-back-tittle mb-45">
			<div class="archivment-front">
				<h3>Q&A 글 비밀번호 입력</h3>
			</div>
			<h3 class="archivment-back">Q&A 글 비밀번호 입력</h3>
		</div>
    	
		<!-- Q&A 글 등록 -->
		<div id="container" class="container box_1170">
				<!-- 비밀번호 확인 시작 { -->
				<div id="pw_confirm" class="mbskin">
				    <h1>답변이 없다면 접수완료로만 표시됩니다</h1>
				    <p>
				    	<strong>비밀글 기능으로 보호된 글입니다.</strong><span><br></span>
				        작성자와 관리자만 열람하실 수 있습니다. <span><br></span>
				        본인이라면 비밀번호를 입력하세요.
		            </p>
				
				    <form name="fboardpassword" action="qnaContent.qna" method="post">
				    <input type="hidden" name="qnaNumber" value="${param.qnaNumber}">
				    <input type="hidden" name="page" value="${param.page}">
				    
				
				    <fieldset>
				        <label for="password_wr_password" class="sound_only">비밀번호<strong>필수</strong></label>
				        <input type="password" name="password" id="password_wr_password" required class="frm_input required" size="15" maxLength="4" placeholder="비밀번호">
				        <input type="submit" value="확인" class="btn_submit">
				    </fieldset>
				    </form>
				
				</div>
				<!-- } 비밀번호 확인 끝 -->
		</div>	
    </main>
    
    
	<div class="dining-area dining-padding-top"></div>
	
	<footer>
		<jsp:include page="/include/footer.jsp"></jsp:include>
		<!-- Footer End-->
	</footer>  
	
</body>

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
        
</html>