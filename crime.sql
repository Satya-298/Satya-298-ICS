drop database if exists crime;

create database crime;

use crime;

create table LawEnforcementAgencies 
(
	AgencyID INT PRIMARY KEY auto_increment,
    AgencyName VARCHAR(20) NOT NULL,
    Jurisdiction VARCHAR(20) NOT NULL,
    Address VARCHAR(20) NOT NULL,
    PhoneNo VARCHAR(20) 
);

Create table Victims
(
	VictimID INT PRIMARY KEY auto_increment,
    FirstName VARCHAR(20) NOT NULL,
    LastName VARCHAR(20) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender ENUM('MALE','FEMALE','OTHER'),
    Address VARCHAR(20) NOT NULL,
    PhoneNo VARCHAR(20) 
);

Create table Suspects
(
	SuspectID INT PRIMARY KEY auto_increment,
    FirstName VARCHAR(20) NOT NULL,
    LastName VARCHAR(20) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender ENUM('MALE','FEMALE','OTHER'),
    Address VARCHAR(20) NOT NULL,
    PhoneNo VARCHAR(20) 
);

Create table Officers
(
	OfficerID INT PRIMARY KEY auto_increment,
    FirstName VARCHAR(20) NOT NULL,
    LastName VARCHAR(20) NOT NULL,
    BadgeNumber INT UNIQUE,
    Ranks INT,
    Address VARCHAR(20) NOT NULL,
    PhoneNo VARCHAR(20),
    AgencyID INT,
    FOREIGN KEY (AgencyId) REFERENCES LawEnforcementAgencies(AgencyID)
);

Create table Incidents
(
	IncidentID INT PRIMARY KEY auto_increment,
    IncidentType ENUM('Robbery','Homicide','Theft') NOT NULL,
    IncidentDate DATE NOT NULL,
    Latitude DECIMAL(9,2) NOT NULL,
    Longitude DECIMAL(9,2) NOT NULL,
    Descriptions TEXT,
    Status ENUM('Open','Closed','Under Investigation') NOT NULL,
	AgencyID INT,
    OfficerID INT,
    FOREIGN KEY (AgencyID) REFERENCES LawEnforcementAgencies(AgencyID),
    FOREIGN KEY (OfficerID) REFERENCES Officers(OfficerID)
);

Create table Evidence
(
	EvidenceID INT PRIMARY KEY auto_increment,
    Descriptions TEXT,
    LocationFound VARCHAR(50),
    IncidentID INT,
    FOREIGN KEY (IncidentID) REFERENCES Incidents(IncidentID)
);

Create table Reports
(
	ReportID INT PRIMARY KEY auto_increment,
    IncidentID INT,
    ReportingOfficer INT,
    ReportDate DATE NOT NULL,
    ReportDetails TEXT NOT NULL, 
    Status ENUM('Draft','Finalized') NOT NULL,
    FOREIGN KEY (ReportingOfficer) REFERENCES Officers(OfficerID)
);

INSERT INTO LawEnforcementAgencies (AgencyName, Jurisdiction, Address, PhoneNo)
VALUES 
('Central PD', 'Urban', '123 Main St', '555-0100'),
('Northside Dept', 'Suburban', '456 Oak Ave', '555-0101'),
('East Precinct', 'Urban', '789 Elm Rd', '555-0102');

INSERT INTO Victims (FirstName, LastName, DateOfBirth, Gender, Address, PhoneNo)
VALUES 
('Alice', 'Johnson', '1990-05-12', 'FEMALE', '101 Maple St', '555-0200'),
('Bob', 'Smith', '1985-03-22', 'MALE', '202 Pine Ave', '555-0201'),
('Charlie', 'Lee', '2000-11-15', 'OTHER', '303 Birch Rd', '555-0202');

INSERT INTO Suspects (FirstName, LastName, DateOfBirth, Gender, Address, PhoneNo)
VALUES 
('Daniel', 'Morris', '1988-08-08', 'MALE', '404 Cedar Blvd', '555-0300'),
('Ella', 'King', '1992-02-10', 'FEMALE', '505 Spruce Ln', '555-0301'),
('Francis', 'Nguyen', '1995-09-30', 'OTHER', '606 Redwood Ct', '555-0302');

INSERT INTO Officers (FirstName, LastName, BadgeNumber, Ranks, Address, PhoneNo, AgencyID)
VALUES 
('Henry', 'Black', 1001, 3, '707 Sycamore St', '555-0400', 1),
('Ivy', 'White', 1002, 2, '808 Poplar Ave', '555-0401', 2),
('Jack', 'Green', 1003, 1, '909 Fir Rd', '555-0402', 3);

INSERT INTO Incidents (IncidentType, IncidentDate, Latitude, Longitude, Descriptions, Status, AgencyID, OfficerID)
VALUES 
('Robbery', '2024-11-10', 40.71, -74.01, 'Armed robbery at downtown store', 'Open', 1, 1),
('Homicide', '2025-01-22', 34.05, -118.24, 'Body found near riverbank', 'Under Investigation', 2, 3),
('Theft', '2025-03-15', 41.88, -87.63, 'Stolen vehicle reported in parking lot', 'Closed', 1, 2);

INSERT INTO Evidence (Descriptions, LocationFound, IncidentID)
VALUES 
('Fingerprint on handlebar', 'Bike rack near 123 Main St', 1),
('Broken bottle used in fight', 'Public Park Bench Area', 2),
('Fake ID and transaction logs', 'Victim’s mailbox', 3);

INSERT INTO Reports (IncidentID, ReportingOfficer, ReportDate, ReportDetails, Status)
VALUES 
(1, 1, '2025-04-02', 'Victim reported theft. Initial investigation started.', 'Draft'),
(2, 2, '2025-04-06', 'Suspect detained and questioned.', 'Finalized'),
(3, 3, '2025-04-11', 'Case closed with successful prosecution.', 'Finalized');

CREATE TABLE IncidentVictims (
    IncidentID INT,
    VictimID INT,
    PRIMARY KEY (IncidentID, VictimID),
    FOREIGN KEY (IncidentID) REFERENCES Incidents(IncidentID),
    FOREIGN KEY (VictimID) REFERENCES Victims(VictimID)
);

CREATE TABLE IncidentSuspects (
    IncidentID INT,
    SuspectID INT,
    PRIMARY KEY (IncidentID, SuspectID),
    FOREIGN KEY (IncidentID) REFERENCES Incidents(IncidentID),
    FOREIGN KEY (SuspectID) REFERENCES Suspects(SuspectID)
);


-- select * from LawEnforcementAgencies;
select * from Incidents;
