<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>


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
      /* .help-block 을 일단 보이지 않게 설정 */
        #myForm .help-block{
            display: none;
        }
        /* glyphicon 을 일단 보이지 않게 설정 */
        #myForm .glyphicon{
            display: none;
        }
        
        .all{
        	height : 100%;
        	width : 600px;
        	margin : auto;
        	background:  #f5f5f5;
  			box-shadow: 13px 13px 20px #cbced1,
              -13px -13px 20px #ffffff;
        	
        }
        .tdmail .tdpw .tdpwch .tdnn{
        	height : 15px;
        }
    </style>
    
   <script type="text/javascript"> 
	$(document).ready(function(){ 
		$('#btn1').on('click', function(){ 
			$.ajax({ type: 'POST',
				url: '${pageContext.request.contextPath}/emailCheck.do',
				data: { "email" : $('#email').val() }, 
				success: function(data){ 
					if($.trim(data) == 0)
					{ 
						alert('사용가능한 이메일 입니다.');
					}else{ 
						alert('사용불가, 다른 이메일을 입력해주세요.');
					} 
					} 
				}); 
			}); 
		
		$('#btn2').on('click', function(){ 
			$.ajax({ type: 'POST', 
				url: '${pageContext.request.contextPath}/nickCheck.do', 
				data: { "nickname" : $('#nickname').val() }, 
				success: function(data){ 
					if($.trim(data) == 0)
					{ 
						alert('사용가능한 닉네임 입니다.');
					}else{ 
						alert('사용불가, 다른 닉네임을 입력해주세요.');
					} 
					} 
				}); 
			}); 
		}); 
	</script>
</head>
<body class="text-center">

  <header>
	<jsp:include page="/include/header.jsp"></jsp:include>
	</header>	
	<br><br>
	<hr style="color:gray">
	<br><br><br>
    
	<main class="form-signin">
	
  	<form action="RegisterOk.do" method="post" onsubmit= "return emailcheck(this)">
    <img class="mb-4" src="image/Bean.svg" alt="" width="72" height="57">
    <h1 class="h3 mb-3 fw-normal">회원가입</h1>
    

    <div class="form-floating">
      <input type="email" class="form-control" id="email" name="email" placeholder="이메일 입력" required="required">
      <label for="floatingInput">이메일 계정</label>
    </div>
    <div class="tdmail"></div>
      <button class="w-20 btn btn-sm btn-warning" id="btn1" name="btn1" type="button">중복체크</button>
       <br>
       <br>
     

<div class="form-floating">
      <input type="text" class="form-control" id="nickname" name="nickname"  placeholder="닉네임 입력" required="required">
      <label for="floatingInput">닉네임</label>
    </div>
    <div class="tdnn"></div>
    <button class="w-20 btn btn-sm btn-warning" id="btn2" name="btn2" type="button">중복체크</button>
       <br>
       <br>

    <div class="form-floating">
      <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호 입력" required="required">
      <label for="floatingPassword">비밀번호</label>
    </div>
	<div class="tdpw"></div>
	
<div class="form-floating">
      <input type="password" class="form-control" id="password2" name="password2" placeholder="비밀번호 입력" required="required">
      <label for="floatingPassword">비밀번호 확인</label>
    </div>
    <div class="tdpwch"></div>

    <div class="checkbox mb-3"></div>
    <button class="w-100 btn btn-lg btn-warning" id="submit" type="submit">회원가입</button>
    <div class="checkbox mb-3"></div>
    <a href="main.bo"><h5>취소</h5></a>
    <p class="mt-4 mb-2 text-muted">&copy;Team_4</p>
  </form>
  </main>
  <div class="dining-area dining-padding-top">
    
</div>

	<footer>
	<jsp:include page="/include/footer.jsp"></jsp:include>
	</footer>
	 
<br><br><br><br><br><br>
	</body>
	<script type="text/javascript">
	$(document).ready(function(){
		
	function check_pwd(){
		console.log("뭐");
		let password = $('#password').val();
		let num = password.search(/[0-9]/g);
		let eng = password.search(/[0-9]/ig);
		let spe = password.search(/[`~!@#$%^&*|\\\'\";:\/?]/gi);
		let text = "";
		//1. 6자리 ~12자리
		if(password.length < 6 || password.length > 12){
			$('.tdpw').text("6-12자리 이내로 입력하세요");
			return false;
		} else if (password.search(/\s/)!=-1){
			$('.tdpw').text("공백은 입력할 수 없습니다");
			return false;
		} else if (num < 0 || eng < 0  || spe < 0 ){
			$('.tdpw').text("영문, 숫자, 특수문자를 포함하여 입력하세요");
			return false;
		} else {
			$('.tdpw').text("");
			return true;
		}
		if(password == ''){
			alert('비밀번호를 입력하세요');
		}
	}
	/* 비밀번호 일치 체크 */
	function check_pwd2(){
		let check = $('#password2').val();
		let pwd = $('#password').val();
		if(!(check===pwd)){
			$('.tdpwch').text("비밀번호가 일치하지 않습니다");
			return false;
		}else {
			$('.tdpwch').text("");
			return true;
		}
		
		if(check == ''){
			alert('비밀번호를 입력하세요');
		}
	}
	$('#password').on('keyup', check_pwd);
	$('#password2').on('keyup', check_pwd2);
	 
		//이메일 유효성 체크
		function check_email(){
			let userEmail = $('#email').val();
			let regExp = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
			if(!regExp.test(userEmail)) {
	    	$('.tdmail').text('이메일 주소가 올바르지 않습니다');
	    	return false;
			}else if(userEmail.length < 1){
				$('.tdmail').text('이메일 주소를 입력하세요');
				return false;
			}else {
			$('.tdmail').text("");
			return true;
		}
			if(userEmail == ''){
			alert('이메일 주소를 입력하세요');
		}
	}
		$('#email').on('keyup', check_email);

		/* 닉네임 유효성체크 */
	function check_nickname(){
		let nickname = $('#nickname').val();
		let regExp = /[가-힣]{3,6}/gm;
		
		//1. 3자리 ~5자리
		
		if(!regExp.test(nickname)) {
	    	$('.tdnn').text('3-5자리 이내로 입력하세요(한글만 가능)');
	    	return false;
	    	
		}else if(nickname.length < 3 || nickname.length > 5){
			$('.tdnn').text("3-5자리 이내로 입력하세요(한글만 가능)");
			return false;
		} else if (nickname.search(/\s/)!=-1){
			$('.tdnn').text("공백은 입력할 수 없습니다");
			return false;
		} else {
			$('.tdnn').text("");
			return true;
		}
		if(nickname == ''){
			alert('닉네임을 입력하세요');
		}
	}
	$('#nickname').on('keyup', check_nickname);
	

		$('#submit').click(function(){

			/* if(check_pwd() == false) return false; //비밀번호
			if(check_pwd2() == false) return false; //비밀번호 확인
			if(check_email() == false) return false; //이메일
			if(check_nickname() == false) return false; //닉네임
			 */
			if(check_pwd() == false || check_pwd2() == false) return false; //비밀번호
			if(check_email() == false) return false; //이메일
			if(check_nickname() == false) return false; //닉네임
			
			return true;
		});
		
		
		//reset
		$("#reset").click(function() {  
	     $("#signForm")[0].reset();    
	});
	});
</script>
</html>