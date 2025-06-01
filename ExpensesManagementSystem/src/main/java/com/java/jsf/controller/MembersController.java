package com.java.jsf.controller;

import java.sql.SQLException;
import java.util.List;

import com.java.ejb.beans.MembersEjbImpl;
import com.java.ejb.model.Groups;
import com.java.ejb.model.Users;
import com.java.jsf.dao.MembersDao;

public class MembersController {

    private MembersDao membersDao;
    private com.java.jsf.model.GroupMembers members;
    private com.java.ejb.model.GroupMembers ejbMembers;
    private MembersEjbImpl ejbMembersImpl;
    private Users admin;
    private int groupId;

    // Getters and setters omitted for brevity...

    public List<com.java.jsf.model.GroupMembers> showMembers() {
        return membersDao.showMembersDao();
    }

    public Users getAdminByEmail(String email) throws ClassNotFoundException, SQLException {
        return getEjbMembersImpl().getAdminByEmail(email);
    }

    public Groups getGroupForAdminEmail(String email) throws ClassNotFoundException, SQLException {
        return getEjbMembersImpl().getGroupForAdminEmail(email);
    }

    public String loginAndAddMembers() throws ClassNotFoundException, SQLException {
        // Validate admin login
        Users loggedInAdmin = getEjbMembersImpl().getAdminByEmailAndPassword(admin);
        if (loggedInAdmin == null) {
            // Admin login failed
            System.out.println("Admin login failed.");
            return "loginFailed"; // Navigation case or message to show in UI
        }

        // Get group of admin
        Groups adminGroup = getEjbMembersImpl().getGroupForAdminEmail(loggedInAdmin.getEmail());
        if (adminGroup == null) {
            System.out.println("No group found for admin.");
            return "groupNotFound"; // Navigation or error message
        }

        // Set the group id to the group found
        this.groupId = adminGroup.getId();

        // Set group in ejbMembers (the member to add)
        getEjbMembers().setGroups(adminGroup);

        // Now add member to group via ejbMembersImpl (with validation inside)
        String result = getEjbMembersImpl().addMemberToGroup(getEjbMembers());
        return result;
    }

    // Getters and setters for admin, members, ejbMembers, ejbMembersImpl, groupId...

    public MembersDao getMembersDao() {
        return membersDao;
    }

    public void setMembersDao(MembersDao membersDao) {
        this.membersDao = membersDao;
    }

    public com.java.jsf.model.GroupMembers getMembers() {
		return members;
	}

	public void setMembers(com.java.jsf.model.GroupMembers members) {
		this.members = members;
	}

	public com.java.ejb.model.GroupMembers getEjbMembers() {
        if (ejbMembers == null) {
            ejbMembers = new com.java.ejb.model.GroupMembers();
            ejbMembers.setUsers(new Users());
            ejbMembers.setGroups(new Groups());
        }
        return ejbMembers;
    }

    public void setEjbMembers(com.java.ejb.model.GroupMembers ejbMembers) {
        this.ejbMembers = ejbMembers;
    }

    public MembersEjbImpl getEjbMembersImpl() {
        if (ejbMembersImpl == null) {
            ejbMembersImpl = new MembersEjbImpl();
        }
        return ejbMembersImpl;
    }

    public void setEjbMembersImpl(MembersEjbImpl ejbMembersImpl) {
        this.ejbMembersImpl = ejbMembersImpl;
    }

    public Users getAdmin() {
        if (admin == null) {
            admin = new Users();
        }
        return admin;
    }

    public void setAdmin(Users admin) {
        this.admin = admin;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
