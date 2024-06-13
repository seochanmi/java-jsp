<%@page import="test.friend.dto.FriendDto"%>
<%@page import="java.util.ArrayList"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//DB에서 불러온 멤버 목록이라고 가정하자
		List<FriendDto> friends=new ArrayList<>();
		friends.add(new FriendDto(1,"이지혜","01026554858"));
		friends.add(new FriendDto(2,"홍길동","01026557858"));
		friends.add(new FriendDto(3,"김주현","01014771858"));
		friends.add(new FriendDto(4,"이희은","01048559858"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="insertform.jsp">친구 추가</a>
	<a href="${pageContext.request.contextPath }/friend/insertform.jsp">친구추가</a>
	<h3>친구 목록입니다</h3>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
	</thead>
	<tbody>
		<% for(FriendDto tmp: friends){ %>
			<tr>
				<td><%=tmp.getNum() %></td>
				<td><%=tmp.getName() %></td>
				<td><%=tmp.getPhone() %></td>
				<td><a href="updateform.jsp?num=<%=tmp.getNum() %>">수정</a></td>
				<td><a href="${pageContext.request.contextPath }/friend/delete.jsp?num=<%=tmp.getNum() %>">삭제</a></td>
			</tr>
		<%} %>
		</tbody>
	</table>
</body>
</html>