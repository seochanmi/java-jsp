<%@page import="test.friend.dao.FriendDao"%>
<%@page import="test.friend.dto.FriendDto"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	String name=request.getParameter("name");
	String addr=request.getParameter("phone");
	
	FriendDto dto=new FriendDto();
	dto.setName(name);
	

	//2. DB에 저장한다.
	FriendDao dao=new FriendDao();
	boolean isSuccess=dao.insert(dto);
	//3. 응답한다.
		

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/friend/insert.jsp</title>
</head>
<body>
	<div class="container">
	<h3>알림</h3>
	<%if(isSuccess){ %>
	<p> <strong><%=name %></strong> 님의 정보를 저장했습니다.
		<a href="${pageContext.request.contextPath }/friend/list.jsp">목록보기</a>
	</p>
	<%}else{ %> 
		<p>
			회원 정보 저장 실패!
			<a href="${pageContext.request.contextPath }/friend/insertform.jsp">다시 작성</a>
		</p>
	<%} %>
	</div>
</body>
</html>