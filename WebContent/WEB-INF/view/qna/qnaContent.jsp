<%@page import="java.io.FileInputStream"%>
<%@page import="kr.or.bit.qnaboard.dto.QnaBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	//다운로드할 파일명 얻기
	String filename = ((QnaBoard)request.getAttribute("qnaBoard")).getQnaRealFilename();
	
	//물리적 경로 얻기
	String savepath = "upload";
	String downloadpath = request.getRealPath(savepath);
	String FilePath = downloadpath + "\\" + filename;
%>
<%-- 			        <a href="filedownload.qna?qnaRealFilename=${board.qnaFilename}"> --%>
<!DOCTYPE html>
<html class="no-js" lang="zxx">

<head>
	<meta charset="utf-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<title>Barber</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- <link rel="manifest" href="site.webmanifest"> -->
	<!-- Place favicon.ico in the root directory -->
	<link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">

	
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
    
    <c:set var="board" value="${requestScope.qnaBoard }" />
    <c:set var="filepath" value="${FilePath}" />
    <main>
    	<div class="font-back-tittle mb-45">
			<div class="archivment-front">
				<h3>Q&A 글 상세보기</h3>
			</div>
			<h3 class="archivment-back">Q&A 글 상세보기</h3>
		</div>
    	
		
		<div id="container" class="container box_1170">
		
			 <header>
		        <h2 id="bo_v_title">
		        	<c:choose>
    					<c:when test="${board.qnaStatus eq 0}">
    						<td><span class="qnaIco qnaIco2"><i class="far fa-smile" aria-hidden="true"></i> 답변완료</span></td>
    					</c:when>
    					
    					<c:when test="${board.qnaStatus eq 1}">
    						<td><span class="qnaIco qnaIco3"><i class="fa fa-spinner" aria-hidden="true"></i> 접수완료</span></td>
    					</c:when>
    				</c:choose>
			        <span class="bo_v_tit">${board.qnaTitle}</span>
        		</h2>
    		</header>
			<!-- Q&A 글 상세보기 시작 -->
		    <section id="bo_v_info">
		        <!-- 작성자 -->
		        <strong><span class="sv_guest">${board.nickname}</span></strong>
		        <!-- 조회수 -->
		        <strong><i class="fa fa-eye" aria-hidden="true"></i> ${board.qnaViewcount}</strong>
		        <!-- 작성일 -->
		        <strong class="if_date"><i class="fa fa-clock-o" aria-hidden="true"></i> ${board.qnaWritedate}</strong>
		    </section>

		    <section id="bo_v_atc">
		        <div class="nofile" align="center">
		        	<!-- 파일다운로드이미지 및 파일이름 -->
			        <strong>
			    <a href="filedownload.qna?qnaRealFilename=${board.qnaFilename}">
<%-- 			        <a href="upload/${board.qnaFilename}"> --%>
			        <i class="fas fa-file-archive" aria-hidden="true"></i> ${board.qnaFilename}</a></strong>
			        <!-- 파일 이미지 -->
			        <div id="bo_v_img" style="max-width: 50%">
			        <img src="upload/${board.qnaFilename}">
			        </div>
		        </div>
		        

		        <!-- 본문 내용 시작 { -->
		        <div id="bo_v_con" class="form-group">
		        	<textarea rows="10" disabled="disabled" class="form-control" style="background-color: white;">${board.qnaContent}</textarea>
		        </div>
		        <!-- } 본문 내용 끝 -->
    		</section>
    		<!-- Q&A 글 상세보기 끝 -->
    
	    	<!-- 게시물 상단 버튼 시작 { -->
		    <div id="bo_v_top">
		        
		        <ul class="bo_v_left">
		            <li><a href="register.qna?qnaNumber=${board.qnaNumber}&page=${requestScope.page}&update=true" class="btn_b01 btn"><i class="fas fa-pencil-alt" aria-hidden="true"></i> 수정</a></li> 
		            <li><a href="deleteContent.qna?qnaNumber=${board.qnaNumber}&page=${requestScope.page}" class="btn_b01 btn" onclick="del(this.href); return false;"><i class="fas fa-trash" aria-hidden="true"></i> 삭제</a></li>                                            
		        </ul>
		
		        <ul class="bo_v_com">
		           <li><a href="boardList.qna?page=${requestScope.page}" class="btn_b01 btn"><i class="fas fa-list" aria-hidden="true"></i> 목록</a></li>
		           <c:if test="${sessionScope.user.division eq 0 }">
		           		<li><a href="register.qna?qnaNumber=${board.qnaNumber}&rewrite=true&page=${requestScope.page}" class="btn_b02 btn"><i class="fas fa-pencil-alt" aria-hidden="true"></i> 답글달기</a></li>
		           </c:if>
		        </ul>
            </div>
    		<!-- } 게시물 상단 버튼 끝 -->
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
        <script type="text/javascript">
        if('${board.qnaFilename}'==''){
			$('.nofile').css("display","none");	
		}
        </script>
        
        
</html>