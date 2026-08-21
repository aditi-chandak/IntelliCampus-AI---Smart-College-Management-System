<%-- 
    Document   : FacultyManagement
    Created on : 26 Feb 2026, 4:57:50 pm
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.User"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Faculty-Management</title>
        <style>
            body {
    font-family: Arial, sans-serif;
    background-color: #f4f6f9;
    margin: 0;
    padding: 0;
    text-align: center;
}

h1 {
    margin-top: 20px;
    color: #333;
}

h3 {
    color: #555;
}

a {
    text-decoration: none;
}

.add-btn {
    display: inline-block;
    margin: 20px 0;
    padding: 10px 18px;
    background-color: #007bff;
    color: white;
    border-radius: 5px;
    font-weight: bold;
}

.add-btn:hover {
    background-color: #0056b3;
}

table {
    width: 90%;
    margin: auto;
    border-collapse: collapse;
    background-color: white;
    box-shadow: 0px 2px 8px rgba(0,0,0,0.1);
}

th {
    background-color: #007bff;
    color: white;
    padding: 12px;
}

td {
    padding: 10px;
    border-bottom: 1px solid #ddd;
}

tr:hover {
    background-color: #f1f1f1;
}

.edit-btn {
    background-color: #28a745;
    color: white;
    padding: 6px 12px;
    border-radius: 4px;
    margin-right: 5px;
}

.edit-btn:hover {
    background-color: #1e7e34;
}

.delete-btn {
    background-color: #dc3545;
    color: white;
    padding: 6px 12px;
    border-radius: 4px;
}

.delete-btn:hover {
    background-color: #b02a37;
}
        </style>
    </head>
    <body>
     <%
         Integer branchId=(Integer)session.getAttribute("branch_id");
         String username=(String)session.getAttribute("username");
         if(branchId == null){
         response.sendRedirect("");
         return;
         }
         List<User> facultyList = (List<User>)request.getAttribute("facultyList");
         
     %>
     <h1>Faculty Management - Welcome , <%=username%></h1>
     <a href="addFaculty.jsp"class="add-btn">Add new Faculty</a>
     <table border='1' cellpadding="8">
         <tr>
             <td>ID</td>
             <td>Name</td>
             <td>Email</td>
             <td>Mobile</td>
             
         </tr>
         <%
             if(facultyList != null){
             for(User f : facultyList){
             
         %>
         <tr>
             <td><%= f.getUserId()%></td>
             <td><%= f.getUserName()%></td>
             <td><%= f.getUserEmail()%></td>
             <td><%= f.getUserMobile()%></td>
            
         </tr>
         <%
             }
}
         %>
     </table>
    </body>
</html>
