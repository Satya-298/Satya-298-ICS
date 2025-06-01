
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%> 
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%> 
<f:view>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <h:form prependId="false">
 <h:outputLabel value="Name" /> 
<h:inputText  id="name" value="#{ejbUsers.name}"/>
     <h:message for="name" style="color:red" />
<br>
 <h:outputLabel value="Email" /> 
<h:inputText  id="email" value="#{ejbUsers.email}"/>
     <h:message for="email" style="color:red" />
<br>
<h:outputLabel value="Password" /> 
<h:inputSecret  id="password" value="#{ejbUsers.password}"/>
     <h:message for="password" style="color:red" />
<br>

	<h:commandButton action="#{usersController.addUsersEjb(ejbUsers)}" 
		value="Add Users" />
        </h:form>
    </body>
</html>
</f:view>
