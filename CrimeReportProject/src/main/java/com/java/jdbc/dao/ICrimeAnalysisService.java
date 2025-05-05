package com.java.jdbc.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.java.jdbc.model.IncidentType;
import com.java.jdbc.model.Incidents;
import com.java.jdbc.model.Reports;
import com.java.jdbc.model.Status;

public interface ICrimeAnalysisService {
	
	boolean createIncident(Incidents incident) throws ClassNotFoundException, SQLException;
	boolean updateIncidentStatus(Status status, int incidentId) throws ClassNotFoundException, SQLException;
	List<Incidents> getIncidentsInDateRange(Date startDate, Date endDate) throws ClassNotFoundException, SQLException;
	List<Incidents> searchIncidents(IncidentType criteria) throws ClassNotFoundException, SQLException;
	Reports generateIncidentReport(Incidents incident) throws ClassNotFoundException, SQLException;

}
