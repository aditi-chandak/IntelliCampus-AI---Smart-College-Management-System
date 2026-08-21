package controller;
import java.sql.Connection;
import java.io.IOException;
import db.DBConnector;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author HP
 */
public class ViewAttendanceServlet extends HttpServlet 
{
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    System.out.println("VIEW ATTENDANCE SERVLET CALLED");

       
        HttpSession session = request.getSession(false);

        if(session == null)
        {
            System.out.println("Session is NULL");

            response.sendRedirect("studentLogin.html");
            return;
        }

       
        Object studentObj =
        session.getAttribute("studentID");

        Object branchObj =
        session.getAttribute("branchID");

        
        if(studentObj == null || branchObj == null)
        {
            System.out.println("Student ID or Branch ID is NULL");

            response.sendRedirect("studentLogin.html");
            return;
        }

       
        int studentId =
        Integer.parseInt(studentObj.toString());

        int branchId =
        Integer.parseInt(branchObj.toString());

        System.out.println("Student ID = " + studentId);
        System.out.println("Branch ID = " + branchId);

        try
        {
            
            Connection con =
            DBConnector.getConnection();

            System.out.println("DB Connected");

           
            PreparedStatement ps =
            con.prepareStatement(

            "SELECT * FROM attendance " +
            "WHERE student_id=? AND branch_id=?"

            );

            ps.setInt(1, studentId);
            ps.setInt(2, branchId);

            System.out.println("Query Executing...");

            ResultSet rs = ps.executeQuery();
            PreparedStatement totalPs = con.prepareStatement("SELECT COUNT(*) FROM attendance "+"WHERE student_id=?");
            totalPs.setInt(1, studentId);
            ResultSet totalRs = totalPs.executeQuery();
            int totalDays=0;
      if(totalRs.next()){
          totalDays=totalRs.getInt(1);
      }
            System.out.println("total working days ="+totalDays);
            PreparedStatement presentPs =
con.prepareStatement("SELECT COUNT(*) FROM attendance " +"WHERE student_id=? AND status='Present'");

presentPs.setInt(1, studentId);

ResultSet presentRs =
presentPs.executeQuery();

int presentDays = 0;

if(presentRs.next())
{
    presentDays = presentRs.getInt(1);
}

System.out.println("Present Days = " + presentDays);
double percentage = 0;

if(totalDays > 0)
{
    percentage =
    ((double)presentDays / totalDays) * 100;
}

System.out.println("Attendance Percentage = " + percentage);

request.setAttribute("attendancePercentage",percentage);

            System.out.println("Query Executed");

         
            request.setAttribute("attendanceData", rs);

            RequestDispatcher rd =
            request.getRequestDispatcher(
            "viewAttendance.jsp"
            );

            rd.forward(request, response);

            System.out.println("Forwarded to JSP");
        }
        catch(Exception e)
        {
            System.out.println("EXCEPTION OCCURRED");

            e.printStackTrace();
        }
    }
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    doGet(request,response);
}
}

