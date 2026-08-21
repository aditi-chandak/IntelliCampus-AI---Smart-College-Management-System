<%-- 
    Document   : hodDashBoard
    Created on : 1 Feb 2026, 4:33:41 pm
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
response.sendRedirect("hodLogin.html");
return;
}      
 String username=(String)session.getAttribute("username");
 if(username == null || username.trim().equals("")  ){
 response.sendRedirect("hodLogin.html");
 return;
            }
            
Connection con = null;
Statement st = null;
ResultSet rs = null;

    String hodName = "";
    String branchName = "";

    try {
      
        con = DBConnector.getConnection();
        st = DBConnector.getStatement();

        
        String query = "SELECT u.user_name, b.branch_name " +
                       "FROM user u JOIN branch b " +
                       "ON u.branch_id = b.branch_id " +
                       "WHERE u.user_name = '" + username + "' AND u.user_role = 'HOD'";

        rs = st.executeQuery(query);
        if(rs.next()) {
            hodName = rs.getString("user_name");
            branchName = rs.getString("branch_name");
        } else {
            hodName = "Unknown HOD";
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

         <title>HOD-Dashboard</title>
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
.ai-alert-box
{
    width: 320px;
    margin: 20px auto;
    background: white;
    padding: 18px;
    border-radius: 15px;
    text-align: center;
    box-shadow: 0px 4px 10px rgba(0,0,0,0.2);
}

.ai-alert-box h2
{
    color: #1E293B;
    margin-bottom: 10px;
    font-size: 22px;
}

.ai-alert-box p
{
    color: #333;
    font-size: 17px;
    font-weight: bold;
    line-height: 1.5;
}
.top-faculty-box
{
    width: 320px;
    margin: 20px auto;
    background: white;
    padding: 18px;
    border-radius: 15px;
    text-align: center;
    box-shadow: 0px 4px 10px rgba(0,0,0,0.2);
}

.top-faculty-box h3
{
    color: #1E293B;
    margin-bottom: 10px;
    font-size: 22px;
}

.top-faculty-box p
{
    color: #333;
    font-size: 17px;
    font-weight: bold;
    line-height: 1.5;
}
.subject-ranking-box
{
    width: 340px;
    margin: 20px auto;
    background: white;
    padding: 20px;
    border-radius: 15px;
    text-align: center;
    box-shadow: 0px 4px 10px rgba(0,0,0,0.2);
}

.subject-ranking-box h3
{
    color: #1E3A8A;
    margin-bottom: 15px;
    font-size: 22px;
}

.subject-ranking-box p
{
    font-size: 17px;
    color: #333;
    line-height: 1.6;
}
         </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="0">
       
    </head>
    <body bgcolor='salmon'>
        <h1>HOD's-DashBoard Page</h1>
        <h1>Welcome HOD...!!! </h1>
            <h2><strong><i>HELLO,   <%=hodName%>  !   You Belong to <%=branchName%> - Branch.</i></strong></h2>
    <div class="ai-alert-box">

    <h2>AI Attendance Alert</h2>

    <p>
        <b>${lowAttendanceCount}</b>
        students have attendance below 60%
    </p>

</div>
        <div class="top-faculty-box">
            <h3>Top Subject Performance</h3>
            <p>
                <%
                    String topSubject=(String)request.getAttribute("topSubject");
                    if(topSubject != null)
                    {
                %>
                <%= topSubject %>
                has highest Student Attendance
                <%
                    }
                    else
                    {
                %>
                No Subject Data Available 
                <%
                    }
                %>
            </p>
        </div>
            <div class="subject-ranking-box">

                <h3><strong>Subject Performance Ranking</strong></h3>

<p>

<b><strong>Top Performing Subject:</strong></b>

<strong><%= request.getAttribute("topSubject") %></strong>
<p>
   <strong> Handled By :</strong> <%= request.getAttribute("topFaculty")
    %>
</p>
<strong><%= request.getAttribute("topAttendance") %>%</strong>

<p>

<b><strong>Lowest Performing Subject:</strong></b>

<strong><%= request.getAttribute("lowSubject") %></strong>

<p>
    <strong> Handled By : </strong><%= request.getAttribute("lowFaculty")
    %>
</p>

<strong><%= request.getAttribute("lowAttendance") %>%</strong>

</div>
<div class="control-panel">
    <h1><i>HOD-Control Panel...!!</i></h1>
    <ul>
    
    <li>
        <a href="FacultyServlet"><strong>Faculty Management</strong></a>
        </li>
        <li>
    <a href="StudentServlet"><strong>Student Overview</strong></a>
    </li>
      <li>
          <a href="HodNotices.jsp"><strong>Notices</strong></a>
          </li>
            <li>
    <a href="HodApprovals.jsp"><strong>Approvals</strong></a>
    </li>
      <li>
    <a href="hodProfile.jsp"><strong>My-Profile</strong></a>
    </li>
      <li>
    <a href="logoutOne.jsp"><strong>Logout</strong></a>
</li>
    </ul>
</div>
    </body>
</html>
