package com.java.ejb.beans;

import java.sql.SQLException;

import javax.ejb.Remote;

import com.java.ejb.model.Groups;
import com.java.ejb.model.Users;

@Remote
public interface GroupsJdbcBeanRemote {
	
	String addGroups(Groups groups) throws ClassNotFoundException, SQLException;
	Users loginAdmin(Users users) throws ClassNotFoundException, SQLException;

}
