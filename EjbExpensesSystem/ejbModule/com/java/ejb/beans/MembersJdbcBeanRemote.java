package com.java.ejb.beans;

import java.sql.SQLException;

import javax.ejb.Remote;

import com.java.ejb.model.GroupMembers;
import com.java.ejb.model.Groups;
import com.java.ejb.model.Users;

@Remote
public interface MembersJdbcBeanRemote {
	
	String addMembers(GroupMembers members) throws ClassNotFoundException, SQLException;
	
	boolean isAdminFound(Groups groups) throws ClassNotFoundException, SQLException;

	Users getAdminByEmailandPassword(Users users) throws ClassNotFoundException, SQLException;

	Users findUserByEmail(String email) throws ClassNotFoundException, SQLException;

	Groups findGroupByAdminId(int adminId) throws ClassNotFoundException, SQLException;

}
