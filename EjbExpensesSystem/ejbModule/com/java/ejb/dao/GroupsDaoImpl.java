package com.java.ejb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.ejb.model.Groups;
import com.java.ejb.model.Users;
import com.java.ejb.util.ConnectionHelper;

public class GroupsDaoImpl implements GroupsDao{

	@Override
	public String addGroupsDao(Groups groups) throws ClassNotFoundException, SQLException {
	    Connection conn = ConnectionHelper.getConnection();
	    System.out.println("now in remote method");
	    Users inputUser = groups.getAdmin();
	    Users admin = loginAdmin(inputUser) ;
	    conn.setAutoCommit(false);
	    String cmd = "INSERT INTO group_s(name, advance, adminId) VALUES (?, ?, ?)";
	    PreparedStatement pst = conn.prepareStatement(cmd);

	    pst.setString(1, groups.getName());
	    pst.setDouble(2, groups.getAdvance());
	    pst.setInt(3, admin.getId()); 

	    pst.executeUpdate();
	    
	    conn.commit();
	    System.out.println("remote method executed");
	    return "Group Added";
	}

	@Override
    public Users loginAdmin(Users users) throws ClassNotFoundException, SQLException {
		System.out.println("now in login admin");
        Connection conn = ConnectionHelper.getConnection();
        String cmd = "SELECT * FROM users WHERE email = ? AND password = ?";
        PreparedStatement pst = conn.prepareStatement(cmd);
        pst.setString(1, users.getEmail());
        pst.setString(2, users.getPassword());

        ResultSet rs = pst.executeQuery();
        Users admin = null;

        if (rs.next()) {
            admin = new Users();
            admin.setId(rs.getInt("id"));
            admin.setName(rs.getString("name"));
            admin.setEmail(rs.getString("email"));
            admin.setPassword(rs.getString("password")); 
        }
        System.out.println("login admin executed");
        return admin;
    }

}

