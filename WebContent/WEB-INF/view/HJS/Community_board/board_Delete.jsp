<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="icon" type="image/png" href="http://example.com/myicon.png">
	<link rel="manifest" href="site.webmanifest">
	<link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">

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
	<link rel="stylesheet" href="css/bopage.css">
	<script type="text/javascript">
		function delCheck(){
			
				if(del.pwd.value==""){
					alert("비밀번호를 입력해야합니다.");
					del.pwd.focus();
					return false;
				}
				if(del.pwd.value.length>8){
					alert("비밀번호는 8자리 이내입니다.");
					del.pwd.select();
					return false;
				}//if---------
				document.del.submit();
			}
	</script>
</head>
<body>
	<%
		pageContext.include("/include/header.jsp");
	%>
	<div id="pageContainer">
		<div style="padding-top: 25px; text-align: center">
			<form name="del" method="POST" action="c_content_delete_ok.ha">
				<center>
					비밀번호 :
					<input type="password" name="pwd">
					<input type="hidden"  name="c_number" value="${c_number}">
					<hr width="500" color="gold">
					<input type="button" value="삭제" onclick="delCheck();">
					<input type="reset" value="다시">
				</center>
			</form>
		</div>
	</div>
</body>
</html>