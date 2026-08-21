<%-- 
    Document   : viewStudentList
    Created on : 1 May 2026, 7:49:41 pm
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Students</title>
        <style>

        body
        {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background: #f4f6f9;
        }

        .container
        {
            width: 90%;
            margin: 40px auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.2);
        }

        h2
        {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 25px;
        }

        table
        {
            width: 100%;
            border-collapse: collapse;
        }

        th
        {
            background-color: #2c3e50;
            color: white;
            padding: 12px;
        }

        td
        {
            padding: 10px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        tr:hover
        {
            background-color: #f1f1f1;
        }

        .btn
        {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 18px;
            background-color: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
        }

        .btn:hover
        {
            background-color: #2980b9;
        }

        .noData
        {
            text-align: center;
            color: red;
            font-size: 18px;
            margin-top: 20px;
        }

    </style>
    </head>
    <body>
       <div class="container">

    <h2>Student List</h2>

    <table>

        <tr>

            <th>Student ID</th>
            <th>Student Name</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Section</th>

        </tr>

        <%

            ResultSet rs =

            (ResultSet)
            request.getAttribute("studentData");

            boolean dataFound = false;

            if(rs != null)
            {
                while(rs.next())
                {
                    dataFound = true;

        %>

        <tr>

            <td>
                <%= rs.getInt("student_id") %>
            </td>

            <td>
                <%= rs.getString("student_name") %>
            </td>

            <td>
                <%= rs.getString("student_email") %>
            </td>

            <td>
                <%= rs.getString("student_mobile") %>
            </td>

            <td>
                <%= rs.getString("section") %>
            </td>

        </tr>

        <%
                }
            }

            if(!dataFound)
            {
        %>

        <tr>

            <td colspan="5">

                No Students Found

            </td>

        </tr>

        <%
            }
        %>

    </table>

    <center>

        <a href="facultyDashBoard.jsp"
           class="btn">

           Back to Dashboard

        </a>

    </center>

</div>
    </body>
</html>
