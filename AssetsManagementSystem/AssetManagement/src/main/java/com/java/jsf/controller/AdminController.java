package com.java.jsf.controller;

import java.sql.SQLException;

import com.java.jsf.dao.AdminDao;
import com.java.jsf.dao.AdminDaoImpl;
import com.java.jsf.model.Admin;

public class AdminController {

    private AdminDao adminDao;
    private Admin admin;

    public AdminDao getAdminDao() {
		return adminDao;
	}
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	// Add Admin
	public String addAdmin() throws ClassNotFoundException, SQLException {
		return adminDao.addAdmin(admin);
	}

    // Login admin
    public String loginAdmin() throws ClassNotFoundException, SQLException {
    	return adminDao.loginAdmin(admin.getEmail(), admin.getPassword());
    }
}
