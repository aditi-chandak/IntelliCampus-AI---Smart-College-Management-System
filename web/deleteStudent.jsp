<%-- 
    Document   : deleteStudent
    Created on : 27 Jan 2026, 3:30:09 pm
    Author     : HP
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete-Student</title>
        <style>
          body{
font-family: Arial, sans-serif;
background:#eaf3ff;
margin:0;
padding:0;
}

/* main container */

.container{
width:400px;
margin:auto;
margin-top:120px;
background:white;
padding:30px;
border-radius:10px;
box-shadow:0px 0px 12px rgba(0,0,0,0.15);
text-align:center;
}

/* heading */

h2{
color:#2c3e50;
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

input[type="text"]{
width:100%;
padding:10px;
border:1px solid #ccc;
border-radius:5px;
margin-bottom:20px;
font-size:14px;
}

/* delete button */

input[type="submit"]{
width:100%;
padding:10px;
background:#dc3545;
color:white;
border:none;
border-radius:5px;
font-size:15px;
cursor:pointer;
}

/* hover effect */

input[type="submit"]:hover{
background:#b02a37;
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
    <body bgcolor="darkorchid">
        <%
HttpSession s=request.getSession(false);
if( s== null || s.getAttribute("username") == null){
response.sendRedirect("adminLogin.html");
return;
}     
        %>
        <div class="container">
        <h1><u>Delete-Student</u></h1>
        
        <form action="DeleteStudentServlet" method="post">
            <strong> Enter Student-ID : </strong>
            <br>
  <input type="text" name="studentId" requried>
          
     <input type="submit" value="Delete Student">        
        </form>
        </div>
    </body>
</html>
