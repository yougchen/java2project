<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增小說資料</title>
<link href="<c:url value="/resources/css/mystyle.css" />" rel="stylesheet" type="text/css">
</head>
<body>
	<br/>
	<br/>
	<h2>新增小說</h2>
	<form:form name="form" modelAttribute="book">
		<table class="frame">	
			<form:input type="hidden" name="book_id" path="book_id"/>
			<tr>
				<td>*書籍名稱</td>
				<td>
					<form:input type="text" path="book_Name"/>
				    <form:errors path="book_Name" cssClass="error"></form:errors>
				</td>
			</tr>
			<tr>
				<td>*作者名稱</td>
				<td>
					<form:input type="text" path="writer_Name"/>
				    <form:errors path="writer_Name" cssClass="error"></form:errors>
				
				</td>
			</tr>
			<tr>
				<td>*書籍簡介</td>
				<td>
					<form:textarea name="book_information" rows="10" cols="30" class="txt" path="book_information"></form:textarea>
                	<form:errors path="book_information" cssClass="error"></form:errors>
				</td>
			</tr>
		</table>
		<button type="submit" formaction="${pageContext.request.contextPath}/binsert?url=binsert" formmethod="post" onclick="return doConfirm()">存檔</button>
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