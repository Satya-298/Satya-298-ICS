<%@page import="com.java.jdbc.model.Agent"%>
<%@page import="java.util.List"%>
<%@page import="com.java.jdbc.dao.AgentDaoImpl"%>
<%@page import="com.java.jdbc.dao.AgentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="beanAgentDao" class="com.java.jdbc.dao.AgentDaoImpl" />
	<%
		List<Agent> agentList = beanAgentDao.showAgentDao();
	%>
	
	<table border="3" align="center">
		<tr>
			<th>AgentId</th>
			<th>Name</th>
			<th>City</th>
			<th>Gender</th>
			<th>MaritalStatus</th>
			<th>Premium</th>
			<th>Update</th>
			<th>Delete</th>
		</tr>
		
		<%
			for (Agent agent : agentList ) {
		%>
		<tr>
			<td><%=agent.getAgentId() %></td>
			<td><%=agent.getName() %></td>
			<td><%=agent.getCity() %></td>
			<td><%=agent.getGender() %></td>
			<td><%=agent.getMaritalstatus() %></td>
			<td><%=agent.getPremium() %></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>