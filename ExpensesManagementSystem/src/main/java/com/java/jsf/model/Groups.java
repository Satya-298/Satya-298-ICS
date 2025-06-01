package com.java.jsf.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

public class Groups implements Serializable {

    private int id;
    private String name;
    private Users admin;
    private Timestamp created_at;
    private int advance;

    private Set<GroupMembers> groupMembers;
    private Set<Expenses> expenses;
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Users getAdmin() {
		return admin;
	}
	public void setAdmin(Users admin) {
		this.admin = admin;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public int getAdvance() {
		return advance;
	}
	public void setAdvance(int advance) {
		this.advance = advance;
	}
	public Set<GroupMembers> getGroupMembers() {
		return groupMembers;
	}
	public void setGroupMembers(Set<GroupMembers> groupMembers) {
		this.groupMembers = groupMembers;
	}
	public Set<Expenses> getExpenses() {
		return expenses;
	}
	public void setExpenses(Set<Expenses> expenses) {
		this.expenses = expenses;
	}
	public Groups(int id, String name, Users admin, Timestamp created_at, int advance, Set<GroupMembers> groupMembers,
			Set<Expenses> expenses) {
		this.id = id;
		this.name = name;
		this.admin = admin;
		this.created_at = created_at;
		this.advance = advance;
		this.groupMembers = groupMembers;
		this.expenses = expenses;
	}
	
	public Groups() {
		super();
		// TODO Auto-generated constructor stub
	}
}

//CREATE TABLE group_s (
//	    id INT AUTO_INCREMENT PRIMARY KEY,
//	    name VARCHAR(100) NOT NULL,
//	    adminId INT ,
//	    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//	    advance INT,
//	    FOREIGN KEY (adminId)   REFERENCES users(id)
//	);
//
