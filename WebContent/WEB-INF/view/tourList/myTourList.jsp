<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html class="no-js" lang="zxx">

<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

	<meta charset="utf-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<title>Barber</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- <link rel="manifest" href="site.webmanifest"> -->
	<link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">
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
   
   <link rel="stylesheet" href="css/TourList.css">
   
   <!-- sweetalert -->
   <script src="http://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
   
</head>

<style>
	.myTour_name{
		background: #e6e6e6;
		height: 100px;
	}
	
	.mapArea{
		background: #f2f2f2;
		height: 500px;
		
		display: flex;
	}
	
	.map{
		width: 100%;
		height: 100%;
		
		margin: auto;
	}
</style>

<body>
<!-- DB에서 가져온 cid 처리하려고 hidden으로 받아둠 -->
<input id="myTourList" type="hidden" value="<%=request.getAttribute("myTourList") %>">

	<jsp:include page="/include/header.jsp" />
    
	<!-- 요기부터 -->
	<!-- 내가 담은 관광지 (헤더) -->
	<div class="mylist-header-margin"></div>
	<div class="font-back-tittle mb-45">
		<div class="archivment-front">
			<h3>My TourList</h3>
		</div>
			<h3 class="archivment-back">My TourList</h3>
	</div>
	
	<div class="container box_1170 mapArea">
		<div id="map" class="map">
		
		</div>
	</div>
	
	<!-- Room Start -->
	<div class="container box_1170 text-center mt-5">
	    <section class="room-area">
	        <div class="container">
	            
	            <div id="row" class="row"></div>
	            
	        </div>
    </section>
    </div>
    
	<div class="container box_1170 text-center mt-5">
		<a href="javascript:void(0)" id="test" class="genric-btn primary-border radius">모두 보기</a>
	</div>
	
	<!-- 페이지 영역 -->
	<div id="paging" class="text-center"></div>
	
	<!-- 모달 영역 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"></div>
	<div class="dining-area dining-padding-top">
		</div>
	<footer>
		<jsp:include page="/include/footer.jsp"></jsp:include>
		<!-- Footer End-->
	</footer>
	
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ff92759dc421a0bf0b08eca76214da7f"></script>

	<!-- 요기까지 -->
	
	
	
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
        <script src="js/myTourList.js"></script>
        
    	
</body>
</html>