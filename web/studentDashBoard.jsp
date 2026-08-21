<%-- 
    Document   : studentDashBoard
    Created on : 10 Mar 2026, 7:31:02 pm
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="db.DBConnector" %>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>

        <%
         
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

            
HttpSession s=request.getSession(false);
if( s== null || s.getAttribute("username") == null){
response.sendRedirect("studentLogin.html");
return;
}      
 String username=(String)session.getAttribute("username");
 if(username == null || username.trim().equals("")  ){
 response.sendRedirect("studentLogin.html");
 return;
            }
            
Connection con = null;
Statement st = null;
ResultSet rs = null;

    String studentName = "";
    String branchName = "";

    try {
      
        con = DBConnector.getConnection();
        st = DBConnector.getStatement();

        
        String query = "SELECT u.student_name, b.branch_name " +
                       "FROM student u JOIN branch b " +
                       "ON u.branch_id = b.branch_id " +
                       "WHERE u.student_name = '" + username + "' ";

        rs = st.executeQuery(query);
        if(rs.next()) {
            studentName = rs.getString("student_name");
            branchName = rs.getString("branch_name");
        } else {
            studentName = "Unknown Student ";
            branchName = "Unknown Branch";
        }
    } catch(SQLException e) {
        System.out.println(e);;
    } 

            %>
<!DOCTYPE html>
<html>
    <head>
         <script>
window.addEventListener("pageshow", function (event) {
    if (event.persisted) {
        window.location.reload();
    }
});
</script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student-DashBoard</title>
         <style>
           body {
    margin: 0;
    padding: 0;
    font-family: Arial, sans-serif;
    background: linear-gradient(135deg, #3f5fbf, #5c7ed6);
    color: white;
    text-align: center;
}
h1 {
    margin-top: 30px;
    font-size: 36px;
    letter-spacing: 1px;
}

h2 {
    margin-top: 10px;
    font-size: 24px;
    font-weight: 500;
}
p {
    font-size: 18px;
    margin: 10px 0 25px 0;
}
.control-panel {
    background: rgba(255, 255, 255, 0.95);
    width: 60%;
    margin: 20px auto;
    padding: 25px;
    border-radius: 15px;
    color: #333;
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
}
.control-panel h2 {
    margin-bottom: 10px;
    color: #3f5fbf;
}
.control-panel h3 {
    margin-bottom: 20px;
    font-weight: 500;
    color: #444;
}
ul {
    list-style: none;
    padding: 0;
    margin: 0;
}
ul li {
    margin: 10px 0;
}
ul li a {
    display: block;
    width: 250px;
    margin: 8px auto;
    padding: 12px;
    background: #2f4ea3;
    color: white;
    text-decoration: none;
    border-radius: 10px;
    font-size: 16px;
    transition: 0.3s;
}
ul li a:hover {
    background: #1f3c88;
}
.ai-box
{
    width: 80%;
    
    margin: 30px auto;
    
    padding: 25px;
    
    background: #ffffff;
    
    border-radius: 12px;
    
    box-shadow: 0px 4px 12px rgba(0,0,0,0.15);
    
    text-align: center;
}


.ai-box h2
{
    color: #2c3e50;
    
    margin-bottom: 20px;
    
    font-size: 28px;
}


.ai-box input[type="submit"]
{
    background-color: #007bff;
    
    color: white;
    
    border: none;
    
    padding: 12px 25px;
    
    font-size: 18px;
    
    border-radius: 8px;
    
    cursor: pointer;
    
    transition: 0.3s;
}


.ai-box input[type="submit"]:hover
{
    background-color: #0056b3;
}


.result-box
{
    width: 80%;
    
    margin: 20px auto;
    
    padding: 25px;
    
    background: #f8f9fa;
    
    border-left: 6px solid #007bff;
    
    border-radius: 12px;
    
    box-shadow: 0px 4px 10px rgba(0,0,0,0.1);
}


.result-box h3
{
    color: #007bff;
    
    margin-top: 20px;
    
    margin-bottom: 10px;
    
    font-size: 24px;
}


.result-box p
{
    font-size: 18px;
    
    color: #333;
    
    line-height: 1.7;
    
    background: white;
    
    padding: 15px;
    
    border-radius: 8px;
    
    box-shadow: 0px 2px 5px rgba(0,0,0,0.08);
}
         </style>
    </head>
    <body bgcolor='salmon'>
        <h1>Student's-DashBoard Page</h1>
        <h1>Welcome Student...!!! </h1>
            <h2><strong><i>HELLO,   <%=studentName%>  !   You Belong to <%=branchName%> - Branch.</i></strong></h2>
    <div class="ai-box">

<h2>Student AI Academic Assistant</h2>

<form action="StudentAIAnalyticsServlet"
method="post">

<input type="submit"
value="View AI Insights">

</form>

</div>


<%
String weakSubject =
(String)request.getAttribute("weakSubject");

String improvementMessage =
(String)request.getAttribute("improvementMessage");

String semesterInsight =
(String)request.getAttribute("semesterInsight");
%>

<% if(weakSubject != null) { %>

<div class="result-box">

<h3>Weak Subject Detection</h3>

<p><%= weakSubject %></p>

<h3>Improvement Tracker</h3>

<p><%= improvementMessage %></p>

<h3>Semester Insight</h3>

<p><%= semesterInsight %></p>

</div>

<% } %>
<div class="control-panel">
    <h1><i>Student-Control Panel...!!</i></h1>
    <ul>
    
    <li>
        <a href="ViewAttendanceServlet"><strong>View Attendance</strong></a>
        </li>
        <li>
    <a href="marksSelection.jsp"><strong>View Marks</strong></a>
    </li>
    <li>
    <a href="changePassword.jsp"><strong>Change Password</strong></a>
      </li>
      <li>
    <a href="studentProfile.jsp"><strong>My-Profile</strong></a>
    </li>
      <li>
    <a href="logoutThree.jsp"><strong>Logout</strong></a>
</li>
    </ul>
</div>
    </body>
</html>
