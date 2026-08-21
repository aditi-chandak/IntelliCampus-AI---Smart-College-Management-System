package controller;
import dto.FacultyLoginDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FacultyLoginAuthenticator;

/**
 *
 * @author HP
 */
public class FacultyLoginServlet extends HttpServlet 
{
public void doGet(HttpServletRequest request , HttpServletResponse response)throws ServletException,IOException
{ 
    response.sendRedirect("facultyLogin.html");
}
public void doPost(HttpServletRequest request , HttpServletResponse response)throws ServletException,IOException
{ 
 String username=request.getParameter("username");
 String password=request.getParameter("password");
 

FacultyLoginDTO Faculty = new FacultyLoginDTO();
 Faculty.setUsername(username);
 Faculty.setPassword(password);
 FacultyLoginAuthenticator authenticator = new FacultyLoginAuthenticator();
 boolean FacultyLogin = authenticator.isLogin(Faculty);
 
 if(FacultyLogin){
HttpSession session = request.getSession(true);
session.setAttribute("facultyId", authenticator.getFacultyId());

session.setAttribute("username", username);
int branchId=authenticator.getBranchID(username);
     System.out.println("Branch id fetched from DB :"+branchId);
  
session.setAttribute("branch_id",branchId);
response.sendRedirect("facultyDashBoard.jsp");  
 }
 else{
     response.sendRedirect("facultyLogin.html");
 }
 
}
    
    
}
