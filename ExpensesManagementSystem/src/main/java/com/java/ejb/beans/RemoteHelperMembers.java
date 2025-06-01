package com.java.ejb.beans;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RemoteHelperMembers {

	private static Context createInitialContext() throws NamingException {
        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, 
          "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProperties.put(Context.URL_PKG_PREFIXES, 
          "org.jboss.ejb.client.naming");
        jndiProperties.put(Context.PROVIDER_URL, 
           "http-remoting://localhost:8080");
        jndiProperties.put("jboss.naming.client.ejb.context", true);
        return new InitialContext(jndiProperties);
    }
	   public static MembersJdbcBeanRemote lookupRemoteStatelessMembers () throws NamingException {
		   System.out.println("inside remotehelper");
		   Context ctx = createInitialContext();
		   MembersJdbcBeanRemote remote = (MembersJdbcBeanRemote)
				   ctx.lookup("ejb:/EjbExpensesSystem/MembersJdbcBean!com.java.ejb.beans.MembersJdbcBeanRemote");
		   System.out.println("remotehelper returned" +remote);
		   return remote;
	        // The app name is the application name of the deployed EJBs. This is typically the ear name
	        // without the .ear suffix. However, the application name could be overridden in the application.xml of the
	        // EJB deployment on the server.
	        // Since we haven't deployed the application as a .ear, the app name for us will be an empty string
	    //    final String appName = "";
	        // This is the module name of the deployed EJBs on the server. This is typically the jar name of the
	        // EJB deployment, without the .jar suffix, but can be overridden via the ejb-jar.xml
	        // In this example, we have deployed the EJBs in a jboss-as-ejb-remote-app.jar, so the module name is
	        // jboss-as-ejb-remote-app
	      //  final String moduleName = "EJBModule";
	        // AS7 allows each deployment to have an (optional) distinct name. We haven't specified a distinct name for
	        // our EJB deployment, so this is an empty string
//	        final String distinctName = "";
//	        // The EJB name which by default is the simple class name of the bean implementation class
//	        final String beanName = "UsersJdbcBean";
//	        System.out.println(beanName);
//	        // the remote view fully qualified class name
//	   //     System.out.println(RemoteCalculator.class.getName());
//	        final String viewClassName = UsersJdbcBeanRemote.class.getName();
//	        // let's do the lookup
//	   //     String namespace="ejb:/HelloWorldEJB!com.ejb.HelloWorld";
//	     //   return (RemoteCalculator) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
//	        String appName = "";
//            String moduleName = "EjbExpensesSystem";
//          //  String distinctName = "";
//           // String beanName = CalculatorBean.class.getSimpleName();
//           // String viewClassName = RemoteCalculator.class.getName();
////            return (MembersJdbcBeanRemote) ctx.lookup("ejb:" 
////              + appName + "/" + moduleName 
////              + "/" + distinctName + "/" + beanName + "!" + viewClassName);
	    }

}
