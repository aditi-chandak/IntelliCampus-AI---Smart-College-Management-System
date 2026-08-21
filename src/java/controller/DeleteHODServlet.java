package controller;
import java.sql.SQLException;
import java.sql.Statement;
import db.DBConnector;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author HP
 */
public class DeleteHODServlet extends HttpServlet 
{
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException
{
    HttpSession s=request.getSession(false);
if( s== null || s.getAttribute("username") == null){
response.sendRedirect("adminLogin.html");
return;
}     
  response.sendRedirect("deleteHOD.jsp");
}

public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException
{
    HttpSession s=request.getSession(false);
if( s== null || s.getAttribute("username") == null){
response.sendRedirect("adminLogin.html");
return;
}     
    String hid=request.getParameter("hodId");
    try{
      Statement st = DBConnector.getStatement();
      String query = "DELETE FROM user WHERE user_id="+hid;
      
      int result=st.executeUpdate(query);
      if(result > 0){
          response.sendRedirect("adminDashboard.jsp");
      }
      else{
      response.sendRedirect("deleteHOD.jsp");
          
      }
    }
    catch(SQLException e ){
        System.out.println(e);
     
    }
}
   
   
}
