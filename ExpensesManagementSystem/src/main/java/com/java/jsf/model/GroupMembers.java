package com.java.jsf.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class GroupMembers implements Serializable{
	
	private int id;
    private Groups groups;          
    private Users users;            
    private Timestamp joined_at;
    private int advance;

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

	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}

	public Timestamp getJoined_at() {
		return joined_at;
	}
	public void setJoined_at(Timestamp joined_at) {
		this.joined_at = joined_at;
	}
	
	public int getAdvance() {
		return advance;
	}
	public void setAdvance(int advance) {
		this.advance = advance;
	}
	
	public GroupMembers(int id, Groups groups, Users users, Timestamp joined_at, int advance) {
		this.id = id;
		this.groups = groups;
		this.users = users;
		this.joined_at = joined_at;
		this.advance = advance;
	}
	public GroupMembers() {
		super();
		// TODO Auto-generated constructor stub
	}
}

//CREATE TABLE group_members (
//	    id INT AUTO_INCREMENT PRIMARY KEY,
//	    group_id INT NOT NULL,
//	    user_id INT NOT NULL,
//	    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//	    FOREIGN KEY (group_id) REFERENCES group_s(id),
//	    FOREIGN KEY (user_id) REFERENCES users(id)
//	);

