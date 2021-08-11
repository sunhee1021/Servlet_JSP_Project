<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">
<title>회사소개</title>
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

<style type="text/css">

 * {
 	font-family: 'Nanum Gothic', sans-serif;
 }

.head_area {
	width: 100%;
	height: 300px;
	background: #587D4E;
}

.font-back-tittle .archivment-front h3 {
	color: white;
}

.introImg {
	margin: 0 auto;
	width: 70%;
}

/* .content-title {
    font-size: 40px;
    color: #363636;
    font-weight: bold;
} */

.content-title-text {
	font-size: 30px;
}

.ainboard-margin {
 	width: 100%;
 	height: 100px;
 }
 
</style>

</head>
<body>
	<c:import url="/include/header.jsp" />
	<div class="head_area">
		<div class="font-back-tittle mb-50">
			<div class="archivment-front">
				<h3 style="padding-top: 120px;">JejuYeohangPlanner</h3>
			</div>
			<h3 class="archivment-back">JejuYeohangPlanner</h3>
		</div>
	</div>
	
	<div class="ainboard-margin"></div>

	<div id="pageContainer">
		<div class="content-title" align="center">
			<hr>
			<span class="content-title-text">  회사 소개  </span>
			<hr>
		</div>
		<div style="padding-top: 25px; text-align: center; margin-bottom: 50px;">
			<div class="introImg">
				<img
					src="${pageContext.request.contextPath}/image/ain/introImage.png"
					alt="My Image" width="100%" />
			</div>
		</div>
		
		<div class="content-title" align="center">
			<hr>
				<span class="content-title-text">  팀원 소개  </span>
			<hr>
		</div>
		
		<!-- Testimonial Start -->
        <div class="testimonial-area t-padding">
            <div class="container">
               <div class="row justify-content-center">
                    <div class="col-xl-9 col-lg-9 col-md-9">
                        <div class="h1-testimonial-active">
                            <!-- Bo Single Testimonial -->
                            <div class="single-testimonial pt-65">
                                <!-- Testimonial tittle -->
                                <div class="font-back-tittle mb-105">
                                    <div class="archivment-front">
                                        <img src="${pageContext.request.contextPath}/image/ain/bo.png" alt="" width="100px">
                                    </div>
                                    <h3 class="archivment-back">BaekBoSung</h3>
                                </div>
                                 <!-- Testimonial Content -->
                                <div class="testimonial-caption text-center">
                                    <p>Be a Developer beyond a Programmer.
                                    </p>
                                    <!-- Rattion -->
                                    <div class="testimonial-ratting">
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                    </div>
                                </div>
                            </div>
                            <!-- Ain Single Testimonial -->
                            <div class="single-testimonial pt-65">
                                <!-- Testimonial tittle -->
                                <div class="font-back-tittle mb-105">
                                    <div class="archivment-front">
                                        <img src="${pageContext.request.contextPath}/image/ain/ain.png" alt="" width="100px">
                                    </div>
                                    <h3 class="archivment-back">J.Ain</h3>
                                </div>
                                 <!-- Testimonial Content -->
                                <div class="testimonial-caption text-center">
                                    <p>Success seems to be connected with action. Successful people keep moving.
                                    </p>
                                    <!-- Rattion -->
                                    <div class="testimonial-ratting">
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                    </div>
                                </div>
                            </div>
                            <!-- Moon Single Testimonial -->
                            <div class="single-testimonial pt-65">
                                <!-- Testimonial tittle -->
                                <div class="font-back-tittle mb-105">
                                    <div class="archivment-front">
                                        <img src="${pageContext.request.contextPath}/image/ain/moon.png" alt="" width="100px">
                                    </div>
                                    <h3 class="archivment-back">Moon.HC</h3>
                                </div>
                                 <!-- Testimonial Content -->
                                <div class="testimonial-caption text-center">
                                    <p>Pardon all but yourself.
                                    </p>
                                    <!-- Rattion -->
                                    <div class="testimonial-ratting">
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                    </div>
                                </div>
                            </div>
                            <!-- Ha Testimonial -->
                            <div class="single-testimonial pt-65">
                                <!-- Testimonial tittle -->
                                <div class="font-back-tittle mb-105">
                                    <div class="archivment-front">
                                        <img src="${pageContext.request.contextPath}/image/ain/ha.png" alt="" width="100px">
                                    </div>
                                    <h3 class="archivment-back">HaJoonSoo</h3>
                                </div>
                                 <!-- Testimonial Content -->
                                <div class="testimonial-caption text-center">
                                    <p>success doesn't come overnight
                                    </p>
                                    <!-- Rattion -->
                                    <div class="testimonial-ratting">
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                    </div>
                                </div>
                            </div>
                            <!-- Sun Testimonial -->
                            <div class="single-testimonial pt-65">
                                <!-- Testimonial tittle -->
                                <div class="font-back-tittle mb-105">
                                    <div class="archivment-front">
                                        <img src="${pageContext.request.contextPath}/image/ain/sun.png" alt="" width="100px">
                                    </div>
                                    <h3 class="archivment-back">P.SunHee</h3>
                                </div>
                                 <!-- Testimonial Content -->
                                <div class="testimonial-caption text-center">
                                    <p>Never compare myself to other people. It's comparing my behind the scenes to their highlight real.
                                    </p>
                                    <!-- Rattion -->
                                    <div class="testimonial-ratting">
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                    </div>
                                </div>
                            </div>
                            <!-- Single Testimonial -->
                        </div>
                    </div>
               </div>
            </div>
        </div>
        <!-- Testimonial End -->
		
		<div class="content-title" align="center">
			<hr>
			<span class="content-title-text">  위치  </span>
			<hr>
		</div>
		<div style="padding-top: 25px; text-align: center; margin-bottom: 50px;">
			<div class="introImg">
				<img
					src="${pageContext.request.contextPath}/image/ain/MapImage.png"
					alt="My Image" width="100%" />
			</div>
		</div>
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
		
		<!-- Scrollup, nice-select, sticky -->
        <script src="./assets/js/jquery.scrollUp.min.js"></script>
        <script src="./assets/js/jquery.nice-select.min.js"></script>
		<script src="./assets/js/jquery.sticky.js"></script>
        <script src="./assets/js/jquery.magnific-popup.js"></script>
        
        <!-- contact js -->
        <script src="./assets/js/contact.js"></script>
        <script src="./assets/js/jquery.form.js"></script>
        <script src="./assets/js/jquery.validate.min.js"></script>
        <script src="./assets/js/mail-script.js"></script>
        <script src="./assets/js/jquery.ajaxchimp.min.js"></script>
        
		<!-- Jquery Plugins, main Jquery -->	
        <script src="./assets/js/plugins.js"></script>
        <script src="./assets/js/main.js"></script>
        
</body>
<!-- include libraries(jQuery, bootstrap) -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</html>