package controller;
import dao.StudentDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
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
public class AttendanceServlet extends HttpServlet
{
private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Attendance Servlet Called");

        try {

            HttpSession session = request.getSession(false);
            System.out.println("session object:"+session);
            System.out.println("branch_id:"+session.getAttribute("branch_id"));

            if (session == null) {
                response.sendRedirect("facultyLogin.html");
                return;
            }

            Integer branchId = (Integer)session.getAttribute("branch_id");

            if (branchId == null) {
                System.out.println("Branch ID not found in session");
                response.sendRedirect("facultyLogin.html");
                return;
            }

            System.out.println("Branch ID: " + branchId);

            List<Student> studentList = studentDAO.getAllStudents(branchId);
            session.setAttribute("studentList", studentList);
            System.out.println("Attendance servlet: student list fetched");
            for(Student s : studentList){
                System.out.println(s.getStudentId()+"-"+s.getStudentName());
            }

            request.setAttribute("studentList", studentList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("attendance.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
