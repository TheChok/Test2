<!-- page 디렉티브의 session 속성의 기본값은 ture 이므로 false로 지정하지 않으면 자동 생성된다. -->
<!-- 세션을 사용여부 옵션 -->
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   		uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" 		uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- views/upload/uploadResult.jsp -->
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>

<div class="container">

파일이 업로드되었습니다.<br>
파일명 : ${savedName}

</div>

</body>

</html>





















