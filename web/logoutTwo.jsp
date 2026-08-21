<%-- 
    Document   : logoutTwo
    Created on : 7 Mar 2026, 7:30:30 pm
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout for the Faculty</title>
    </head>
    <body>
       <%
         HttpSession s = request.getSession(false);
    if (s != null) {
        s.invalidate();
    }

    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
            response.sendRedirect("index.html");
        %>
    </body>
</html>
