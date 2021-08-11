/**
 * 
 */
	 /* 비밀번호 :
		1. 8자리 ~ 20자리
		2. 공백없이
		3. 영문 + 숫자 + 특수문자 포함
	*/
	
	$(document).ready(function() {
	/* 비밀번호 유효성체크 */
	function check_pwd(){
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
 	$('#userEmail').on('keyup', check_email);

 	/* 닉네임 유효성체크 */
	function check_nickname(){
		let nickname = $('#nickname').val();
		let regExp = /[가-힣]{3,6}/gm;
		
		//1. 3자리 ~5자리
		
		if(!regExp.test(nickname)) {
        	$('.tdnn').text('3-5자리 이내로 입력하세요(한글만 가능)');
        	return false;
        } else if(nickname.length < 3 || nickname.length > 5){
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
 		if(check_pwd() == false) return false; //비밀번호
 		if(check_pwd2() == false) return false; //비밀번호 확인
 		if(check_email() == false) return false; //이메일
 		if(check_nickname() == false) return false; //닉네임
 		
 		return true;
 	});
 	
 	//reset
 	$("#reset").click(function() {  
         $(".form-horizontal")[0].reset();    
});
});