package com.java.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.java.lms.model.Employ;
import com.java.lms.model.Leave;
import com.java.lms.util.ConnectionHelper;

public class LeaveDaoImpl implements LeaveDao{
	
	Connection connection;
	PreparedStatement ps;

	@Override
	public String applyLeave(Leave leave) throws ClassNotFoundException, SQLException {
		
		/**
		 * Requirements
		 * avoid the weekends : if user applying for 5 days of leave and weekends are there then 
		 * avoid them and don't count as leaves.
		 * 
		 * avoid special holidays : if user applying for 6 days and between there some special
		 * holidays are there then don't count them as leaves.
		 * 
		 * if user applied from 20/04 to 30/04, and again applying for 20/04 to 25/04 then show 
		 * some message as you have already applied for this date.
		 */
		
		connection = ConnectionHelper.getConnection();
		
		 LocalDate today = LocalDate.now();
	     LocalDate startDate = leave.getLeaveStDt().toLocalDate();
	     LocalDate endDate = leave.getLeaveEndDt().toLocalDate();
	    
	     if (startDate.isBefore(today) || endDate.isBefore(today)) {
	            return "Leave start or end date cannot be in the past.";
	     }
	     
	     if (startDate.isAfter(endDate)) {
	            return "Leave start date cannot be after end date.";
	     }
		
		long diff = leave.getLeaveEndDt().getTime() - leave.getLeaveStDt().getTime();
        int noOfDays = (int)(diff / (1000 * 60 * 60 * 24)) + 1;
        
        EmployDao employDao = new EmployDaoImpl();
        Employ employ = employDao.searchEmployDao(leave.getEmpId());
        
        if(employ.getEmpLeaveBal() < noOfDays) {
        	return "Insufficient Leave Balance";
        }
		
		
        String query = "INSERT INTO leave_history (LEAVE_NO_OF_DAYS, LEAVE_MNGR_COMMENTS, EMP_ID, LEAVE_START_DATE, LEAVE_END_DATE, LEAVE_TYPE, LEAVE_STATUS, LEAVE_REASON) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		ps = connection.prepareStatement(query);
		ps.setInt(1, noOfDays); 
        ps.setString(2, ""); 
        ps.setInt(3, leave.getEmpId());
        ps.setDate(4, leave.getLeaveStDt()); 
        ps.setDate(5, leave.getLeaveEndDt()); 
        ps.setString(6, leave.getLeaveType());
        ps.setString(7, "PENDING"); 
        ps.setString(8, leave.getLeaveReason());
		
        int leaveRow = ps.executeUpdate();

        if (leaveRow > 0) {
            employ.setEmpLeaveBal(employ.getEmpLeaveBal() - noOfDays);
            employDao.updateEmployDao(employ.getEmpId(), employ.getEmpLeaveBal());

            return "Leave applied successfully. Please wait for manager approval.";
        } else {
            return "Failed to apply for leave.";
        }
	}

	@Override
	public List<Leave> showLeaveHistory(int empId) throws ClassNotFoundException, SQLException {
		
		Leave leave = null;
		List<Leave> leaveList = new ArrayList<Leave>();
		connection = ConnectionHelper.getConnection();
		
		String query = "select * from leave_history where emp_Id = ?";
		ps = connection.prepareStatement(query);
		ps.setInt(1, empId);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			leave = new Leave();
			leave.setLeaveId(rs.getInt("leave_id"));
	        leave.setEmpId(rs.getInt("emp_id"));
	        leave.setLeaveStDt(rs.getDate("leave_start_date"));
	        leave.setLeaveEndDt(rs.getDate("leave_end_date"));
	        leave.setLeaveType(rs.getString("leave_type"));
	        leave.setLeaveReason(rs.getString("leave_reason"));
	        leave.setLeaveStatus(rs.getString("leave_status"));
	        leave.setLeaveMgrCmts(rs.getString("leave_mngr_comments"));
	        leave.setNoOfDays(rs.getInt("leave_no_of_days"));
	        
	        leaveList.add(leave);
		}
		
		return leaveList;
		
	}

	@Override
	public List<Leave> pendingLeaveList(int mgrId) throws ClassNotFoundException, SQLException {
		Leave leave = null;
		List<Leave> pendingList = new ArrayList<Leave>();
		connection = ConnectionHelper.getConnection();
		
		String query = "select * from leave_history where emp_id in\r\n"
				+ "(\r\n"
				+ "select Emp_Id from employee where emp_manager_id= ? \r\n"
				+ ") and leave_status = 'PENDING'";
		
		ps = connection.prepareStatement(query);
		ps.setInt(1, mgrId);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			leave = new Leave();
			leave.setLeaveId(rs.getInt("leave_id"));
	        leave.setEmpId(rs.getInt("emp_id"));
	        leave.setLeaveStDt(rs.getDate("leave_start_date"));
	        leave.setLeaveEndDt(rs.getDate("leave_end_date"));
	        leave.setLeaveType(rs.getString("leave_type"));
	        leave.setLeaveReason(rs.getString("leave_reason"));
	        leave.setLeaveStatus(rs.getString("leave_status"));
	        leave.setLeaveMgrCmts(rs.getString("leave_mngr_comments"));
	        leave.setNoOfDays(rs.getInt("leave_no_of_days"));
	        
	        pendingList.add(leave);
		}
		
		return pendingList;
		
	}

	@Override
	public String manageLeaveDao(int leaveId, int mgrId, boolean isAccepted, String mgrComment) throws ClassNotFoundException, SQLException {
		connection = ConnectionHelper.getConnection();
        String query = "SELECT * FROM leave_history WHERE leave_id = ?";
        ps = connection.prepareStatement(query);
        ps.setInt(1, leaveId);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            return "Leave request not found.";
        }

        int empId = rs.getInt("emp_id");
        int noOfDays = rs.getInt("leave_no_of_days");
        String leaveStatus = isAccepted ? "APPROVED" : "DENIED";

        if (!isAccepted) {
            EmployDao employDao = new EmployDaoImpl();
            Employ employee = employDao.searchEmployDao(empId);
            employee.setEmpLeaveBal(employee.getEmpLeaveBal() + noOfDays);
            employDao.updateEmployDao(empId, employee.getEmpLeaveBal());
        }

        String updateQuery = "UPDATE leave_history SET LEAVE_STATUS = ?, LEAVE_MNGR_COMMENTS = ? WHERE leave_id = ?";
        ps = connection.prepareStatement(updateQuery);
        ps.setString(1, leaveStatus);
        ps.setString(2, mgrComment);
        ps.setInt(3, leaveId);

        int updatedRows = ps.executeUpdate();

        if (updatedRows > 0) {
            return "Leave request " + (isAccepted ? "approved" : "denied") + " successfully.";
        } else {
            return "Failed to update leave request.";
        }
	}
	
	public static java.sql.Date convertToSqlDate(String dateStr) {
	    if (dateStr == null || dateStr.trim().isEmpty()) {
	        return null;
	    }
	    return java.sql.Date.valueOf(dateStr);
	}
}
