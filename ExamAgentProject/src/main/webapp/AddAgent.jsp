<%@page import="com.java.jdbc.model.Agent"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id = "beanAgentDao" class="com.java.jdbc.dao.AgentDaoImpl" />
	
	<%
		List<Agent> agentList = beanAgentDao.showAgentDao();
	%>
	
	<h2>AgentList</h2>
	<table border="1" align="center" >
		<thead>
			<tr>
				<th>Agent ID</th>
				<th>Name</th>
				<th>City</th>
				<th>Gender</th>
				<th>MaritalStatus</th>
				<th>Premium</th>
				<th>Update</tr>
				<th>Delete</th>
	
	</table>

</body>
</html>