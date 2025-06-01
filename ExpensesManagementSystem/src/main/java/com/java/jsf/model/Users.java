package com.java.jsf.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

public class Users implements Serializable {

    private int id;
    private String name;
    private String email;
    private String password;
    private Timestamp created_at;

    private Set<GroupMembers> groupMembers;
    private Set<Expenses> expensesPaid;
    private Set<Splits> splits;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Set<GroupMembers> getGroupMembers() {
		return groupMembers;
	}
	public void setGroupMembers(Set<GroupMembers> groupMembers) {
		this.groupMembers = groupMembers;
	}
	public Set<Expenses> getExpensesPaid() {
		return expensesPaid;
	}
	public void setExpensesPaid(Set<Expenses> expensesPaid) {
		this.expensesPaid = expensesPaid;
	}
	public Set<Splits> getSplits() {
		return splits;
	}
	public void setSplits(Set<Splits> splits) {
		this.splits = splits;
	}
	
	public Users(int id, String name, String email, String password, Timestamp created_at,
			Set<GroupMembers> groupMembers, Set<Expenses> expensesPaid, Set<Splits> splits) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.created_at = created_at;
		this.groupMembers = groupMembers;
		this.expensesPaid = expensesPaid;
		this.splits = splits;
	}
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
}

//CREATE TABLE users (
//	    id INT AUTO_INCREMENT PRIMARY KEY,
//	    name VARCHAR(100) NOT NULL,
//	    email VARCHAR(100) NOT NULL UNIQUE,
//	    password VARCHAR (30) NOT NULL,
//	    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
//	);