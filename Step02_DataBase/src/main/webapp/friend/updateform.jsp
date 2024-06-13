<%@page import="test.friend.dao.FriendDao"%>
<%@page import="test.friend.dto.FriendDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	int num=Integer.parseInt(request.getParameter("num"));


	FriendDto dto=new FriendDao().getData(num);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/friend/updateform.jsp</title>
</head>
<body>
	<div class="container">
		<h3>친구정보 수정 양식</h3>
		<form action="${pageContext.request.contextPath }/friend/update.jsp" method="post">
			<div>
				<label for="num">번호</label>
				<input type="text" name="num" id="num" value="<%=dto.getNum() %>" />
			</div>
			<div>
				<label for="name">이름</label>
				<input type="text" name="name" id="name" value="<%=dto.getName() %>" />
			</div>
			<div>
				<label for="phone">전화번호</label>
				<input type="text" name="phone" id="phone" value="<%=dto.getPhone() %>" />
			</div>
			<button type="submit">수정확인</button>
			<button type="reset">취소</button>
		</form>
	</div>
</body>
</html>