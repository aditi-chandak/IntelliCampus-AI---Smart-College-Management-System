<%-- 
    Document   : viewAttendance
    Created on : 29 Apr 2026, 8:02:44 pm
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Attendance for Students </title>
        <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f9;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 85%;
            margin: 40px auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            color: #2c3e50;
        }
        h3 {
            text-align: center;
            color: #2c3e50;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th {
            background: #3498db;
            color: white;
            padding: 12px;
        }

        td {
            padding: 10px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        tr:hover {
            background: #f1f1f1;
        }

        .btn {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 15px;
            background: #2ecc71;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .btn:hover {
            background: #27ae60;
        }
    </style>
    </head>
    <body>

<div class="container">

    <h2>Student Attendance Report</h2>
    <h3>OverAll-Attendance:<%= request.getAttribute("attendancePercentage")%>%</h3>
    <table>
        <tr>
            <th>Date</th>
            <th>Status</th>
        </tr>

        <%
            ResultSet rs =
            (ResultSet) request.getAttribute("attendanceData");

            if(rs != null)
            {
                while(rs.next())
                {
        %>

        <tr>
            <td><%= rs.getDate("date") %></td>
            <td><%= rs.getString("status") %></td>
        </tr>

        <%
                }
            }
            else
            {
        %>

        <tr>
            <td colspan="2">No Attendance Data Found</td>
        </tr>

        <%
            }
        %>

    </table>

    <center>
        <a href="studentDashBoard.jsp" class="btn">
             Back to DashBoard
        </a>
    </center>

</div>

</body>
</html>
