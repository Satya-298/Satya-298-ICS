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
        <h:form>
        <center>
                <h2><h:outputText value="Users Records"/></h2>
            </center>
            

        <br/><br/>
        <h:dataTable value="#{usersController.showUsers()}" var="u" border="3">

        	    <h:column>
                    <f:facet name="header">
                    	<h:outputLabel value="ID" />
                    </f:facet>
                    <h:outputText value="#{u.id}"/>
                </h:column>
                <h:column>
                     <f:facet name="header">
                    	<h:outputLabel value="Name" />
                     </f:facet>
                    <h:outputText value="#{u.name}"/>
                </h:column>
                    <h:column>
                     <f:facet name="header">
                    	<h:outputLabel value="Email" />
                    </f:facet>
                    <h:outputText value="#{u.email}"/>
                </h:column>
                    <h:column>
    				 <f:facet name="header">
        				<h:outputLabel value="Created At" />
    				</f:facet>
    				<h:outputText value="#{u.created_at}" />
				</h:column>

                 <h:column>
                     <f:facet name="header">
                    	<h:outputLabel value="Delete User" />
                    </f:facet>
                    <h:commandButton action="#{usersController.deleteUsers(u.id)}" 
                    		value="Delete User" />
                </h:column>
        </h:dataTable>
        </h:form>
    </body>
</html>
</f:view>
