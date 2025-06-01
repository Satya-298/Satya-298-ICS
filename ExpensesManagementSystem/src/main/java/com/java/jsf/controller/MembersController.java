package com.java.jsf.controller;

import java.sql.SQLException;
import java.util.List;

import com.java.ejb.beans.MembersEjbImpl;
import com.java.jsf.dao.MembersDao;
import com.java.jsf.model.GroupMembers;

public class MembersController {

    private MembersDao membersDao;
    private GroupMembers members;
    private com.java.ejb.model.GroupMembers ejbMembers;
    private MembersEjbImpl ejbMembersImpl;
    private com.java.ejb.model.Users admin;

    private int groupId;

    // Getters and Setters
    public MembersDao getMembersDao() {
        return membersDao;
    }

    public void setMembersDao(MembersDao membersDao) {
        this.membersDao = membersDao;
    }

    public GroupMembers getMembers() {
        return members;
    }

    public void setMembers(GroupMembers members) {
        this.members = members;
    }

    public com.java.ejb.model.GroupMembers getEjbMembers() {
    	if (ejbMembers == null) {
            ejbMembers = new com.java.ejb.model.GroupMembers();
            ejbMembers.setUsers(new com.java.ejb.model.Users()); 
            ejbMembers.setGroups(new com.java.ejb.model.Groups()); 
        }

        return ejbMembers;
    }

    public void setEjbMembers(com.java.ejb.model.GroupMembers ejbMembers) {
        this.ejbMembers = ejbMembers;
    }

    public MembersEjbImpl getEjbMembersImpl() {
        return ejbMembersImpl;
    }

    public void setEjbMembersImpl(MembersEjbImpl ejbMembersImpl) {
        this.ejbMembersImpl = ejbMembersImpl;
    }

    public com.java.ejb.model.Users getAdmin() {
        if (admin == null) {
            admin = new com.java.ejb.model.Users();
        }
        return admin;
    }

    public void setAdmin(com.java.ejb.model.Users admin) {
        this.admin = admin;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    // View Method
    public List<GroupMembers> showMembers() {
        return membersDao.showMembersDao();
    }

    // Action Method
    public String loginAndAddMembers() throws ClassNotFoundException, SQLException {
        System.out.println("in controller: loginAndAddMembers");

        String result = ejbMembersImpl.loginAndAddMember(admin, ejbMembers, groupId);

        System.out.println("loginAndAddMembers result: " + result);
        return result;
    }
}
