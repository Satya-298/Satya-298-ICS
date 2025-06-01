package com.java.ejb.beans;

import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import com.java.ejb.model.GroupMembers;
import com.java.ejb.model.Users;

public class MembersEjbImpl {

    static MembersJdbcBeanRemote remote;

    static {
        try {
            remote = RemoteHelperMembers.lookupRemoteStatelessMembers();
            System.out.println("Remote Lookup Success: " + remote);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public String loginAndAddMember(Users adminInput, GroupMembers memberInput, int groupId) throws ClassNotFoundException, SQLException {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean isValid = true;

        // Step 1: Validate admin login
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,}$";

        if (adminInput.getEmail() == null || !Pattern.matches(emailRegex, adminInput.getEmail())) {
            context.addMessage("adminEmail", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Email", "Please enter a valid email address."));
            context.validationFailed();
            isValid = false;
        }

        if (adminInput.getPassword() == null || !Pattern.matches(passwordRegex, adminInput.getPassword())) {
            context.addMessage("adminPassword", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Password", "Password must contain at least 6 characters with uppercase, lowercase, digit, and special character."));
            context.validationFailed();
            isValid = false;
        }

        if (!isValid) return null;

        Users loggedInAdmin = remote.getAdminByEmailandPassword(adminInput.getEmail(), adminInput.getPassword());
        if (loggedInAdmin == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Login Failed", "Invalid email or password."));
            context.validationFailed();
            return null;
        }

        // Step 2: Check if admin is authorized for this group
        boolean isAdmin = remote.isAdminFound(loggedInAdmin.getId(), groupId);
        if (!isAdmin) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Unauthorized", "You are not the admin of this group."));
            context.validationFailed();
            return null;
        }

        // Step 3: Validate and add member
        if (memberInput.getUsers() == null || memberInput.getUsers().getId() <= 0) {
            context.addMessage("memberUser", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Member", "Select a valid member to add."));
            context.validationFailed();
            return null;
        }

        if (memberInput.getAdvance() < 0) {
            context.addMessage("memberAdvance", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Advance", "Advance must be a non-negative value."));
            context.validationFailed();
            return null;
        }

        // Assign the group ID manually, assuming Groups object is already inside `GroupMembers`
        memberInput.getGroups().setId(groupId);

        remote.addMembers(memberInput);
        return "MembersDashboard?faces-redirect=true";
    }
}
