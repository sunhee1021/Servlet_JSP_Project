<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- sweet alert -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css" />


<%
  String msg = (String)request.getAttribute("msg");
  String url = (String)request.getAttribute("url");
  
  if(msg != null && url != null){
%>
	<script>
		swal('<%= msg %>');		
	    location.href='<%=url%>';
	</script>
	
<%	  
  }
%>