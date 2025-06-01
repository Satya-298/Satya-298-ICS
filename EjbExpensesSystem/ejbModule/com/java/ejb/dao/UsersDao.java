package com.java.ejb.dao;

import java.sql.SQLException;
import java.util.List;

public interface UsersDao {

	String addUsersDao(com.java.ejb.model.Users users) throws ClassNotFoundException, SQLException;
}
