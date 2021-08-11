<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board_content</title>

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
   

   <style type="text/css">
	
	*{
	 font-family: ''Nanum Gothic', sans-serif';
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
  <!--  <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
   <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
   -->
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
<jsp:include page="/include/header.jsp"></jsp:include>
               
	<c:set var="board" value="${requestScope.board}" />
	<c:set var="c_number" value="${requestScope.c_number}" />
	<c:set var="cpage" value="${requestScope.cp}" />
	<c:set var="pagesize" value="${requestScope.ps}" />
	<c:set var="replyList" value="${requestScope.replyList}" />
	<input type="hidden" id="email" name="email" value="${sessionScope.email}">
	
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
		<div style="padding-top: 30px; text-align: center">
			<center>
				<table width="80%" border="1">
					<tr>
						<td width="20%" align="center"><b> 글번호 </b></td>
						<td width="30%">${c_number}</td>
						<td width="20%" align="center"><b>작성일</b></td>
						<td>${board.writedate}</td>
					</tr>
					<tr>
						<td width="20%" align="center"><b>글쓴이</b></td>
						<td width="30%">${board.nickname}</td>
						<td width="20%" align="center" name="viewcount"><b>조회수</b></td>
						<td>${board.viewcount}</td>
					</tr>
					<tr>
						<td width="20%" align="center"><b>첨부파일</b></td>
						<td><a href="file.sun?file_name=${board.filename}">${board.filename}</a></td>
					</tr>
					<tr>
						<td width="20%" align="center"><b>제목</b></td>
						<td colspan="3">${board.title}</td>
					</tr>
					<tr height="100">
						<td width="20%" align="center"><b>글내용</b></td>
						<td colspan="3">${fn:replace(board.content, newLineChar,"<br>")}</td>
					</tr>
					<tr>
						<td colspan="4" align="center"><a
							href="boardList.sun?cp=${cpage}&ps=${pagesize}">목록가기</a>&nbsp
							<c:if test="${sessionScope.email == 'admin@naver.com'}">
							<a href="boardEdit.sun?c_number=${c_number}&cp=${cpage}&ps=${pagesize}">수정</a>&nbsp
							<a href="boardDelete.sun?c_number=${c_number}&cp=${cpage}&ps=${pagesize}">삭제</a>&nbsp
						</c:if> 
						
						<c:if test="${sessionScope.email == board.email}">
							<a href="boardEdit.sun?c_number=${c_number}&cp=${cpage}&ps=${pagesize}">수정</a>&nbsp 
							<a href="boardDelete.sun?c_number=${c_number}&cp=${cpage}&ps=${pagesize}">삭제</a>&nbsp 
						</c:if>
						<c:if test="${sessionScope.email != null}">
							<a href="boardRewrite.sun?c_number=${c_number}&cp=${cpage}&ps=${pagesize}&title=${board.title}">답글</a>
						</c:if>
						<c:if test="${sessionScope.email != board.email}">
						<td colspan="2"></td>
						</c:if>
						</td>
					</tr>
				</table>
				<script type="text/javascript" src="${pageContext.request.contextPath}/js/sunTest.js?ver=1"></script>
				<br>
				<!--  꼬리글 달기 테이블 -->
				<form name="reply" action="ReplyOk.sun" method="POST">
						<!-- hidden 태그  값을 숨겨서 처리  -->
						<input type="hidden" name="c_number" value="${c_number}"> 
						<input type="hidden" name="nickname" value="${nickname}"><!-- 추후 필요에 따라  -->
						<!-- hidden data -->
						<table width="80%" border="1">
							<tr>
								<th colspan="3" >댓글</th>
							</tr>
							<tr>
								<td width="20%" align="left"> [ 작성자 ]
								 	<input type="text" name="nickname" value="${nickname}" style="border:none" readonly><br /> 
								 	</td>
								 <td width="60%" align="left">
								 	<textarea id="reply_content" name="reply_content" rows="2" cols="90" style="border:none"></textarea></td>
								 <td width="20%" align="center">	
								 	<input type="button" value="등록" onclick="reply_check(this.form)"/>

								</td>
							</tr>
						</table>
				</form>
				<!-- 유효성 체크	
				<script type="text/javascript" src="${pageContext.request.contextPath}/js/test.js"></script>-->
				<br>
				<!-- 꼬리글 목록 테이블 -->
				<span id="span-reply">
				<c:if test="${not empty replyList}">
					<c:forEach var="reply" items="${replyList}">
						<table width="80%" border="1" class="replysun">
							<tr>
								<th colspan="2">REPLY LIST</th>
							</tr>
							<tr align="left">
								<td width="80%">
								[${reply.nickname}] : ${reply.content}
								<br> 작성일:${reply.writedate}
								</td>
								<td width="20%" align="center">
								<form action="ReplyDeleteOk.sun" method="POST" name="replyDel">
									<input type="hidden" name="cr_number" value="${reply.cr_number}"> 
									<input type="hidden" name="c_number" value="${c_number}"> 
									<c:if test="${sessionScope.email == reply.email}">
										<input type="button" value="삭제" onclick="reply_del_check(this.form)">
									</c:if>
								</form>
								</td>
							</tr>
						</table>
					</c:forEach>
					
				</c:if>
				</span>
			</center>
		</div>
	</div>
	</article>
	</div>
	</div>
	</div>
	</div>
	<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>





