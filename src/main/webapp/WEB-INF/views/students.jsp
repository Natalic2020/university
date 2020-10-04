<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center">
    <h2>Students</h2>
    <table border="1" cellpadding="5">
        <tr>
            <th>UUID</th>
            <th>First name</th>
            <th>Last name</th>
        </tr>
        <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.idStudent}</td>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
        </tr>
        </c:forEach>
    </table>
</div>   
</body>
</html>