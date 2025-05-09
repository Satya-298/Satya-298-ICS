package com.java.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
@AllArgsConstructor
@NoArgsConstructor
public class Agent {

	private int agentId;
	private String name;
	private String city;
	private Gender gender;
	private int maritalstatus;
	private double premium;
}

//AgentID INT primary key,
//Name varchar(30),
//City varchar(30),
//GENDER ENUM('MALE','FEMALE'),
//MaritalStatus INT,
//Premium numeric(9,2)