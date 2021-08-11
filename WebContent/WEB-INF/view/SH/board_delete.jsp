<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
   
   
   <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
   
   <style type="text/css">
	
	*{
	 font-family: 'Nanum Gothic', sans-serif;
	}
	</style>
	
	 <link rel="Stylesheet" href="${pageContext.request.contextPath}/style/default.css" />
	 
</head>
<body>
	<c:import url="/include/header.jsp" />
	
	<c:set var="c_number" value="${requestScope.c_number}" />
	
	<div id="pageContainer">
		<div style="padding-top: 25px; text-align: center">
			<form name="del" method="POST" action="boardDeleteOk.sun">
				<center>
					삭제하시겠습니까?
					<br>
					
					<input type="hidden"  name="c_number" value="${c_number}">
					
					<hr width="500" color="gold">
					<input type="submit" value="삭제">
					<input type="button"  value="취소" onclick="location.href='boardList.sun';"/>
				</center>
			</form>
		</div>
	</div>
	<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
</html>