<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html style="margin : 100px 100px">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		var message ='${message}';
		if(message != ''){
			alert(message);
		}
		
		$(".list").click(function() {
			var cur = $(this).attr("title");
			var s = '${pager.search}';
			var t = '${pager.kind}';
			document.frm.curPage.value = cur;
			document.frm.search.value = s;
			document.frm.kind.value = t;
			document.frm.submit();
		});
	});
</script>
<style type="text/css">
	.list {
		cursor: pointer;
	}
</style>
<body>
	<c:if test="${board eq 'notice'}">
		<h1>Notice</h1>
	</c:if>
	
	<c:if test="${board eq 'qna'}">
		<h1>QnA</h1>
	</c:if>
	<%-- <h1>${board}</h1> --%>
	
	<form name="frm" action="./${board}List" method="get">
		<input type="hidden" value="1" name="curPage">	
		<select name="kind">
			<option value="title">Title</option>
			<option value="writer">Writer</option>
			<option value="contents">Contents</option>
		</select>
		<input type="text" name="search">
		<button>Search</button>
	</form>

	<table class="table table-hover">
		<tr>
			<td>No</td>
			<td>Title</td>
			<td>Writer</td>
			<td>Date</td>
			<td>Hit</td>
		</tr>
		
		<c:forEach items="${list}" var="dto">
			<tr>
				<td>${dto.num}</td>
				<td>
					<c:catch>
						<c:forEach begin="1" end="${dto.depth-1}">
							&nbsp;&nbsp;
						</c:forEach>
					</c:catch>
					<a href="${board}View?num=${dto.num}">${dto.title}</a>
				</td>
				<td>${dto.writer}</td>
				<td>${dto.reg_date}</td>
				<td>${dto.hit}</td>
			</tr>
		</c:forEach>
	</table>
	
	<a class="btn btn-default" href="${board}Write">Write</a>
		
	<div>
		<c:if test="${pager.curBlock gt 1}">
			<span class="list" title="${pager.startNum-1}">[이전]</span>
		</c:if>
		
		<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
			<span class="list" title="${i}">${i}</span>
		</c:forEach>
		
		<c:if test="${pager.curBlock lt pager.totalBlock}">
			<span class="list" title="${pager.lastNum+1}">[다음]</span>
		</c:if>
	</div>
</body>
</html>