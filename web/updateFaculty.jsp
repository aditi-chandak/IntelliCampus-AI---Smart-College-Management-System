<%-- 
    Document   : updateFaculty
    Created on : 31 Jan 2026, 3:44:27 pm
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update-Faculty</title>
        <style>
            body{
font-family: Arial, sans-serif;
background:linear-gradient(135deg, #1e3c72, #2a5298);
margin:0;
padding:0;
}

/* main container */

.container{
width:420px;
margin:auto;
margin-top:100px;
background:white;
padding:30px;
border-radius:10px;
box-shadow:0px 0px 12px rgba(0,0,0,0.15);
text-align:center;
}

/* heading */

h2{
color:#2c3e50;
margin-bottom:10px;
}

/* sub heading */

p{
color:#555;
font-size:14px;
margin-bottom:25px;
}

/* label */

label{
display:block;
text-align:left;
font-weight:bold;
margin-bottom:8px;
}

/* input field */

input[type="number"]{
width:100%;
padding:10px;
border:1px solid #ccc;
border-radius:5px;
margin-bottom:20px;
font-size:14px;
}

/* button */

button{
width:100%;
padding:10px;
background:#007BFF;
color:white;
border:none;
border-radius:5px;
font-size:15px;
cursor:pointer;
}

/* button hover */

button:hover{
background:#0056b3;
}

/* back button */

.back-btn{
display:block;
margin-top:20px;
text-decoration:none;
color:#007BFF;
font-size:14px;
}

.back-btn:hover{
text-decoration:underline;
}
        </style>
    </head>
    <body bgcolor='crimson'>
        <%
HttpSession s=request.getSession(false);
if( s== null || s.getAttribute("username") == null){
response.sendRedirect("adminLogin.html");
return;
} 
         %>
         <div class="container">
        <h1><i>Update the Faculty's Record:</i></h1>
        <h3><strong>Please enter the Faculty-ID whose record is to be Updated:</strong></h3>
        <form action="FetchFacultyServlet" method="get">
        <label for="facultyId"><strong>Faculty- ID:</strong></label>
        <input type="text" id="facultyId" name="facultyId" requried>
           
        <input type="submit" value="Fetch Faculty"> 
        </form>
        <a href="adminDashboard.jsp"class="back-btn">Back to DashBoard</a>
         </div>
    </body>
</html>
