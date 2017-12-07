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
<!-- 
<script type="text/javascript">
	$(function() {
		/* 아이디 유효성 검사 */
		$("#id1").blur(function() {
			var id = $("#id1").val();
			var regex = /^[a-z0-9+]{4,16}$/;
			
			$.ajax({
				type : 'POST',
				url : "memberIdCheck.member",
				data : {
					id : id
				},
				success: function(data) {
					if(id == "") {
						$("#idCheck").text("아이디를 입력하세요");
						$("#id").focus();
						return false;
					}
					if(regex.test(id) == false)
						$("#idCheck").text("4~16자의 영문 소문자, 숫자만 사용 가능합니다.");
					else if(data.trim()=="true" && id != "") {
						$("#idCheck").text("사용 가능한 아이디입니다.");
						$("#idCheck").css("color", "green");
					} else {
						$("#idCheck").text("이미 사용중인 아이디입니다.");
					}
				}
			}); // end ajax
		}); // end blur
		
		/* 유효성 검사 */
		$("#pw1").change(function() {
			var val = $(this).val();
			var regex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}/;
			if(regex.test(val) == false) {
				$("#pwCheck").text("비밀번호는 8~16자리이어야 하며 공백 없이 영문과 숫자, 특수문자를 반드시 포함하여야 합니다.");
				$(this).val("");
			}
			else
				$("#pwCheck").text("");
		});
		
		/* 필수 입력란 */
		$("#joinForm").submit(function () {
			if($("#pw1").val() == "") {
				alert("비밀번호를 입력하세요");
				$("#pw1").focus();
				return false;
			} else if($("#pw2").val() == "") {
				alert("비밀번호를 확인하세요");
				$("#pw2").focus();
				return false;
			} else if($("#pw1").val() != $("#pw2").val()) {
				alert("비밀번호가 일치하지 않습니다.");
				$("#pw1").focus();
				return false;
			} else if($("#name").val() == "") {
				alert("이름을 입력하세요.");
				$("#name").focus();
				return false;
			} else if($("#phone").val() == "") {
				alert("휴대폰 번호를 입력하세요.");
				$("#phone").focus();
				return false;
			} else if($("#email").val() == "") {
				alert("이메일 주소를 입력하세요");
				$("#email").focus();
				return false;
			}
		}); // end submit
	});
</script> -->
<style>
	label {
		float: left;
	}
</style>
<body>
<section>
	<h1 style="text-align: center;">회원가입</h1>
		<div class="container">
			<form style="text-align: center;" id="frm" name="frm" 
				class="form-horizontal" action="memberJoin" method="post">
				<div class="form-group">
					<label class="control-label " for="pw">아이디</label>
						<div class="">
							<input type="text" class="form-control" id="id"
								placeholder="아이디" name="id">
							<p id="idCheck"></p>	
						</div>
					</div>

					<!-- Pw -->
					<div class="form-group">
						<label class="control-label " for="pw">비밀번호</label>
						<div class="">
							<input type="password" class="form-control" id="pw1"
									placeholder="비밀번호를 입력해 주세요" name="pw">
							<p id="pwCheck"></p>
						</div>
					</div>

					<!-- Confirm Pw -->
					<div class="form-group">
						<label class="control-label " for="pw">비밀번호 확인</label>
						<div class="">
							<input type="password" class="form-control" id="pw2"
								placeholder="비밀번호를 다시 한번 입력해 주세요">
						</div>
					</div>

					<!-- Name -->
					<div class="form-group">
						<label class="control-label " for="name">이름</label>
						<div class="">
							<input type="text" class="form-control" id="name"
								placeholder="이름을 입력해 주세요" name="name">
						</div>
					</div>

					<!-- Phone -->
					<div class="form-group">
						<label class="control-label " for="phone">휴대폰</label>
						<div class="">
							<input type="text" class="form-control" id="phone"
								placeholder="휴대폰 번호를 입력해 주세요(-제외)" name="phone" maxlength="11">
						</div>
					</div>
						
					<!-- Age -->
					<div class="form-group">
						<label class="control-label " for="phone">나이</label>
						<div class="">
							<input type="text" class="form-control" id="age"
								placeholder="나이를 입력해 주세요" name="age" maxlength="3">
						</div>
					</div>
						
					<!-- Job -->
					<div class="form-group">
						<label class="control-label " for="job">Job</label>
						<div>
							<input type="radio" class="" id="job" name="job" value="teacher">Teacher
							<input type="radio" class="" id="job" name="job" value="student">Student
						</div>
					</div>

					<!-- 회원가입, 취소 -->
					<div class="form-group">
						<div class="col-sm-offset-2 ">
							<button type="submit" class="btn btn-default">회원가입</button>
							<a href="../" class="btn btn-default">취소</a>
						</div>
					</div>
				</form>
			</div>
	</section>
</body>
</html>