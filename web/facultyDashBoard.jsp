<%-- 
    Document   : facultyDashBoard
    Created on : 7 Mar 2026, 7:14:25 pm
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
response.sendRedirect("facultyLogin.html");
return;
}      
 String username=(String)session.getAttribute("username");
 if(username == null || username.trim().equals("")  ){
 response.sendRedirect("facultyLogin.html");
 return;
            }
            
Connection con = null;
Statement st = null;
ResultSet rs = null;

    String facultyName = "";
    String branchName = "";

    try {
      
        con = DBConnector.getConnection();
        st = DBConnector.getStatement();

        
        String query = "SELECT u.user_name, b.branch_name " +
                       "FROM user u JOIN branch b " +
                       "ON u.branch_id = b.branch_id " +
                       "WHERE u.user_name = '" + username + "' AND u.user_role = 'FACULTY'";

        rs = st.executeQuery(query);
        if(rs.next()) {
            facultyName = rs.getString("user_name");
            branchName = rs.getString("branch_name");
        } else {
            facultyName = "Unknown HOD";
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
        <title>Faculty-Dashboard Page</title>
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
.defaulter-box
{
    width: 320px;
    margin: 20px auto;
    background: white;
    padding: 20px;
    border-radius: 15px;
    text-align: center;
    box-shadow: 0px 4px 10px rgba(0,0,0,0.2);
}

.defaulter-box h3
{
    color: #B91C1C;
    margin-bottom: 10px;
    font-size: 22px;
}

.defaulter-box p
{
    color: #333;
    font-size: 18px;
    font-weight: bold;
}
.subject-box
{
    width: 320px;
    margin: 20px auto;
    background: white;
    padding: 20px;
    border-radius: 15px;
    text-align: center;
    box-shadow: 0px 4px 10px rgba(0,0,0,0.2);
}

.subject-box h3
{
    color: #1D4ED8;
    margin-bottom: 10px;
    font-size: 22px;
}

.subject-box p
{
    color: #333;
    font-size: 18px;
    font-weight: bold;
}

.recommendation-box
{
    width: 350px;
    margin: 20px auto;
    background: white;
    padding: 20px;
    border-radius: 15px;
    text-align: center;
    box-shadow: 0px 4px 10px rgba(0,0,0,0.2);
}

.recommendation-box h3
{
    color: #0f766e;
    margin-bottom: 10px;
}

.recommendation-box p
{
    color: #333;
    font-size: 17px;
    font-weight: bold;
    line-height: 1.5;
}
         </style>
    </head>
    <body bgcolor='salmon'>
        <h1>Faculty's-DashBoard Page</h1>
        <h1>Welcome Faculty...!!! </h1>
            <h2><strong><i>HELLO,   <%=facultyName%>  !   You Belong to <%=branchName%> - Branch.</i></strong></h2>
    <form action="FacultyAnalyticsServlet" method="get">

<button type="submit">

View AI Analytics

</button>

</form>
    <div class="defaulter-box">

<h3>AI Defaulter Alert</h3>

<p>

<%
Integer defaulterCount =
(Integer)request.getAttribute
("defaulterCount");

if(defaulterCount != null)
{
%>

<%= defaulterCount %>

students are below 75% attendance

<%
}
else
{
%>

No Defaulter Data Available

<%
}
%>

</p>

</div>

<div class="recommendation-box">

<h3>Smart Recommendation</h3>

<p>

<%
String recommendation =
(String)request.getAttribute
("recommendation");

if(recommendation != null)
{
%>

<%= recommendation %>

<%
}
else
{
%>

No Recommendation Available

<%
}
%>

</p>

</div>

<div class="subject-box">
    <h3>Subject-Attendance Analytics </h3>
    <p>
       <%
           String topSubject=(String)request.getAttribute("topSubject");
           Double topAttendance=(Double)request.getAttribute("topAttendance");
           if(topSubject != null)
           {
       %>
       <strong>Top Subject : </strong>
       <%= topSubject %>
       <br>
        <strong>Average Attendance :</strong>
       <%= topAttendance %>
       
       
       <%
           }
else{
       %>
      <strong>  No Subject Analytics Available  </strong>
       
       <%
           }
       %>
    </p>
</div>
<div class="control-panel">
    <h1><i>Faculty-Control Panel...!!</i></h1>
    <ul>
    
    <li>
        <a href="ViewStudentListServlet"><strong>Student List</strong></a>
        </li>
        <li>
    <a href="AttendanceServlet"><strong>Take Attendance</strong></a>
    </li>
    <li>
    <a href="UploadMarksServlet"><strong>Upload Marks</strong></a>
      </li>
      <li>
          <a href="FacultyNotices.jsp"><strong>Notices</strong></a>
          </li>
            <li>
    <a href="ViewAttendanceReportServlet"><strong>View Attendance Report</strong></a>
    </li>
      <li>
    <a href="facultyProfile.jsp"><strong>My-Profile</strong></a>
    </li>
      <li>
    <a href="logoutTwo.jsp"><strong>Logout</strong></a>
</li>
    </ul>
</div>
    </body>
</html>
