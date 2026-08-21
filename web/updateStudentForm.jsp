<%-- 
    Document   : updateStudentForm
    Created on : 29 Jan 2026, 7:08:15 pm
    Author     : HP
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.StudentDTO"%>
        <%
         HttpSession s=request.getSession(false);
if( s== null || s.getAttribute("username") == null){
response.sendRedirect("adminLogin.html");
return;
}     
StudentDTO student=(StudentDTO)request.getAttribute("student");
if(student == null){
out.println("No student data to display..");
return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Student</title>
     <style>

    body
    {
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
        background: linear-gradient(135deg, #1e3c72, #2a5298);
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .container
    {
        width: 420px;
        background-color: white;
        padding: 35px;
        border-radius: 12px;
        box-shadow: 0px 0px 15px rgba(0,0,0,0.3);
    }

    h2
    {
        text-align: center;
        color: #1e3c72;
        margin-bottom: 25px;
        font-size: 28px;
    }

    form
    {
        display: flex;
        flex-direction: column;
    }

    label
    {
        margin-top: 12px;
        margin-bottom: 6px;
        font-weight: bold;
        color: #333;
    }

    input[type="text"],
    input[type="password"],
    input[type="email"]
    {
        padding: 12px;
        border: 1px solid #ccc;
        border-radius: 6px;
        font-size: 15px;
        outline: none;
        transition: 0.3s;
    }

    input[type="text"]:focus,
    input[type="password"]:focus,
    input[type="email"]:focus
    {
        border-color: #2a5298;
        box-shadow: 0px 0px 6px rgba(42,82,152,0.4);
    }

    input[type="submit"]
    {
        margin-top: 25px;
        padding: 12px;
        background-color: #1e3c72;
        color: white;
        border: none;
        border-radius: 6px;
        font-size: 16px;
        font-weight: bold;
        cursor: pointer;
        transition: 0.3s;
    }

    input[type="submit"]:hover
    {
        background-color: #16325c;
        transform: scale(1.02);
    }

</style>
</head>
<body>
    <div class="container">
    <h2><i><strong>Update Student Details..!!</strong></i></h2>
   
<form action="UpdateStudentServlet" method="post">
    <label for="studentId"><strong>Student ID:</strong></label>
        <input type="text" id="studentId" name="studentId" value="<%= student.getStudentId() %>" required>     
        <label for="studentName"><strong>Name:</strong></label>
        <input type="text" id="studentName" name="studentName" value="<%= student.getStudentName() %>" required>
        <label for="studentPassword"><strong>Password:</strong></label>
        <input type="text" id="studentPassword" name="studentPassword" value="<%= student.getStudentPassword() %>" required>
        <label for="studentEmail"><strong>Email:</strong></label>
        <input type="email" id="studentEmail" name="studentEmail" value="<%= student.getStudentEmail() %>" required>
       <label for="studentMobile"><strong>Mobile:</strong></label>
        <input type="text" id="studentMobile" name="studentMobile" value="<%= student.getStudentMobile() %>" required>
       
        <input type="submit" value="Update Student">
</form>
    </div>
    </body>
</html>
