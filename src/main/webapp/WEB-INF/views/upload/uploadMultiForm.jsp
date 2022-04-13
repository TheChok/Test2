<!-- page 디렉티브의 session 속성의 기본값은 ture 이므로 false로 지정하지 않으면 자동 생성된다. -->
<!-- 세션을 사용여부 옵션 -->
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   		uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" 		uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
<style>
iframe {
	width: 800px;
	height: 200px;
	border: 1px;
	border-style: solid;
}
</style>
</head>
<body>

<div class="container">

	<!-- 세션변수가 존재하면 
	if( session.getAttribute("userid") != null )
	sessionScope : 세션 영역에 저장된 변수를 말한다.
	세션에 저장된 변수는 세션이 종료될 때 까지는 유지된다.
	기본 15분이나 연장시킬 수 있다.	
	자바코드로는 아래와 같다.
	// session.getAttribute("userid");
	-->
	<!--  
	<c:if test="${sessionScope.userid != null }">
		<h2>
			${sessionScope.name}
			(${sessionScope.userid})님의 방문을 환영합니다.
		</h2>
	</c:if>
	<hr>
	-->

	<div align="center">
		<h2>여러 파일 올리기</h2>
	</div>

	<!-- 파일 업로드를 위한 필수 속성
	enctype="multipart/form-data" -->
	
	<!-- target="iframe1" -->
	<!-- 파일을 올리면 원래는 action="${path}/upload/uploadForm"에 가야하는데 
	     페이지를 넘어가지 않기 위해서 제출된 결과를 iframe으로 보낸다.-->
	<form name="fileForm" action="${path}/upload/uploadMultiForm" method="post" enctype="multipart/form-data">
		<!-- name="file" 의 변수명과 컨트롤러의 MultipartFile file과 일치해야 한다. -->
        <input multiple="multiple" type="file" name="file" />
        <input type="text" name="src" />
        <input type="submit" value="전송" />
	</form>
	<!-- iframe에 업로드 결과 출력 -->
	<iframe name="iframe1"></iframe>
	
	
</div>

</body>

</html>




















