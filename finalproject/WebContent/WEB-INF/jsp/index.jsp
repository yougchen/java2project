<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小說與標籤管理</title>
<link href="<c:url value="/resources/css/mystyle.css" />" rel="stylesheet" type="text/css">
</head>
<body>
<h2>小說與標籤管理</h2>
<br/>
	<form:form id="main" accept-charset="utf-8">
		<table class="frame">
			<input type="hidden" name="mark_id" class="txt" />
			<tr>
				<td>書籍名稱</td>
				<td><input type="text" name="book_Name" class="txt" /></td>
			</tr>
			<tr>
				<td>作者名稱</td>
				<td><input type="text" name="writer_Name" class="txt" /></td>
			</tr>
		</table>
		<button type="submit" formaction="${pageContext.request.contextPath}/binsert" formmethod="get">新增</button>
		<button type="submit" formaction="${pageContext.request.contextPath}/bquery" formmethod="post">查詢</button>
		<button type="reset">清除</button>
	</form:form>
</body>
</html>