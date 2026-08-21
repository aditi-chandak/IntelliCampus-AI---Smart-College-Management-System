package controller;
import db.DBConnector;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author HP
 */
public class FacultyAnalyticsServlet extends HttpServlet
{
protected void doGet
    (
        HttpServletRequest request,
        HttpServletResponse response
    )

    throws ServletException, IOException
    {
        System.out.println("Faculty Analytics Servlet called");
        HttpSession session =
        request.getSession(false);

        if (session == null || session.getAttribute("branch_id") == null) {
    response.sendRedirect("facultyLogin.html");
    return;
}
        
        int branchId =
        (Integer)session.getAttribute
        ("branch_id");

        int defaulterCount = 0;
        String topSubject="";
        double topAttendance=0;
        String lowSubject="";
        double lowAttendance=0;
        String recommendation="";
        String facultyName="";
        
        
        try
        {

            Connection con =
            DBConnector.getConnection();

            String defaulterQuery =

            "SELECT student_id, " +

            "AVG(CASE " +

            "WHEN status='Present' " +

            "THEN 100 " +

            "ELSE 0 " +

            "END) AS att " +

            "FROM attendance " +

            "WHERE branch_id = ? " +

            "GROUP BY student_id " +

            "HAVING att < 75";

            PreparedStatement ps =
            con.prepareStatement
            (defaulterQuery);

            ps.setInt(1, branchId);

            ResultSet rs =
            ps.executeQuery();

            while(rs.next())
            {
                defaulterCount++;
            }
            
String subjectQuery =

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
            
         PreparedStatement ps2 =
con.prepareStatement
(subjectQuery);

ps2.setInt(1, branchId);

ResultSet rs2 =
ps2.executeQuery();

if(rs2.next())
{
    topSubject =
    rs2.getString("subject");
    System.out.println("Top subject="+topSubject);
    topAttendance =
    rs2.getDouble("att");
    System.out.println("Top Attendance="+topAttendance);
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
PreparedStatement ps3 =
con.prepareStatement
(lowSubjectQuery);

ps3.setInt(1, branchId);

ResultSet rs3 =
ps3.executeQuery();

if(rs3.next())
{
    lowSubject =
    rs3.getString("subject");

    lowAttendance =
    rs3.getDouble("att");
}
String facultyQuery =

"SELECT u.user_name " +

"FROM subject_faculty sf " +

"JOIN user u " +

"ON sf.faculty_user_id = u.user_id " +

"WHERE sf.subject = ? " +

"AND sf.branch_id = ?";


PreparedStatement ps4 =
con.prepareStatement
(facultyQuery);

ps4.setString(1, lowSubject);

ps4.setInt(2, branchId);

ResultSet rs4 =
ps4.executeQuery();

if(rs4.next())
{
    facultyName =
    rs4.getString("user_name");
}

recommendation =

"Attendance in " +

lowSubject +

" is low. Please coordinate with " +

facultyName +

" to improve student engagement.";


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
    "recommendation",
    recommendation
);
            request.setAttribute
            (
                "defaulterCount",
                defaulterCount
            );

            RequestDispatcher rd =
            request.getRequestDispatcher
            (
                "facultyDashBoard.jsp"
            );

            rd.forward(request, response);

        }

        catch(Exception e)
        {
            System.out.println(e);
        }

    }
    
}
