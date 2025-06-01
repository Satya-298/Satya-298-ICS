package com.java.ejb.dao;

import java.sql.SQLException;

import com.java.ejb.model.GroupMembers;
import com.java.ejb.model.Groups;
import com.java.ejb.model.Users;

public interface MembersDao {
	
	String addMemberToGroup(GroupMembers members) throws ClassNotFoundException, SQLException;
	
    public boolean isAdminOfGroup(Groups groups) throws ClassNotFoundException, SQLException;
	
	Users getAdminByCredentials(Users users) throws ClassNotFoundException, SQLException;

	Groups findGroupByAdminId(int adminId) throws ClassNotFoundException, SQLException;

	Users findUserByEmail(String email) throws ClassNotFoundException, SQLException;

}
