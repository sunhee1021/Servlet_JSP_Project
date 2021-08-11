<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">

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
   <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">

<!-- 폰트 -->
   <link rel="preconnect" href="https://fonts.gstatic.com">
   <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">

<style type="text/css">
 * {
 	font-family: 'Nanum Gothic', sans-serif;
 }
 
 .genric-btn.success {
	background: #EAAF24;
}

.btn i {
    color: black;
    }
    
table.aintable, th, td {
    border: 5px solid white;
    border-top: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
}

</style>
	<%-- <link rel="Stylesheet" href="${pageContext.request.contextPath}/style/default.css" /> --%>
	
	<!-- include libraries(jQuery, bootstrap) -->
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<title>답글쓰기페이지</title>
<link rel="shortcut icon" type="image/x-icon" href="./assets/img/favicon.png">

<%-- <link rel="Stylesheet" href="${pageContext.request.contextPath}/style/default.css" /> --%>

<SCRIPT type="text/javascript">
		function check(){
		    if(!bbs.title.value){
		        alert("제목을 입력하세요");
		        bbs.title.focus();
		        return false;
		    }
		    if(!bbs.content.value){            
		        alert("글 내용을 입력하세요");
		        bbs.content.focus();
		        return false;
		    }
		    document.bbs.submit();
}
</SCRIPT>
</head>
<body>
	<c:import url="/include/header.jsp" />

	<c:set var="cNumber" value="${requestScope.cNumber}" />
	<c:set var="cpage" value="${requestScope.cp}" />
	<c:set var="pagesize" value="${requestScope.ps}" />
	<c:set var="title" value="${requestScope.title}" />
	
	<div class="font-back-tittle mb-50">
		<div class="archivment-front">
			<h3>  🌈 ReWrite 🌈 </h3>
		</div>
		<h3 class="archivment-back">AinBoard</h3>
	</div>

	<div id="pagecontainer" style="margin-bottom: 200px;">
		<div style="padding-top: 25px; text-align: center">
			<form name="bbs" action="boardReWriteOk.ain" method="POST" enctype="multipart/form-data">

				<input type="hidden" name="cp" value="${cpage}" /> 
				<input type="hidden" name="ps" value="${pagesize}" /> 
				<input type="hidden" name="cNumber" value="${cNumber}" />

				<table class="aintable" width="80%" border="0" align="center">
					<tr>
						<td colspan="2" align="left">
							<input type="text" name="title" value="RE_${title}" size="100%" style="height: 35px; border: 1px solid #cccccc;">
						</td>
					</tr>
					<tr>
                        <td colspan="2" align="left">
                        	<textarea rows="10" cols="60" name="content" id="summernote"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td width="10%" align="center">첨부파일</td>
                        <td width="90%" align="left"><input type="file" id="filename" name="filename" accept=".jpg, .jpeg, .png, .gif"></td>
                    </tr>
					<tr>
						<td colspan="2" align="center" style="padding-top: 20px; padding-bottom: 20px;">
							<input type="button" value="글쓰기" class="genric-btn success medium" onclick="check();" /> 
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
<!-- include summernote css/js -->
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	
	<script>
	$(document).ready(function() {
		  $('#summernote').summernote({
			height: 450,
			minHeight: 300,             
			maxHeight: 600,       
		    lang: 'ko-KR',
		    placeholder: ' 내용을 입력해주세요.'
		  });
		});
  </script>
  
</html>

