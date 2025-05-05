package com.java.jdbc.model;

import java.sql.Date;

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

public class Reports {
	
	private int reportId;
	private int incidentId;
	private int reportingOfficer;
	private Date reportDate;
	private String reportDetails;
	private ReportStatus rstatus;

}


//Create table Reports
//(
//	ReportID INT PRIMARY KEY auto_increment,
//    IncidentID INT,
//    ReportingOfficer INT,
//    ReportDate DATE NOT NULL,
//    ReportDetails TEXT NOT NULL, 
//    Status ENUM('Draft','Finalized') NOT NULL,
//    FOREIGN KEY (ReportingOfficer) REFERENCES Officers(OfficerID)
//);