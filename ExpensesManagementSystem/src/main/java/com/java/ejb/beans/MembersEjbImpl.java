package com.java.ejb.beans;

import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.java.ejb.model.GroupMembers;
import com.java.ejb.model.Groups;
import com.java.ejb.model.Users;

public class MembersEjbImpl {

    static MembersJdbcBeanRemote remote;

    static {
        try {
            remote = RemoteHelperMembers.lookupRemoteStatelessMembers();
            System.out.println("Remote Lookup Success: " + remote);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 1. Admin Login Validation
    public Users getAdminByEmailAndPassword(Users adminInput) throws ClassNotFoundException, SQLException {
        System.out.println("getAdminByEmailAndPassword called");

        FacesContext context = FacesContext.getCurrentInstance();
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,}$";

        if (adminInput.getEmail() == null || !Pattern.matches(emailRegex, adminInput.getEmail())) {
            context.addMessage("adminEmail", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Email", "Enter a valid email address."));
            context.validationFailed();
            return null;
        }

        if (adminInput.getPassword() == null || !Pattern.matches(passwordRegex, adminInput.getPassword())) {
            context.addMessage("adminPassword", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Password", "Password must be strong."));
            context.validationFailed();
            return null;
        }

        return remote.getAdminByEmailandPassword(adminInput);
    }

    // 2. Check if admin belongs to group
    public boolean isAdminOfGroup(Users admin, int groupId) throws ClassNotFoundException, SQLException {
        Groups group = new Groups();
        group.setId(groupId);
        group.setAdmin(admin);
        return remote.isAdminFound(group);
    }

    // 3. Add member with validation
    public String addMemberToGroup(GroupMembers memberInput) throws ClassNotFoundException, SQLException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (memberInput.getUsers() == null || memberInput.getUsers().getId() <= 0) {
            context.addMessage("memberUser", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Member", "Select a valid member."));
            context.validationFailed();
            return null;
        }

        if (memberInput.getAdvance() < 0) {
            context.addMessage("memberAdvance", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Advance", "Advance must be non-negative."));
            context.validationFailed();
            return null;
        }

        remote.addMembers(memberInput);
        return "MembersDashboard?faces-redirect=true";
    }

    // 4. New: Get admin by email with validation
    public Users getAdminByEmail(String email) throws ClassNotFoundException, SQLException {
        FacesContext context = FacesContext.getCurrentInstance();
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (email == null || !Pattern.matches(emailRegex, email)) {
            context.addMessage("email", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Email", "Enter a valid email address."));
            context.validationFailed();
            return null;
        }

        return remote.findUserByEmail(email);
    }

    // 5. New: Get group using admin email with validation
    public Groups getGroupForAdminEmail(String email) throws ClassNotFoundException, SQLException {
        FacesContext context = FacesContext.getCurrentInstance();
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (email == null || !Pattern.matches(emailRegex, email)) {
            context.addMessage("adminEmail", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid Email", "Enter a valid email address."));
            context.validationFailed();
            return null;
        }

        Users admin = remote.findUserByEmail(email);
        if (admin == null) {
            context.addMessage("adminEmail", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Not Found", "No admin exists with that email."));
            context.validationFailed();
            return null;
        }

        return remote.findGroupByAdminId(admin.getId());
    }
}
