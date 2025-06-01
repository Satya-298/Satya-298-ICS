package com.java.ejb.beans;

import java.sql.SQLException;

import javax.ejb.Remote;

import com.java.ejb.model.GroupMembers;
import com.java.ejb.model.Users;

@Remote
public interface MembersJdbcBeanRemote {
	
	String addMembers(GroupMembers members) throws ClassNotFoundException, SQLException;
	
	boolean isAdminFound(int adminId, int groupId) throws ClassNotFoundException, SQLException;	
	
	Users getAdminByEmailandPassword(String email, String password) throws ClassNotFoundException, SQLException;

}
