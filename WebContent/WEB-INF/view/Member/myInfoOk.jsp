

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
<meta charset="UTF-8">
<title>개인정보수정</title>
<!--  
<link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sign-in/">
<link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">
-->
<!-- <link href="shp/bootstrap.css" rel="stylesheet">
<link href="shp/bootstrap-responsive.css" rel="stylesheet">
<link href="shp/docs.css" rel="stylesheet">
<link href="shp/main.css" rel="stylesheet"> -->
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="span12">
				<section id="typography">
				<div class="page-header">
					<h1>개인정보수정</h1>
				</div>
				
				<form class="form-horizontal" action="InfoEditOk.do" method="post">
					<c:if test="${not empty errorMessage}">
					<div class="control-group">
						<label class="error">${errorMessage}</label>
					</div>
					</c:if>
					
					<div class="control-group">
						<label class="control-label" for="email">이메일 계정</label>
						<div class="controls">
							<input type="text" name="email" value="${dto.email}" readonly />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="nickname">닉네임</label>
						<div class="controls">
							<input type="text" id="nickname" name="nickname" placeholder="닉네임 입력">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">비밀번호</label>
						<div class="controls">
							<input type="password" id="password" name="password" placeholder="비밀번호 입력">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">비밀번호 확인</label>
						<div class="controls">
							<input type="password" id="password2" name="password" placeholder="비밀번호 확인">
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-primary">개인정보 수정완료</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>