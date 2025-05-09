<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="leaveDao" class="com.java.hib.dao.EmployDaoImpl" />
	<c:set var="employList" value="${leaveDao.showEmployDao()}" />
	<table align="center" border="3">
		<tr>
			<th>Employ No</th>
			<th>Name</th>
			<th>Gender</th>
			<th>Dept</th>
			<th>Desig</th>
			<th>Basic</th>
		</tr>
		<c:forEach var="employ" items="${param.employList}">
			<tr>
				<td>${employ.empno}</td>
				<td>${employ.name}</td>
				<td>${employ.gender}</td>
				<td>${employ.dept}</td>
				<td>${employ.desig}</td>
				<td>${employ.basic}</td>
			</tr>
		
		</c:forEach>
	</table>

</body>
</html>