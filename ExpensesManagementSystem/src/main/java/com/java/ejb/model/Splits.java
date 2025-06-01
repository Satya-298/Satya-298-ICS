package com.java.ejb.model;

import java.io.Serializable;

public class Splits implements Serializable {
    
    private int id;
    private Expenses expense;  
    private Users user;        
    private double amount_owed;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Expenses getExpense() {
		return expense;
	}
	public void setExpense(Expenses expense) {
		this.expense = expense;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public double getAmount_owed() {
		return amount_owed;
	}
	public void setAmount_owed(double amount_owed) {
		this.amount_owed = amount_owed;
	}
	
	public Splits(int id, Expenses expense, Users user, double amount_owed) {
		this.id = id;
		this.expense = expense;
		this.user = user;
		this.amount_owed = amount_owed;
	}
	
	public Splits() {
		super();
		// TODO Auto-generated constructor stub
	}  
}


//CREATE TABLE expense_splits (
//	    id INT AUTO_INCREMENT PRIMARY KEY,
//	    expense_id INT NOT NULL,
//	    user_id INT NOT NULL,
//	    amount_owed DECIMAL(10,2) NOT NULL,
//	    FOREIGN KEY (expense_id) REFERENCES expenses(id),
//	    FOREIGN KEY (user_id) REFERENCES users(id)
//	);
