<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="zxx">
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>JejuYeohangPlanner</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">

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
<link rel="stylesheet" href="assets	/css/themify-icons.css">
<link rel="stylesheet" href="assets/css/slick.css">
<link rel="stylesheet" href="assets/css/nice-select.css">
<link rel="stylesheet" href="assets/css/style.css">
<link rel="stylesheet" href="assets/css/responsive.css">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/bopage.css">

<style>
.sl1{
 background-image: url("image/imgSlider/1.jpeg") !important;
}
.sl2{
 background-image: url("image/imgSlider/2.jpeg") !important;
}
.sl3{
 background-image: url("image/imgSlider/3.jpeg") !important;
}
</style>

</head>

<body>
				
	<!-- Preloader Start -->
	
	<!-- Preloader Start -->

	<header>
		<!-- Header Start -->
		<jsp:include page="/include/header.jsp"></jsp:include>
		<!-- Header End -->
	</header>
	<main>

		<!-- slider Area Start-->
		<div class="slider-area ">
			<!-- Mobile Menu -->
			<!-- 1번 이미지 -->
			<div class="slider-active dot-style">
				<div
					class="single-slider  hero-overly slider-height d-flex align-items-center sl1"
					data-background="image/imgSlider/1.jpeg">
					<div class="container">
						<div class="row justify-content-center text-center">
							<div class="col-xl-9">
								<div class="h1-slider-caption">
									<h1 data-animation="fadeInUp" data-delay=".4s">꿈과 희망이 가득한 제주</h1>
									<h3 data-animation="fadeInDown" data-delay=".4s">Jeju Yeohang Planner와 함께</h3>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 2번 이미지 -->
				<div
					class="single-slider  hero-overly slider-height d-flex align-items-center sl2"
					data-background="image/imgSlider/2.jpeg">
					<div class="container">
						<div class="row justify-content-center text-center">
							<div class="col-xl-9">
								<div class="h1-slider-caption">
									<h1 data-animation="fadeInUp" data-delay=".4s">전통과 역사가 있는 제주</h1>
									<h3 data-animation="fadeInDown" data-delay=".4s">Jeju Yeohang Planner와 함께</h3>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 3번 이미지 -->
				<div
					class="single-slider  hero-overly slider-height d-flex align-items-center sl3"
					data-background="image/imgSlider/3.jpeg">
					<div class="container">
						<div class="row justify-content-center text-center">
							<div class="col-xl-9">
								<div class="h1-slider-caption">
									<h1 data-animation="fadeInUp" data-delay=".4s">자연과 풍경이 아름다운 제주</h1>
									<h3 data-animation="fadeInDown" data-delay=".4s">Jeju Yeohang Planner와 함께</h3>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 날씨 시작 !!!!!!!-->
		<section class="make-customer-area customar-padding fix">
			<div class="blog-area blog-padding">
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-xl-8">
							<!-- Seciton Tittle  -->
							<div class="font-back-tittle mb-50">
								<div class="archivment-front">
									<h3>앞으로 제주의 날씨는?</h3>
								</div>
								<h3 class="archivment-back">제주의 날씨</h3>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xl-4 col-lg-4 col-md-6">
							<!-- Single Blog -->
							<div class="single-blog mb-30">
								<div class="blog-caption">
									<div class="blog-cap-top d-flex justify-content-between mb-40">
										<span>TODAY</span>
										<ul>
											<li><a class="day1"></a></li>
										</ul>
									</div>
									<div class="blog-cap-mid">
										<div class="wDiv day1icon" style="">
											<img src="" alt=""> <a class=day1description></a>
										<br>
										<a class=day1temp></a>
										</div>
									</div>
									<!-- Comments -->
									<div class="blog-cap-bottom d-flex justify-content-between">
										<!-- <span class="day1"></span>
                                    <span><img src="assets/img/our_blog/blog-comments-icon.jpg" alt="">3</span> -->
									</div>
								</div>
							</div>
						</div>
						<div class="col-xl-4 col-lg-4 col-md-6">
							<!-- Single Blog -->
							<div class="single-blog mb-30">
								<div class="blog-caption">
									<div class="blog-cap-top d-flex justify-content-between mb-40">
										<span>DAY+1</span>
										<ul>
											<li><a class="day2"></a></li>
										</ul>
									</div>
									<div class="blog-cap-mid">
										<div class="wDiv day2icon" style="">
											<img src="http://openweathermap.org/img/wn/10d@2x.png" alt="">
											<a class=day2description></a>
										<br>
										<a class=day2temp></a>
										</div>
									</div>
									<!-- Comments -->
									<div class="blog-cap-bottom d-flex justify-content-between">
										<!-- <span class="day2"></span>
                                    <span><img src="assets/img/our_blog/blog-comments-icon.jpg" alt="">3</span> -->
									</div>
								</div>
							</div>
						</div>
						<div class="col-xl-4 col-lg-4 col-md-6">
							<!-- Single Blog -->
							<div class="single-blog mb-30">
								<div class="blog-caption">
									<div class="blog-cap-top d-flex justify-content-between mb-40">
										<span>DAY+2</span>
										<ul>
											<li><a class="day3"></a></li>
										</ul>
									</div>
									<div class="blog-cap-mid">
										<div class="wDiv day3icon" style="">
											<img src="http://openweathermap.org/img/wn/10d@2x.png" alt="">
											<a class=day3description></a>
										<br>
										<a class=day3temp></a>
										</div>
									</div>
									<!-- Comments -->
									<div class="blog-cap-bottom d-flex justify-content-between">
										<!-- <span class="day3"></span>
                                    <span><img src="assets/img/our_blog/blog-comments-icon.jpg" alt="">3</span> -->
									</div>
								</div>
							</div>
						</div>
						<div class="col-xl-4 col-lg-4 col-md-6">
							<!-- Single Blog -->
							<div class="single-blog mb-30">
								<div class="blog-caption">
									<div class="blog-cap-top d-flex justify-content-between mb-40">
										<span>DAY+3</span>
										<ul>
											<li><a class="day4"></a></li>
										</ul>
									</div>
									<div class="blog-cap-mid">
										<div class="wDiv day4icon" style="">
											<img src="http://openweathermap.org/img/wn/10d@2x.png" alt="">
											<a class=day4description></a>
										<br>
										<a class=day4temp></a>
										</div>
									</div>
									<!-- Comments -->
									<div class="blog-cap-bottom d-flex justify-content-between">
										<!-- <span class="day4"></span>
                                    <span><img src="assets/img/our_blog/blog-comments-icon.jpg" alt="">3</span> -->
									</div>
								</div>
							</div>
						</div>

						<div class="col-xl-4 col-lg-4 col-md-6">
							<!-- Single Blog -->
							<div class="single-blog mb-30">
								<div class="blog-caption">
									<div class="blog-cap-top d-flex justify-content-between mb-40">
										<span>DAY+4</span>
										<ul>
											<li><a class="day5"></a></li>
										</ul>
									</div>
									<div class="blog-cap-mid">
										<div class="wDiv day5icon" style="">
											<img src="http://openweathermap.org/img/wn/10d@2x.png" alt="">
											<a class=day5description></a>
											<br>
										<a class=day5temp></a>
										</div>
									</div>
									<!-- Comments -->
									<div class="blog-cap-bottom d-flex justify-content-between">
										<!-- <span>Feb 28, 2020</span>
                                    <span><img src="assets/img/our_blog/blog-comments-icon.jpg" alt="">3</span> -->
									</div>
								</div>
							</div>
						</div>
						<div class="col-xl-4 col-lg-4 col-md-6">
							<!-- Single Blog -->
							<div class="single-blog mb-30">
								<div class="blog-caption">
									<div class="blog-cap-top d-flex justify-content-between mb-40">
										<span>DAY+5</span>
										<ul>
											<li><a class="day6"></a></li>
										</ul>
									</div>
									<div class="blog-cap-mid">
										<div class="wDiv day6icon" style="">
											<img src="http://openweathermap.org/img/wn/10d@2x.png" alt="">
											<a class=day6description></a>
											<br>
										<a class=day6temp></a>
										</div>
									</div>
									<!-- Comments -->
									<div class="blog-cap-bottom d-flex justify-content-between">
										<!-- <span class="day5"></span>
                                    <span><img src="assets/img/our_blog/blog-comments-icon.jpg" alt="">3</span> -->
									</div>
								</div>
							</div>
						</div> 
					</div>
				</div>
			</div>
		</section>
		<!-- 날씨 끝!!!!!-->
		
		
		<!-- 
		<div class="dining-area dining-padding-top">
		<div class="font-back-tittle mb-45">
							<div class="archivment-front">
								<h3>핀터레스트 방식 이미지 갤러리</h3>
							</div>
							<h3 class="archivment-back">Our Rooms</h3>
						</div>
			Single Left img
			<div class="single-dining-area left-img">
				<div class="container">
					<div class="row justify-content-end">
						<div class="col-lg-8 col-md-8">
							<div class="dining-caption">
								<span>Our resturent</span>
								<h3>Dining & Drinks</h3>
								<p>
									Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
									do eiusmod<br> tempor incididunt ut labore et dolore magna
									aliqua. Ut enim ad minim <br>veniam, quis nostrud.
								</p>
								<a href="#" class="btn border-btn">Learn More <i
									class="ti-angle-right"></i>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			single Right img
			<div class="single-dining-area right-img">
				<div class="container">
					<div class="row justify-content-start">
						<div class="col-lg-8 col-md-8">
							<div class="dining-caption text-right">
								<span>Our Pool</span>
								<h3>Swimming Pool</h3>
								<p>
									Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
									do eiusmod<br> tempor incididunt ut labore et dolore magna
									aliqua. Ut enim ad minim <br>veniam, quis nostrud.
								</p>
								<a href="#" class="btn border-btn">Learn More <i
									class="ti-angle-right"></i></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div> -->


		<!-- Room Start -->
		<section class="room-area">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-xl-8">
						<!--font-back-tittle  -->
						<div class="font-back-tittle mb-45">
							<div class="archivment-front">
								<h3>어디로 여행을 떠날까요?</h3>
							</div>
							<h3 class="archivment-back">어디로 갈까</h3>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xl-4 col-lg-6 col-md-6">
						<!-- Single Room -->
						<div class="single-room mb-50 random1">
							<div class="room-img">
								<a><img class="randomImg" src="image/boLoading.svg"
									alt=""></a>
							</div>
							<div class="room-caption">
								<h3 class=CDB>
									<a></a>
								</h3>
								<div class="per-night random1title ">
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-4 col-lg-6 col-md-6">
						<!-- Single Room -->
						<div class="single-room mb-50 random2">
							<div class="room-img">
								<a><img class="randomImg" src="image/boLoading.svg"
									alt=""></a>
							</div>
							<div class="room-caption">
								<h3>
									<a></a>
								</h3>
								<div class="per-night random2title ">
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-4 col-lg-6 col-md-6">
						<!-- Single Room -->
						<div class="single-room mb-50 random3">
							<div class="room-img">
								<a> <img class="randomImg" src="image/boLoading.svg"
									alt=""></a>
							</div>
							<div class="room-caption">
								<h3>
									<a></a>
								</h3>
								<div class="per-night random3title ">
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-4 col-lg-6 col-md-6">
						<!-- Single Room -->
						<div class="single-room mb-50 random4">
							<div class="room-img">
								<a> <img class="randomImg" src="image/boLoading.svg"
									alt=""></a>
							</div>
							<div class="room-caption">
								<h3>
									<a></a>
								</h3>
								<div class="per-night random4title ">
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-4 col-lg-6 col-md-6">
						<!-- Single Room -->
						<div class="single-room mb-50 random5">
							<div class="room-img">
								<a> <img class="randomImg" src="image/boLoading.svg"
									alt=""></a>
							</div>
							<div class="room-caption">
								<h3>
									<a></a>
								</h3>
								<div class="per-night random5title ">
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-4 col-lg-6 col-md-6">
						<!-- Single Room -->
						<div class="single-room mb-50 random6">
							<div class="room-img">
								<a> <img class="randomImg" src="image/boLoading.svg"
									alt=""></a>
							</div>
							<div class="room-caption">
								<h3>
									<a href=""></a>
								</h3>
								<div class="per-night random6title ">
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-4 col-lg-6 col-md-6">
						<!-- Single Room -->
						<div class="single-room mb-50 random7">
							<div class="room-img">
								<a> <img class="randomImg" src="image/boLoading.svg"
									alt=""></a>
							</div>
							<div class="room-caption">
								<h3>
									<a></a>
								</h3>
								<div class="per-night random7title ">
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-4 col-lg-6 col-md-6">
						<!-- Single Room -->
						<div class="single-room mb-50 random8">
							<div class="room-img">
								<a> <img class="randomImg" src="image/boLoading.svg"
									alt=""></a>
							</div>
							<div class="room-caption">
								<h3>
									<a></a>
								</h3>
								<div class="per-night random8title ">
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-4 col-lg-6 col-md-6">
						<!-- Single Room -->
						<div class="single-room mb-50 random9">
							<div class="room-img">
								<a> <img class="randomImg" src="image/boLoading.svg"
									alt=""></a>
							</div>
							<div class="room-caption">
								<h3>
									<a href=""></a>
								</h3>
								<div class="per-night random9title ">
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-4 col-lg-6 col-md-6">
						<!-- Single Room -->
						<div class="single-room mb-50 random10">
							<div class="room-img">
								<a><img class="randomImg" src="image/boLoading.svg"
									alt=""></a>
							</div>
							<div class="room-caption">
								<h3>
									<a></a>
								</h3>
								<div class="per-night random10title ">
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-4 col-lg-6 col-md-6">
						<!-- Single Room -->
						<div class="single-room mb-50 random11">
							<div class="room-img">
								<a><img class="randomImg" src="image/boLoading.svg"
									alt=""></a>
							</div>
							<div class="room-caption">
								<h3>
									<a></a>
								</h3>
								<div class="per-night random11title ">
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-4 col-lg-6 col-md-6">
						<!-- Single Room -->
						<div class="single-room mb-50 random12">
							<div class="room-img">
								<a> <img class="randomImg" src="image/boLoading.svg"
									alt=""></a>
							</div>
							<div class="room-caption">
								<h3>
									<a></a>
								</h3>
								<div class="per-night random12title ">
								</div>
							</div>
							
						</div>
					</div>
				</div>
				<div class="row justify-content-center">
					<div class="room-btn pt-70">
						<a href="tourList.tourlist" class="btn view-btn1">더보기<i
							class="ti-angle-right"></i>
						</a>
					</div>
				</div>
			</div>
		<div class="dining-area dining-padding-top">
		</div>
		</section>
		<!-- Room End -->

		<!-- Gallery img Start-->
		<div class="gallery-area fix">
			<div class="container-fluid p-0">
				<div class="row">
					<div class="col-md-12">
						<div class="gallery-active owl-carousel">
							<div class="gallery-img">
								<a href="#"><img src="./image/imgSlider/5.jpg"
									alt=""></a>
							</div>
							<div class="gallery-img">
								<a href="#"><img src="./image/imgSlider/6.jpg"
									alt=""></a>
							</div>
							<div class="gallery-img">
								<a href="#"><img src="./image/imgSlider/7.jpg"
									alt=""></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Gallery img End-->
	</main>
	<footer>
		<jsp:include page="/include/footer.jsp"></jsp:include>
		<!-- Footer End-->
	</footer>

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
	<script src="./js/boapge.js"></script>


</body>
</html>