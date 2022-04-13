<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page import="java.io.PrintWriter" %>
<%	request.setCharacterEncoding("UTF-8");
String	managerId = "";
// 매니저의 세션 아이디를 가져온다.
managerId = (String)session.getAttribute("managerId");

// 세션 값이 없이 들어온 경우는 로그인 화면으로 보낸다.
if(managerId == null || managerId.equals("")) {
	PrintWriter pw = response.getWriter();
	pw.println("<script>");
	pw.println("alert('로그인을 하셔야 합니다.')");
	pw.println("location.href='/manager/logon/managerLoginForm'");
	pw.println("</script>");
}
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>관리자 메인 화면</title>
	
	<c:choose>
		<c:when test="${isLogOn == 'false'}">
			<script>
			window.onload=function() {
				alert("먼저 로그인을 하셔야 합니다.");
			}
			</script>
		</c:when>
	</c:choose>

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<meta name="viewport" content="width=device-widht, initial-scale=1">

</head>
<body data-spy="scroll" data-target=".navbar" data-offset="50">

<!-- 화면 최상단 부분 -->
<div class="container-fluid" style="background-color:#F44336; color:#FFF; height:200px;">
	<h1>도서 쇼핑몰 관리</h1>
	<h3>관리자가 쇼핑몰에 관한 모든 것을 관리하는 페이지입니다.</h3>
	<p>메뉴바는 화면이 스크롤되면 최상단에 보이게 되고, 스크롤에서 제외됩니다.</p>
</div>

<!-- 관리자 메뉴바 -->
<nav class="navbar navbar-inverse" data-spy="affix" data-offset-top="197">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">BookShop</a>
		</div>
		<div>
			<div class="form-group collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">
							상품관리 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/goods/bookRegisterForm.do">상품등록</a></li>
							<li><a href="/goods/bookList.do">상품목록(수정/삭제)</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">
							배송관리 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="product/bookRegisterForm.do">배송목록</a></li>
							<li><a href="product/bookList.do">배송현황</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">
							통계관리 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="product/bookRegisterForm.do">월별 판매 리스트(꺽은선)</a></li>
							<li><a href="product/bookList.do">월별 판매 리스트(막대)</a></li>
						</ul>
					</li>
					<li><a href="/manager/loginForm.go">로그아웃</a></li>
				</ul>
			</div>
		</div>
	</div>
</nav>

</body>
</html>







