package controller;
import dto.AdminDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AdminLoginAuthenticator;
/**
 *
 * @author HP
 */
public class AdminLoginServlet extends HttpServlet 
{  
public void doGet(HttpServletRequest request , HttpServletResponse response)throws ServletException,IOException
{ 
    response.sendRedirect("adminLogin.html");
}
public void doPost(HttpServletRequest request , HttpServletResponse response)throws ServletException,IOException
{ 
 String username=request.getParameter("username");
 String password=request.getParameter("password");
 
 AdminDTO admin = new AdminDTO();
 admin.setUsername(username);
 admin.setPassword(password);
 AdminLoginAuthenticator authenticator = new AdminLoginAuthenticator();
 boolean adminLogin = authenticator.isLogin(admin);
   
 if(adminLogin){
HttpSession session = request.getSession(true);
session.setAttribute("username", username);
   response.sendRedirect("adminDashboard.jsp");  
 }
 else{
     response.sendRedirect("adminLogin.html");
 }
 
}
    
}
