<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%> 
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%> 

<f:view>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Group Member</title>
</head>
<body>

    <h:form prependId="false">

        <h2><h:outputText value="Add Member to Group" /></h2>

        <!-- Admin Email -->
        <h:outputLabel value="Admin Email:" for="adminEmail" />
        <h:inputText id="adminEmail" value="#{membersController.admin.email}" required="true" />
        <h:message for="adminEmail" style="color:red" />
        <br/><br/>

        <!-- Admin Password -->
        <h:outputLabel value="Admin Password:" for="adminPassword" />
        <h:inputSecret id="adminPassword" value="#{membersController.admin.password}" required="true" />
        <h:message for="adminPassword" style="color:red" />
        <br/><br/>

        <!-- Member User ID -->
        <h:outputLabel value="Member User ID:" for="memberUser" />
        <h:inputText id="memberUser" value="#{membersController.ejbMembers.users.id}" required="true" />
        <h:message for="memberUser" style="color:red" />
        <br/><br/>

        <!-- Advance Amount -->
        <h:outputLabel value="Advance Amount:" for="memberAdvance" />
        <h:inputText id="memberAdvance" value="#{membersController.ejbMembers.advance}" required="true" />
        <h:message for="memberAdvance" style="color:red" />
        <br/><br/>

        <!-- Submit Button -->
        <h:commandButton value="Login & Add Member"
                         action="#{membersController.loginAndAddMembers}" />

    </h:form>

</body>
</html>
</f:view>
