<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Login</title>
</head>
<body>
    <h2>Admin Login</h2>

    <h:form prependId="false">

        <!-- Email -->
        <h:outputLabel value="Email:" for="email" />
        <h:inputText id="email" value="#{admin.email}" required="true" />
        <h:message for="email" style="color:red" />
        <br/>

        <!-- Password -->
        <h:outputLabel value="Password:" for="password" />
        <h:inputSecret id="password" value="#{admin.password}" required="true" />
        <h:message for="password" style="color:red" />
        <br/>

        <!-- Submit Button -->
        <h:commandButton value="Login" action="#{adminController.loginAdmin}" />
    </h:form>
</body>
</html>
</f:view>
