<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
//DB에서 불러온 할일 목록이라고 가정하자
		List<String> todos=new ArrayList<>();
		todos.add("html 공부하기");
		todos.add("css 공부하기");
		todos.add("javascript 공부하기");

%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/todo/list.jsp</title>
</head>
<body>
	<h3>할일 목록입니다</h3>
<ul>
	<% for(String tmp:todos){ %>
	<li><%=tmp %></li>
	<%} %>
</ul>
</body>
</html>