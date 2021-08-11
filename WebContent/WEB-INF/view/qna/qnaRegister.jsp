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
   
   
</head>
<body>
	<c:set var="board" value="${requestScope.qnaboard }" />
	
	<c:choose>
		<c:when test="${not empty param.update }">
			<!-- 글제목 -->
			<c:set var="h3Title" value="<h3>Q&A 글 수정</h3>" />
			<!-- 글제목 배경 -->
			<c:set var="archivementBack" value="<h3 class='archivment-back'>Q&A 글 수정</h3>" />
			<!-- 수정 action -->
			<c:set var="action" value="updateContent.qna"/>
		</c:when>
		
		<c:when test="${not empty param.rewrite }">
			<c:set var="h3Title" value="<h3>Q&A 글 답글</h3>" />
			<c:set var="archivmentBack" value="<h3 class='archivment-back'>Q&A 글 답글</h3>" />
			<c:set var="action" value="rewriteContent.qna"/>
		</c:when>
		
		<c:otherwise>
			<c:set var="h3Title" value="<h3>Q&A 글 등록</h3>" />
			<c:set var="archivmentBack" value="<h3 class='archivment-back'>Q&A 글 등록</h3>" />
			<c:set var="action" value="registerContent.qna"/>
		</c:otherwise>			
	</c:choose>
	
    <jsp:include page="/include/header.jsp" />
    <main>
    	<div class="font-back-tittle mb-45">
			<div class="archivment-front">
				${h3Title}
			</div>
			${archivementBack}
		</div>
    	
		<!-- Q&A 글 등록 -->
		<div id="container" class="container box_1170">
			<form action="${action}" method="post" enctype="multipart/form-data">
				<div class="row">
					<!-- 이메일 -->
					<div class="form-group col-md-6">
						<label id="emailHelp" class="form-text text-muted">로그인 된 유저의 이메일로 등록됩니다.</label>
						<input type="text" value="${sessionScope.user.email}" class="form-control" name="title" aria-describedby="emailHelp" disabled="disabled" style="background-color: white;">
					</div>
					
					<!-- 비밀번호 -->
					<div class="form-group col-md-6">
					  <label for="exampleInputPassword1">Password</label>
					  <input type="password" class="form-control required" name="password" maxlength="4" required="required" placeholder="비밀번호 4자리를 입력하세요.">
					</div>
				</div>
				
				<!-- 글 제목 -->
				<div class="form-group">
					<c:choose>
						<c:when test="${not empty board.qnaTitle}">
							<c:set var="qnaTitle" value="[RE]${board.qnaTitle }" />
						</c:when>
						<c:otherwise>
							<c:set var="qnaTitle" value="" />
						</c:otherwise>
					</c:choose>
					<input type="text" class="form-control" name="title" aria-describedby="emailHelp" required="required" placeholder="제목" value="${qnaTitle }">
				</div>
				
				
				
				<c:if test="${not empty board.qnaContent}">
					<c:set var="qnaContent" value="${board.qnaContent}
---------------------------------------------------------------------" />
				</c:if>
				<!-- 글 내용 -->
				<div class="form-group">
					<textarea class="form-control" name="content" rows="5" required="required" placeholder="내용을 입력해 주세요.">${qnaContent}</textarea>
				</div>
				
				
				
				
				<c:choose>
					<c:when test="${not empty board.qnaFilename}">
						<c:set var="qnafilename" value="${board.qnaFilename}" />
					</c:when>
					<c:otherwise>
						<c:set var="qnafilename" value="파일을 선택주세요." />
					</c:otherwise>
				</c:choose>
				<div class="form-group">
				<!-- 파일 업로드 -->
					<div class="row" class="text-left">
						<label for="upload-file" class="form-text mr-3 col-md-3"><img alt="파일업로드" src="./image/upload_file_image.png">jpg, png, jpeg, pdf, gif</label>
						<input type="file" class="col-md-3" name="filename" id="upload-file" height="30px" style="opacity: 0; display:none;" accept=".jpg, .jpeg, .png, .gif, .pdf">
						<input type="text" class="form-control col-md-6 align-self-center" value="${qnafilename}" id="preview" disabled="disabled" style="background-color: white;">
					</div>
				</div>
				
				<input type="hidden" name="qnaNumber" value="${board.qnaNumber }">
				<input type="hidden" name="page" value="${param.page}">
				<!-- 작성완료 버튼 -->
				<button type="submit" class="btn btn-primary">작성 완료</button>
			</form>
		</div>	
		
	
		
		<!-- 페이징 부분 -->
        <!-- <div id="paging" class="text-center"></div> -->
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
			// 글 등록 페이지에서 첨부파일 바뀔 때
			$(document).on("change", '#upload-file', function(event) {
				const filename = $(event.target).val().split('\\');
				$('#preview').val(filename[filename.length - 1]);
			});
		</script>
        
</html>