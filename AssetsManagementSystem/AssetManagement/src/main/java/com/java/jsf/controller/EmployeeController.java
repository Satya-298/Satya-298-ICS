package com.java.jsf.controller;

import java.sql.SQLException;

import com.java.jsf.dao.EmployeeDao;
import com.java.jsf.model.Employee;

public class EmployeeController {

    private EmployeeDao employeeDao;
    private Employee employee;
	
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	// Add Admin
	public String addEmployee() throws ClassNotFoundException, SQLException {
		return employeeDao.addEmployee(employee);
	}

    // Login admin
    public String loginEmployee() throws ClassNotFoundException, SQLException {
    	return employeeDao.loginEmployee(employee.getEmail(), employee.getPassword());
    }
}