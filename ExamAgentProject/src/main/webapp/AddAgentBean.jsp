<%@page import="com.java.jdbc.dao.AgentDaoImpl"%>
<%@page import="com.java.jdbc.dao.AgentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Agent</title>
</head>
<body>
    <h2 align="center">Add New Agent</h2>
    
    <form method="post" action="AddAgentBean.jsp">
        <center>
        	AgentId :
        	<input type = "number" name = "agentId" />
        	
            Name : 
            <input type="text" name="name" /> <br/><br/>

            City : 
            <input type="text" name="city" /> <br/><br/>

            Gender : 
            <select name="gender">
                <option value="MALE">Male</option>
                <option value="FEMALE">Female</option>
            </select> <br/><br/>

            Marital Status : 
            <select name="married"> 
                <option value="0">Single</option>
                <option value="1">Married</option>
            </select> <br/><br/>

            Premium : 
            <input type="number" name="premium" /> <br/><br/>

            <input type="submit" value="Add Agent" />
        </center>
    </form>

    <%
        if (request.getParameter("agentId") != null
   			&& request.getParameter("premium") != null) {
    %>
        <jsp:useBean id="beanAgent" class="com.java.jdbc.model.Agent" />
        <jsp:useBean id="beanAgentDao" class="com.java.jdbc.dao.AgentDaoImpl" />
         <jsp:setProperty property="*" name="beanAgent" />

        <%= beanAgentDao.addAgentDao(beanAgent) %>

        <jsp:forward page="ShowAgentBean.jsp" />
    <%
        }
    %>
</body>
</html>
