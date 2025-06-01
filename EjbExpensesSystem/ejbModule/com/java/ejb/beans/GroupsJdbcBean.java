package com.java.ejb.beans;

import java.sql.SQLException;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.java.ejb.dao.GroupsDao;
import com.java.ejb.dao.GroupsDaoImpl;
import com.java.ejb.model.Groups;
import com.java.ejb.model.Users;

/**
 * Session Bean implementation class GroupsJdbcBean
 */
@Stateless
@Remote(GroupsJdbcBeanRemote.class)
public class GroupsJdbcBean implements GroupsJdbcBeanRemote {

	static GroupsDao daoImpl;
	
	static {
		daoImpl = new GroupsDaoImpl();
	}
	
    /**
     * Default constructor. 
     */
    public GroupsJdbcBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String addGroups(Groups groups) throws ClassNotFoundException, SQLException {

		System.out.println("now in bean calling the remote method");
		return daoImpl.addGroupsDao(groups);
	}

	@Override
	public Users loginAdmin(Users users) throws ClassNotFoundException, SQLException {

		return daoImpl.loginAdmin(users);
	}
}
