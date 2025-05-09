<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
        /* Apply full viewport height and remove default margins */
        body, html {
            height: 100%;
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }

        /* Center the form using Flexbox */
        .login-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        /* Style the form */
        form {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }

        h2 {
            margin-bottom: 20px;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box; /* Ensures padding doesn't affect width */
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            box-sizing: border-box; /* Ensures padding doesn't affect width */
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .error-message {
            color: red;
            margin-top: 10px;
        }
        
        .adminlogin-link {
    margin-top: 15px;
    font-size: 14px;
    text-align: center;
}

.adminlogin-link a {
    color: #007bff;
    text-decoration: none;
}

.adminlogin-link a:hover {
    text-decoration: underline;
}
        
    </style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="login-container">
<form method="post" action="AdminLogin.jsp">
<center>
<h2>Please Login :</h2>
Admin Name : <input type="text" name="adminName" size="50"> <br/><br/>
Password : <input type="password" name="passWord" size="50"> <br/><br/>
<input type="submit" value="Login">
<div class="adminlogin-link">
    Don't have an account? <a href="AddAdmin.jsp">Create Account</a>
</div>
</center>
</form>
<%
	if (request.getParameter("adminName")!=null && 
		request.getParameter("passWord")!=null) {
		String admin = request.getParameter("adminName");
		String pwd = request.getParameter("passWord");
%>
	<jsp:useBean id="libAdmin" class="com.java.lib.model.LibAdmin" />
	<jsp:setProperty property="*" name="libAdmin"/>
	<jsp:useBean id="libraryDao" class="com.java.lib.dao.LibraryDaoImpl" />
	
<%
	int count = libraryDao.adminlogin(libAdmin);
	if (count==1) {
		session.setAttribute("admin", request.getParameter("adminName"));
%>
	<jsp:forward page="MenuAdmin.jsp" />
<%
	} else {
		out.println("Invalid Credentials...");
%>
	<jsp:forward page="MenuAdmin.jsp" />
<%
	}
}
%>
</div>
</body>
</html>