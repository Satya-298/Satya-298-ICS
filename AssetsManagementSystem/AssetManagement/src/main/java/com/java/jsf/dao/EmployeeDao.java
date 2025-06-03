package com.java.jsf.dao;

import java.sql.SQLException;

import com.java.jsf.model.Employee;

public interface EmployeeDao {
	
	String addEmployee(Employee employee) throws ClassNotFoundException, SQLException;
    String loginEmployee(String email, String password) throws ClassNotFoundException, SQLException;

}
