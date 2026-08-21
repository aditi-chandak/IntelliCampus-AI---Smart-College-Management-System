<%-- 
    Document   : FacultyNotices
    Created on : 7 Mar 2026, 7:33:08 pm
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%
     String username=(String)session.getAttribute("username");
 if(username == null || username.trim().equals("")){
 response.sendRedirect("facultyDashBoard.jsp");
 return;
            }
            %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Faculty-Notices</title>
        <style>
         body{
font-family: Arial, sans-serif;
background-color:#f4f6f9;
margin:0;
padding:0;
}

/* main container */

.container{
width:85%;
margin:auto;
margin-top:40px;
background:white;
padding:25px;
border-radius:8px;
box-shadow:0px 0px 10px rgba(0,0,0,0.1);
}

/* headings */

h1{
text-align:center;
color:#2c3e50;
margin-bottom:10px;
}

h3{
text-align:center;
color:#555;
margin-bottom:25px;
}

/* table design */

table{
width:100%;
border-collapse:collapse;
margin-top:20px;
}

/* table header */

table th{
background-color:#007BFF;
color:white;
padding:12px;
text-align:center;
font-size:15px;
}

/* table data */

table td{
padding:10px;
text-align:center;
border-bottom:1px solid #ddd;
font-size:14px;
}

/* hover effect */

table tr:hover{
background-color:#f1f1f1;
}

/* notice status */

.status-active{
color:green;
font-weight:bold;
}

.status-inactive{
color:red;
font-weight:bold;
}

/* back button */

.back-btn{
display:inline-block;
margin-top:20px;
padding:10px 18px;
background:#28a745;
color:white;
text-decoration:none;
border-radius:5px;
font-size:14px;
}

.back-btn:hover{
background:#218838;
}               

        </style>
    </head>
    <body bgcolor='ivory'>
        <div class="container">
        <h1><u>Department-Notices : </u></h1>
       
        <h2>Welcome ,  <b><%= username %></b> </h2>
        
        <table border='1' cellpadding="8">
            <tr>
                <td><strong>ID:</strong></td>
                <td><strong>Title:</strong></td>
                <td><strong>Message:</strong></td>
                <td><strong>Branch:</strong></td>
                <td><strong>Date:</strong></td>
                <td><strong>Status:</strong></td>
            </tr>
    <%
        String id ="";
        String title ="";
        String message ="";
        String branch="";
        String date ="";
        String status ="";
        String query = "SELECT n.notice_id,n.notice_title,n.notice_message,b.branch_name,n.created_date,n.status FROM notice n JOIN branch b ON n.branch_id = b.branch_id";
        
            Statement st = db.DBConnector.getStatement();
            
            ResultSet rs = st.executeQuery(query);
            
        while(rs.next())
        {
    %>
         <tr>
                <td><strong><%=rs.getInt("notice_id")%></strong></td>
                <td><strong><%=rs.getString("notice_title")%></strong></td>
                <td><strong><%=rs.getString("notice_message")%></strong></td>
                <td><strong><%=rs.getString("branch_name")%></strong></td>
                <td><strong><%=rs.getString("created_date")%></strong></td>
                <td><strong><%=rs.getString("status")%></strong></td>
            </tr>
            <%
                }
            %>
            <a href="facultyDashBoard.jsp" class="back-btn">Back to DashBoard</a>
        </div>
    </body>
</html>
