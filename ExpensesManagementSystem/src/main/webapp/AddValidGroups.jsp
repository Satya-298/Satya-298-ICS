<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%> 
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%> 

<f:view>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Group</title>
    </head>
    <body>
        <h:form prependId="false">

            <h2><h:outputText value="Create New Group"/></h2>

            <!-- Admin Email -->
            <h:outputLabel value="Admin Email:" for="adminEmail" />
            <h:inputText id="adminEmail" value="#{groupsController.admin.email}" />
            <h:message for="adminEmail" style="color:red" />
            <br/><br/>

            <!-- Admin Password -->
            <h:outputLabel value="Admin Password:" for="adminPassword" />
            <h:inputSecret id="adminPassword" value="#{groupsController.admin.password}" />
            <h:message for="adminPassword" style="color:red" />
            <br/><br/>

            <!-- Group Name -->
            <h:outputLabel value="Group Name:" for="name" />
            <h:inputText id="name" value="#{groupsController.ejbGroups.name}" />
            <h:message for="name" style="color:red" />
            <br/><br/>

            <!-- Advance Amount -->
            <h:outputLabel value="Advance Amount:" for="advance" />
            <h:inputText id="advance" value="#{groupsController.ejbGroups.advance}" />
            <h:message for="advance" style="color:red" />
            <br/><br/>

            <!-- Submit Button -->
            <h:commandButton value="Login & Add Group"
                action="#{groupsController.loginAndAddGroups}" />
        </h:form>
    </body>
</html>
</f:view>
