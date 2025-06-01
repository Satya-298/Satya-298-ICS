package com.java.ejb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.java.ejb.model.Users;
import com.java.ejb.util.ConnectionHelper;

public class UsersDaoImpl implements UsersDao{

	Connection conn;
	PreparedStatement pst;

	public String addUsersDao(Users users) throws ClassNotFoundException, SQLException {
		System.out.println("Server Side Users " +users);
	    Connection conn = ConnectionHelper.getConnection();

	    String cmd = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";
	    PreparedStatement pst = conn.prepareStatement(cmd);
	    
	    pst.setString(1, users.getName());
	    pst.setString(2, users.getEmail());
	    pst.setString(3, users.getPassword());

	    pst.executeUpdate();
	    return "User Record Inserted...";
	}
}
