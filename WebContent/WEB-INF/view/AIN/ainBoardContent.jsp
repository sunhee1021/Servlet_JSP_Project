<%@page import="kr.or.bit.ainboard.dto.AinReply"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.bit.ainboard.dto.AinBoard"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board-content</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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

<!-- sweet alert -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css" />

<style type="text/css">
 * {
 	font-family: 'Nanum Gothic', sans-serif;
 }
 
 .font-back-tittle .archivment-front h3 {
 	color: #587D4E;
 }

/* 테이블 */
table.aintable {
    border: 5px solid white;
    border-top: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
}

 .genric-btn.success {
	background: #EAAF24;
}

.genric-btn.success-border {
    color: #EAAF24;
    border: 1px solid #EAAF24;
    background: #fff;
}

#replyListStart {
	font-size: 20px;
    border-top-width: 5px;
    border-bottom-width: 5px;
}

</style>

<%-- <link rel="Stylesheet" href="${pageContext.request.contextPath}/style/default.css" /> --%>

</head>

<body>
	<c:set var="board" value="${requestScope.board}" />
	<c:set var="cNumber" value="${requestScope.cNumber}" />
	<c:set var="cpage" value="${requestScope.cp}" />
	<c:set var="pagesize" value="${requestScope.ps}" />
	<c:set var="replyList" value="${requestScope.replyList}" />
	
	<c:import url="/include/header.jsp" />
	
	<input type="hidden" id="hidden-email" value="${sessionScope.email }">
	
	
	<div class="font-back-tittle mb-50">
		<div class="archivment-front">
			<h3> content </h3>
		</div>
		<h3 class="archivment-back">AinBoard</h3>
	</div>
	
	<div id="pageContainer" style="margin-bottom: 200px;">
		<div style="padding-top: 30px; text-align: center">
			<center>
				<table width="80%" border="1" class="aintable" style="margin-bottom: 20px;">
					<tr>
						<td colspan="4" align="right">
						<a href="boardList.ain?cp=${cpage}&ps=${pagesize}" class="genric-btn success medium">목록가기</a>
						</td>
					</tr>
					<tr style="font-size: 25px">
						<td colspan="4" align="center" style="padding-left: 60px; height: 54px;">${board.title}</td>
					</tr>
					<tr>
						<td width="20%" align="center"><b>글쓴이</b></td>
						<td width="30%">${board.nickname}</td>
						<td width="20%" align="center"><b>조회수</b></td>
						<td>${board.viewcount}</td>
					</tr>
				
					<tr>
						<td width="20%" align="center"><b>작성일</b></td>
						<td>${board.writedate}</td>
						<td width="20%" align="center"><b>첨부파일</b></td>
						<td><a href="file.ain?file_name=${board.filerealname}">${board.filename}</a></td>
					</tr>
					
					
					<tr height="100">
						<td width="20%" align="center"><b>글내용</b></td>
						<td colspan="3" style="height: 400px;">
							<c:if test="${board.filerealname != null}">
							<img src="upload/${board.filerealname}" width="30%">
							</c:if>
							${fn:replace(board.content, newLineChar,"<br>")}
						</td>
					</tr>
					<tr>
						<td colspan="2" align="left">
						<c:if test="${sessionScope.email != null}">
							<a href="boardReWrite.ain?cNumber=${cNumber}&cp=${cpage}&ps=${pagesize}&title=${board.title}" class="genric-btn success medium">답글</a>
						</c:if>
						</td>
						<c:if test="${sessionScope.email == board.email || sessionScope.email == 'admin@naver.com'}">
						<td colspan="2" align="right">
							<a href="boardEdit.ain?cNumber=${cNumber}&cp=${cpage}&ps=${pagesize}" class="genric-btn success medium">수정</a>
							<button type="button" class="genric-btn success medium" id="boardDelete">
								삭제
							</button>
						</td>
						</c:if>
						<%-- <c:if test="${sessionScope.email == 'admin@naver.com'}">
						<td colspan="2" align="right">
							<a href="boardEdit.ain?cNumber=${cNumber}&cp=${cpage}&ps=${pagesize}" class="genric-btn success medium">수정</a>
							<button type="button" class="genric-btn success medium" id="boardDelete">
								삭제
							</button>
						</td>
						</c:if> --%>
						<c:if test="${sessionScope.email != board.email}">
						<td colspan="2">
						</td>
						</c:if>
						
					</tr>
				</table>
				
				
				
				
				
				<!--  댓글 달기 테이블 -->
				<form name="reply" action="replyOk.ain" method="POST">
						<!-- hidden 태그  값을 숨겨서 처리  -->
						<input type="hidden" name="cNumber" value="${cNumber}"> 
						<c:if test="${sessionScope.email != null}">
							<table width="80%" border="1" class="aintable">
								<tr>
									<td width="20%" align="center"> 댓글쓰기 </td>
									<td width="60%" align="left">
									 	<textarea id="replyContent" name="reply_content" rows="2" style="width:100%;" placeholder="댓글을 입력하세요"></textarea>
									</td>
									<td width="20%" align="center">
										<input type="button" value="등록" class="genric-btn success-border medium" onclick="reply_check()">
									</td>
								</tr>
								<tr>
									<td colspan="3" align="left" style="color: #cccccc">
										회원 이메일: ${sessionScope.email}
									</td>
								</tr>
							</table>
						</c:if>
						<c:if test="${sessionScope.email == null}">
							<table width="80%" border="1" class="aintable">
							<tr>
								<td align="center">댓글을 작성하시려면 로그인해주세요 😊</td>
							</tr>
							</table>
						</c:if>
				</form>
				<!-- 유효성 체크	 -->
				<script type="text/javascript" src="${pageContext.request.contextPath}/js/ainTest.js?ver=2"></script>
				<br>
				
				<table width="80%" border="1" class="aintable" id="replyListStart">
						<tr>
							<td align="center">  🍊 댓글 목록 🍊 </td>
						</tr>
						</table>
				<!-- 댓글 목록 테이블 -->
				<span id="span-reply">
				<c:if test="${not empty replyList}">
					<c:forEach var="reply" items="${replyList}">
						<table width="80%" border="1" class="aintable">
							<tr align="left">
								<td width="80%">
								[${reply.nickname}] : ${reply.content}
								<br> 
								작성일:${reply.writedate}
								</td>
								<td width="20%" align="right">
								<form action="ReplyDeleteOk.do" method="POST" name="replyDel">
									<input type="hidden" name="crNumber" value="${reply.crNumber}"> 
									<input type="hidden" name="cNumber" value="${reply.cNumber}"> 
									<c:if test="${sessionScope.email == reply.email}">
										<input type="button" value="삭제" class="genric-btn success-border medium" onclick="reply_del_check(this.form)">
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
	
	<jsp:include page="/include/footer.jsp"></jsp:include>
	
</body>
<script type="text/javascript">
	$("#boardDelete").click(function(){
		var result = confirm("정말 삭제하시겠습니까?😥 \n해당 글에 작성된 답글과 댓글도 함께 삭제되며, \n삭제된 글은 복구할 수 없습니다.");
		if(result) {
			location.href = 'boardDelete.ain?cNumber=${cNumber}&cp=${cpage}&ps=${pagesize}';
		}
	})
</script>
</html>