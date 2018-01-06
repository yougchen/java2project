<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增客戶資料</title>
<link href="<c:url value="/resources/css/mystyle.css" />" rel="stylesheet" type="text/css">
</head>
<body>
	<form:form name="form" modelAttribute="mark">
		<table class="frame">	
			<form:input type="hidden" name="mark_id" path="mark_id"/>
			<tr>
				<td>*書籍的標籤名稱</td>
				<td><form:input type="text" path="mark_Name"/></td>
			</tr>
			<tr>
				<td>*標籤特色</td>
				<td><form:input type="text" name="mark_information" class="txt" path="mark_information"/></td>
			</tr>
		</table>
		<button type="submit" formaction="${pageContext.request.contextPath}/minsert" formmethod="post" onclick="return doConfirm()">存檔</button>
		<button	type="button" onclick="goBack()">回上一頁</button>
	</form:form>
	<script>	
		function doConfirm() {
			return confirm("標籤確定要存檔嗎 ?");
		}
		function goBack() {
		    window.history.back()
		}
	</script>
</body>
</html>