<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="calc" class="com.java.beans.Calculation" scope="session" />

	<jsp:setProperty property="*" name="calc"/>
	Sum is :
		<%=calc.sum() %>
		<br/><br/>
	Subtract is :
		<%=calc.sub() %>
		<br/><br/>
	Mult is :
		<%=calc.mult() %>

</body>
</html>