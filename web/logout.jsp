<%-- 
    Document   : logout
    Created on : 20 Jan 2026, 5:11:05 pm
    Author     : HP
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout for the admin</title>
    </head>
    <body>
        <%
           session.invalidate();
    response.sendRedirect("index.html");
        %>
    </body>
</html>
