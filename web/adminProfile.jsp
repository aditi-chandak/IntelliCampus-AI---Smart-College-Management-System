<%-- 
    Document   : adminProfile
    Created on : 4 Feb 2026, 7:35:09 pm
    Author     : HP
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin-Profile Page</title>
         <style>
            body{
font-family: Arial, sans-serif;
background-color:#f4f6f9;
margin:0;
padding:0;
}

/* profile container */

.container{
width:50%;
margin:auto;
margin-top:60px;
background:white;
padding:30px;
border-radius:10px;
box-shadow:0px 0px 12px rgba(0,0,0,0.15);
}

/* heading */

h2{
text-align:center;
color:#2c3e50;
margin-bottom:25px;
}

/* table */

table{
width:100%;
border-collapse:collapse;
}

/* left column labels */

table td:first-child{
font-weight:bold;
background:#f1f1f1;
width:40%;
padding:12px;
}

/* right column values */

table td:last-child{
padding:12px;
border-bottom:1px solid #ddd;
}

/* row hover */

table tr:hover{
background:#f9f9f9;
}

/* dashboard button */

.back-btn{
display:block;
width:180px;
margin:30px auto 0;
text-align:center;
padding:10px;
background:#007BFF;
color:white;
text-decoration:none;
border-radius:5px;
}

.back-btn:hover{
background:#0056b3;
}
        </style>
    </head>
    <body bgcolor='mistyRose'>
        <%
            String username=(String)session.getAttribute("username");
 if(username == null || username.trim().equals("")){
 response.sendRedirect("adminDashboard.jsp");
 return;
            }
            
String id ="";
String name ="";
String password ="";
String email ="";
String mobile ="";

            
            String query = "SELECT user_id, user_name, user_password, user_email, user_mobile " +
    "FROM user " +
    "WHERE user_name = '" + username + "' " +
    "AND user_role = 'ADMIN'";
            Statement st = db.DBConnector.getStatement();
            
            ResultSet rs = st.executeQuery(query);
            
            if(rs.next())
            {
           id = rs.getString("user_id");
           name = rs.getString("user_name");
               password = rs.getString("user_password");
               email = rs.getString("user_email");
              mobile = rs.getString("user_mobile");
            }
        %>
        <div class="container">
        <h1>Admin's Profile is as follows: </h1>
        <br>
        <table border = "5" cellpadding='8'>
                <tr>
                    <td><strong>Admin-ID :</strong> </td>
                    <td><strong><%=id%></strong> </td>
                    
                </tr>
                
                <tr>
                    <td><strong>Admin-Name :</strong> </td>
                    <td><strong><%=name%></strong> </td>
                    
                </tr>
                
                <tr>
                    <td><strong>Password :</strong> </td>
                    <td><strong><%=password%></strong> </td>
                    
                </tr>
                
                <tr>
                    <td><strong>E-mail :</strong> </td>
                    <td><strong><%=email%></strong> </td>
                    
                </tr>
                
                <tr>
                    <td><strong>Mobile-no :</strong> </td>
                    <td><strong><%=mobile%></strong> </td>
                    
                </tr>      
        </table>
                      <a href="adminDashboard.jsp" class="back-btn">Back to DashBoard</a>
        </div>
    </body>
</html>
