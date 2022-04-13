<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%-- result에 로그인 결과를 저정한다. --%>
<c:set var="result"		 value="${param.result}"/>

<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자 로그인 화면</title>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<meta name="viewport" content="width=device-widht, initial-scale=1">

	<style>
	.modal-header, h4, .close {
		background-color:	#5CB85C;
		 color:				white !important;
		 text-align:		center;
		 font-size:			30px;
	}
	.modal-footer {
		background-color:	#F9F9F9;
	}
	.form-group {
		font-size:			24px;
	}
	</style>
	
	<c:choose>
		<c:when test="${result =='loginFailed'}">
			<script>
			window.onload=function() {
				alert("존재하지 않는 아이디입니다.\n다시 로그인을 하십시오.");
			}
			</script>
		</c:when>
		<c:when test="${result =='passwordFailed'}">
			<script>
			window.onload=function() {
				alert("비밀번호가 틀립니다.\n다시 로그인을 하십시오.");
			}
			</script>
		</c:when>
	</c:choose>
	
</head>
<body>

<div class="container">
	<h2>Manager Login</h2>
	<!-- Trigger the modal with a button -->
	<button type="button" class="btn btn-warning btn-lg" id="myBtn">Login</button>
	
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		
			<!-- Modal content -->
			<div class="modal-content">
				<!-- header -->
				<div class="modal-header" style="padding: 35px 50px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4><span class="glyphicon glyphicon-lock"></span> Manager Login</h4>
				</div>
				
				<!-- body -->
				<div class="modal-body" style="padding: 40px 50px;">
					<form class="form-horizontal" role="form" method="post" action="managerLogin.go">
						<div class="form-group">
							<label for="managerName">
								<span class="glyphicon glyphicon-user"></span> Manager ID
							</label>
							<input type="text" class="form-control" id="id" name="id" maxlength="10"
								placeholder="Enter Admin ID"/>
						</div>
						<div class="form-group">
							<label for="password">
								<span class="glyphicon glyphicon-eye-open"></span> Password
							</label>
							<input type="password" class="form-control" id="pwd" name="pwd" maxlength="20"
								placeholder="Enter Password"/>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-success btn-block">
								<h3><span class="glyphicon glyphicon-off"></span> Login</h3>
							</button>
						</div>
					</form>
				</div>
				
				<!-- footer -->
				<div class="modal-footer">
					<button type="reset" class="btn btn-danger pull-flef" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove"></span> Cancel
					</button>
				</div>
			</div>
		
		</div>
	</div>
</div>

<script>
$(document).ready(function() {
	$("#myBtn").click(function() {
		$("#myModal").modal();
	});
});

/* document가 준비되면 모달 창을 띄우게 한다. */
$(function() {	
	$("#myModal").modal({
		keyboard:	true
	});
});
</script>

</body>
</html>









