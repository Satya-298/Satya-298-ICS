package com.java.jdbc.bal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.java.jdbc.dao.CrimeAnalysisServiceImpl;
import com.java.jdbc.dao.ICrimeAnalysisService;
import com.java.jdbc.exception.IncidentNumberNotFoundException;
import com.java.jdbc.model.IncidentType;
import com.java.jdbc.model.Incidents;
import com.java.jdbc.model.Reports;
import com.java.jdbc.model.Status;
import com.java.jdbc.util.ConnectionHelper;

public class CrimeAnalysisServiceBal {
	
	static ICrimeAnalysisService dao;
	static StringBuilder sb;
	
	static 
	{
		dao = new CrimeAnalysisServiceImpl();
		sb = new StringBuilder();
	}
	
	public boolean createIncident(Incidents incident) throws ClassNotFoundException, SQLException, IncidentNumberNotFoundException {
		
		if (validateIncident(incident) == true) {
			return dao.createIncident(incident);
		}
		throw new IncidentNumberNotFoundException(sb.toString());
	}
	
	public boolean updateIncidentStatus(Status status, int incidentId) throws ClassNotFoundException, SQLException  {
		return dao.updateIncidentStatus(status, incidentId);
		
	}
	
	public List<Incidents> getIncidentsInDateRange(Date startDate, Date endDate) throws ClassNotFoundException, SQLException{
		return dao.getIncidentsInDateRange(startDate, endDate);
	}
	
	public List<Incidents> searchIncidents(IncidentType criteria) throws ClassNotFoundException, SQLException {
		return dao.searchIncidents(criteria);
	}
	
	public Reports generateIncidentReport(Incidents incident) throws ClassNotFoundException, SQLException {
		return dao.generateIncidentReport(incident);
	}
	public boolean validateIncident(Incidents incident) throws ClassNotFoundException, SQLException {
		
		boolean isValidate = true;
		if (incident.getIncidentType() != IncidentType.Robbery 
			&& incident.getIncidentType() != IncidentType.Homicide 
			&& incident.getIncidentType() != IncidentType.Theft) {
			sb.append("IncidentType should be Valid (Robbery,Homicide,Theft\r\n)");
			isValidate = false;
		}
		
		long currentTime = System.currentTimeMillis();
		long inputTime = incident.getIncidentDate().getTime();
				
		if (incident.getIncidentDate() == null 
				|| inputTime > currentTime ) {
			sb.append("Incident Date should not be null or in future\r\n");
			isValidate = false;
		}
		
		if (incident.getLatitude() < -90 
			|| incident.getLatitude() > 90) {
			
			sb.append("Latitude should be between -90 and 90\r\n");
			isValidate = false;
		}	
		
		if (incident.getLongitude() < -180 
			|| incident.getLongitude() > 180) {
			
			sb.append("Longitude should be between -180 and 180\r\n");
			isValidate = false;
		}	
		
		if (incident.getDescriptions() == null 
			|| incident.getDescriptions().length() > 500) {
			
			sb.append("Descriptions should not be null or exceed 500 characters\r\n");
			isValidate = false;
		}
		
		if (incident.getStatus() != Status.Open 
			&& incident.getStatus() != Status.Closed
			&& incident.getStatus() != Status.Under_Investigation) {
			
			sb.append("Status must be Open/Closed/Under_Investigation\r\n");
			isValidate = false;
		}
		
		if (incident.getAgencyId() <= 0) {
			
			sb.append("AgencyId must be positive\r\n");
			isValidate = false;
		} else {
			Connection conn = ConnectionHelper.getConnection();
			String cmd = "Select 1 from LawEnforcementAgencies Where AgencyID = ?;";
			PreparedStatement pstmt = conn.prepareStatement(cmd);
			pstmt.setInt(1, incident.getAgencyId());
			ResultSet rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				sb.append("AgencyId is not present in database\r\n");
				isValidate = false;
			}
			
		}
		
		if (incident.getOfficerId() <= 0) {
			
			sb.append("OfficerId must be positive\r\n");
			isValidate = false;
		} else {
			Connection conn = ConnectionHelper.getConnection();
			String cmd = "Select 1 from Officers Where OfficerID = ?;";
			PreparedStatement pstmt = conn.prepareStatement(cmd);
			pstmt.setInt(1, incident.getOfficerId());
			ResultSet rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				sb.append("OfficerId is not present in database\r\n");
				isValidate = false;
			}
			
		}
		return isValidate;
	}
	

}
