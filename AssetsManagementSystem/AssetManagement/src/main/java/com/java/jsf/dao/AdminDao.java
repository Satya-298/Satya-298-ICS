package com.java.jsf.dao;

import java.sql.SQLException;

import com.java.jsf.model.Admin;

public interface AdminDao {
	
    String addAdmin(Admin admin) throws ClassNotFoundException, SQLException;
    
    String loginAdmin(String email, String password) throws ClassNotFoundException, SQLException;

}
