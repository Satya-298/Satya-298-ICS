package com.java.ejb.beans;

import java.sql.SQLException;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.java.ejb.dao.MembersDao;
import com.java.ejb.dao.MembersDaoImpl;
import com.java.ejb.model.GroupMembers;
import com.java.ejb.model.Groups;
import com.java.ejb.model.Users;

/**
 * Session Bean implementation class MembersJdbcBean
 */
@Stateless
@Remote(MembersJdbcBeanRemote.class)
public class MembersJdbcBean implements MembersJdbcBeanRemote {

	static MembersDao daoImpl;
	
	static {
		
		daoImpl = new MembersDaoImpl();
	}
	
    /**
     * Default constructor. 
     */
    public MembersJdbcBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String addMembers(GroupMembers members) throws ClassNotFoundException, SQLException {
		System.out.println("addMembers called by remote inside MembersJdbcBean");
		return daoImpl.addMemberToGroup(members);
	}

	@Override
	public boolean isAdminFound(Groups groups) throws ClassNotFoundException, SQLException {
		System.out.println("isAdminFound  called by remote inside MembersJdbcBean");
		return daoImpl.isAdminOfGroup(groups);
	}

	@Override
	public Users getAdminByEmailandPassword(Users users) throws ClassNotFoundException, SQLException {
		System.out.println("getAdminByEmailandPassword  called by remote inside MembersJdbcBean");
		return daoImpl.getAdminByCredentials(users);
	}
	
	@Override
    public Users findUserByEmail(String email) throws ClassNotFoundException, SQLException {
		System.out.println("findUserByEmail  called by remote inside MembersJdbcBean");
		return daoImpl.findUserByEmail(email);
    }

    @Override
    public Groups findGroupByAdminId(int adminId) throws ClassNotFoundException, SQLException {
		System.out.println("findGroupByAdminId  called by remote inside MembersJdbcBean");
    	return daoImpl.findGroupByAdminId(adminId);
    }
}
