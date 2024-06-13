<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<a href="${pageContext.request.contextPath }/guest/insertform.jsp">글작성</a>
</head>
<body>
	<div class="container">
		<h1>방명록 목록입니다</h1>
		<a href="insertform.jsp">글작성</a>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>내용</th>
					<th>등록일</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>