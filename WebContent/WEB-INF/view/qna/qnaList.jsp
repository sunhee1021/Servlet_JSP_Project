<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
   
   <style type="text/css">
   		/***로딩이미지***/
   		.wrap-loading{ /*화면 전체를 어둡게 합니다.*/
		    position: fixed;
		    left:0;
		    right:0;
		    top:0;
		    bottom:0;
		    background: rgba(0,0,0,0.2); /*not in ie */
		    filter: progid:DXImageTransform.Microsoft.Gradient(startColorstr='#20000000', endColorstr='#20000000');    /* ie */
		}

   		 .wrap-loading div{ /*로딩 이미지*/
	        position: fixed;
	        top:50%;
	        left:50%;
	        margin-left: -21px;
	        margin-top: -21px;
   		 }

   		 .display-none{ /*감추기*/
     	   display:none;
   		 }
   		/***로딩이미지***/
   </style>
   
</head>
<body>

	<!-- total data 받기 -->
	<input type="hidden" id="total-data" value="${requestScope.total }">
	<input type="hidden" id="current-page" value="${param.page }">

	<c:set var="boardlist" value="${requestScope.list }" />
	
	
	
    <jsp:include page="/include/header.jsp" />
    <main>
    	<div class="font-back-tittle mb-45">
			<div class="archivment-front">
				<h3>Q&A 게시판</h3>
			</div>
			<h3 class="archivment-back">Q&A 게시판</h3>
		</div>
    	
		<!-- Q&A게시판 -->
		<div id="container" class="container box_1170">
			<!-- 게시판 시작 -->
			<div id="qna-list">
				<!-- 게시판 상단 -->
				<div id="qna-list-top" class="row">
					<div id="qna-total-page" class="col-md-6 align-self-center"></div>
					<div class="col-md-6"><a href="register.qna" class="genric-btn primary-border radius float-right" id="qna-register">글 등록</a></div>
				</div>
			</div>
			<hr>
			
			<!-- 게시판 테이블 시작 -->
			<table id="qna-table" class="table table-bordered responsive-table">
		    	<tbody class="text-center">
		    		<tr id="table-head" class="">
		    			<th style="width: 15%;">상태</th>
		    			<th style="width: 55%;">제목</th>
		    			<th style="width: 10%;">작성자</th>
		    			<th style="width: 10%;">조회</th>
		    			<th style="width: 10%;">날짜</th>
		    		</tr>
		    		
		    		<c:if test="${empty list}">
		    			<tr>
		    				<td colspan="5">등록된 게시글이 없습니다.</td>
		    			</tr>
		    		</c:if>
		    		
		    		<c:forEach var="board" items="${list }">
		    			<tr>
		    				<c:choose>
		    					<c:when test="${board.qnaStatus eq 0}">
		    						<td><span class="qnaIco qnaIco2"><i class="far fa-smile" aria-hidden="true"></i> 답변완료</span></td>
		    					</c:when>
		    					
		    					<c:when test="${board.qnaStatus eq 1}">
		    						<td><span class="qnaIco qnaIco3"><i class="fa fa-spinner" aria-hidden="true"></i> 접수완료</span></td>
		    					</c:when>
		    				</c:choose>
		    				
		    				
		    				
			    			<td style="text-align: left;" id="qna-content">
			    				<c:forEach var="i" begin="1" end="${board.qnaDepth}" step="1">
									&nbsp;&nbsp;&nbsp;
								</c:forEach>
								<c:if test="${board.qnaDepth > 0}">
									<i class="fas fa-angle-double-right" aria-hidden="true"></i> 
								</c:if>
			    				${board.qnaTitle }
			    				<input type="hidden" value="${board.qnaNumber} ">
			    				<input type="hidden" value="${board.email} ">
			    			</td>
			    			<td>${board.nickname }</td>
			    			<td>${board.qnaViewcount }</td>
			    			<td>${board.qnaWritedate }</td>
			    		</tr>
		    		</c:forEach>
		    		
		    	</tbody>
		    </table>

			<!-- 검색란 -->
			<div class="row">
				<form action="" class="form-inline col-md-6">
					<select id="select-condition" class="form-group form-control">
					  <option>제목 + 내용</option>
					  <option>제목</option>
					  <option>내용</option>
					  <option>회원아이디</option>
					  <option>작성자</option>
					</select>
					<div class="form-group">
				        <input type="text" name="stx" maxlength="15" class="form-control">
				    </div>
					<div class="form-group">
				        <button class="btn btn-primary" id="search-submit">검색</button>
				    </div>
				</form>
				<div class="col-md-6">
					<a href="register.qna" class="genric-btn primary-border radius float-right" id="qna-register">글 등록</a>
				</div>
			</div>
			
		</div>	
		
		<!-- 페이징 부분 -->
        <div id="paging" class="text-center"></div>
    </main>
    
    
    <!-- 모달 영역 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"></div>
	
	<!-- 로딩이미지용 -->
  	<div class="wrap-loading display-none">
   		 <div><img src="image/tourLoading.svg" /></div>
	</div>
	
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
        
        <script src="js/qnaList.js"></script>
        
</html>