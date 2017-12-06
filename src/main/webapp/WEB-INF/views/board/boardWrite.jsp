<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html style="margin: 100px 100px">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="../resources/SE2/js/HuskyEZCreator.js"></script>

<script type="text/javascript">
	$(function() {
	    //전역변수선언
	    var editor_object = [];
	     
	    nhn.husky.EZCreator.createInIFrame({
	        oAppRef: editor_object,
	        elPlaceHolder: "contents",
	        sSkinURI: "../resources/SE2/SmartEditor2Skin.html",
	        htParams : {
	            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
	            bUseToolbar : true,            
	            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
	            bUseVerticalResizer : true,    
	            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
	            bUseModeChanger : true,
	        }
	    });
	     
	    //전송버튼 클릭이벤트
	    $("#savebutton").click(function(){
	        //id가 smarteditor인 textarea에 에디터에서 대입
	        editor_object.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);
	         
	        // 이부분에 에디터 validation 검증
	         
	        //폼 submit
	        $("#frm").submit();
	    });
	});
	
	$(function() {
		var index = 0;
		var count = 0;
		$("#add").click(function() {
			 if(index < 5){
				var s = '<div id="d'+count+'">';
				s = s+'<input type="file" name="f1"><span class="del" title="d'+count+'">X</span></div>';
				$("#files").append(s);
				count++;
				index++;
			 }else {
				 alert("최대 5개의 파일까지만 가능합니다.");
			 }
		});
		
		$("#files").on("click",".del",function(){
			var id=$(this).attr("title");
			$("#"+id).remove();
			index--;
		});
	});
</script>
<style>
	textarea {
		resize: none;
		width:100%;
		height: 500px";
	}
</style>
<body>
	<c:if test="${board eq 'notice'}">
		<h1>Notice Write</h1>
	</c:if>
	
	<c:if test="${board eq 'qna'}">
		<h1>QnA Write</h1>
	</c:if>

	<form id="frm" action="${board}Write" method="post" enctype="multipart/form-data">
		<table class="table table-hover">
			<tr>
				<td>Title</td>
				<td>Writer</td>
			</tr>
			
			<tr>
				<td><input class="form-control" type="text" name="title" placeholder="Title"></td>
				<td><input class="form-control" type="text" name="writer" placeholder="Writer"></td>
			</tr>
		</table>
		<input type="button" value="File Add" id="add">
		<div id="files"></div>
		<textarea id="contents" name="contents"></textarea>
		
		<button class="btn btn-default">Write</button>
	</form>
</body>
</html>