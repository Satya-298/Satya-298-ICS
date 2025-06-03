package com.java.jsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.jsf.model.Employee;
import com.java.jsf.util.ConnectionHelper;
import com.java.jsf.util.EncryptPassword;

public class EmployeeDaoImpl implements EmployeeDao{

    @Override
    public String addEmployee(Employee employee) throws ClassNotFoundException, SQLException {
        String result = null;

        String sql = "INSERT INTO employee (email, password) VALUES (?, ?)";
        Connection conn = ConnectionHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        String encryptedPwd = EncryptPassword.getCode(employee.getPassword());

        stmt.setString(1, employee.getEmail());
        stmt.setString(2, encryptedPwd);

        int rows = stmt.executeUpdate();
        if (rows > 0) {
	        result = "EmployeeLogin.jsp?faces-redirect=true";
	    }
        stmt.close();
        conn.close();

        return result;
    }


    @Override
    public String loginEmployee(String email, String password) throws ClassNotFoundException, SQLException {
        String result;

        String sql = "SELECT * FROM employee WHERE email = ? AND password = ?";
        Connection conn = ConnectionHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        String encryptedPassword = EncryptPassword.getCode(password);
        stmt.setString(1, email);
        stmt.setString(2, encryptedPassword);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            result = "EmployeeDashboard.jsp?faces-redirect=true";
        } else {
            result = "EmployeeLogin.jsp?faces-redirect=true";
        }

        rs.close();
        stmt.close();
        conn.close();

        return result;
    }
}
