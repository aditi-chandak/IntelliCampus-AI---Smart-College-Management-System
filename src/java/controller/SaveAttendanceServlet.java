package controller;
import dao.AttendanceDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Student;
/**
 *
 * @author HP
 */
public class SaveAttendanceServlet extends HttpServlet 
{
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    System.out.println("do post of save attendance hits : ");
        try {

            HttpSession session = request.getSession(false);
            System.out.println("session obj = "+session);
            if (session == null) {
                response.sendRedirect("facultyLogin.html");
                return;
            }

            int branchId =(Integer)session.getAttribute("branch_id");
            System.out.println("branch id is = " +branchId);
            String subject=request.getParameter("subject");
            System.out.println("subject is : "+subject);
            AttendanceDAO dao = new AttendanceDAO();

          List<Student> studentList=(List<Student>)session.getAttribute("studentList");
if(studentList == null){
    System.out.println("StudentList is NULL : ");
    response.sendRedirect("facultyDashBoard.jsp");
    return;
}
                for (Student s : studentList) {
                    //System.out.println("student ID ="+studentId);
                    int studentId = s.getStudentId();
                    String status=request.getParameter("status_"+studentId);
                    System.out.println("student:"+studentId+"status"+status);
                    System.out.println("calling DAO:");
                    if(status != null){
                   dao.saveAttendance(
                           studentId, 
                           branchId,
                           new 
        java.sql.Date(System.currentTimeMillis()),
                           status,
                           subject
                   );
                   
                    System.out.println("after dao call : ");
                     System.out.println("Attendance saved successfully");
                }
            }
response.sendRedirect("facultyDashBoard.jsp");
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
