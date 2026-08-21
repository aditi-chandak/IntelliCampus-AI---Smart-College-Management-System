package controller;
import dto.HODLoginDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.HodLoginAuthenticator;
/**
 *
 * @author HP
 */
public class HodLoginServlet extends HttpServlet 
{
public void doGet(HttpServletRequest request , HttpServletResponse response)throws ServletException,IOException
{ 
    response.sendRedirect("hodLogin.html");
}
public void doPost(HttpServletRequest request , HttpServletResponse response)throws ServletException,IOException
{ 
 String username=request.getParameter("username");
 String password=request.getParameter("password");
 

HODLoginDTO Hod = new HODLoginDTO();
 Hod.setUsername(username);
 Hod.setPassword(password);
 HodLoginAuthenticator authenticator = new HodLoginAuthenticator();
 boolean HodLogin = authenticator.isLogin(Hod);
 
 if(HodLogin){
HttpSession session = request.getSession(true);
session.setAttribute("hodId", authenticator.getHodId());

session.setAttribute("username", username);
int branchId=authenticator.getBranchID(username);
session.setAttribute("branch_id",branchId);
response.sendRedirect("HODAnalyticsServlet");  
 }
 else{
     response.sendRedirect("hodLogin.html");
 }
 
}
    
}
