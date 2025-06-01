package com.java.ejb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.ejb.model.GroupMembers;
import com.java.ejb.model.Groups;
import com.java.ejb.model.Users;
import com.java.ejb.util.ConnectionHelper;

public class MembersDaoImpl implements MembersDao{
	
	static GroupsDao dao;
	static {
		dao = new GroupsDaoImpl();
	}

    // 1. Get admin user by email and password
	@Override
    public Users getAdminByCredentials(Users users) throws ClassNotFoundException, SQLException {
        System.out.println("getAdminCredentials method called daoimpl");
	
//		Connection conn = ConnectionHelper.getConnection();
//        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
//        PreparedStatement pst = conn.prepareStatement(sql);
//        pst.setString(1, users.getEmail());
//        pst.setString(2, users.getPassword());
//        ResultSet rs = pst.executeQuery();
//
//        if (rs.next()) {
//            Users user = new Users();
//            user.setId(rs.getInt("id"));
//            user.setName(rs.getString("name"));
//            user.setEmail(rs.getString("email"));
//            user.setPassword(rs.getString("password"));
//            return user;
//        }
        System.out.println("getAdminCredentials method executed daoimpl");
        return dao.loginAdmin(users);
    }

    // 2. Check if user is admin of the group
	@Override
    public boolean isAdminOfGroup(Groups groups) throws ClassNotFoundException, SQLException {
        System.out.println("isAdminOfFound method called daoimpl");

		Connection conn = ConnectionHelper.getConnection();
        String sql = "SELECT * FROM group_s WHERE id = ? AND adminId = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, groups.getId());
        pst.setInt(2, groups.getAdmin().getId());
        ResultSet rs = pst.executeQuery();

        System.out.println("isAdminOfFound method executed daoimpl");

        return rs.next();
    }

    // 3. Add a member to the group
	@Override
    public String addMemberToGroup(GroupMembers members) throws ClassNotFoundException, SQLException {
        System.out.println("addMember method called daoimpl");
		
		Connection conn = ConnectionHelper.getConnection();
        String sql = "INSERT INTO group_members (group_id, user_id, advance) VALUES (?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, members.getGroups().getId());
        pst.setInt(2, members.getUsers().getId());
        pst.setInt(3, members.getAdvance());
        pst.executeUpdate();
        
        System.out.println("addMemberToGroup method executed daoimpl");

        return "Member added to group successfully.";
    }
	
	@Override
	public Users findUserByEmail(String email) throws ClassNotFoundException, SQLException {
	    System.out.println("findUserByEmail called");

	    Connection conn = ConnectionHelper.getConnection();
	    String sql = "SELECT * FROM users WHERE email = ?";
	    PreparedStatement pst = conn.prepareStatement(sql);
	    pst.setString(1, email);
	    ResultSet rs = pst.executeQuery();

	    if (rs.next()) {
	        Users user = new Users();
	        user.setId(rs.getInt("id"));
	        user.setName(rs.getString("name"));
	        user.setEmail(rs.getString("email"));
	        user.setPassword(rs.getString("password"));
	        return user;
	    }
	    return null;
	}

	@Override
	public Groups findGroupByAdminId(int adminId) throws ClassNotFoundException, SQLException {
	    System.out.println("findGroupByAdminId called");

	    Connection conn = ConnectionHelper.getConnection();
	    String sql = "SELECT * FROM group_s WHERE adminId = ?";
	    PreparedStatement pst = conn.prepareStatement(sql);
	    pst.setInt(1, adminId);
	    ResultSet rs = pst.executeQuery();

	    if (rs.next()) {
	        Groups group = new Groups();
	        group.setId(rs.getInt("id"));
	        group.setName(rs.getString("name"));
	        
	        Users admin = new Users();
	        admin.setId(adminId);
	        group.setAdmin(admin);
	        group.setAdvance(rs.getInt("advance"));

	        return group;
	    }
	    return null;
	}
}
