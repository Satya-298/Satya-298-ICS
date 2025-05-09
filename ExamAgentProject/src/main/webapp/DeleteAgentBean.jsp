<%@page import="com.java.jdbc.dao.AgentDao"%>
<%@page import="com.java.jdbc.dao.AgentDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Agent</title>
</head>
<body>
	<%
		int agentId = Integer.parseInt(request.getParameter("agentId"));
		AgentDao dao = new AgentDaoImpl();
		dao.deleteAgentDao(agentId);
	%>
		<jsp:forward page="ShowAgentBean.jsp" />
</body>
</html>