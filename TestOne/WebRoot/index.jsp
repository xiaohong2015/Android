<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta charset="UTF-8">
<style type="text/css">
a:HOVER {
	color: red;
}
</style>
</head>

<body>
	This is my JSP page.
	<br>
	<section>
		<a href="${pageContext.request.contextPath}/FirstAction?i=小洪">FirstJsp</a> <!-- GET iso-8859-1 -->
		<a href="FirstAction.action">FirstJsp</a>
		<a href="${pageContext.request.contextPath}/FirstAction!add">调用自定义后台action的method</a>
	</section>
	<section>
		<a href="${pageContext.request.contextPath }/SecondAction_create">SecondJsp_create</a>
		<a href="${pageContext.request.contextPath }/SecondAction_delete">SecondJsp_delete</a>
		<form action="${pageContext.request.contextPath}/FirstAction" method="post">
			<input type="text" name="i" value="小洪">
			<input type="submit" value="submit" />
		</form>
	</section>
</body>
</html>
