package com.java.jsf.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

public class Expenses implements Serializable {
    
    private int id;
    private Groups groups;             
    private Users paidBy;              
    private String description;        
    private double total_amount;       
    private Date expense_date;         
    private Timestamp created_at;      
    
    private Set<Splits> splits;
	
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Groups getGroups() {
		return groups;
	}
	public void setGroups(Groups groups) {
		this.groups = groups;
	}
	public Users getPaidBy() {
		return paidBy;
	}
	public void setPaidBy(Users paidBy) {
		this.paidBy = paidBy;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
	public Date getExpense_date() {
		return expense_date;
	}
	public void setExpense_date(Date expense_date) {
		this.expense_date = expense_date;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Set<Splits> getSplits() {
		return splits;
	}
	public void setSplits(Set<Splits> splits) {
		this.splits = splits;
	}
	
	public Expenses(int id, Groups groups, Users paidBy, String description, double total_amount, Date expense_date,
			Timestamp created_at, Set<Splits> splits) {
		this.id = id;
		this.groups = groups;
		this.paidBy = paidBy;
		this.description = description;
		this.total_amount = total_amount;
		this.expense_date = expense_date;
		this.created_at = created_at;
		this.splits = splits;
	}
	
	public Expenses() {
		super();
		// TODO Auto-generated constructor stub
	}
}


//CREATE TABLE expenses (
//	    id INT AUTO_INCREMENT PRIMARY KEY,
//	    group_id INT NOT NULL,
//	    paid_by_user_id INT NOT NULL,
//	    description VARCHAR(255) NOT NULL,
//	    total_amount DECIMAL(10,2) NOT NULL,
//	    expense_date DATE NOT NULL,
//	    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//	    FOREIGN KEY (group_id) REFERENCES group_S(id),
//	    FOREIGN KEY (paid_by_user_id) REFERENCES users(id)
//	);