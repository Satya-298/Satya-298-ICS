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
        margin: 0;
        padding: 20px;
    }
    table {
        border-collapse: collapse;
        width: 90%;
        margin: 0 auto;
        background-color: white;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    th, td {
        text-align: center;
        padding: 10px;
        border: 1px solid #ccc;
    }
    th {
        background-color: #007bff;
        color: white;
    }
    tr:nth-child(even) {
        background-color: #f2f2f2;
    }
    tr:hover {
        background-color: #e6f7ff;
    }
    h2 {
        text-align: center;
        color: #333;
    }
    a {
        color: #007bff;
        text-decoration: none;
    }
    a:hover {
        text-decoration: underline;
    }
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="employDao" class="com.java.lms.dao.EmployDaoImpl" />
	<c:set var="employList" value="${employDao.showEmploydao()}" />
	<table border="3" align="center">
		<tr>
			<th>Employ Id</th>
			<th>Employee Name</th>
			<th>Employee Email</th>
			<th>Mobile No</th>
			<th>Date of Join</th>
			<th>Department</th>
			<th>Manager Id</th>
			<th>Employee Available Balance</th>
			<th>ShowInfo</th>
		</tr>
		<c:forEach var="employ" items="${employList}">
			<tr>
				<td>${employ.empId}</td>
				<td>${employ.empName}</td>
				<td>${employ.empMail}</td>
				<td>${employ.empMobno}</td>
				<td>${employ.empJoinDate}</td>
				<td>${employ.empDept}</td>
				<td>${employ.empMgrId}</td>
				<td>${employ.empLeaveBal}</td>
				<td>
					<a href="DashBoard.jsp?empId=${employ.empId}
					&empName=${employ.empName}
					&mgrId=${employ.empMgrId}">Show Info</a>

				</td>
			</tr>
		</c:forEach>
	</table>
		   		 
       	
</body>
</html> 