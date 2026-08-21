<%-- 
    Document   : ViewHod
    Created on : 26 Jan 2026, 7:57:12 pm
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
        <title>View-HOD</title>
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
   
      <body bgcolor='deepskyblue'>
          <%
HttpSession s=request.getSession(false);
if( s== null || s.getAttribute("username") == null){
response.sendRedirect("adminLogin.html");
return;
}     
        %>
        <div class="container">
          <h1>HOD's Record is displayed as follows:-</h1>
          <br><br>
        <table border='1' cellpadding="8">
            <tr>
                <td><strong>ID</strong></td>
                <td><strong>Name</strong></td>
                <td><strong>Password</strong></td>
                <td><strong>E-mail</strong></td>
                <td><strong>Mobile</strong></td>
                <td><strong>Branch</strong></td>
            </tr>
       
      <%  
          System.out.println("viewHod.jsp hits : ");
String query = "SELECT u.user_id, u.user_name, u.user_password,u.user_email, u.user_mobile, b.branch_name FROM user u JOIN branch b ON u.branch_id = b.branch_id WHERE u.user_role='HOD' ";
     Statement st=DBConnector.getStatement();
     ResultSet rs= st.executeQuery(query);
            
            while(rs.next())
            {
     String id=rs.getString("user_id");
     String name = rs.getString("user_name");
     String password=rs.getString("user_password");
     String  email=rs.getString("user_email");
     String  mobile=rs.getString("user_mobile");
     String  branchName=rs.getString("branch_name");
        %>
        <tr>
            <td><%=id %></td>
            <td><%=name %></td>
            <td><%=password %></td>
            <td><%=email %></td>
            <td><%=mobile %></td>
            <td><%=branchName%></td>
        </tr>
        <%
            }
        %>
        </table>
        </div>
    </body>
   
</html>
