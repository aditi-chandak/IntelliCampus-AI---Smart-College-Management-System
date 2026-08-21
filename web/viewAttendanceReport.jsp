<%-- 
    Document   : viewAttendanceReport
    Created on : 6 May 2026, 8:33:50 pm
    Author     : HP
--%>

<%@page import="model.Attendance"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Attendance Report</title>
        <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            text-align: center;
        }

        h1 {
            margin-top: 20px;
        }

        form {
            margin: 20px;
        }

        select {
            padding: 8px;
            font-size: 14px;
        }

        button {
            padding: 8px 16px;
            background-color: #2a7ae2;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #1d5bbf;
        }

        table {
            margin: 20px auto;
            border-collapse: collapse;
            width: 80%;
            background-color: white;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
        }

        th {
            background-color: #2a7ae2;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .error {
            color: red;
            font-weight: bold;
        }

        .back-btn {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 18px;
            background-color: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .back-btn:hover {
            background-color: #1e7e34;
        }
    </style>
    </head>
    <body>

<h1>View Attendance Report</h1>
<%
String error = (String) request.getAttribute("error");
if (error != null) {
%>
    <p class="error"><%= error %></p>
<%
}
%>

<form action="ViewAttendanceReportServlet" method="get">
    <label><b>Select Subject:</b></label>

    <select name="subject" required>
        <option value="">-- Select Subject --</option>
        <option value="DSA">DSA</option>
        <option value="DBMS">DBMS</option>
        <option value="Operating System">Operating System</option>
        <option value="Computer Network">Computer Network</option>
        <option value="Software Engineering">Software Engineering</option>
        <option value="Theory of Computation">Theory of Computation</option>
        <option value="Compiler Design">Compiler Design</option>
        <option value="Computer Architecture">Computer Architecture</option>
        <option value="AI-ML">AI-ML</option>
        <option value="Analysis and Design of Algorithms">Analysis and Design of Algorithms</option>
    </select>

    <button type="submit">View Report</button>
</form>
<%
List<Attendance> list = (List<Attendance>) request.getAttribute("attendanceList");

if (list != null && !list.isEmpty()) {
%>

<table>
    <tr>
        <th>Student ID</th>
        <th>Date</th>
        <th>Subject</th>
        <th>Status</th>
    </tr>

<%
    for (Attendance a : list) {
%>
    <tr>
        <td><%= a.getStudentID() %></td>
        <td><%= a.getDate() %></td>
        <td><%= a.getSubject() %></td>
        <td><%= a.getStatus() %></td>
    </tr>
<%
    }
%>

</table>

<%
} else if (list != null) {
%>
    <p>No attendance records found.</p>
<%
}
%>

<a href="facultyDashBoard.jsp" class="back-btn">Back to Dashboard</a>

</body>
</html>
