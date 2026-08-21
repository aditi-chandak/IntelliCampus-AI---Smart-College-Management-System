<%-- 
    Document   : studentList
    Created on : 8 Mar 2026, 7:34:20 pm
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Student" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student-List</title>
        <style>

table{
border-collapse: collapse;
width:80%;
margin:auto;
}

th,td{
border:1px solid black;
padding:10px;
text-align:center;
}

th{
background-color:#f2f2f2;
}

h2{
text-align:center;
}

</style>
    </head>
   <body>
<%
List<Student> list = (List<Student>)request.getAttribute("studentList");
%>
<h2>Student List</h2>

<h3>
  
</h3>
<table>

<tr>
<th>Student ID</th>
<th>Name</th>
<th>Email</th>
<th>Mobile</th>

</tr>

<%


if(list != null){

for(Student s : list){

%>

<tr>

<td><%= s.getStudentId() %></td>
<td><%= s.getStudentName() %></td>
<td><%= s.getStudentEmail() %></td>
<td><%= s.getStudentMobile() %></td>
<!--<td><%= s.get %></td>-->

</tr>

<%
}
}
%>

</table>

</body>
</html>
