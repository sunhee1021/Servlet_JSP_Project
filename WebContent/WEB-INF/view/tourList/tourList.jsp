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
   <!-- 맵API -->
   <script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ff92759dc421a0bf0b08eca76214da7f"></script>
   <!-- sweetalert -->
   <script src="http://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
   <!-- TourList CSS 가져오기 -->
   <link rel="stylesheet" href="css/TourList.css">
   
   <style type="text/css">
   		/***로딩이미지***/
   		.wrap-loading{ /*화면 전체를 어둡게 합니다.*/
		    position: fixed;
		    left:0;
		    right:0;
		    top:0;
		    bottom:0;
		    background: white; /*not in ie */
		    filter: progid:DXImageTransform.Microsoft.Gradient(startColorstr='#20000000', endColorstr='#20000000');    /* ie */
		}

   		 .wrap-loading div{ /*로딩 이미지*/
   		  	display: table;
   		  	position: relative;
   		  	top: 40%;
	        
   		 }

   		 .display-none{ /*감추기*/
     	   display:none;
   		 }
   		 
   		 .room-caption{
   		height: 230px;
   		vertical-align: middle;
   		 }
   		 
   		 .table td, .table th {
 		vertical-align: middle;
    	width: 25%;
		}
   		/***로딩이미지***/
   		.main-header {
 		   z-index: 99999;
		}

		[class^="ti-"], [class*=" ti-"] {
			line-height:inherit;
		}
   </style>
   
</head>

<header>

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
</header>
<body>
    <br>
    <br>
    <br>
    <br>
    <main>
    	<div class="font-back-tittle mb-45">
			<div class="archivment-front">
				<h3>TourList</h3>
			</div>
			<h3 class="archivment-back">TourList</h3>
		</div>
    	
    	<!-- 1차 라벨 선택 -->
    	<div id="div1">
	    	<div class="container box_1170">
			    <table class="table table-bordered">
			    	<tbody class="text-center">
			    		<tr id="division1">
			    			<td style="background-color: orange; color: white;">전체</td>
			    		</tr>
			    	</tbody>
			    </table>
			</div>
		</div>
		<!-- 1차 라벨 선택 -->
		
		<!-- 2차 라벨 선택 -->
		<div id="div2" class=""></div>
		<!-- 2차 라벨 선택 -->
		
		<!-- 라벨선택요청이미지 -->
		<img class="category-choice" src="img/choice.png">
		
		<!-- Room Start -->
        <section class="room-area">
            <div class="container">
                
                
                <div id="row" class="row"></div>
                
                
            </div>
        </section>
        <!-- Room End -->
        
        <div id="paging" class="text-center"></div>
    </main>
    
    
    <!-- 모달 영역 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"></div>
	
	<!-- 로딩이미지용 -->
  	<div class="wrap-loading display-none" align="center" style="position: ;">
   		 <div><img src="image/boLoading.svg"/></div>
<!--    		 <div id="preloader-active"> -->
<!-- 		     <div class="preloader d-flex align-items-center justify-content-center"> -->
<!-- 		         <div class="preloader-inner position-relative"> -->
<!-- 		             <div class="preloader-circle"></div> -->
<!-- 		             <div class="preloader-img pere-text"> -->
<!-- 		                 <strong>JYP</b> -->
<!-- 		             </div> -->
<!-- 		         </div> -->
<!-- 		     </div> -->
<!--  		</div> -->
	</div>
	<div class="dining-area dining-padding-top">
		</div>
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
        
        
        <script src="js/tourList.js?ver=1"></script>
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
        
</html>