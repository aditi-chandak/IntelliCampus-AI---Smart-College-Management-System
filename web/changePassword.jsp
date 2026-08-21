<%-- 
    Document   : changePassword
    Created on : 26 Apr 2026, 6:15:34 pm
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password for Students</title>
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
    width:420px;
    background-color:white;
    padding:40px;
    border-radius:15px;
    box-shadow:0px 0px 25px rgba(0,0,0,0.3);
}

h2{
    text-align:center;
    margin-bottom:30px;
    color:#333;
    font-size:28px;
}

label{
    display:block;
    margin-bottom:8px;
    font-weight:bold;
    color:#444;
}

input[type=password]{
    width:100%;
    padding:12px;
    margin-bottom:20px;
    border:1px solid #ccc;
    border-radius:8px;
    font-size:15px;
    transition:0.3s;
}

input[type=password]:focus{
    border-color:#667eea;
    box-shadow:0px 0px 8px rgba(102,126,234,0.5);
    outline:none;
}

input[type=submit]{
    width:100%;
    padding:12px;
    border:none;
    border-radius:8px;
    background-color:#667eea;
    color:white;
    font-size:18px;
    cursor:pointer;
    transition:0.3s;
}

input[type=submit]:hover{
    background-color:#5a67d8;
    transform:scale(1.03);
}

.message{
    text-align:center;
    color:red;
    margin-bottom:15px;
    font-weight:bold;
}

.success{
    text-align:center;
    color:green;
    margin-bottom:15px;
    font-weight:bold;
}

.back-btn{
    margin-top:20px;
    text-align:center;
}

.back-btn a{
    text-decoration:none;
    background-color:#555;
    color:white;
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

    <h2>Change Password</h2>

    <% 
        String error = request.getParameter("error");
        String success = request.getParameter("success");

        if(error != null){
    %>

        <div class="message">
            <%= error %>
        </div>

    <% 
        }

        if(success != null){
    %>

        <div class="success">
            <%= success %>
        </div>

    <% 
        }
    %>

    <form action="ChangePasswordServlet" method="post">

        <label>Enter Old Password</label>
        <input type="password" name="oldPassword" required>

        <label>Enter New Password</label>
        <input type="password" name="newPassword" required>

        <label>Confirm New Password</label>
        <input type="password" name="confirmPassword" required>

        <input type="submit" value="Change Password">

    </form>
    <br>
    <div class="back-btn">
        <a href="studentDashBoard.jsp">Back to Dashboard</a>
    </div>

</div>

    </body>
</html>
