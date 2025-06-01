package com.java.ejb.beans;

import java.sql.SQLException;

import javax.ejb.Remote;

import com.java.ejb.model.Users;

@Remote
public interface UsersJdbcBeanRemote {
	
	String addUsers(Users users) throws ClassNotFoundException, SQLException;
}
