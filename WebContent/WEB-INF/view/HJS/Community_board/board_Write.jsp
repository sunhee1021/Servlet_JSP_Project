<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv='content_Type' content='text/html' charset="UTF-8">
	<title>게시판 글쓰기</title>
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
		function check(){
			if(!bbs.title.value){ // 제목 칸이 비었을 경우
				alert("제목을 입력해주세요");
				bbs.title.focus();
				return false;
			}
			if(!bbs.content.value){
				alert("내용을 입력해주세요");
				bbs.content.focus();
				return false;
			}
			document.bbs.submit();
		} 
	</script>

</head>
<body>
	<c:import url="/include/header.jsp" />
	<!-- 헤더부분 추가하기 -->
	<div id="pageContainer">
        <div style="padding-top: 25px; text-align: center">
            <!-- form 시작 ---------->
            <form name="bbs" action="boardWrite_ok.ha" method="POST">
            <a href="boardWrite.ha"></a>
                <table width="95%" border="2" align="center">
                    <tr>
                        <td width="20%" align="center">제목</td>
                        <td width="80%" align="left"><input type="text" name="title" size="40"></td>
                    </tr>
                    <tr>
                        <td width="20%" align="center">글내용</td>
                        <td width="80%" align="left"><textarea rows="10" cols="60" name="content" class="ckeditor"></textarea></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="button" value="글쓰기" onclick="check();" /> 
                            <input type="reset"  value="다시쓰기" />
                            <br>
                            <a href="board_list_next.ha">목록</a>
                        </td>
                    </tr>
                </table>
              </form>
            
        </div>
    </div>
    <div id="emptyspace" style="margin-bottom: 180px;"></div>
    <%
		pageContext.include("/include/footer.jsp");
	%>
</body>
</html>