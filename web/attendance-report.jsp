<%-- 
    Document   : attendance-report
    Created on : 9 Mar 2026, 7:49:23 pm
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Attendance" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Attendance-Report</title>
        <style>

body{
font-family: Arial, sans-serif;
background-color:#f2f4f7;
margin:0;
padding:0;
}

.container{
width:80%;
margin:auto;
margin-top:40px;
background:white;
padding:20px;
border-radius:8px;
box-shadow:0px 0px 10px rgba(0,0,0,0.1);
}

h2{
text-align:center;
color:#333;
margin-bottom:20px;
}

table{
width:100%;
border-collapse:collapse;
}

table th{
background-color:#007BFF;
color:white;
padding:10px;
}

table td{
padding:10px;
text-align:center;
border-bottom:1px solid #ddd;
}

tr:hover{
background-color:#f5f5f5;
}

.back-btn{
display:inline-block;
margin-top:20px;
padding:10px 15px;
background:#28a745;
color:white;
text-decoration:none;
border-radius:5px;
}

.back-btn:hover{
background:#218838;
}

</style>
    </head>
    <body>

<div class="container">

<h2>Attendance Report</h2>

<table>

<tr>
<th>Student ID</th>
<th>Date</th>
<th>Status</th>
</tr>

<%
List<Attendance> reportList = (List<Attendance>) request.getAttribute("reportList");

if(reportList != null){

for(Attendance a : reportList){
%>

<tr>

<td><%= a.getStudentID() %></td>
<td><%= a.getDate() %></td>
<td><%= a.getStatus() %></td>

</tr>

<%
}
}
%>

</table>

<a href="facultyDashBoard.jsp" class="back-btn">Back to Dashboard</a>

</div>

</body>
</html>
