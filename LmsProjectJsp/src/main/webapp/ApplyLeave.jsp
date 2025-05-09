<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f6f8;
        margin: 0;
        padding: 0;
    }

    h2 {
        text-align: center;
        color: #333;
    }

    form {
        background-color: #fff;
        padding: 20px;
        margin: 30px auto;
        width: 500px;
        box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
        border-radius: 8px;
    }

    label {
        font-weight: bold;
        display: block;
        margin-bottom: 5px;
    }

    input[type="text"],
    input[type="date"],
    select {
        width: 100%;
        padding: 8px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    input[type="submit"] {
        background-color: #007bff;
        color: white;
        padding: 10px 15px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        width: 100%;
        font-size: 16px;
    }

    input[type="submit"]:hover {
        background-color: #0056b3;
    }

    .message {
        text-align: center;
        color: green;
        font-weight: bold;
        margin-top: 20px;
    }
</style>

<meta charset="UTF-8">
<title>Apply Leave</title>
</head>
<body>
	<form method="post" action="ApplyLeave.jsp" >
		<center>
			Enter Employ Id : 
			<input type="text" name="empId" /> <br/><br/>
			Enter Leave Start Date :
			<input type="date" name="leaveStDt" /> <br/><br/>
			Enter Leave End Date :
			<input type="date" name="leaveEndDt" /> <br/><br/>
			Enter Leave Type :
			<select id="leave" name="leaveType" > <br/><br/>
  				<option value="EL">EL</option>
  				<option value="PL">PL</option>
  				<option value="SL">SL</option>
			</select>
			Enter Leave Reason :
			<input type="text" name="leaveReason"> <br/><br/>
			<input type="submit" value="Apply Leave" />
	</form>
	
	<!--  Checks whether the data are empty or not -->
	<c:if test="${not empty param.empId
			  and not empty param.leaveStDt
			  and not empty param.leaveEndDt
			  and not empty param.leaveType
			  and param.leaveType != ''}">
			  
	<!-- 	Convert Date -->
	<jsp:useBean id="leaveDao" class="com.java.lms.dao.LeaveDaoImpl" />
	<c:set var="stdt" value="${leaveDao.convertToSqlDate(param.leaveStDt)}" />
	<c:set var="endt" value="${leaveDao.convertToSqlDate(param.leaveEndDt)}" />	
	
	<!-- Set variables for Leave object -->
	<jsp:useBean id="leave" class="com.java.lms.model.Leave" />
	
	<jsp:setProperty property="empId" name="leave" value="${param.empId}" />
	
	<jsp:setProperty property="leaveStDt" name="leave" value="${stdt}" />
	
	<jsp:setProperty property="leaveEndDt" name="leave" value="${endt}" />
	
	<jsp:setProperty property="leaveType" name="leave" value="${param.leaveType}" />
	
	<jsp:setProperty property="leaveReason" name="leave" value="${param.leaveReason}" />
	
	<c:set var="result" value="${leaveDao.applyLeave(leave)}" />
	<c:out value="${result}"></c:out>
	
	<jsp:forward page="DashBoard.jsp" />
	</c:if>
</body>
</html>