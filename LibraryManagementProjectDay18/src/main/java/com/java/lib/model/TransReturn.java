package com.java.lib.model;

import java.sql.Date;
import java.time.LocalDate;

import lombok.Data;

@Data
public class TransReturn {
	
	private String userName;
	private int bookId;
	private Date fromDate;
	private LocalDate toDate;
	
}


//Username varchar(50) NOT NULL,
//BookId INT NOT NULL,
//Fromdate date,
//Todate TIMESTAMP DEFAULT CURRENT_TIMESTAMP