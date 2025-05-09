package com.java.lms.model;

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
public class Leave {
	private int leaveId;
	private int noOfDays;
	private String leaveMgrCmts;
	private int empId;
	private Date leaveStDt;
	private Date leaveEndDt;
	private String leaveType;
	private String leaveStatus;
	private String leaveReason;
}
