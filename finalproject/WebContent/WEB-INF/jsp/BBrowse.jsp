<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/mystyle.css" />" rel="stylesheet" type="text/css">
<title>客戶資料瀏覽</title>
  <script type="application/javascript">
	 function doReset(){  
	  for(i=0;i<document.all.tags("input").length;i++){  
	      if(document.all.tags("input")[i].type=="text"){  
	          document.all.tags("input")[i].value="";  
	      }  
	  }  
	}  
	</script>	
</head>
<body >
		<a href="${pageContext.request.contextPath}/minsert">新增標籤</a>
		<a href="${pageContext.request.contextPath}/mlist"/>修改標籤</a>
		<br/>
			書本類型
			<c:forEach var="mark" items="${marks}">
					
					<a href="${pageContext.request.contextPath}/marksearchbquery?mark_id=${mark.mark_id}">
						<c:if test = "${mark.mark_id == mark_id}">
							<font color="red">
						</c:if>
						
								${mark.mark_Name}
								
						<c:if test = "${mark.mark_id == mark_id}">
							</font>
						</c:if>
					</a>
			</c:forEach>

	<form:form id="form"  modelAttribute="book" accept-charset="utf-8">
		<table class="frame">
			<tr>
				<td>書籍名稱</td>
				<td><input type="text" value = "${book_Name}" name="book_Name" class="txt"/></td>
			</tr>
			<tr>
				<td>作者名稱</td>
				<td><input type="text" value = "${writer_Name}" name="writer_Name" class="txt"/></td>
			</tr>
		</table>
		<button type="submit" formaction="${pageContext.request.contextPath}/binsert" formmethod="get">新增</button>
		<button type="submit" formaction="${pageContext.request.contextPath}/bquery"  formmethod="post">查詢</button>
		<button type="button" onClick="doReset()">清除</button> 
		<table class="frame">
			<tr>
				<th></th>
				<th></th>
				<th>書籍名稱</th>
				<th>作者名稱</th>
			</tr>
			<c:forEach var="book" items="${books}">
				<tr>
				
					<td><a href="${pageContext.request.contextPath}/bupdate?book_id=${book.book_id}"/>修改</a></td>
					
					<td><a href="${pageContext.request.contextPath}/bdelete?book_id=${book.book_id}"  onclick="return doDeletion()"/>刪除</a></td>
			
					<td>
					
						<c:if test = "${book.book_id == book_id}">
							<font color="red">
						</c:if>
						
							${book.book_Name}
							
						<c:if test = "${book.book_id == book_id}">
							</font>
						</c:if>
					</td><td>
					
						<c:if test = "${book.book_id == book_id}">
							<font color="red">
						</c:if>
						
							${book.writer_Name}
					
						<c:if test = "${book.book_id == book_id}">
							</font>
						</c:if>
					
					</td>
					
				</tr>
			</c:forEach>
		</table>
	</form:form>
	<script>

		function doDeletion() {
			return confirm("確定刪除嗎 ?");
		}
	</script>

</body>
</html>