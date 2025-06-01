<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
<html>
<head>
    <title>Menu</title>
</head>
<body>
    <h:form>
        <h2>Welcome to Expenses Management</h2>
        
        <h:commandLink value="Add Valid Users" action="AddValidUsers" />
        
        &nbsp;&nbsp;&nbsp;&nbsp;

        <h:commandLink value="Add Valid Groups" action="AddValidGroups" />
      	
      	&nbsp;&nbsp;&nbsp;&nbsp;
      	
      	<h:commandLink value="Add Valid Members" action="AddValidMembers" />
      	
      	&nbsp;&nbsp;&nbsp;&nbsp;

        <h:commandLink value="Users Dashboard" action="UsersDashboard" />
        
        &nbsp;&nbsp;&nbsp;&nbsp;

        <h:commandLink value="Groups Dashboard" action="GroupsDashboard" />
        
        &nbsp;&nbsp;&nbsp;&nbsp;

        <h:commandLink value="Members Dashboard" action="MembersDashboard" />
    </h:form>
</body>
</html>
</f:view>
