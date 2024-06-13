
<%@page import="test.friend.dao.FriendDao"%>
<%@page import="test.friend.dto.FriendDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	int num=Integer.parseInt(request.getParameter("num"));
	String name=request.getParameter("name");
	String phone=request.getParameter("phone");
	

	FriendDto dto=new FriendDto(num, name, phone);
	boolean isSuccess = new FriendDao().update(dto);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
		
		<%if(isSuccess){%>
			alert("수정했습니다.");
			location.href="${pageContext.request.contextPath }/friend/list.jsp";
		<%}else{%>
			alert("수정 실패!");
			location.href="${pageContext.request.contextPath }/friend/updateform.jsp?num=<%=num%>";
		<%}%>
	
	</script>
</body>
</html>