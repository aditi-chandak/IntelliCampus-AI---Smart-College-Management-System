<%-- 
    Document   : ViewStudent
    Created on : 26 Jan 2026, 4:19:37 pm
    Author     : HP
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="db.DBConnector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> View-Student</title>
        <style>

body{
    font-family: Arial, sans-serif;
    background-color:#f4f6f9;
    margin:0;
    padding:0;
}

/* container */
.container{
    width:80%;
    margin:auto;
    text-align:center;
}

/* heading */
h2{
    margin-top:30px;
    color:#2c3e50;
}

/* table */
table{
    width:100%;
    margin-top:30px;
    border-collapse:collapse;
    background:white;
    box-shadow:0 4px 8px rgba(0,0,0,0.1);
}

/* header row */
th{
    background:#2c3e50;
    color:white;
    padding:12px;
}

/* cells */
td{
    border:1px solid #ddd;
    padding:10px;
}

/* hover effect */
tr:hover{
    background:#f1f1f1;
}

</style>
    </head>
    <body>
        <%
HttpSession s=request.getSession(false);
if( s== null || s.getAttribute("username") == null){
response.sendRedirect("adminLogin.html");
return;
}     
        %>
        <div class="container">
        <h1>Student's Record is displayed as follows:-</h1>
        
        <table border='1' cellpadding="8">
            <tr>
                <td><strong>ID</strong></td>
                <td><strong>Name</strong></td>
                <td><strong>Password</strong></td>
                <td><strong>E-mail</strong></td>
                <td><strong>Mobile</strong></td>
                <td><strong>Branch</strong></td>
                <td><strong>Section</strong></td>
            </tr>
       
      <%  
          
            String query = "SELECT student.student_id,student.student_name,student.student_password,student.student_email,student.student_mobile,student.section,branch.branch_name FROM student JOIN branch ON student.branch_id=branch.branch_id";
            
            
     Statement st=DBConnector.getStatement();
     ResultSet rs= st.executeQuery(query);
            
            while(rs.next())
            {
     String id=rs.getString("student_id");
     String name = rs.getString("student_name");
     String password=rs.getString("student_password");
     String email=rs.getString("student_email");
     String mobile=rs.getString("student_mobile");
    String branchName=rs.getString("branch_name");
     String section=rs.getString("section");
        %>
        <tr>
            <td><%=id %></td>
            <td><%=name %></td>
            <td><%=password %></td>
            <td><%=email %></td>
            <td><%=mobile %></td>
            <td><%=branchName %></td>
            <td><%=section %></td>
        </tr>
        <%
            }
        %>
        </table>
        </div>
    </body>
</html>
