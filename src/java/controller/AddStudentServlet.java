package controller;
import dto.AddStudentDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AddStudentAuthenticator;
/**
 *
 * @author HP
 */
public class AddStudentServlet extends HttpServlet 
{
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException
{
    HttpSession s=request.getSession(false);
if( s== null || s.getAttribute("username") == null){
response.sendRedirect("adminLogin.html");
return;
}     
  response.sendRedirect("addStudent.html");
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
String branchName=request.getParameter("branchName");
String section=request.getParameter("section");

System.out.println("check : "+username+password+email+mobile+id+branchName+section);

AddStudentDTO dto= new AddStudentDTO ();
dto.setUsername(username);
dto.setPassword(password);
dto.setEmail(email);
dto.setMobile(mobile);
dto.setId(id);
dto.setBranchName(branchName);
dto.setSection(section);

AddStudentAuthenticator authenticator= new AddStudentAuthenticator();
int branchId=authenticator.getBranchIdByName(branchName);

boolean result=authenticator.isAdd(dto);

if(result){ 
    response.sendRedirect("adminDashboard.jsp");
}
else{
  response.sendRedirect("adminLogin.html");   
}
}
}
