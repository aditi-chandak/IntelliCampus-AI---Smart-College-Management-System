<%-- 
    Document   : marksSelection
    Created on : 26 Apr 2026, 6:50:37 pm
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Select Exam Type to View Marks</title>
        <style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:Arial, sans-serif;
}

body{
    height:100vh;
    display:flex;
    justify-content:center;
    align-items:center;
   background: linear-gradient(135deg, #3f5fbf, #5c7ed6);
}

.container{
    width:400px;
    background-color:white;
    padding:40px;
    border-radius:15px;
    box-shadow:0px 0px 20px rgba(0,0,0,0.3);
    text-align:center;
}

h2{
    margin-bottom:30px;
    color:#333;
    font-size:28px;
}

select{
    width:100%;
    padding:12px;
    border-radius:8px;
    border:1px solid #ccc;
    font-size:16px;
    margin-bottom:25px;
    outline:none;
    transition:0.3s;
}

select:focus{
    border-color:#007bff;
    box-shadow:0px 0px 8px rgba(0,123,255,0.5);
}

input[type=submit]{
    width:100%;
    padding:12px;
    border:none;
    border-radius:8px;
    background-color:#007bff;
    color:white;
    font-size:18px;
    cursor:pointer;
    transition:0.3s;
}

input[type=submit]:hover{
    background-color:#0056b3;
    transform:scale(1.03);
}

.back-btn{
    margin-top:20px;
}

.back-btn a{
    text-decoration:none;
    color:white;
    background-color:#555;
    padding:10px 20px;
    border-radius:8px;
    transition:0.3s;
}

.back-btn a:hover{
    background-color:black;
}

</style>
    </head>
    <body>
        <div class="container">
        <h2>Select Examination Type...</h2>
        <form action="ViewMarksServlet" method="get">
            <select name="examType">
 <option value="MST1"> View Marks for Mid-Semester-1 </option> 
 <option value="MST2">View Marks for Mid-Semester-2 </option> 
 <option value="ENDSEM">View Marks for End-Semester </option> 
            </select>
            <br>
            <input type="submit" value="View Marks">
        </form>
        <div class="back-btn">
            <a href="studentDashBoard.jsp">
                Back to DashBoard
            </a>
        </div>
        </div>
    </body>
</html>
