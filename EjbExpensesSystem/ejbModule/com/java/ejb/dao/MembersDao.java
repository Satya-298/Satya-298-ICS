package com.java.ejb.dao;

import java.sql.SQLException;

import com.java.ejb.model.GroupMembers;
import com.java.ejb.model.Users;

public interface MembersDao {
	
	String addMemberToGroup(GroupMembers members) throws ClassNotFoundException, SQLException;
	
	boolean isAdminOfGroup(int adminId, int groupId) throws ClassNotFoundException, SQLException;	
	
	Users getAdminByCredentials(String email, String password) throws ClassNotFoundException, SQLException;

}
