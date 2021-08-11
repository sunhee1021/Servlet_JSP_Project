<%@page import="kr.or.bit.ainboard.utils.ThePager"%>
<%@page import="kr.or.bit.ainboard.dto.AinBoard"%>

<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">
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
 
 .ainboard-margin {
 	width: 100%;
 	height: 50px;
 }
 
 .font-back-tittle .archivment-front h3 {
 	color: #587D4E;
 }
 
 .a, button {
    color: #587D4E;
    outline: medium none;
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


</style>

</head>
<body>
	<c:import url="/include/header.jsp" />

	<c:set var="pagesize" value="${requestScope.pagesize}" />
	<c:set var="cpage" value="${requestScope.cpage}" />
	<c:set var="pagecount" value="${requestScope.pagecount}" />
	<c:set var="memberList" value="${requestScope.memberList}" />
	<c:set var="pager" value="${requestScope.pager}" />

	<div class="ainboard-margin"></div>

	<div class="font-back-tittle mb-50">
		<div class="archivment-front">
			<h3> MemberList </h3>
		</div>
		<h3 class="archivment-back">AdminPage</h3>
	</div>
	
	<div class="ainboard-margin"></div>

	<div id="pagecontainer" style="margin-bottom: 200px;">
		<div style="padding-top: 30px; text-align: cetner">
			<table width="80%" border="1" cellspacing="0" align="center" class="aintable">
				<tr>
					<td colspan="5" style="text-align: right;">
						<form name="list" >
							<select name="ps" onchange="submit()">
							   <c:forEach var="i" begin="5" end="20" step="5">
							   		<c:choose>
							   			<c:when test="${pagesize == i}">
							   				<option value="${i}" selected>${i}개씩</option>
							   			</c:when>
						   				<c:otherwise>
						   					<option value="${i}">${i}개씩</option>
						   				</c:otherwise>
							   		</c:choose>
							   </c:forEach>
		   					</select>
						</form>
					</td>
				</tr>
				
				<tr style="text-align: center; background: #F2CF7C;">
					<th width="20%">닉네임</th>
					<th width="40%">이메일</th>
					<th width="20%">비밀번호</th>
					<th width="10%">회원상태</th>
					<th width="10%"> </th>
					<!-- 여유있으면 게시글조회기능 넣자 ㅠ_ㅠ ... -->
				</tr>
				<!-- 데이터가 한건도 없는 경우  -->

				<!-- forEach()  목록 출력하기  -->
				<c:forEach var="member" items="${memberList}">
					<tr onmouseover="this.style.backgroundColor='#FAECCC'" onmouseout="this.style.backgroundColor='white'">
						<td align="center">${member.nickname}</td>
						<td align="center">${member.email}</td>
						<td align="center">${fn:substring(member.password,0,2)}****</td>
						<td align="center">
							<c:if test="${member.division eq 0}">
							<span class="badge badge-primary">관리자</span>
							</c:if>
							<c:if test="${member.division eq 1}">
							<span class="badge badge-success">회원</span>
							</c:if>
							<c:if test="${member.division eq 99}">
							<span class="badge badge-danger">활동정지</span>
							</c:if>
						</td>
						<td align="center">
							<!-- <button type="button" class="genric-btn success small" id="memberDelete">
								탈퇴
							</button> -->
							<c:if test="${member.division eq 99}">
								<a href="memberReJoin.ain?email=${member.email}&cp=${cpage}&ps=${pagesize}" 
									type="button" class="genric-btn success small">복구
								</a>
							</c:if>
							<c:if test="${member.division eq 1}">
								<a href="memberDelete.ain?email=${member.email}&cp=${cpage}&ps=${pagesize}" 
									type="button" class="genric-btn success-border small">정지
								</a>
							</c:if>
					</tr>
				</c:forEach>
				<!-- forEach()  -->
				<tr>
					<td colspan="5" align="center">
					${pager}
					</td>
				</tr>
				
			</table>
		</div>
		
		<div class="ainboard-margin"></div>
		
		<!-- 비동기 검색 테이블 -->
		<table id="order-listing" width="60%" border="1" cellspacing="0" align="center" class="aintable">
		<tr>
					<td colspan="5" height="30px" align="center">
						🔍회원 검색🔍				
					</td>			
				</tr>
				<tr>
					<td width="25%"></td>
					<td width="20%" align="right">
						<select id="selectBox" class="form-control">
				            <option value="이메일" selected>이메일</option>
				            <option value="닉네임">닉네임</option>
           				</select>
					</td>
					<td width="30%" align="left">
						<input type="search" id="search" class="form-control" placeholder="Search">
					</td>
					<td width="25%"></td>
		</tr>
		</table>
		<table id="order-listing" width="60%" border="1" cellspacing="0" align="center" class="aintable">
         
         <tr>
					<td colspan="4" height="30px" align="center">
						✨ 검색결과 ✨				
					</td>			
		</tr>
         <tr style="text-align: center; background: #F2CF7C;">
         	<th width="20%">닉네임</th>
			<th width="40%">이메일</th>
			<th width="20%">비밀번호</th>
			<th width="20%">회원상태</th>
         </tr>
         <tbody id="tbody">
         </tbody>
      </table>
	</div>
	
	<div class="ainboard-margin"></div>
	
	<jsp:include page="/include/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$("#memberDelete").click(function(){
		var result = confirm("해당 멤버를 탈퇴시키시겠습니까? \n 탈퇴시킨 유저는 복구할 수 없습니다.");
		
		if(result) {
			location.href = 'memberDelete.ain?email=${member.email}&cp=${cpage}&ps=${pagesize}';
		}
	})
