package com.java.jsf.model;

public class Employee {
    private int employeeId;
    private String email;
    private String password;

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
	public Employee(int employeeId, String email, String password) {
		this.employeeId = employeeId;
		this.email = email;
		this.password = password;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
