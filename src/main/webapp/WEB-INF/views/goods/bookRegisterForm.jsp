<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.Timestamp" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>

<%
Timestamp nowTime = new Timestamp(System.currentTimeMillis());
// 년도 4자리를 정수로 변환하여 저장한다.
int	lastYear	= Integer.parseInt(nowTime.toString().substring(0, 4)); 
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>상품 등록 화면</title>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<meta name="viewport" content="width=device-widht, initial-scale=1">
</head>
<body>

<div class="container">
	<form class="form-horizontal" name="bookWriteform" action="" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<div class="col-sm-2"></div>
			<div class="col-sm-6">
				<h2 align="center">상품 등록</h2>
			</div>
			<div class="col-sm-3">
				<a href="/manager/managerMain" class="btn btn-success">메인메뉴</a>
				<a href="#" class="btn btn-info">목록으로</a>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">책 종류</label>
			<div class="col-sm-2">
				<select class="form-control" name="book_type" id="book_type">
				<c:forEach var="bookTypes" items="${bookTypList}">
					<option value="${bookTypes.getType()}">${bookTypes.getName()}</option>
				</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">제  목</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" maxlength="100" name="book_title"
					onkeydown="nextFocus(book_price)" placeholder="제목"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">가  격</label>
			<div class="col-sm-2">
				<div class="input-group">
					<input type="text" class="form-control" maxlength="8" name="book_price"
						onkeydown="nextFocus(book_count)" placeholder="가격"/>
					<span class="input-group-addon">원</span>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">수  량</label>
			<div class="col-sm-2">
				<div class="input-group">
					<input type="text" class="form-control" maxlength="6" name="book_count"
						onkeydown="nextFocus(book_writer)" placeholder="수량"/>
					<span class="input-group-addon">권</span>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">저  자</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" maxlength="40" name="book_writer"
					onkeydown="nextFocus(publishing_com)" placeholder="저자"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">출판사</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" maxlength="40" name="publishing_com"
					onkeydown="nextFocus(publishing_year)" placeholder="출판사"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">출판일</label>
			<div class="col-sm-2">
				<div class="input-group">
					<select id="publishing_year" class="form-control" name="publishing_year" style="width:120px;"
						onkeydown="nextFocus(publishing_month)">
						<%for(int i = lastYear; i >= 2010; i--) { %>
						<option value="<%=i %>"><%=i %></option>
						<%} %>
					</select>
					<span class="input-group-addon">년</span>
				</div>
			</div>
			<div class="col-sm-2">
				<div class="input-group">
					<select id="publishing_month" class="form-control" name="publishing_month" style="width:120px;"
						onkeydown="nextFocus(publishing_day)">
						<c:forEach var="month" begin="1" end="12">
							<option value="${month}">${month}</option>
						</c:forEach>
					</select>
					<span class="input-group-addon">월</span>
				</div>
			</div>

			<div class="col-sm-2">
				<div class="input-group">
					<select id="publishing_day" class="form-control" name="publishing_day" style="width:120px;"
						onkeydown="nextFocus(publishing_day)">
						<c:forEach var="day" begin="1" end="31">
							<option value="${day}">${day}</option>
						</c:forEach>
					</select>
					<span class="input-group-addon">일</span>
				</div>
			</div>

			<div class="col-sm-2">
				<div class="input-group">
					<select class="form-control" name="publishing_month" style="width:120px;"
						onkeydown="nextFocus(publishing_day)">
						<%for(int i = 1; i <= 12; i++) { %>
						<option value="<%=i %>"><%=i %></option>
						<%} %>
					</select>
					<span class="input-group-addon">월</span>
				</div>
			</div>
			<div class="col-sm-2">
				<div class="input-group">
					<select class="form-control" name="publishing_day" style="width:120px;"
						onkeydown="nextFocus(book_image)">
						<%for(int i = 1; i <= 31; i++) {%>
						<option value="<%=i%>"><%=i%></option>
						<% } %>
					</select>
					<span class="input-group-addon">일</span>
				</div>
			</div>
		</div>
		
		<input type="hidden" id="publishing_date" name="publishing_date" value=""/>
		
		
		
		<div class="form-group">
			<label class="control-label col-sm-2">출판일</label>
			<div class="col-sm-2">
				<input type="text" class="form-control" id="date" name="date"
					onkeydown="nextFocus(publishing_year)" placeholder="출판일자선택"/>
			</div>
			<div class="col-sm-2">
				<div class="input-group">
					<input type="text" class="form-control" id="i_year" value=""/>
					<span class="input-group-addon">년</span>
				</div>
			</div>
			<div class="col-sm-2">
				<div class="input-group">
					<input type="text" class="form-control" id="i_month" value=""/>
					<span class="input-group-addon">월</span>
				</div>
			</div>
			<div class="col-sm-2">
				<div class="input-group">
					<input type="text" class="form-control" id="i_day" value=""/>
					<span class="input-group-addon">일</span>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-3 col-sm-10">
				연도 : <p id="year"></p>
				월   : <p id="month"></p>
				일   : <p id="day"></p>
				요일 : <p id="dotWeek"></p>
			</div>		
		</div>
		
		
		<div class="form-group">
			<label class="control-label col-sm-2">이미지명</label>
			<div class="col-sm-4">
				<input type="file" class="form-control" name="book_image" onkeydown="nextFocus(book_content)"
					onchange="readURL(this);"/>
				<img id="preview" width="200" height="200"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">내  용</label>
			<div class="col-sm-7">
				<textarea class="form-control col-sm-7" name="book_content"
					rows="10" cols="140" placeholder="책의 내용"></textarea>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2">할인율</label>
			<div class="col-sm-2">
				<div class="input-group">
					<input type="text" class="form-control" size="4" maxlength="4"
						name="discount_rate" onkeydown="nextFocus(btn_OK)" placeholder="할인율"/>
					<span class="input-group-addon">%</span>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-4 col-sm-2">
				<input type="button" class="btn btn-primary" value="등록"
					onclick="checkForm(this.form)" name="btn_OK"/>
				<input type="reset"  class="btn btn-warning" value="다시작성"/>
			</div>
		</div>
		
	</form>
</div>

<script type="text/javascript" src="${contextPath}/resources/js/commonFunction.js"></script>

<script>
/* Enter Key를 누르면 다음 항목으로 이동하는 함수	*/
function nextFocus(where) {
	if(event.keyCode == 13) {
		where.focus();
	}
}

// jQuery를 이용하여 이미지 파일을 첨부할 때 미리보기 기능을 구현한다.
function readURL(input) {
	if(input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#preview").attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}
</script>

<script type="text/javascript">
$(function() {
	$("#date").datepicker({
		dateFormat:		"yy/mm/dd",
		dayNamesMin:	["일", "월", "화", "수", "목", "금", "토"],
		monthNames:		["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
		onSelect:	function(d) {
			// alert(d + "가 선택되었습니다.");
		
			var	arr		= d.split("/");
			var	year	= arr[0];
			var month	= arr[1];
			var	day		= arr[2];
		
			$("#i_year").val(year);
			$("#i_month").val(month);
			$("#i_day").val(day);
			
			$("#year").text(year);
			$("#month").text(month);
			$("#day").text(day);
			
					
		}
	});
});
</script>

</body>
</html>















