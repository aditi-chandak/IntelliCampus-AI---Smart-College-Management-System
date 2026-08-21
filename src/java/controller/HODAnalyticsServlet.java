package controller;
import db.DBConnector;
import java.io.IOException;
import java.sql.Connection;
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
public class HODAnalyticsServlet extends HttpServlet 
{

    protected void doGet
    (
        HttpServletRequest request,
        HttpServletResponse response
    )

    throws ServletException, IOException
    {

        HttpSession session =
        request.getSession(false);

        if(session == null)
        {
            response.sendRedirect("hodLogin.html");
            return;
        }

        Integer branchId =
        (Integer) session.getAttribute("branch_id");

        String username =
        (String) session.getAttribute("username");

        int lowAttendanceCount = 0;
        String topSubject="";
        String lowSubject="";
        double topAttendance=0.0;
        String topFaculty="";
        String lowFaculty="";
        double lowAttendance=0.0;
        try
        {
            Connection con =
            DBConnector.getConnection();

            String query =

            "SELECT COUNT(DISTINCT student_id) AS total " +

            "FROM attendance " +

            "WHERE branch_id = ? " +

            "AND status = 'Absent'";

            PreparedStatement ps1 =
            con.prepareStatement(query);

            ps1.setInt(1, branchId);

            ResultSet rs1 =
            ps1.executeQuery();

            if(rs1.next())
            {
                lowAttendanceCount =
                rs1.getInt("total");
            }
String subjectQuery ="SELECT subject, " +"AVG(CASE " +"WHEN status='Present' " +"THEN 100 " +"ELSE 0 " +"END) AS att " +"FROM attendance " +"WHERE branch_id = ? " +"GROUP BY subject " +"ORDER BY att DESC " +"LIMIT 1";
           
            PreparedStatement ps2 =
            con.prepareStatement(subjectQuery);

            ps2.setInt(1, branchId);

            ResultSet rs2 =
            ps2.executeQuery();

            if(rs2.next())
            {
                topSubject =
                rs2.getString("subject");
            }
            
            String topSubjectQuery =

"SELECT subject, " +

"AVG(CASE " +

"WHEN status='Present' " +

"THEN 100 " +

"ELSE 0 " +

"END) AS att " +

"FROM attendance " +

"WHERE branch_id = ? " +

"GROUP BY subject " +

"ORDER BY att DESC " +

"LIMIT 1";
            PreparedStatement ps3 =
con.prepareStatement(topSubjectQuery);

ps3.setInt(1, branchId);

ResultSet rs3 =
ps3.executeQuery();

if(rs3.next())
{
    topSubject =
    rs3.getString("subject");

    topAttendance =
    rs3.getDouble("att");
}

String topFacultyQuery =

"SELECT u.user_name " +

"FROM subject_faculty sf " +

"JOIN user u " +

"ON sf.faculty_user_id = u.user_id " +

"WHERE REPLACE(TRIM(sf.subject), '-', '') = " +

"REPLACE(TRIM(?), '-', '') " +

"AND sf.branch_id = ?";
  System.out.println("Top subject sent = ["+topSubject+"]");
  System.out.println("Branch ID sent = ["+branchId+"]");
PreparedStatement psTopFaculty =
con.prepareStatement(topFacultyQuery);

psTopFaculty.setString(1, topSubject);

psTopFaculty.setInt(2, branchId);

ResultSet rsTopFaculty =
psTopFaculty.executeQuery();

if(rsTopFaculty.next())
{
    topFaculty =
    rsTopFaculty.getString("user_name");
    System.out.println("top faculty = "+topFaculty);
}
else{
    System.out.println("No faculty found ");
}

String lowSubjectQuery =

"SELECT subject, " +

"AVG(CASE " +

"WHEN status='Present' " +

"THEN 100 " +

"ELSE 0 " +

"END) AS att " +

"FROM attendance " +

"WHERE branch_id = ? " +

"GROUP BY subject " +

"ORDER BY att ASC " +

"LIMIT 1";
PreparedStatement ps4 =
con.prepareStatement(lowSubjectQuery);

ps4.setInt(1, branchId);

ResultSet rs4 =
ps4.executeQuery();

if(rs4.next())
{
    lowSubject =
    rs4.getString("subject");

    lowAttendance =
    rs4.getDouble("att");
}
String lowFacultyQuery =

"SELECT u.user_name " +

"FROM subject_faculty sf " +

"JOIN user u " +

"ON sf.faculty_user_id = u.user_id " +

"WHERE REPLACE(TRIM(sf.subject), '-', '') = " +

"REPLACE(TRIM(?), '-', '') " +

"AND sf.branch_id = ?";

PreparedStatement psLowFaculty =
con.prepareStatement(lowFacultyQuery);

psLowFaculty.setString(1, lowSubject);

psLowFaculty.setInt(2, branchId);

ResultSet rsLowFaculty =
psLowFaculty.executeQuery();

if(rsLowFaculty.next())
{
    lowFaculty =
    rsLowFaculty.getString("user_name");
    System.out.println("low faculty = "+lowFaculty);
}

            request.setAttribute
            (
                "lowAttendanceCount",
                lowAttendanceCount
            );
            request.setAttribute
            (
                "topFaculty",
                topFaculty
            );
            request.setAttribute
            (
                "lowFaculty",
                lowFaculty
            );
             request.setAttribute
            (
                "topSubject",
                topSubject
            );

            request.setAttribute
            (
                "username",
                username
            );
            request.setAttribute
(
    "topSubject",
    topSubject
);

request.setAttribute
(
    "topAttendance",
    topAttendance
);

request.setAttribute
(
    "lowSubject",
    lowSubject
);

request.setAttribute
(
    "lowAttendance",
    lowAttendance
);

            RequestDispatcher rd =
            request.getRequestDispatcher
            (
                "hodDashBoard.jsp"
            );

            rd.forward(request, response);

        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
