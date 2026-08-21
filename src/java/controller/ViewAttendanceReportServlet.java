package controller;
import dao.AttendanceDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Attendance;
import javax.servlet.http.HttpSession;
/**
 *
 * @author HP
 */
public class ViewAttendanceReportServlet extends HttpServlet
{
protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println(" View Attendance Report Servlet HIT");

        try {
            HttpSession session = request.getSession(false);

            if (session == null) {
                response.sendRedirect("facultyLogin.html");
                return;
            }
            Integer branchId = (Integer)session.getAttribute("branch_id");

            System.out.println("Branch ID: " + branchId);

            if (branchId == null) {
                response.sendRedirect("facultyLogin.html");
                return;
            }
            String subject = request.getParameter("subject");

            System.out.println("Subject: " + subject);
            AttendanceDAO dao = new AttendanceDAO();
            if(subject == null || subject.equals("") ){
                request.setAttribute("error", "Please select the Subject");   
            
        request.getRequestDispatcher("viewAttendanceReport.jsp").forward(request, response);
        return;
            }
        List<Attendance>attendanceList=dao.getAttendanceByBranch(branchId, subject);
        
            System.out.println("Attendance List Size: " + attendanceList.size());
            
            request.setAttribute("attendanceList", attendanceList);
            request.getRequestDispatcher("viewAttendanceReport.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
}
