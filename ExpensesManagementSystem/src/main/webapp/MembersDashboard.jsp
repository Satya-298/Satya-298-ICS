<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%> 
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%> 

<f:view>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Group Members Dashboard</title>
    </head>
    <body>
        <h:form>
            <center>
                <h2><h:outputText value="Group Members Records" /></h2>
            </center>

            <br/><br/>

            <h:dataTable value="#{membersController.showMembers()}" var="gm" border="3">

                <!-- ID -->
                <h:column>
                    <f:facet name="header">
                        <h:outputLabel value="Member ID" />
                    </f:facet>
                    <h:outputText value="#{gm.id}" />
                </h:column>

                <!-- Group ID -->
                <h:column>
                    <f:facet name="header">
                        <h:outputLabel value="Group ID" />
                    </f:facet>
                    <h:outputText value="#{gm.groups.id}" />
                </h:column>

                <!-- Group Name -->
                <h:column>
                    <f:facet name="header">
                        <h:outputLabel value="Group Name" />
                    </f:facet>
                    <h:outputText value="#{gm.groups.name}" />
                </h:column>

                <!-- User ID -->
                <h:column>
                    <f:facet name="header">
                        <h:outputLabel value="User ID" />
                    </f:facet>
                    <h:outputText value="#{gm.users.id}" />
                </h:column>

                <!-- User Name -->
                <h:column>
                    <f:facet name="header">
                        <h:outputLabel value="User Name" />
                    </f:facet>
                    <h:outputText value="#{gm.users.name}" />
                </h:column>

                <!-- Joined At -->
                <h:column>
                    <f:facet name="header">
                        <h:outputLabel value="Joined At" />
                    </f:facet>
                    <h:outputText value="#{gm.joined_at}" />
                </h:column>
                
                <!-- Advance -->
                <h:column>
                    <f:facet name="header">
                        <h:outputLabel value="Advance" />
                    </f:facet>
                    <h:outputText value="#{gm.advance}" />
                </h:column>

            </h:dataTable>
        </h:form>
    </body>
</html>
</f:view>
