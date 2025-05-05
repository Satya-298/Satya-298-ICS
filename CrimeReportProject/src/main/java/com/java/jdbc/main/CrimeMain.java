package com.java.jdbc.main;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.java.jdbc.bal.CrimeAnalysisServiceBal;
import com.java.jdbc.exception.IncidentNumberNotFoundException;
import com.java.jdbc.model.IncidentType;
import com.java.jdbc.model.Incidents;
import com.java.jdbc.model.Reports;
import com.java.jdbc.model.Status;

public class CrimeMain {
	
	static Scanner sc;
	static CrimeAnalysisServiceBal bal;
	
	static {
		
		sc = new Scanner(System.in);
		bal = new CrimeAnalysisServiceBal();
	}
	
	public static void main(String[] args) {
		
		int n;
		
		do {
			System.out.println("1. Create Incident ");
			System.out.println("2. Update Incident Status ");
			System.out.println("3. Get Incidents In Date Range");
			System.out.println("4. Search Incidents");
			System.out.println("5. Generate Incident Report");
			System.out.println("6. Exit");
			System.out.println("Enter Your Choice");
			
			n = sc.nextInt();
			
			switch(n) {
			
			case 1 : 
				createIncidentMain();
				break;
			case 2 :
				updateIncidentStatus();
				break;
			case 3 :
				getIncidentsInDateRange();
				break;
			case 4 :
				searchIncidents();
				break;
			case 5 :
				generateIncidentReport();
				break;
			case 6 :
				System.out.println("Come Back Soon");
				break;
			default :
				System.out.println("Invalid Choice. Please Enter A number between 1 and 6.");
			}
		} while (n != 6);
		
	}
	
		public static void generateIncidentReport() {
			
			System.out.println("Enter the IncidentId");
			int incidentid;
			incidentid = sc.nextInt();
			
			Incidents incident = new Incidents();
			incident.setIncidentId(incidentid);
			
			try {
				Reports report = bal.generateIncidentReport(incident);
				
				if (report != null) {
					System.out.println("Generated Incident Report : " +report);
				} else {
					System.out.println("Incident Report Not Found For IncidentId " +incidentid);
				}
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Error Generating Incident Report " +e.getMessage());
			}
		}
	
		public static void searchIncidents() {
			
			System.out.println("Enter the criteria (Robbery, Homicide, Theft");
			IncidentType crit = IncidentType.valueOf(sc.next());
			
			try {
				List<Incidents> incidentList = bal.searchIncidents(crit);
				if (incidentList.isEmpty()) {
					System.out.println("Searched Incident Not Found");
				} else {
					for (Incidents incidents : incidentList) {
						System.out.println(incidents);
					}
				}
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Error Searched Incidents " +e.getMessage());
				
			}
		}
		
		
		
		public static void getIncidentsInDateRange() {
			System.out.println("Enter the StartDate (yyyy-mm-dd");
			Date strdate = Date.valueOf(sc.next());
			System.out.println("Enter the EndDate (yyyy-mm-dd");
			Date enddate = Date.valueOf(sc.next());
			
			if (strdate.after(enddate)) {
				System.out.println("Start date should not after end date");
				return;
			}
			
			try {
				List<Incidents> incidentList = bal.getIncidentsInDateRange(strdate, enddate);
				if (incidentList.isEmpty()) {
					System.out.println("Entered Date is not in range");
				} else {
					for (Incidents incidents : incidentList) {
						System.out.println(incidents);
					}
				}
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Error in Date Range " +e.getMessage());
			}
		}		
		
		
		public static void updateIncidentStatus() {
			
			System.out.println("Enter the Status of Incident (Open,Closed,Under_Investigation)");
			Status status = Status.valueOf(sc.next());
			System.out.println("Enter the IncidentId");
			int incidentid = sc.nextInt();
			
			try {
				boolean res = bal.updateIncidentStatus(status, incidentid);
				
				if(res) {
					System.out.println("Incident Status Updated Successfully");
				} else {
					System.out.println("Failed To Update Incident Status");
				}
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Error Updating Incident " +e.getMessage());
			}
		}
		
		public static void createIncidentMain() {
			Incidents incident = new Incidents();
			System.out.println("Enter the Incident Type (Robbery,Homicide,Theft)");
			incident.setIncidentType(IncidentType.valueOf(sc.next()));
			System.out.println("Enter the Incident Date (yyyy-mm-dd)");
			incident.setIncidentDate(Date.valueOf(sc.next()));
			System.out.println("Enter the Latitude (-90 to 90)");
			incident.setLatitude(sc.nextDouble());
			System.out.println("Enter the Longitude (-180 to 180)");
			incident.setLongitude((sc.nextDouble()));
			System.out.println("Enter the Descriptions (max 500 characters)");
			sc.nextLine();
			incident.setDescriptions(sc.nextLine());
			System.out.println("Enter the Incident Status (Open, Closed, Under_Investigation)");
			incident.setStatus(Status.valueOf(sc.next()));
			System.out.println("Enter the AgencyId");
			incident.setAgencyId(sc.nextInt());
			System.out.println("Enter the OfficerId");
			incident.setOfficerId(sc.nextInt());
			
			try {
				boolean res = bal.createIncident(incident);
				if(res) {
					System.out.println("Incident Created Successfully");
				} else {
					System.out.println("Failed To Create Incident");
				}
			} catch (ClassNotFoundException | SQLException | IncidentNumberNotFoundException e) {
				System.out.println("Error Creating Incident: " +e.getMessage());
			}
	}
}
	
