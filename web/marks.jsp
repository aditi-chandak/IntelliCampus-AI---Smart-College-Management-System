<%-- 
    Document   : marks
    Created on : 13 Mar 2026, 6:55:09 pm
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,model.Marks" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student-Marks</title>
        <style>

body{
font-family: Arial, Helvetica, sans-serif;
background:#f4f6f9;
margin:0;
padding:0;
}

.container{
width:700px;
margin:60px auto;
background:white;
padding:30px;
border-radius:8px;
box-shadow:0px 4px 10px rgba(0,0,0,0.2);
}

h2{
text-align:center;
color:#333;
margin-bottom:25px;
}

table{
width:100%;
border-collapse:collapse;
}

th{
background:#2c3e50;
color:white;
padding:12px;
font-size:16px;
}

td{
padding:10px;
text-align:center;
border-bottom:1px solid #ddd;
}

tr:hover{
background:#f1f1f1;
}

.total{
font-weight:bold;
background:#ecf0f1;
}

.back-btn{
display:block;
width:120px;
margin:25px auto 0px auto;
padding:10px;
text-align:center;
background:#3498db;
color:white;
text-decoration:none;
border-radius:5px;
}

.back-btn:hover{
background:#2980b9;
}

</style>
    </head>
    <body>

<div class="container">

<h2>Student Examination Marks</h2>

<table>

<tr>
<th>Subject</th>
<th>Marks</th>
</tr>

<%

List<Marks> list = (List<Marks>)request.getAttribute("marksList");

int total = 0;

if(list!=null){

for(Marks m : list){

total += m.getMarks();

%>

<tr>
<td><%=m.getSubjectName()%></td>
<td><%=m.getMarks()%></td>
</tr>

<%
}
}
%>

<tr class="total">

<td>Total</td>
<td><%=total%></td>

</tr>

</table>

<a class="back-btn" href="studentDashBoard.jsp">Back</a>

</div>

</body>
</html>
