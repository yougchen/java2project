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
	<form:form name="form" modelAttribute="book">
		<table class="frame">	
			<form:input type="hidden" name="book_id" path="book_id"/>
			<tr>
				<td>*書籍名稱</td>
				<td><form:input type="text" path="book_Name"/></td>
			</tr>
			<tr>
				<td>*作者名稱</td>
				<td><form:input type="text" path="writer_Name"/></td>
			</tr>
			<tr>
				<td>*書籍簡介</td>
				<td><form:input type="text" name="book_information" class="txt" path="book_information"/></td>
			</tr>
			<tr>
				<td>*標籤</td>
				<td>
				<c:forEach var="mark" items="${marks}">
					<a href="${pageContext.request.contextPath}/linsert?mark_id=${ mark.mark_id}" formmethod="post" >${mark.mark_Name}</a>
				</c:forEach>
				</td>
			</tr>
		</table>
		
		<a href="#" role="button" data-onclick="spreadmark(${marks});return false;">書本新增標籤……</a>
		<button type="submit" formaction="${pageContext.request.contextPath}/binsert" formmethod="post" onclick="return doConfirm()">存檔</button>

		<button	type="button" onclick="goBack()">回上一頁</button>
	</form:form>
	<script>	
		function doConfirm() {
			return confirm("書本確定要存檔嗎 ?");
		}
		function goBack() {
		    window.history.back()
		}
	</script>
</body>
</html>