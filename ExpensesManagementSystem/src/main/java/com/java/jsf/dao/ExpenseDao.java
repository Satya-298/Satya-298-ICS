package com.java.jsf.dao;

import java.util.List;

import com.java.jsf.model.Users;

public interface ExpenseDao {
	
	List<Users> showUsersDao();
	String addUsersDao(Users users);
	String deleteUsersDao(int id);

}
