<%-- 
    Document   : addHOD
    Created on : 29 Jan 2026, 6:44:58 pm
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <style>
            body{
                font-family: Arial,sans-serif;
             background: linear-gradient(135deg, #3f5fbf, #5c7ed6);
                margin: 0;
                padding: 0;             
            }
            .container{
                width: 500px;
                margin: 50px auto;
                background-color: #ffffff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 8px 20px rgba(0,0,0,0.2);             
            }
            .container h1{
                text-align: center;
                color:#1a1a1a;
                margin-bottom: 25px;           
            }
            .container table{
                width: 100%;
               border-collapse: collapse;              
            }
            .container table td{
                padding: 10px;
                font-weight: 500;
                color: #333;
                vertical-align: middle;             
           }
            .container table td input{
                padding: 8px;
            width: 95%;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 14px;
            box-sizing: border-box;  
            transition: 0.3s;
            background-color: #f4f6fa;
            }
            .container table td input:focus{
               outline: none;
               border-color: #0d3b66;
               background-color: #ffffff;
               box-shadow: 0 0 5px rgba(13,59,102,0.4);
               
            }
            .container .buttons {
    margin-top: 20px;
    text-align: center;
}
.container .buttons input[type="submit"],
.container .buttons input[type="reset"] {
    padding: 12px 25px;
    margin: 5px;
    border: none;
    border-radius: 8px;
    font-size: 15px;
    cursor: pointer;
  color:white;
    background-color: #0d3b66;
    transition: 0.3s ease;
}
.container .buttons input[type="submit"]:hover,
.container .buttons input[type="reset"]:hover {
    background-color: #061f36;
    transform: translateY(-2px);
}
          
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add-HOD</title>
    </head>
   <body bgcolor="tomato">
        <%
HttpSession s=request.getSession(false);
if( s== null || s.getAttribute("username") == null){
response.sendRedirect("adminLogin.html");
return;
}     
        %>
         <div class="container"> 
        <h1><i><u>Add-HOD's Data....</i></u></h1>
        <form action="AddHODServlet" method="post">
            <table>
                <tr>
                    <td><strong>ID :</strong> </td>
                    <td><input type="number" name="id" placeholder="Enter ID Here:"/></td>
                </tr>
                <tr>
                    <td><strong>Username :</strong> </td>
                    <td><input type="text" name="username" placeholder="Enter Username Here:"/></td>
                </tr>
                
                <tr>
                    <td><strong>Password :</strong> </td>
                    <td><input type="password" name="password" placeholder="Enter Password Here:"/></td>
                </tr>
                
                <tr>
                    <td><strong>E-mail :</strong> </td>
                    <td><input type="email" name="email" placeholder="Enter e-mail Here:"/></td>
                </tr>
                
                <tr>
                    <td><strong>Mobile :</strong> </td>
                    <td><input type="text" name="mobile" placeholder="Enter mobile  Here:"/></td>
                </tr>
                
                 <tr>
                    <td><strong>Role :</strong> </td>
                    <td><input type="text" name="role" placeholder="Enter role Here:"/></td>
                </tr>
                  <tr>
  <td><strong>Branch</strong></td>
  <td>
    <select name="branchName" required>

      <option value="">-- Select Branch --</option>
      <option value="Computer Science">Computer Science</option>
      <option value="Mechanical">Mechanical</option>
      <option value="Electrical">Electrical</option>
      <option value="Electronics">Electronics</option>
      <option value="Civil">Civil</option>

    </select>
  </td>
</tr>
                 <div class="buttons">
                <tr>
                    <td></td>
                    <td><input type="submit" name="Login" /></td>
                </tr>
                
                <tr>
                    <td></td>
                    <td><input type="reset" name="Reset" /></td>
                </tr>
                
                
            </table>
        </div>
        </form>   
         </div>
    </body>
</html>
