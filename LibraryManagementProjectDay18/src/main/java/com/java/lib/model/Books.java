package com.java.lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Books {
	
	private int id;
	private String name;
	private String author;
	private String edition;
	private String dept;
	private int noOfBooks;	
	

}
