<%@page import="com.java.lib.model.TransReturn"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="libraryDao" class="com.java.lib.dao.LibraryDaoImpl" />
<%
	String userName = (String)session.getAttribute("user");
	List<TransReturn> bookHistory = libraryDao.history(userName);
%>
<jsp:include page="Menu.jsp"/>
<form action="History.jsp">
<table border="3">
	<tr>
		<th>User Name</th>
		<th>Book Id</th>
		<th>From Date</th>
		<th>To Date</th>
	</tr>
<%
	for(TransReturn tr : bookHistory) {
%>
	<tr>
		<td><%=tr.getUserName()%> </td>
		<td><%=tr.getBookId()%> </td>
		<td><%=tr.getFromDate()%> </td>
		<td> <%=tr.getToDate()%> </td>
			
		</td>
	</tr>
<%
	}
%>
</table>

</body>
</html>