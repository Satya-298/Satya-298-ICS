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

public class Incidents {
	
	private int incidentId;
	private IncidentType incidentType;
	private Date incidentDate;
	private double latitude;
	private double longitude;
	private String descriptions;
	private Status Status;
	private int agencyId;
	private int officerId;

}
