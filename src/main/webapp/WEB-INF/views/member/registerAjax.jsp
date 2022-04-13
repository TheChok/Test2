<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
</head>
<body>

<h1 align="center">회원 가입(Ajax)</h1>
<div class="container">
	<form class="form-horizontal" method="post" action="/member/register">
		<div class="form-group">
			<div class="col-sm-2"></div>
			<div class="col-sm-6">
				<h2 align="center">회원 가입(실시간 아이디 검사)</h2>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">아이디</label>
			<div class="col-sm-2">
				<input type="text" class="form-control" id="id" name="id" maxlength="10" placeholder="Enter ID"/>
			</div>
			<div class="col-sm-2">
				<button class="idCheck btn btn-warning" type="button" id="idCheck" onclick="fn_idCheck();" value="N">중복확인</button>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">비밀번호</label>
			<div class="col-sm-3">
				<input type="password" class="form-control" id="pwd" name="pwd" maxlength="20" placeholder="Enter Password"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">이    름</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="name" name="name" maxlength="50" placeholder="Enter Name"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2">이메일</label>
			<div class="col-sm-4">
				<input type="email" class="form-control" id="email" name="email" maxlength="50" placeholder="Enter Email"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-success" type="submit" id="submit">회원가입</button>
				<button class="btn btn-primary signUpBtn" type="submit" disabled="disabled">회원가입</button>
				<button class="cancel btn btn-danger" type="button">취소</button>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="text" class="form-control msg" name="msg" placeholder="Message" id="msg"/>
			</div>
			<div id="msg2"></div>
		</div>
	</form>
</div>

<script>
$(document).ready(function() {
	// 취소
	$(".cancel").on("click", function() {
		location.href = "/member/loginForm.do";
	});
	
	// 아이디 입력란에 글자를 쓰면 실시간으로 사용가능한 아이디인지 검사한다.
	$("#id").on("input", function() {
		// alert("글자를 입력하셨습니다.");
		var inputID = $("#id").val();
		// alert(inputID);
		
		$.ajax({
			url:		"/member/idCheck",
			type:		"post",
			dataType:	"json",
			data:		{"id" : $("#id").val()},
			success: function(data) {
				if(inputID == "" && data == '0') {
					$(".signUpBtn").prop("disabled", true);
					$(".signUpBtn").css("background-color", "#AAAAAA");
					$("#idCheck").css("background-color", "#FFCECE");
					document.getElementById("msg2").innerHTML = "아이디를 입력하십시오.";
					document.getElementById("msg").value = "아이디를 입력하십시오.";
				} else if(inputID != "" && data == '0' && inputID.length >= 4) {
					$("#idCheck").css("background-color", "#B0F6AC");
					document.getElementById("msg2").innerHTML = "사용이 가능한 아이디입니다.";
					document.getElementById("msg").value = "사용이 가능한 아이디입니다.";
					$("#msg").css("background-color", "#B0F6AC");
					$(".signUpBtn").prop("disabled", false);
					$(".signUpBtn").css("background-color", "#4CAF50");
				} else if(data == '1') {
					$(".signUpBtn").prop("disabled", true);
					$(".signUpBtn").css("background-color", "#AAAAAA");
					$("#idCheck").css("background-color", "#FFCECE");
					document.getElementById("msg2").innerHTML = "이미 사용 중인 아이디입니다.";
					document.getElementById("msg").value = "이미 사용 중인 아이디입니다.";
					$("msg").css("background-color", "#FFCECE");
				}
			}
		});
	});
	
	$("#submit").on("click", function() {
		if($("#id").val() == "") {
			alert("아이디를 입력하십시오.");
			$("#id").focus();
			return false;
		}
		if($("#pwd").val() == "") {
			alert("비밀번호를 입력하십시오.");
			$("#pwd").focus();
			return false;
		}
		if($("#name").val() == "") {
			alert("이름을 입력하십시오.");
			$("#name").focus();
			return false;
		}
		if($("#email").val() == "") {
			alert("이메일을 입력하십시오.");
			$("#email").focus();
			return false;
		}
	});
	
});

function fn_idCheck() {
	$.ajax({
		url:		"/member/idCheck",
		type:		"post",
		dataType:	"json",
		data:		{"id" : $("#id").val()	},
		success:	function(data) {
			if(data == 1) {
				alert("이미 사용 중인 아이디입니다.");
			} else if(data == 0) {
				$("#idCheck").attr("value", "Y");
				alert("사용 가능한 아이디입니다.");
			}
		}
	});
}
</script>

</body>
</html>















