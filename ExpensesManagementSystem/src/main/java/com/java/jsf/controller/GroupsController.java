package com.java.jsf.controller;

import java.sql.SQLException;
import java.util.List;

import com.java.ejb.beans.GroupsEjbImpl;
import com.java.jsf.dao.GroupsDao;
import com.java.jsf.model.Groups;
import com.java.jsf.model.Users;

public class GroupsController {
	
	private GroupsDao groupsDao;
	private Groups groups;
	private com.java.ejb.model.Groups ejbGroups;
	private GroupsEjbImpl ejbGroupsImpl;
	private com.java.ejb.model.Users admin;
	
	public GroupsDao getGroupsDao() {
		return groupsDao;
	}
	public void setGroupsDao(GroupsDao groupsDao) {
		this.groupsDao = groupsDao;
	}
	public Groups getGroups() {
		return groups;
	}
	public void setGroups(Groups groups) {
		this.groups = groups;
	}
	public com.java.ejb.model.Groups getEjbGroups() {
		if (ejbGroups == null) {
			ejbGroups = new com.java.ejb.model.Groups();
		}
		return ejbGroups;
	}
	public void setEjbGroups(com.java.ejb.model.Groups ejbGroups) {
		this.ejbGroups = ejbGroups;
	}
	public GroupsEjbImpl getEjbGroupsImpl() {
		return ejbGroupsImpl;
	}
	public void setEjbGroupsImpl(GroupsEjbImpl ejbGroupsImpl) {
		this.ejbGroupsImpl = ejbGroupsImpl;
	}
	public com.java.ejb.model.Users getAdmin() {
		if (admin == null) {
	        admin = new com.java.ejb.model.Users();
	    }
		return admin;
	}
	public void setAdmin(com.java.ejb.model.Users admin) {
		System.out.println("setting admin");
		this.admin = admin;
	}
	
	public List<Groups> showGroups(){
		return groupsDao.showGroupsDao();
	}
	
	public String deleteGroups(int id) {
		return groupsDao.deleteGroupsDao(id);
	}
	
	public String loginAndAddGroups() throws ClassNotFoundException, SQLException {
        System.out.println("in controller: loginAndAddGroup");
        
        // ejbGroups and admin must be populated via your JSF form
        String result = ejbGroupsImpl.loginAndAddGroup(admin, ejbGroups);
        
        System.out.println("loginAndAddGroup result: " + result);
        return result;
    }
		
}
