<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//여기는 Java 코딩영역 입니다.
	
	int num=1;
	String name="김구라";

	//msg라는 파라미터명으로 전달되는 문자열 읽어오기
	String msg=request.getParameter("msg");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>send.jsp</title>
</head>
<body>
	<p>클라이언트야 메세지 잘 받았오</p>
	<p>메세지 내용 : <strong><%out.print(msg); %></strong></p>
	<p>메세지 내용 : <strong><%=msg %></strong></p>
</body>
</html>