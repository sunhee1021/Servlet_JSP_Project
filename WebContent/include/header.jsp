<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<style>
.main-header {
    z-index: 99999;
}

[class^="ti-"], [class*=" ti-"] {
	line-height:inherit;
}

</style>
<div id="preloader-active">
		<div class="preloader d-flex align-items-center justify-content-center">
			<div class="preloader-inner position-relative">
			<img src="image/boLoading.svg"/>
			</div>
		</div>
	</div>
</head>
<body>
<div class="header-area header-sticky">
			<div class="main-header ">
				<div class="container">
					<div class="row align-items-center">
						<!-- logo -->
						<div class="col-xl-2 col-lg-2">
							<div class="logo">
								<a href="main.bo"><img src="image/headerFinal.png" alt=""></a>
							</div>
						</div>
						<div class="col-xl-8 col-lg-8">
							<!-- main-menu -->
							<div class="main-menu f-right d-none d-lg-block">
								<nav>
									<ul id="navigation">
										<li><a href="tourList.tourlist">관광지</a></li>
										<li><a>커뮤니티</a>
											<ul class="submenu" style="z-index: 5">
												<li><a href="boardList.sun">선희 커뮤니티</a></li>
												<li><a href="boardList.ha">준수 커뮤니티</a></li>
												<li><a href="boardList.ain">아인 커뮤니티</a></li>
											</ul></li>
										<li><a>고객센터</a>
											<ul class="submenu">
												<li><a href="boardList.bo">공지사항</a></li>
												<li><a href="boardList.qna?pageSize=10&currentPage=1">Q&A</a></li>
												<li><a href="introduce.do">회사소개</a></li>
												<li><a href="terms.do">이용약관</a></li>
												<li><a href="personalInfo.do">개인정보처리방침</a></li>
											</ul></li>
										<li><a class="notAdmin" href="#">마이페이지</a>
											<ul class="submenu">
												<li><a href="InfoEdit.do">내 정보 변경</a>
												<li><a href="mytourList.tourlist">내 여행지</a></li>
											</ul></li>
											<li class="imAdmin"><a class="imAdmin">관리자</a>
											<ul class="submenu">
												<li><a href="admin.ain">관리자 페이지</a></li>
												<li><a href="chart.bo">통계자료</a></li>
											</ul></li>
									</ul>
								</nav>
							</div>
						</div>
						<div class="col-xl-2 col-lg-2">
							<!-- header-btn -->
							<div class="header-btn">
								<a class="btn btn1 d-none d-lg-block"
								<%
						if(session.getAttribute("email") != null){
						out.print("href='LogOutOk.do'>Logout");
						}else{
						out.print("href='Login.do'>Login"); 
						}
						%> </a>
							</div>
						</div>
						<!-- Mobile Menu -->
						<div class="col-12">
							<div class="mobile_menu d-block d-lg-none"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- All JS Custom Plugins Link Here here -->
	<script src="./assets/js/vendor/modernizr-3.5.0.min.js"></script>

	<!-- Jquery, Popper, Bootstrap -->
<!-- 	<script src="./assets/js/vendor/jquery-1.12.4.min.js"></script> -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
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
			var nowEmail= '<%=session.getAttribute("email")%>'
			$('.notAdmin').css("display","none");
			$('.imAdmin').css("display","none");
			console.log(nowEmail+"header");
			if(nowEmail == 'admin@naver.com'){
				$('.imAdmin').css("display","");
				}else{
				$('.notAdmin').css("display","");
				}  
		</script>
</body>
