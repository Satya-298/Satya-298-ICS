package com.java.ejb.dao;

import java.sql.SQLException;

import com.java.ejb.model.Users;

public interface GroupsDao {
	
	String addGroupsDao(com.java.ejb.model.Groups groups) throws ClassNotFoundException, SQLException;
	Users loginAdmin(Users users) throws ClassNotFoundException, SQLException;

}
