<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
</head>
<body>
<a href="/menu/account/logout">退出</a><br/>
<c:forEach var="list" items="${reslist}">
	<c:if test="${list.resources != null}">
		<br/><a href="#">${list.resName}</a>
		<c:forEach var="sub" items="${list.resources}">
			<br/>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/menu${sub.resHref}">${sub.resName}</a>
		</c:forEach>
	</c:if>
	<c:if test="${list.resources == null}">
		<a href="/menu${list.resHref}">${list.resName}</a><br/>
	</c:if>
</c:forEach>
</body>
</html>