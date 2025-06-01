package com.java.jsf.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.java.ejb.beans.UsersEjbImpl;
import com.java.jsf.dao.UsersDao;
import com.java.jsf.dao.UsersDaoImpl;
import com.java.jsf.model.Users;

public class UsersController implements Serializable{
	
	private UsersDao usersDao;
	private Users users;
	private com.java.ejb.model.Users ejbUsers;
	private UsersEjbImpl ejbUsersImpl;
	
	public UsersDao getUsersDao() {
		return usersDao;
	}
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}	
	public com.java.ejb.model.Users getEjbUsers() {
		return ejbUsers;
	}
	public void setEjbUsers(com.java.ejb.model.Users ejbUsers) {
		this.ejbUsers = ejbUsers;
	}
	public UsersEjbImpl getEjbUsersImpl() {
		return ejbUsersImpl;
	}
	public void setEjbUsersImpl(UsersEjbImpl ejbUsersImpl) {
		this.ejbUsersImpl = ejbUsersImpl;
	}
	public List<Users> showUsers(){
		return usersDao.showUsersDao();
	}
	
	public String deleteUsers(int id) {
		return usersDao.deleteUsersDao(id);
	}
	
	public String addUsersEjb(com.java.ejb.model.Users users) throws ClassNotFoundException, SQLException {
	    return ejbUsersImpl.addUsersValid(users);
	}
}