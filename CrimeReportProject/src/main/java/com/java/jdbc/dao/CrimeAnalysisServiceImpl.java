package com.java.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.jdbc.model.IncidentType;
import com.java.jdbc.model.Incidents;
import com.java.jdbc.model.ReportStatus;
import com.java.jdbc.model.Reports;
import com.java.jdbc.model.Status;
import com.java.jdbc.util.ConnectionHelper;

public class CrimeAnalysisServiceImpl implements ICrimeAnalysisService{
	
	Connection conn;
	PreparedStatement pstmt;
	
	
	@Override
	public boolean createIncident(Incidents incident) throws ClassNotFoundException, SQLException {
		
		conn = ConnectionHelper.getConnection();
		String cmd = "Insert INTO Incidents(IncidentType, IncidentDate, Latitude, Longitude, Descriptions, Status, AgencyID, OfficerID) Values(?,?,?,?,?,?,?,?);";
		pstmt = conn.prepareStatement(cmd);
		pstmt.setString(1, incident.getIncidentType().name());
		pstmt.setDate(2, incident.getIncidentDate());
		pstmt.setDouble(3, incident.getLatitude());
		pstmt.setDouble(4, incident.getLongitude());
		pstmt.setString(5, incident.getDescriptions());
		pstmt.setString(6, incident.getStatus().name());
		pstmt.setInt(7, incident.getAgencyId());
		pstmt.setInt(8, incident.getOfficerId());
		int res = pstmt.executeUpdate();
		
				
		return res > 0;
	}

	@Override
	public boolean updateIncidentStatus(Status status, int incidentId) throws ClassNotFoundException, SQLException {
		
		conn = ConnectionHelper.getConnection();
		String cmd = "Update Incidents set Status = ? where IncidentID = ?;";
		pstmt = conn.prepareStatement(cmd);
		pstmt.setString(1, status.name());
		pstmt.setInt(2, incidentId);
		int res = pstmt.executeUpdate();
		
		
		return res > 0;
	}

	@Override
	public List<Incidents> getIncidentsInDateRange(Date startDate, Date endDate) throws ClassNotFoundException, SQLException {
		
		List<Incidents> incidentList = new ArrayList<Incidents>();
		
		conn = ConnectionHelper.getConnection();
		String cmd = "Select * from Incidents where IncidentDate BETWEEN ? AND ?;";
		pstmt = conn.prepareStatement(cmd);
		pstmt.setDate(1, startDate);
		pstmt.setDate(2, endDate);
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			Incidents incident = new Incidents();
			incident.setIncidentId(rs.getInt("IncidentId"));
			incident.setIncidentType(IncidentType.valueOf(rs.getString("IncidentType")));
			incident.setIncidentDate(rs.getDate("IncidentDate"));
			incident.setLatitude(rs.getDouble("Latitude"));
			incident.setLongitude(rs.getDouble("Longitude"));
			incident.setDescriptions(rs.getString("Descriptions"));
			incident.setStatus(Status.valueOf(rs.getString("Status")));
			incident.setAgencyId(rs.getInt("AgencyID"));
			incident.setOfficerId(rs.getInt("OfficerID"));
			
			incidentList.add(incident);
			
		}
		
		
		return incidentList;
	}

	@Override
	public List<Incidents> searchIncidents(IncidentType criteria) throws ClassNotFoundException, SQLException {
		
		List<Incidents> incidentList = new ArrayList<Incidents>();
		
		conn = ConnectionHelper.getConnection();
		String cmd = "Select * from Incidents where IncidentType = ?;";
		pstmt = conn.prepareStatement(cmd);
		pstmt.setString(1, criteria.name());
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			Incidents incident = new Incidents();
			incident.setIncidentId(rs.getInt("IncidentId"));
			incident.setIncidentType(IncidentType.valueOf(rs.getString("IncidentType")));
			incident.setIncidentDate(rs.getDate("IncidentDate"));
			incident.setLatitude(rs.getDouble("Latitude"));
			incident.setLongitude(rs.getDouble("Longitude"));
			incident.setDescriptions(rs.getString("Descriptions"));
			incident.setStatus(Status.valueOf(rs.getString("Status")));
			incident.setAgencyId(rs.getInt("AgencyID"));
			incident.setOfficerId(rs.getInt("OfficerID"));
			
			incidentList.add(incident);
			
		}
		
		return incidentList;
	}

	@Override
	public Reports generateIncidentReport(Incidents incident) throws ClassNotFoundException, SQLException {
		
		conn = ConnectionHelper.getConnection();
		String cmd = "Select * from Reports Where IncidentID = ?;";
		pstmt = conn.prepareStatement(cmd);
		pstmt.setInt(1, incident.getIncidentId());
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			Reports report = new Reports();
			report.setReportId(rs.getInt("ReportId"));
			report.setIncidentId(rs.getInt("IncidentId"));
			report.setReportingOfficer(rs.getInt("ReportingOfficer"));
			report.setReportDate((rs.getDate("ReportDate")));
			report.setReportDetails(rs.getString("ReportDetails"));
			try {
	            report.setRstatus(ReportStatus.valueOf(rs.getString("Status")));
	        } catch (IllegalArgumentException e) {
	            // set a default value (e.g., Draft)
	            System.out.println("Status found in database for Incident ID " + incident.getIncidentId() + ": " + rs.getString("Status"));
	            report.setRstatus(ReportStatus.DRAFT);  // Set a default status
	        }
			
			return report;
		}
		return null;
	}

}
