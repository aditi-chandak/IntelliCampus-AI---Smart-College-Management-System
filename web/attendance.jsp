<%-- 
    Document   : attendance
    Created on : 8 Mar 2026, 8:56:28 pm
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,model.Student" %>

    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Take-Attendance</title>
        <style>
        table {
            border-collapse: collapse;
            width: 60%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        .submit-btn {
            display: block;
            margin: 20px auto;
            padding: 10px 20px;
            font-size: 16px;
        }
    </style>
    </head>
    <body>
<%
    java.util.Date today = new java.util.Date();
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MMM-yyyy");
    String currentDate = sdf.format(today);
    String subject = request.getParameter("subject");
    if(subject == null || subject.isEmpty()){
    subject="Not Selected";
    }
%>
<h1 style="text-align:center;">Take Attendance</h1>
<form action="SaveAttendanceServlet" method="post">
    <h3>Date : <%= currentDate%></h3>
    <h3><label><strong>Subject : </strong></label></h3>
<select name="subject" requried>
    <option value="" ><strong>Select Subject</strong></option>
    <option value="Data Structures and Algorithms"> Data Structures and Algorithms</option>
    <option value="DBMS" >DBMS</option>
    <option value="Operating System" >Operating System</option>
    <option value="Computer Network" >Computer Network</option>
    <option value="Software-Engineering"> Software-Engineering</option>
    <option value="Theory of Computation"> Theory of Computation</option>
    <option value="Compiler Design"> Compiler Design</option>
    <option value="AI-ML" >AI-ML</option>
    <option value="Computer Architecture" >Computer Architecture</option>
    <option value="Analysis and Design of Algorithms"> Analysis and Design of Algorithms</option>
</select>
    <table>
        <tr>
            <th>Student ID</th>
            <th>Name</th>
            <th>Present</th>
            <th>Absent</th>
        </tr>

        <%
            List<Student> studentList = (List<Student>)request.getAttribute("studentList");
            if(studentList != null) {
                for(Student s : studentList) {            
        %>
        <tr>
            <td><%= s.getStudentId() %></td>
            <td><%= s.getStudentName() %></td>
            <td>
                <input type="radio" name="status_<%= s.getStudentId()%>" value="Present" requried>
            </td>
            <td>
                <input type="radio" name="status_<%= s.getStudentId()%>" value="Absent" requried>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="3">No students found for this branch.</td>
        </tr>
        <%
            }
        %>
    </table>

    <input type="submit" class="submit-btn" value="Save Attendance">
</form>

</body>
</html>
