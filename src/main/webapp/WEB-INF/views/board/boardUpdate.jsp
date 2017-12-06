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
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript" src="../resources/SE2/js/HuskyEZCreator.js"></script>
<script type="text/javascript">
	$(function() {
		var index = ${view.ar.size()};
		var count = 0;
		$("#add").click(function() {
			 if(index < 5){
				var s = '<div id="d'+count+'">';
				s = s+'<input type="file" name="f1"><span class="del" title="d'+count+'">X</span></div>';
				$("#files").append(s);
				count++;
				index++;
			 } else {
				 alert("최대 5개의 파일까지만 가능합니다.");
			 }
		});

		$(".del").click(function() {
			var fnum = $(this).attr("title");
			var message = confirm("파일을 삭제하시겠습니까?");
			if(message == true) {
				$.ajax({
					url: "../util/fileDelete",
					type: "post",
					data: {
						fnum: fnum
					},
					success: function(data) {
						$("#del"+fnum).remove();
						index--;
					}
				});
			} else {
				alert("삭제가 취소되었습니다.");
			}
		});
		
		$("#files").on("click",".del",function(){
			var id=$(this).attr("title");
			$("#"+id).remove();
			index--;
		}); 
	});
	
	
	$(function() {
	    //전역변수선언
	    var editor_object = [];
	     
	    nhn.husky.EZCreator.createInIFrame({
	        oAppRef: editor_object,
	        elPlaceHolder: "contents1",
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
	        editor_object.getById["contents1"].exec("UPDATE_CONTENTS_FIELD", []);
	         
	        // 이부분에 에디터 validation 검증
	         
	        //폼 submit
	        $("#frm").submit();
	    })
	})
</script>
<body>
	<h1>${board} Update</h1>
	<form action="${board}Update" method="post">
		<table class="table table-hover">
			<tr>
				<td>Title</td>
				<td>Writer</td>
			</tr>
			
			<tr>
				<td><input class="form-control" type="text" name="title" value="${view.title}"></td>
				<td><input class="form-control" type="text" name="writer" value="${view.writer}" readonly="readonly"></td>
				<td><input class="form-control" type="hidden" name="num" value="${view.num}" readonly="readonly"></td>
				<td><input class="form-control" type="hidden" name="reg_date" value="${view.reg_date}" readonly="readonly"></td>
				<td><input class="form-control" type="hidden" name="hit" value="${view.hit}" readonly="readonly"></td>
			</tr>
			
			<c:forEach items="${view.ar}" var="file">
				<tr>
					<td id="del${file.fnum}" colspan="5">
						${file.oname}<input class="del btn btn-default" title="${file.fnum}" type="button" value="X" style="margin-left: 20px">
					</td>
				</tr>
			</c:forEach>
			
		</table>
		<input type="button" value="File Add" id="add">
		<div id="files"></div>
		<textarea id="contents1" name="contents" style="resize: none; height: 500px">${view.contents}</textarea>
		
		<button class="btn btn-default">Update</button>
	</form> 
</body>
</html>