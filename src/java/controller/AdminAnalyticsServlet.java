package controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import db.DBConnector;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
public class AdminAnalyticsServlet extends HttpServlet 
{
protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException
    {
        System.out.println("servlet HIT");
        String branchName =
        request.getParameter("branchName");

        System.out.println("Selected Branch = " + branchName);

        try
        {
            Connection conn =
            DBConnector.getConnection();

            int totalStudents = 0;
            int totalFaculty = 0;
            double averageAttendance = 0.0;
            String mostIrregularStudent = "";
            String lowestAttendanceBranch="";
            String bestBranch="";
           
            PreparedStatement ps1 =
            conn.prepareStatement(
            "SELECT COUNT(*) FROM student s JOIN branch b ON s.branch_id=b.branch_id WHERE b.branch_name=?");

            ps1.setString(1, branchName);

            ResultSet rs1 =
            ps1.executeQuery();

            if(rs1.next())
            {
                totalStudents =
                rs1.getInt(1);
            }

          
            PreparedStatement ps2 =
            conn.prepareStatement(
            "SELECT COUNT(*) FROM user u JOIN branch b ON u.branch_id=b.branch_id WHERE u.user_role='FACULTY' AND b.branch_name=?");

            ps2.setString(1, branchName);

            ResultSet rs2 =
            ps2.executeQuery();

            if(rs2.next())
            {
                totalFaculty =
                rs2.getInt(1);
            }

           
            PreparedStatement ps3 =
            conn.prepareStatement(
            "SELECT AVG(CASE WHEN status='Present' THEN 100 ELSE 0 END) " +
            "FROM attendance a JOIN branch b ON a.branch_id=b.branch_id " +
            "WHERE b.branch_name=?");

            ps3.setString(1, branchName);

            ResultSet rs3 =
            ps3.executeQuery();

            if(rs3.next())
            {
                averageAttendance =
                rs3.getDouble(1);
            }

           
            PreparedStatement ps4 =
            conn.prepareStatement(
            "SELECT s.student_name, " +
            "AVG(CASE WHEN a.status='Present' THEN 100 ELSE 0 END) as att " +
            "FROM attendance a JOIN student s ON a.student_id=s.student_id " +
            "JOIN branch b ON s.branch_id=b.branch_id " +
            "WHERE b.branch_name=? " +
            "GROUP BY s.student_name " +
            "ORDER BY att ASC");

            ps4.setString(1, branchName);

            ResultSet rs4 =
            ps4.executeQuery();

            if(rs4.next())
            {
                mostIrregularStudent =
                rs4.getString(1);
                System.out.println("Most Irregular Student :"+mostIrregularStudent);  
            }
          PreparedStatement ps5 = conn.prepareStatement(
"SELECT b.branch_name, " +
"AVG(CASE WHEN a.status='Present' THEN 100 ELSE 0 END) AS avg_att " +
"FROM attendance a " +
"JOIN branch b ON a.branch_id = b.branch_id " +
"GROUP BY b.branch_name " +
"ORDER BY avg_att ASC LIMIT 1"
);

ResultSet rs5 = ps5.executeQuery();

if(rs5.next())
{
    lowestAttendanceBranch = rs5.getString("branch_name");
    System.out.println("lowest attendance branch: "+ lowestAttendanceBranch);
}
PreparedStatement ps6 = conn.prepareStatement(
"SELECT b.branch_name, " +
"AVG(CASE WHEN a.status='Present' THEN 100 ELSE 0 END) AS avg_att " +
"FROM attendance a " +
"JOIN branch b ON a.branch_id = b.branch_id " +
"GROUP BY b.branch_name " +
"ORDER BY avg_att DESC LIMIT 1"
);

ResultSet rs6 = ps6.executeQuery();

if(rs6.next())
{
    bestBranch = rs6.getString("branch_name");
    System.out.println("Best branch : "+ bestBranch );
}
            request.setAttribute("totalStudents", totalStudents);
            request.setAttribute("totalFaculty", totalFaculty);
            request.setAttribute("averageAttendance", averageAttendance);
            request.setAttribute("mostIrregularStudent", mostIrregularStudent);
            request.setAttribute("branchName", branchName);
            request.setAttribute("lowestAttendanceBranch", lowestAttendanceBranch);
            request.setAttribute("bestBranch", bestBranch);
             
            request.getRequestDispatcher("adminDashboard.jsp")
                   .forward(request, response);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
