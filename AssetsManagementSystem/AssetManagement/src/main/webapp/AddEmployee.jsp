<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<f:view>
    <h:form>
        <h:panelGrid columns="2" cellpadding="5">
            
            <h:outputLabel for="email" value="Email:" />
            <h:inputText id="email" value="#{employee.email}" required="true" />
            
            <h:outputLabel for="password" value="Password:" />
            <h:inputSecret id="password" value="#{employee.password}" required="true" />
            
            <h:commandButton value="Register" action="#{employeeController.addEmployee}" />
        </h:panelGrid>
    </h:form>
</f:view>
