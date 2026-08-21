<%-- 
    Document   : adminDashboard
    Created on : 20 Jan 2026, 4:42:36 pm
    Author     : HP
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
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

        <title>Admin-Dashboard Page</title>
        <style>
            *
{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body
{
    font-family: Arial, sans-serif;
    background: linear-gradient(to right, #74ABE2, #5563DE);
    text-align: center;
    overflow-x: hidden;
}

h1
{
    color: #1E293B;
    margin-top: 20px;
    font-size: 28px;
}

h3
{
    display: inline-block;
    background: white;
    padding: 12px 18px;
    margin: 10px;
    border-radius: 12px;
    box-shadow: 0px 4px 12px rgba(0,0,0,0.2);
    color: #1E293B;
    font-size: 18px;
}

h2
{
    margin-top: 30px;
    color: #1E293B;
    font-size: 28px;
}

p
{
    color: white;
    font-size: 16px;
    margin-top: 10px;
}

p a
{
    color: #FFE082;
    text-decoration: none;
    font-weight: bold;
}

form
{
    margin-top: 20px;
}

select
{
    padding: 10px;
    border-radius: 8px;
    border: none;
    width: 200px;
    font-size: 14px;
}

button
{
    padding: 10px 16px;
    margin-left: 10px;
    border: none;
    border-radius: 8px;
    background: #1E293B;
    color: white;
    cursor: pointer;
    font-weight: bold;
}

.analytics-box
{
    width: 380px;
    margin: 20px auto;
    background: white;
    padding: 18px;
    border-radius: 15px;
    box-shadow: 0px 4px 15px rgba(0,0,0,0.25);
}

.analytics-box h3
{
    display: block;
    margin: 10px 0;
    font-size: 17px;
    background: none;
    box-shadow: none;
    padding: 0;
}

.toggle-section
{
    margin-top: 20px;
}

.toggle-section label
{
    display: inline-block;
    padding: 10px 14px;
    background: white;
    border-radius: 10px;
    cursor: pointer;
    margin: 8px;
    font-weight: bold;
    font-size: 14px;
}

.toggle-section ul
{
    list-style: none;
    margin-top: 10px;
    display: none;
}

.toggle-section input[type="checkbox"]:checked + label + ul
{
    display: block;
}
.toggle-section input[type="checkbox"]
{
    display: none;
}
.toggle-section ul li
{
    background: white;
    width: 180px;
    margin: 8px auto;
    padding: 10px;
    border-radius: 8px;
    box-shadow: 0px 3px 10px rgba(0,0,0,0.2);
    font-size: 14px;
}

.signout
{
    margin-top: 40px;
    margin-bottom: 20px;
}

.signout a
{
    color: white;
    font-weight: bold;
    text-decoration: none;
    font-size: 16px;
}
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body>
       <%
             response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

 String username=(String)session.getAttribute("username");
 if(username == null || username.trim().equals("")){
 response.sendRedirect("adminLogin.html");
 return;
            }
            %> 
          
            <h1>Hello <%=username%>..!</h1>
        <h1><i>Admin-Control Panel...!!</i></h1>
       
        <p><strong>Click Here to View your Profile : <a href="adminProfile.jsp">My-Profile</a></strong></p>
        
        
       
         <h3>
             Lowest-Attendance Branch:
             <b>${lowestAttendanceBranch}</b>
         </h3>
            
        
         <h3>
             Best-Performing Branch:
             <b>${bestBranch}</b>
         </h3>
         
      
         <form action="AdminAnalyticsServlet" method="get">

    <select name="branchName" required>

        <option value="">
            Select Branch
        </option>

        <option value="Computer Science">
            Computer Science
        </option>

        <option value="Mechanical">
            Mechanical
        </option>

        <option value="Electronics">
            Electronics
        </option>

        <option value="Electrical">
            Electrical
        </option>

        <option value="Civil">
            Civil
        </option>

    </select>

    <button type="submit">

        View Analytics

    </button>

</form>
         <div class="analytics-box" > 
         <h2>AI-Analytics Summary : </h2>
        
             <h3>
        <strong>Total Students :</strong>
        ${totalStudents}
    </h3>
        
     
    <h3>
        <strong>Total Faculty :</strong>
        ${totalFaculty}
    </h3>
   
    
    <h3>
        <strong>Average Attendance :</strong>
        ${averageAttendance}
    </h3>
    
    <h3>
        <strong>Most Irregular Student :</strong>
        <%
            if(request.getAttribute("mostIrregularStudent")!= null)
            {
        %>
        ${mostIrregularStudent}
        <%
            }
else{
        %>
        <h3> No Data Available</h3>
        <%
            }
        %>
    </h3>
          </div>
    <div class="toggle-section">
<input type="checkbox" id="toggleAddUser">
<label for="toggleAddUser" ><strong>Add User</strong></label>
  <ul>
      <li><a href="addStudent.jsp"><strong><i>Add Student</i></strong></a></li>
    <li><a href="addFaculty.jsp"><strong><i>Add Faculty</i></strong></a></li>
    <li><a href="addHOD.jsp"><strong><i>Add HOD</i></strong></a></li>
  </ul> 
    </div>
      <div class="toggle-section">
<input type="checkbox" id="toggleUpdateUser" hidden>
<label for="toggleUpdateUser" ><strong>Update User</strong></label>
  <ul>
    <li><a href="updateStudent.jsp"><strong><i>Update Student</i></strong></a></li>
    <li><a href="updateFaculty.jsp"><strong><i>Update Faculty</i></strong></a></li>
    <li><a href="updateHod.jsp"><strong><i>Update HOD</i></strong></a></li>
  </ul>
      </div>
      <div class="toggle-section">
   <input type="checkbox" id="toggleDeleteUser" hidden>
<label for="toggleDeleteUser"><strong>Delete User</strong></label>
  <ul>
    <li><a href="deleteStudent.jsp"><strong><i>Delete Student</i></strong></a></li>
    <li><a href="deleteFaculty.jsp"><strong><i>Delete Faculty</i></strong></a></li>
    <li><a href="deleteHOD.jsp"><strong><i>Delete HOD</i></strong></a></li>
  </ul>
      </div>
      <div class="toggle-section">
<input type="checkbox" id="toggleViewUser" hidden>
<label for="toggleViewUser" ><strong>View User</strong></label>
  <ul>
    <li><a href="ViewStudent.jsp"><strong><i>View Student</i></strong></a></li>
    <li><a href="ViewFaculty.jsp"><strong><i>View Faculty</i></strong></a></li>
    <li><a href="ViewHod.jsp"><strong><i>View HOD</i></strong></a></li>
  </ul>
      </div>
<p class="signout"><strong><u>Click here to Sign-Out.....<a href="logout.jsp">Sign-Out</a></u></strong> </p> 
          
    </body>
</html>
