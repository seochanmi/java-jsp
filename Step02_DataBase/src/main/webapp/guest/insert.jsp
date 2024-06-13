<%@page import="test.guest.dao.GuestDao"%>
<%@page import="test.guest.dto.GuestDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1.  폼 전송되는 writer, content, pwd를 추출한다
	String writer=request.getParameter("writer");
	String content=request.getParameter("content");
	String pwd=request.getParameter("pwd");
	
	//2. DB 에 저장한다
	//글정보를 GuestDto에 담는다.
	GuestDto dto=new GuestDto();
	dto.setWriter(writer);
	dto.setContent(content);
	dto.setPwd(pwd);
	//GuestDao 객체의 참조값 얻어오기
	GuestDao dao=GuestDao.getInstance();
	boolean isSuccess=dao.insert(dto);
	
	//3. 응답한다
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
	<div class="container">
		<h3>알림</h3>
	<%if(isSuccess){ %>
		<p class="alert alert-success">
			<%=writer %> 님이 작성한 글을 성공적으로 저장 했습니다.
			<a class="alert-link"
			href="${pageContext.request.contextPath }/guest/list.jsp">목록보기</a>
		</p>
		<%}else{ %>
			<p class="alert alert-danger">
				회원 정보 저장 실패!
				<a class="alert-link" href="${pageContext.request.contextPath }/guest/inserform.jsp">다시 작성</a>
			</p>
			<%} %>
	</div>
</body>
</html>