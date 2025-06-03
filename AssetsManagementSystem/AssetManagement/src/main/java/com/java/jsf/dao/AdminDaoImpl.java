package com.java.jsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.java.jsf.model.Admin;
import com.java.jsf.util.ConnectionHelper;
import com.java.jsf.util.EncryptPassword;

public class AdminDaoImpl implements AdminDao{
	
	
	@Override
	public String addAdmin(Admin admin) throws ClassNotFoundException, SQLException{
		String result = null;
	    
	    String sql = "INSERT INTO admin (email, password) VALUES (?, ?)";
	    Connection conn = ConnectionHelper.getConnection();
	    PreparedStatement stmt = conn.prepareStatement(sql);

	    String encryptedPwd = EncryptPassword.getCode(admin.getPassword());

	    stmt.setString(1, admin.getEmail());
	    stmt.setString(2, encryptedPwd);

	    int rows = stmt.executeUpdate();
	    if (rows > 0) {
	        result = "AdminLogin.jsp?faces-redirect=true";
	    }
	    
	    return result;
	}
	@Override
	public String loginAdmin(String email, String password) throws ClassNotFoundException, SQLException {
	    String result;

	    String sql = "SELECT * FROM admin WHERE email = ? AND password = ?";
	    Connection conn = ConnectionHelper.getConnection();
	    PreparedStatement stmt = conn.prepareStatement(sql);

	    String encryptedPassword = EncryptPassword.getCode(password);
	    stmt.setString(1, email);
	    stmt.setString(2, encryptedPassword);

	    ResultSet rs = stmt.executeQuery();

	    if (rs.next()) {
	        // Admin found, login successful
	        result = "AssetDashboard.jsp?faces-redirect=true";
	    } else {
	        // Admin not found or invalid credentials
	        result = "LoginAdmin.jsp?faces-redirect=true";
	    }

	    rs.close();
	    stmt.close();
	    conn.close();

	    return result;
	}


}
