package controller;
import dto.StudentLoginDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.StudentLoginAuthenticator;

/**
 *
 * @author HP
 */
public class StudentLoginServlet extends HttpServlet 
{
public void doGet(HttpServletRequest request , HttpServletResponse response)throws ServletException,IOException
{ 
    response.sendRedirect("studentLogin.html");
}
public void doPost(HttpServletRequest request , HttpServletResponse response)throws ServletException,IOException
{ 
 String username=request.getParameter("username");
 String password=request.getParameter("password");
 

StudentLoginDTO student = new StudentLoginDTO();
 student.setUsername(username);
 student.setPassword(password);
 StudentLoginAuthenticator authenticator = new StudentLoginAuthenticator();
 boolean StudentLogin = authenticator.isLogin(student);
 
 if(StudentLogin){
HttpSession session = request.getSession(true);
session.setAttribute("studentID", authenticator.getStudentID());
session.setAttribute("branchID", authenticator.getBranchID());
     System.out.println("Session studentID : "+session.getAttribute("studentID"));
      System.out.println("Session branchID : "+session.getAttribute("branchID"));
session.setAttribute("username", username);
int branchId=authenticator.getBranchId(username);
     System.out.println("Branch id fetched from DB :"+branchId);
     
session.setAttribute("branch_id",branchId);
response.sendRedirect("studentDashBoard.jsp");  
 }
 else{
     response.sendRedirect("studentLogin.html");
 }
 
}
    
    
    
}
