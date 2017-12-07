<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://bootswatch.com/4/yeti/bootstrap.min.css">
<style>
	label {
		float: left;
	}
</style>
<body>
	<h1 style="text-align: center;">로그인</h1>
	<div class="container">
		<form style="text-align: center;" id="frm" name="frm" class="form-horizontal" action="memberLogin" method="post">
			<div class="form-group">
				<label class="control-label" for="pw">아이디</label>
				<div class="">
					<input type="text" class="form-control" id="id" placeholder="아이디" name="id">
				</div>
			</div>
	
			<div class="form-group">
				<label class="control-label " for="pw">비밀번호</label>
				<div class="">
					<input type="password" class="form-control" id="pw" placeholder="비밀번호를 입력해 주세요" name="pw">
				</div>
			</div>
			
			<!-- Job -->
			<div class="form-group">
				<div>
					<input type="radio" class="" id="job" name="job" value="teacher">Teacher
					<input type="radio" class="" id="job" name="job" value="student">Student
				</div>
			</div>
			
			<!-- 회원가입, 취소 -->
			<div class="form-group">
				<div class="col-sm-offset-2 ">
					<button type="submit" class="btn btn-default">로그인</button>
					<a href="../" class="btn btn-default">취소</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>