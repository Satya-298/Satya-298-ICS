<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f6f9;
        padding: 20px;
    }
    table {
        width: 90%;
        margin: 20px auto;
        border-collapse: collapse;
        background-color: #fff;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    th, td {
        padding: 12px;
        text-align: center;
        border: 1px solid #ccc;
    }
    th {
        background-color: #007bff;
        color: white;
    }
    tr:nth-child(even) {
        background-color: #f9f9f9;
    }
    tr:hover {
        background-color: #eef;
    }
    .center-button {
        text-align: center;
        margin-top: 20px;
    }
    .center-button input[type="submit"] {
        padding: 10px 20px;
        background-color: #28a745;
        color: white;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
    }
    .center-button input[type="submit"]:hover {
        background-color: #218838;
    }
    p {
        margin: 8px 0;
    }
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="employDao" class="com.java.lms.dao.EmployDaoImpl" />
	<c:set var="empId" value="${param.empId}" />
	<c:set var="mgrId" value="${param.mgrId}" />
	<c:set var="myInfo" value="${employDao.searchEmployDao(param.empId)}" />
	<c:set var="myManagerInfo" value="${employDao.searchEmployDao(param.mgrId)}" />
	<table border="3" align="center">
		<tr>
			<th>
				<p>Employee Id :<b>${myInfo.empId} </b> </p>
				<p>Employee Name <b>${myInfo.empName}</b> </p>
				<p>Email <b>${myInfo.empMail}</b>
				<p>Mobile No : <b>${myInfo.empMobno }</b> </p>
				<p>Department : <b>${myInfo.empDept}</b> </p>
				<p>Date Of Join :<b>${myInfo.empJoinDate}</b> </p>
				<p>Manager Id : <b>${myInfo.empMgrId}</b> </p>
				<p>Employ Leave Balance <b>${myInfo.empLeaveBal} </b> </p>
			</th>
			<th>
				<p>Manager Id : <b>${myManagerInfo.empId} </b> </p>
				<p>Manager Name <b>${myManagerInfo.empName}</b> </p>
				<p>Manager Email <b>${myManagerInfo.empMail}</b>
				<p>Manager Mobile No : <b>${myManagerInfo.empMobno }</b> </p>
				<p>Manager Department : <b>${myManagerInfo.empDept}</b> </p>
				<p>Manager Date Of Join :<b>${myManagerInfo.empJoinDate}</b> </p>
				<p>Manager Id : <b>${myManagerInfo.empMgrId}</b> </p>
				<p>Manager Leave Available Balance <b>${myManagerInfo.empLeaveBal} </b> </p>
			</th>
		</tr>
	</table>
	
	<!--Leave History  -->
	
	<jsp:useBean id="leaveDao" class="com.java.lms.dao.LeaveDaoImpl" />
	<c:set var="leaveList" value="${leaveDao.showLeaveHistory(empId)}" />
	
		<%-- <center><h2>Leave History of Emp Id ${myInfo.empId} <br/>
	  					Employ Name ${myInfo.empName}</h2></center>  --%>
	
	<table border="3" align="center">
		<tr>
			<th>Leave Id</th>
			<th>Employ Id</th>
			<th>Leave Start Date</th>
			<th>Leave End Date</th>
			<th>Leave Type</th>
			<th>Leave Reason</th>
			<th>Leave Status</th>
			<th>Leave Manager Comments</th>
			<th>No Of Days</th>
		</tr>
		
		<c:forEach var="leave" items="${leaveList}" >
			<tr>
				<td>${leave.leaveId}</td>
				<td>${leave.empId}</td>
				<td>${leave.leaveStDt}</td>
				<td>${leave.leaveEndDt}</td>
				<td>${leave.leaveType}</td>
				<td>${leave.leaveReason}</td>
				<td>${leave.leaveStatus}</td>
				<td>${leave.leaveMgrCmts}</td>
				<td>${leave.noOfDays}</td>				
				
			</tr>
		</c:forEach>
		</table>
		<!-- Apply Leave Button Below Table -->
	<div style="text-align:center; margin-top: 20px;">
       	<form action="ApplyLeave.jsp" method="get">
       		<input type="submit" value="Apply Leave"> 
       	</form>
    </div>
    
    <!-- Pending Leaves -->
    <c:set var="empId" value="${param.empId}" />
    <c:set var="leaveHistory" value="${leaveDao.showLeaveHistory(param.empId)}" />
    <c:set var="temp" value="0"/>
		<table border="3" align="center">
		<c:forEach var="leave" items="${leaveHistory}">
			<c:if test="${temp == leave.empId }">
			<tr>
				<td></td>
				<td>${leave.leaveId}</td>
				<td>${leave.noOfDays}</td>
				<td>${leave.leaveStDt}</td>
				<td>${leave.leaveEndDt}</td>
				<td>${leave.leaveType}</td>
				<td>${leave.leaveStatus}</td>
				<td colspan="2">${leave.leaveReason}</td>
			</tr></c:if>
			<c:if test="${temp != leave.empId }">
			<c:set var="temp" value="${leave.empId}" />
			<c:set var="employ" value="${employDao.searchEmployDao(leave.empId)}" />
			
			<tr>
				<th colspan="2">Employee ID</th>
				<td>${employ.empId}</td>
				<th colspan="2">Employee Name</th>
				<td>${employ.empName}</td>
				<th colspan="2">Employee Leave Balance</th>
				<td>${employ.empLeaveBal}</td>
			</tr>
			<tr>
				<th></th>
				<th>LeaveId</th>
				<th>Number Of Days</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Leave Type</th>
				<th>Status</th>
				<th colspan="2">Leave Reason</th>
			</tr>
			</c:if>
		</c:forEach>
	</table><br/>
		
	
	
</body>
</html>