<%-- 
    Document   : upload-marks
    Created on : 9 Mar 2026, 7:22:17 pm
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Student" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload-Marks</title>
        <style>

body{
font-family: Arial, sans-serif;
background-color:#f4f6f9;
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
}

table{
width:100%;
border-collapse:collapse;
margin-top:20px;
}

table th{
background:#007BFF;
color:white;
padding:10px;
}

table td{
padding:10px;
text-align:center;
border-bottom:1px solid #ddd;
}

input[type="number"]{
width:80px;
padding:5px;
}

input[type="text"]{
padding:6px;
}

button{
display: flex;
margin-left: auto;
justify-content: space-between;
align-items: center;
background:#28a745;
color:white;
padding:10px 20px;
border:none;
border-radius:5px;
cursor:pointer;
margin-top:20px;
}

button:hover{
background:#218838;
}

</style>
    </head>
    <body>

<div class="container">

<h2>Upload Student Marks</h2>

<form action="SaveMarksServlet" method="post">

<label>Subject :</label>
<input type="text" name="subject" required>
<table>

<tr>
<th>Student ID</th>
<th>Student Name</th>
<th>Marks</th>
<th>ExamType</th>
</tr>

<%
List<Student> studentList = (List<Student>) request.getAttribute("studentList");

if(studentList != null){

for(Student s : studentList){
%>

<tr>

<td><%= s.getStudentId() %></td>

<td><%= s.getStudentName() %></td>

<td>
<input type="number" name="marks_<%= s.getStudentId() %>" min="0" max="100">
</td>
<td>
    <select name="examType_<%=s.getStudentId()%>"requried>
    <option value="">Select Exam-Type </option>
    <option value="MST1">MST-1 Marks</option>
    <option value="MST2">MST-2 Marks </option>
    <option value="ENDSEM">End-Sem Marks </option>
    </select>
</td>
</tr>

<%
}
}
%>

</table>

<center>
<button type="submit">Save Marks</button>
</center>
</form>

</div>

</body>
</html>
