<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>우편번호 검색</title>
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
<body>

<h1 align="center">주 소 검 색</h1>
<div class="container">
	<form class="form-horizontal" name="zipForm" method="post">
		<div class="form-group">
			<label class="col-sm-2">우편번호</label>
			<div class="col-sm-6">
				<input type="text"	 class="form-control" name="zipcode" id="zipcode" readonly/>
				<input type="button" class="form-control" onclick="daumZipCode()" value="우편번호검색"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2">주    소</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" name="address01" id="address01"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2">상세주소</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" name="address02" id="address02"/>
			</div>
		</div>
	</form>
</div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
var themeObj = {
		   //bgColor: "#5599FF", //바탕 배경색
		   searchBgColor: "#6699DD", //검색창 배경색
		   //contentBgColor: "", //본문 배경색(검색결과,결과없음,첫화면,검색서제스트)
		   //pageBgColor: "", //페이지 배경색
		   //textColor: "#FFFFFF", //기본 글자색
		   queryTextColor: "#FFFFFF", //검색창 글자색
		   //postcodeTextColor: "", //우편번호 글자색
		   //emphTextColor: "", //강조 글자색
		   //outlineColor: "" //테두리
		};

function daumZipCode() {
	new daum.Postcode({
		theme: themeObj,
		oncomplete: function(data) {
			// 팝업 창에서 검색한 결과 중 항목을 클릭하였을 경우에 실행할 코드를 이곳에 작성한다.
			
			// 각 주소의 노출규칙에 따라서 주소를 조합한다.
			// 내려오는 변수의 값이 없을 경우에는 공백('') 값을 가지므로, 이름을 참조하여 분기한다.
			var fullAddress	= '';	// 최종   주소 변수
			var	subAddress	= '';	// 조합형 주소 변수
			
			// 사용자가 선택한 주소의 타입에 따라서 해당 주소 값을 가져온다.
			if(data.userSelectedType == 'R') {	// 도로명 주소를 선택한 경우
				fullAddress	= data.roadAddress;
			} else {	// 지번 주소를 선택한 경우
				fullAddress = data.jibunAddress;
			}
			
			// 사용자가 선택한 주소가 도로명 타입일 때 주소를 조합한다.
			if(data.userSelectedType == 'R') {
				// 법정동명이 있을 경우 추가한다.
				if(data.bname != '') {
					subAddress += data.bname;
				}
				// 건물명이 있을 경우 추가한다.
				if(data.buildingName != '') {
					subAddress += (subAddress != '' ? ', ' + data.buildingName : buildingName);
				}
			}
			
			// 조합형 주소의 유무에 따라서 양쪽에 괄호를 추가하여 최종 주소를 만든다.
			fullAddress += (subAddress != '' ? '(' + subAddress + ')' : '');
			
			// 우편번호와 주소 정보를 해당 필드에 출력시킨다.
			document.getElementById('zipcode').value	= data.zonecode;	// 우편번호
			document.getElementById('address01').value	= fullAddress;		// 주소내용
			
			// 커서를 상세주소 입력 필드에 나타나게 한다.
			document.getElementById('address02').focus();
		}
	    //theme: {
	    //    searchBgColor: "#0B65C8", //검색창 배경색
	    //    queryTextColor: "#FFFFFF" //검색창 글자색
	    //}
	// }).open();	// open()만 기술을 하면 팝업 창이 여러 개 나타나게 된다.
	}).open({
		// 우편번호 팝업 창이 여러 개 뜨는 것을 방지하기 위해 popupName을 사용한다.
		popupName:	'postcodePopup'
	});
}
</script>

</body>
</html>
















