<%-- 
    Document   : studentOverview
    Created on : 9 May 2026, 8:00:55 pm
    Author     : HP
--%>

<%@page import="model.Student"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student-Overview</title>
        <style>

body{
    font-family: Arial, sans-serif;
    background-color: #f4f6f9;
    margin: 0;
    padding: 0;
}

.container{
    width: 90%;
    margin: 40px auto;
}

h2{
    text-align: center;
    color: #333;
    margin-bottom: 25px;
}

table{
    width: 100%;
    border-collapse: collapse;
    background-color: white;
    box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
}

th{
    background-color: #2c3e50;
    color: white;
    padding: 14px;
    text-align: center;
}

td{
    padding: 12px;
    text-align: center;
    border-bottom: 1px solid #ddd;
}

tr:hover{
    background-color: #f1f1f1;
}

.back-btn{
    display: inline-block;
    margin-top: 20px;
    padding: 10px 18px;
    background-color: #3498db;
    color: white;
    text-decoration: none;
    border-radius: 5px;
}

.back-btn:hover{
    background-color: #2980b9;
}

.no-data{
    text-align: center;
    color: red;
    margin-top: 20px;
    font-size: 18px;
}

</style>
    </head>
   <body>

<div class="container">

<h2>Student Overview</h2>

<%

List<Student> studentList =
(List<Student>) request.getAttribute("studentList");

if(studentList != null && !studentList.isEmpty()) {

%>

<table>

<tr>
    <th>Student ID</th>
    <th>Name</th>
    <th>Email</th>
    <th>Mobile</th>
    <th>Section</th>
</tr>

<%

for(Student s : studentList) {

%>

<tr>

<td><%= s.getStudentId() %></td>

<td><%= s.getStudentName() %></td>

<td><%= s.getStudentEmail() %></td>

<td><%= s.getStudentMobile() %></td>

<td><%= s.getStudentSection() %></td>

</tr>

<%
}
%>

</table>

<%
}
else {
%>

<div class="no-data">
    No Students Found
</div>

<%
}
%>

<a href="hodDashBoard.jsp" class="back-btn">
    Back to Dashboard
</a>

</div>

</body>
</html>
