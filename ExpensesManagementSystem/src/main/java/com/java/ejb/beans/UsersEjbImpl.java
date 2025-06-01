package com.java.ejb.beans;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

public class UsersEjbImpl {
	
	static UsersJdbcBeanRemote remote;
	static {
		try {
			remote = RemoteHelperUsers.lookupRemoteStatelessUsers();
			System.out.println("S Remove  " +remote);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	public String addUsersValid(com.java.ejb.model.Users users) throws ClassNotFoundException, SQLException {
		System.out.println(users);
		System.out.println("Remote is  " +remote);
		boolean isValid = true;
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (users.getName() == null || users.getName().length() < 5 || users.getName().length() > 30) {
			context.addMessage("name", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"User Name Invalid", "User Name must be between 5 and 30"));
			context.validationFailed();
			isValid = false;
		}
		
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(users.getEmail());
        
        if (users.getEmail() == null || !matcher.matches()) {
            context.addMessage("email", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Email Invalid", "Enter a valid email address."));
            context.validationFailed();
            isValid = false;
        }

        String password = users.getPassword();
        if (password == null || password.length() < 8 || password.length() > 20) {
            context.addMessage("password", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Password Invalid", "Password must be between 8 and 20 characters."));
            context.validationFailed();
            isValid = false;
        } else {
            // Optional: add complexity check
            String complexityRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$";
            if (!password.matches(complexityRegex)) {
                context.addMessage("password", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Password Weak", "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character."));
                context.validationFailed();
                isValid = false;
            }
        }
        
        if (isValid == false) {
        	return null;
        }
        
        System.out.println("Final Users " +users);
        
        remote.addUsers(users);
        return "UsersDashboard?faces-redirect=true";
	}

}
