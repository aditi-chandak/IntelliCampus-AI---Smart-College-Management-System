package controller;
import java.io.IOException;
import db.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author HP
 */
public class ViewStudentListServlet extends HttpServlet 
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException
    {
System.out.println(" VIEW STUDENT LIST SERVLET CALLED");
     HttpSession session =
        request.getSession(false);
        if(session == null ||
           session.getAttribute("branch_id") == null)
        {
            System.out.println(
            "Branch ID not found in session"
            );

            response.sendRedirect(
            "facultyLogin.html"
            );

            return;
        }
        int branchId =
        Integer.parseInt(

        session.getAttribute(
        "branch_id"
        ).toString()

        );

        System.out.println(
        "Faculty Branch ID = " + branchId
        );

        try
        {
            Connection con =
            DBConnector.getConnection();

            System.out.println("Database Connected");
            String query =

            "SELECT student_id, " +
            "student_name, " +
            "student_email, " +
            "student_mobile, " +
            "section " +

            "FROM student " +

            "WHERE branch_id=?";

            PreparedStatement ps =
            con.prepareStatement(query);

            ps.setInt(1, branchId);

            System.out.println(
            "Executing Query..."
            );

            ResultSet rs =
            ps.executeQuery();

            System.out.println(
            "Query Executed Successfully"
            );

            request.setAttribute(
            "studentData",
            rs
            );

            RequestDispatcher rd =

            request.getRequestDispatcher(
            "viewStudentList.jsp"
            );

            rd.forward(request, response);

            System.out.println(
            "Forwarded to JSP"
            );
        }

        catch(Exception e)
        {
            System.out.println(
            "Exception Occurred"
            );

            System.out.println(e);
        }
    } 
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException
    {
      doGet(request,response);  
    }
}
