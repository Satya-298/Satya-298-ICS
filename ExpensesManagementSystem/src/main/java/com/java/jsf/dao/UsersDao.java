package com.java.jsf.dao;

import java.util.List;

import com.java.jsf.model.Users;

public interface UsersDao {

	List<Users> showUsersDao();
	String deleteUsersDao(int id);
	Users userLogin(String email, String password);

}
