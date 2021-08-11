<%@page import="kr.or.bit.communityboard.dto.CommunityBoard"%>
<%@page import="kr.or.bit.communityboard.service.CommunityBoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	//수정하기
	String c_number = request.getParameter("c_number");
	if(c_number == null || c_number.trim().equals("")){
		response.sendRedirect("board_list.jsp"); //cpage=1 , ps=5
		return;
	}
	CommunityBoardService service = CommunityBoardService.getInBoardService();
	CommunityBoard board = service.board_EditContent(c_number);
	if(board == null){
		out.print("데이터 오류");
		out.print("<hr><a href='board_list.jsp'>목록가지</a>");
		return;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>게시글 편집창</title>
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
	function editCheck() {
		if (!edit.c_title.value) {
			alert("제목을 입력하세요");
			edit.c_title.focus();
			return false;
		}
		if (!edit.c_content.value) {
			alert("글 내용을 입력하세요");
			edit.c_content.focus();
			return false;
		}

		document.edit.submit();
	}
</script>
</head>
<body>
	<%
		pageContext.include("/include/header.jsp");
	%>
	<div id="pageContainer">
		<div style="padding-top: 25px; text-align: center">
			<!-- form 시작 -->
			<form name="edit" action="board_editok.ha" method="POST">
				<center>
					<table width="90%" border="1">
						<tr>
							<td width="20%" align="center"><b> 글번호 </b></td>
							<td width="30%">
									<%=c_number%> 
									<input type="hidden" name="c_number" value="<%=c_number%>"></td>
							<td width="20%" align="center"><b>작성일</b></td>
							<td><%=board.getcWritedate()%></td>
						</tr>
						<tr>
							<td width="20%" align="center"><b>이메일</b></td>
							<td>
								<input type="text" name="email" value="<%=board.getEmail()%>">
							</td>
						</tr>

						<tr>
							<td width="20%" align="center"><b>제목</b></td>
							<td colspan="3">
								<input type="text" name="c_title" value="<%=board.getcTitle()%>" size="40">
							</td>
						</tr>
						<tr height="100">
							<td width="20%" align="center"><b>글내용</b></td>
							<td colspan="3">
								<textarea rows="7" cols="50" name="c_content">
									<%=board.getcContent()%>
								</textarea>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="button" value="수정하기" onclick="editCheck();"> 
								<input type="reset" value="다시쓰기">
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<a href="board_list_next.ha">목록</a>
							</td>
						</tr>
					</table>
				</center>
			</form>
		</div>
	</div>
	<div id="emptyspace" style="margin-bottom: 180px;"></div>
	<%
		pageContext.include("/include/footer.jsp");
	%>
</body>
</html>