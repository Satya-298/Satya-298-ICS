<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%> 
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%> 

<f:view>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Groups Dashboard</title>
    </head>
    <body>
        <h:form>
            <center>
                <h2><h:outputText value="Groups Records" /></h2>
            </center>

            <br/><br/>

            <h:dataTable value="#{groupsController.showGroups()}" var="g" border="3">
                
                <!-- ID -->
                <h:column>
                    <f:facet name="header">
                        <h:outputLabel value="ID" />
                    </f:facet>
                    <h:outputText value="#{g.id}" />
                </h:column>

                <!-- Name -->
                <h:column>
                    <f:facet name="header">
                        <h:outputLabel value="Name" />
                    </f:facet>
                    <h:outputText value="#{g.name}" />
                </h:column>
                
                <!-- Admin Id -->
                <h:column>
    				<f:facet name="header">
        				<h:outputLabel value="Admin ID" />
    				</f:facet>
    				<h:outputText value="#{g.admin.id}" />
				</h:column>

                <!-- Advance -->
                <h:column>
                    <f:facet name="header">
                        <h:outputLabel value="Advance" />
                    </f:facet>
                    <h:outputText value="#{g.advance}" />
                </h:column>

                <!-- Created At -->
                <h:column>
                    <f:facet name="header">
                        <h:outputLabel value="Created At" />
                    </f:facet>
                    <h:outputText value="#{g.created_at}" />
                </h:column>

                <!-- Delete Button -->
                <h:column>
                    <f:facet name="header">
                        <h:outputLabel value="Delete Group" />
                    </f:facet>
                    <h:commandButton action="#{groupsController.deleteGroups(g.id)}" 
                                     value="Delete Group" />
                </h:column>
            </h:dataTable>
        </h:form>
    </body>
</html>
</f:view>
