<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'MyJsp.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<table>
		<tr>
			<th>Value</th>
			<th>Square</th>
		</tr>
		<c:forEach var="x" begin="0" end="10" step="2">
			<tr>
				<td><c:out value="${x}" /></td>
				<td><c:out value="${x * x}" /></td>
			</tr>
		</c:forEach>
	</table>
	<c:set var="i" scope="session" value="11" />
	<c:out value="${i }"></c:out>
	<c:remove var="i" scope="session"/>


	This is my create JSP page.
	<br>
	<input type="button" onclick="history.back()" value="Come Back" />
</body>
</html>