</script>

<script>
   (function($) {
      "use strict";
      
      var keyword = $("#selectBox option:selected").val();
      $('#selectBox').change(function() {
         keyword = $("#selectBox option:selected").val();
      })

      $("#search").keyup(
            function() {
               if (keyword == "이메일") {
                  $.ajax({
                     url : "ainSearch.ain",
                     type : 'POST',
                     dataType : "json",
                     data : {
                        email : $("#search").val()
                     },
                     success : function(data) {
                    	
                        $('#tbody').empty();
                        if($("#search").val() != ""){
                           
                        $.each(data, function(key, value) {
      
                           let startable = $("#tbody");
                           
                              startable += "<tr>";
                              startable += "<td align='center'>" + value.nickname + "</td>";
                              startable += "<td align='center'>" + value.email + "</td>";
                              startable += "<td align='center'>" + value.password.substring(0,2) + "****</td>";
                              if(value.division === "0") {
                            	  startable += "<td align='center'><span class='badge badge-primary'>관리자</span></td>";
                              }else if(value.division === "1"){
                            	  startable += "<td align='center'><span class='badge badge-success'>회원</span></td>";
                              }else {
                            	  startable += "<td align='center'><span class='badge badge-danger'>활동정지</span></td>";
                              }
                              startable += "</tr>";

                           startable += "</table>";
                           
                            $('#tbody').append(startable);
                        });
                     }
                        }

                     });
               } else {
                  $.ajax({
                     url : "ainSearch.ain",
                     type : 'POST',
                     dataType : "json",
                     data : {
                        nickname : $("#search").val()
                     },
                     success : function(data) {
                        $('#tbody').empty();
                        if($("#search").val() != ""){
                        
                        $.each(data, function(key, value) {
                        	let startable = $("#tbody");
                            
                            startable += "<tr>";
                            startable += "<td align='center'>" + value.nickname + "</td>";
                            startable += "<td align='center'>" + value.email + "</td>";
                            startable += "<td align='center'>" + value.password.substring(0,2) + "****</td>";
                            if(value.division === "0") {
                          	  startable += "<td align='center'><span class='badge badge-primary'>관리자</span></td>";
                            }else if(value.division === "1"){
                          	  startable += "<td align='center'><span class='badge badge-success'>회원</span></td>";
                            }else {
                          	  startable += "<td align='center'><span class='badge badge-danger'>활동정지</span></td>";
                            }
                            startable += "</tr>";

                         startable += "</table>";
                         
                          $('#tbody').append(startable);
                        });
                     }
                        }

                     });
               }
            })

   })(jQuery);
</script>
</html>