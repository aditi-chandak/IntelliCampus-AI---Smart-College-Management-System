package controller;
import dto.AddFacultyDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AddFacultyAuthenticator;
/**
 *
 * @author HP
 */
public class AddFacultyServlet extends HttpServlet 
{
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException
{
    HttpSession s=request.getSession(false);
if( s== null || s.getAttribute("username") == null){
response.sendRedirect("adminLogin.html");
return;
}     
  response.sendRedirect("addFaculty.html");
}

public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException
{
    HttpSession s=request.getSession(false);
if( s== null || s.getAttribute("username") == null){
response.sendRedirect("adminLogin.html");
return;
}     
String username=request.getParameter("username");
String password=request.getParameter("password");
String email=request.getParameter("email");
String mobile=request.getParameter("mobile");
String id=request.getParameter("id");
String role=request.getParameter("role");
String branchName=request.getParameter("branchName");
    System.out.println("check : "+username+password+email+mobile+id+role+branchName);

AddFacultyDTO facultydto= new AddFacultyDTO ();
 facultydto.setUsername(username);
 facultydto.setPassword(password);
 facultydto.setEmail(email);
 facultydto.setMobile(mobile);
 facultydto.setId(id);
 facultydto.setRole(role);
 facultydto.setBranchName(branchName);
AddFacultyAuthenticator authenticator= new AddFacultyAuthenticator();
boolean result=authenticator.isAdd(facultydto);

if(result){ 
    response.sendRedirect("adminDashboard.jsp");
}
else{
  response.sendRedirect("addFaculty.html");   
}
}
    
}
