<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">

<!-- CSS here -->
	<link href="css/bootstrap.min.css" rel="stylesheet">
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
	<link rel="stylesheet" href="boCss/bopage.css">
	
	<!-- include libraries(jQuery, bootstrap) -->
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

 
<!-- -->
<link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sign-in/">
<link href="css/signin.css" rel="stylesheet">

<style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
</head>
  
  <body class="text-center">
  <header>
	<jsp:include page="/include/header.jsp"></jsp:include>
	</header>	
	<br><br>
	<hr style="color:gray">
	<br><br><br>
    
	<main class="form-signin">
	
  	<form action="LoginOk.do" method="post">
    <img class="mb-4" src="image/Bean.svg" alt="" width="72" height="57">
    <h1 class="h3 mb-3 fw-normal">로그인</h1>

    <div class="form-floating">
      <input type="email" class="form-control" id="floatingInput" name="email" placeholder="이메일 입력" required="required">
      <label for="floatingInput">이메일 계정</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="floatingPassword" name="password" placeholder="비밀번호 입력" required="required">
      <label for="floatingPassword">비밀번호</label>
    </div>

    <div class="checkbox mb-3"></div>
    <button class="w-100 btn btn-lg btn-warning" id="loginsubmit" type="submit">로그인</button>
    <div class="checkbox mb-3"></div>
    <a href="access_terms.do"><h5>회원가입</h5></a>
    <p class="mt-4 mb-2 text-muted">&copy;Team_4</p>
  </form>
  </main>
  <div class="dining-area dining-padding-top">
		</div>
	<footer>
	<jsp:include page="/include/footer.jsp"></jsp:include>
	</footer>
	</body>
<script src="js/reqReg.js?ver=1"></script>
</html>