<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html style="margin : 100px 100px">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<body>
	<h1>${board} View</h1>
	<table class="table table-hover">
		<tr>
			<td>No</td>
			<td>Title</td>
			<td>Writer</td>
			<td>Date</td>
			<td>Hit</td>
		</tr>
		
		<tr>
			<td>${view.num}</td>
			<td>${view.title}</td>
			<td>${view.writer}</td>
			<td>${view.reg_date}</td>
			<td>${view.hit}</td>
		</tr>
	</table>
	<textarea class="form-control" readonly="readonly" rows="" cols="" style="resize: none; height: 500px">${view.contents}</textarea>
	
	<c:forEach items="${view.ar}" var="file">
		<a href="../resources/upload/${file.fname}">${file.oname}</a>
	</c:forEach>	
	<hr>
	<a href="./${board}Update?num=${view.num}">Update</a>
	<a href="./${board}Delete?num=${view.num}">Delete</a>
	<a href="./${board}List">List</a>
</body>
</html>