package com.java.jdbc.daoTest;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.java.jdbc.dao.CrimeAnalysisServiceImpl;
import com.java.jdbc.model.IncidentType;
import com.java.jdbc.model.Incidents;
import com.java.jdbc.model.Status;

public class AppTest {
	
	
	private CrimeAnalysisServiceImpl incidentDao;
	
	@Before
	public void setUp() {
		incidentDao = new CrimeAnalysisServiceImpl();
	}
	
	@Test
	public void testCreateIncident() throws ClassNotFoundException, SQLException {
		
		Incidents incident = new Incidents();
		incident.setIncidentType(IncidentType.Robbery);
		incident.setIncidentDate(Date.valueOf("2024-04-02"));
		incident.setLatitude(23.456);
		incident.setLongitude(120.345);
		incident.setDescriptions("Test Incident Description");
		incident.setStatus(Status.Open);
		incident.setAgencyId(1);
		incident.setOfficerId(1);
	
		boolean result = incidentDao.createIncident(incident);
	
		assertTrue("Incident should be created successfully", result);
	}
		
}
