package com.java.ejb.beans;

import java.sql.SQLException;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.java.ejb.dao.UsersDao;
import com.java.ejb.dao.UsersDaoImpl;
import com.java.ejb.model.Users;

/**
 * Session Bean implementation class UsersJdbcBean
 */
@Stateless
@Remote(UsersJdbcBeanRemote.class)
public class UsersJdbcBean implements UsersJdbcBeanRemote {

	static UsersDao daoImpl;
	
	static {
		daoImpl = new UsersDaoImpl();
	}
	
    /**
     * Default constructor. 
     */
    public UsersJdbcBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String addUsers(Users users) throws ClassNotFoundException, SQLException {
		return daoImpl.addUsersDao(users);
	}

}
