<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="notice/noticeList">Notice</a>
<a href="qna/qnaList">QnA</a>
<c:if test="${empty sessionScope.member}">
	<a href="member/memberLogin">Login</a>
	<a href="member/memberJoin">Join</a>
</c:if>
<c:if test="${not empty sessionScope.member}">
	<a href="#">Logout</a>
	<a href="#">My Page</a>
</c:if>
${sessionScope.member}
</body>
</html>
