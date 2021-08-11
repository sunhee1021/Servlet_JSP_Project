
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	
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
   
    <!-- 부트스트랩 start -->
	<link rel="apple-touch-icon" href="apple-icon.png">
	<link rel="shortcut icon" href="favicon.ico">
	
	<link rel="stylesheet"
	   href="WEB-INF/vendors/bootstrap/dist/css/bootstrap.min.css">
	<link rel="stylesheet"
	   href="WEB-INF/vendors/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet"
	   href="WEB-INF/vendors/themify-icons/css/themify-icons.css">
	<link rel="stylesheet"
	   href="WEB-INF/vendors/flag-icon-css/css/flag-icon.min.css">
	<link rel="stylesheet" href="WEB-INF/vendors/selectFX/css/cs-skin-elastic.css">
	<link rel="stylesheet" href="WEB-INF/vendors/jqvmap/dist/jqvmap.min.css">
	
	<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">


	<link rel="stylesheet" href="assets/css/style.css">
	
	<link
   href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800'
   rel='stylesheet' type='text/css'>
<!-- 부트스트랩 end  -->
   
   <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
   
   <style type="text/css">
	
	*{
	 font-family: 'Nanum Gothic', sans-serif;
	}
	
	.note-editable{
	height:300px;
	}
	
	.btn-primary{
		color : #F2CF7C;
	}
	</style>
   
	<%-- <link rel="Stylesheet" href="${pageContext.request.contextPath}/css/default.css" /> --%>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

   <!-- include summernote css/js -->
   <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
   <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
   
   
   <script>
    $(document).ready(function() {
        $('#summernote').summernote();
    });
     </script>
	
	<script type="text/javascript">
	
	function editCheck() {

		if (!edit.title.value) {
			alert("제목을 입력하세요");
			edit.title.focus();
			return false;
		}

		if (!edit.summernote.value) {
			alert("글 내용을 입력하세요");
			edit.summernote.focus();
			return false;
		}

		document.edit.submit();
	}
	
</script>
</head>
<body>
	<!-- Header-->
       <jsp:include page="/include/header.jsp"></jsp:include>
      <!-- /Header -->
			
      	<c:set var="c_number" value="${requestScope.c_number}" />
		<c:set var="dto" value="${requestScope.dto}" />
		
		<hr style="color : gray;">
     <!--   <div class="breadcrumbs">
         <div class="col-sm-4">
            <div class="page-header float-left">
            </div>
         </div>
         <div class="col-sm-8">
            <div class="page-header float-right">
               <div class="page-title">
               
               </div>
            </div>
         </div>
      </div> -->

      <!-- <form name="list"> -->
      <%-- ${requestScope.list} --%>
      <!--  </form> -->
     <div class="main-panel">
         <div class="content-wrapper">
            <div class="row ">
               <div class="col-12">
               
               
       <article>
		<div class="container" role="main">
			<form name="edit" action="boardEditOk.sun" enctype="multipart/form-data" method="POST">
				<input type="hidden" name="cp" value="${requestScope.cpage}" /> 
				<input type="hidden" name="ps" value="${requestScope.pagesize}" /> 
				<input type="hidden" name="c_number" value="${requestScope.c_number}" />
				
				<div class="mb-3" >
					<label for="reg_id"><b>글번호</b></label><div>${dto.c_number}</div></div>
		
					<label for="reg_id"><b>작성일</b></label> 
					<div>${dto.writedate}</div>
					<div style = "padding: 5px 1px 2px 3px;"></div>

				<div class="mb-3">
					<label for="reg_id"><b>작성자</b></label> 
					<input type="hidden" name="email" value="${dto.email}" />
					<input type="text" class="form-control" name="nickname" id="nickname" value="${nickname}" readonly>
				</div>
				<div class="mb-3">
					<label for="reg_id"><b>제목</b></label> 
					<input type="text" class="form-control" name="title" id="title" value="${dto.title}">
				</div>
				
				
				<div class="mb-3">
					<label for="reg_id"><b>첨부파일</b></label> 
					<input type="hidden" name="filename" value="${dto.filename}">
					<input type="file" class="form-control" name="filename" id="filename" 
						placeholder="첨부파일">
				</div>

				<!-- 썸머노트 반영 -->
				<div class="mb-3">
					<label for="content"><b>내용</b></label>
				<textarea class="form-control" rows="50" name="content" 
				id="summernote">${dto.content}</textarea>
					<!--  <textarea id="summernote" name="editordata"></textarea>-->
				</div>
				
			</form>
			<div>
				<button type="submit" class="btn" id="btnSave" onclick="editCheck()">저장</button>
				<button type="button" class="btn" id="btnList" onclick="location.href='boardList.sun';">취소</button>
			</div>
			<div style = "padding: 5px 1px 2px 3px;"></div>
		</div>

	</article>
              
            </div>
         </div>
         </div>
      </div> 
<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>