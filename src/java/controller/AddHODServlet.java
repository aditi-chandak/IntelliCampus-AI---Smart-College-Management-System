package controller;
import dto.AddhodDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AddHODAuthenticator;
/**
 *
 * @author HP
 */
public class AddHODServlet extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException
{
    HttpSession s=request.getSession(false);
if( s== null || s.getAttribute("username") == null){
response.sendRedirect("adminLogin.html");
return;
}     
  response.sendRedirect("addHOD.html");
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
    System.out.println("check : "+username+password+email+mobile+id+role);

 AddhodDTO dto= new  AddhodDTO();
dto.setUsername(username);
dto.setPassword(password);
dto.setEmail(email);
dto.setMobile(mobile);
dto.setId(id);
dto.setRole("HOD");
dto.setBranchName(branchName);
AddHODAuthenticator authenticator= new AddHODAuthenticator();
boolean result=authenticator.isAdd(dto);

if(result){ 
    response.sendRedirect("adminDashboard.jsp");
}
else{ 
  response.sendRedirect("web/ addHOD.html");   
}
}
   
}
