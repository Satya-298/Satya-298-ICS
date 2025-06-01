package com.java.ejb.beans;

import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import com.java.ejb.model.Groups;
import com.java.ejb.model.Users;

public class GroupsEjbImpl {
	
	static GroupsJdbcBeanRemote remote;
	static {
		try {
			System.out.println("calling remotehelper");
			remote = RemoteHelperGroups.lookupRemoteStatelessGroups();
			System.out.println("remote is"+remote);
			System.out.println("remote helper called");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
    public String loginAndAddGroup(Users adminInput, Groups groupInput) throws SQLException, ClassNotFoundException {
        FacesContext context = FacesContext.getCurrentInstance();

        // Step 1: Validate admin credentials
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,}$";
        boolean isValid = true;

        if (adminInput == null || adminInput.getEmail() == null || !Pattern.matches(emailRegex, adminInput.getEmail())) {
            context.addMessage("adminEmail", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Email", "Please enter a valid email address."));
            context.validationFailed();
            isValid = false;
        }

        if (adminInput.getPassword() == null || !Pattern.matches(passwordRegex, adminInput.getPassword())) {
            context.addMessage("adminPassword", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Password", "Password must have at least 6 characters, one uppercase, one lowercase, one number, and one special character."));
            context.validationFailed();
            isValid = false;
        }

        if (!isValid) return null;

        Users loggedInAdmin = remote.loginAdmin(adminInput);
        if (loggedInAdmin == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Login Failed", "Invalid email or password."));
            context.validationFailed();
            return null;
        }

        // Step 2: Validate group fields
        if (groupInput.getName() == null || groupInput.getName().length() < 3 || groupInput.getName().length() > 50) {
            context.addMessage("groupName", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Group Name", "Group name must be between 3 and 50 characters."));
            context.validationFailed();
            return null;
        }

        if (groupInput.getAdvance() < 0) {
            context.addMessage("advance", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Advance", "Advance must be a non-negative value."));
            context.validationFailed();
            return null;
        }

        // Step 3: Set admin and add group
        groupInput.setAdmin(loggedInAdmin);
        remote.addGroups(groupInput);
        return "GroupsDashboard?faces-redirect=true";
    }
}
