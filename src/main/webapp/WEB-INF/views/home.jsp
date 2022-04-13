<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>

	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<meta name="viewport" content="width=device-widht, initial-scale=1">

</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<hr>
<div class="container">
	<h2>날짜 선택</h2>
	일자: <input type="text" id="datepicker1" placeholder="날짜를 선택하십시오."/>
	<hr>
	일자: <input type="text" id="datepicker2" placeholder="날짜를 선택하십시오."/>
	<hr>
	<p>조회기간을 선택하십시오
		<input type="text" id="datepicker3"/> ~ <input type="text" id="datepicker4"/>
	</p>
</div>

<script>
$("#datepicker1").datepicker();

$("#datepicker2").datepicker({
	// 선택할 수 있는 최대 날짜 +1m +1w 은 1달 1주일 뒤까지 선택이 가능하다.
	// [+, -][숫자][y, m, w, d]
	maxDate:	"+1m +1w", 
	minDate:	"-1y"
});

$(function() {
	$("#datepicker3, #datepicker4").datepicker({
		// 옵션......
	});
});

// 데이트피커의 기본설정
$.datepicker.setDefaults({
	showOn:			"both",		// 버튼이나 텍스트 필드를 선택했을 때 모두 캘린터를 보여준다.
	changeYear:		true,		// 년도를 바꿀 수 있는 셀렉트 박스를 보여준다.
	changeMonth:	true,		// 월을 바꿀 수 있는 셀렉트 박스를 보여준다.
	showAnim:		"slide",	// 애니메이션을 적용한다.
	dateFormat:		'yy-mm-dd',	// 날짜 포맷. 보통 yy-mm-dd를 많이 사용한다.
	prevText:		'이전 달',	// ◀ 버튼에 마우스 오버시 이전달 텍스트를 보여준다.
	nextText:		'다음 달',	// ▶ 버튼에 마우스 오버시 다음달 텍스트를 보여준다.
	closeText:		'닫기',		// 닫기 버튼의 텍스트 변경
	currentText:	'오늘',		// 오늘 텍스트 변경
	// 한들 캘린더 중 월 표시를 위한 부분
	monthNames:		['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	monthNamesShort:['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	dayNames:		['일', '월', '화', '수', '목', '금', '토'],
	dayNamesShort:	['일', '월', '화', '수', '목', '금', '토'],
	dayNamesMin:	['일', '월', '화', '수', '목', '금', '토'],
	showMonthAfterYear:	true,	// true : 년 월, false : 월 년 순으로 보여준다.
	yearSuffix:		'년',		// 년도 뒤에 글자
	showButtonPanel:	true,	// 오늘로 가는 버튼과 달력의 닫기 버튼 보기 옵션
	// buttonImageOnly:		true,	// input 옆에 조그마한 아이콘으로 캘린더 선택가능하게 하기
	// buttonImage:			"images/calendar.gif"	// 조그마한 아이콘 이밎
	// buttonText:			"Select Date"			// 조그마한 아이콘 툴팁
});
</script>

</body>
</html>










