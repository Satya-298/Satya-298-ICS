<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="AddNewBook.jsp" >
	<center>
		Name :
		<input type="text" name="name" />
		Author :
		<input type="text" name="author" />
		Edition :
		<input type="text" name="edition" />
		Dept :
		<input type="text" name="dept" />
		No. Of Books :
		<input type="text" name="noOfBooks" />
	</center>
	<input type="submit" name="Add Book" />
	</form>
	<%
		if(request.getParameter("author") != null 
			&& request.getParameter("name") != null) {

	%>
	<jsp:useBean id="libBooks" class="com.java.lib.model.Books" />
	<jsp:setProperty property="*" name="libBooks"/>
	<jsp:useBean id="libDao" class="com.java.lib.dao.LibraryDaoImpl"/>
	<%=libDao.addBook(libBooks)	%>
	
	<%
		}
	%>

</body>
</html>