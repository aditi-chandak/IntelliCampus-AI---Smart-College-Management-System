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
public class DeleteStudentServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException
{
    HttpSession s=request.getSession(false);
if( s== null || s.getAttribute("username") == null){
response.sendRedirect("adminLogin.html");
return;
}     
  response.sendRedirect("deleteStudent.jsp");
}

public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException
{
    HttpSession s=request.getSession(false);
if( s== null || s.getAttribute("username") == null){
response.sendRedirect("adminLogin.html");
return;
}     
    String sid=request.getParameter("studentId");
    try{
      Statement st = DBConnector.getStatement();
      String query = "DELETE FROM student WHERE student_id="+sid;
      int result=st.executeUpdate(query);
      if(result > 0){
          response.sendRedirect("adminDashboard.jsp");
      }
      else{
      response.sendRedirect("deleteStudent.jsp");
          
      }
    }
    catch(SQLException e ){
        System.out.println(e);
     
    }
}
}