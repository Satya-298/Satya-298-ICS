package com.java.jsf.model;

import java.util.Date;

public class AssignedAsset {
	
	private int assignId;
    private Asset asset;
    private Employee employee;
    private Admin admin;
    private Date assignDate;
    private Date returnDate;
    private String status;
	public int getAssignId() {
		return assignId;
	}
	public void setAssignId(int assignId) {
		this.assignId = assignId;
	}
	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Date getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public AssignedAsset(int assignId, Asset asset, Employee employee, Admin admin, Date assignDate, Date returnDate,
			String status) {
		this.assignId = assignId;
		this.asset = asset;
		this.employee = employee;
		this.admin = admin;
		this.assignDate = assignDate;
		this.returnDate = returnDate;
		this.status = status;
	}
	
	public AssignedAsset() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

}
