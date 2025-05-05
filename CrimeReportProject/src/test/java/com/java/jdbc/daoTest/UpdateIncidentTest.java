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

public class UpdateIncidentTest {
	
	
	private CrimeAnalysisServiceImpl incidentDao;
	
	@Before
	public void setUp() {
		incidentDao = new CrimeAnalysisServiceImpl();
	}
	
	@Test
	public void testUpdateIncidentStatus() throws ClassNotFoundException, SQLException {
		
		Incidents incident = new Incidents();
		incident.setStatus(Status.Closed);
	
		
		boolean update = incidentDao.updateIncidentStatus(Status.Open, 2);
		
		assertTrue("Incident Status should be updated successfully", update);
	}
		
}
