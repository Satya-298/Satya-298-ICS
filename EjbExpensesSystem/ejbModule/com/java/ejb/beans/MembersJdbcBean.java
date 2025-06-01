package com.java.ejb.beans;

import java.sql.SQLException;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.java.ejb.dao.MembersDao;
import com.java.ejb.dao.MembersDaoImpl;
import com.java.ejb.model.GroupMembers;
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
		return daoImpl.addMemberToGroup(members);
	}

	@Override
	public boolean isAdminFound(int adminId, int groupId) throws ClassNotFoundException, SQLException {
		return daoImpl.isAdminOfGroup(adminId, groupId);
	}

	@Override
	public Users getAdminByEmailandPassword(String email, String password) throws ClassNotFoundException, SQLException {
		return daoImpl.getAdminByCredentials(email, password);
	}
}
