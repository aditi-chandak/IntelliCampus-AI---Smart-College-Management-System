package controller;
import dao.StudentDAO;
import dto.StudentDTO;
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
public class UpdateStudentServlet extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException
{
    HttpSession s=request.getSession(false);
if( s== null || s.getAttribute("username") == null){
response.sendRedirect("adminLogin.html");
return;
}     
doPost(request,response);
}
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException
{
    HttpSession s=request.getSession(false);
if( s== null || s.getAttribute("username") == null){
response.sendRedirect("adminLogin.html");
return;
}     
String studentId = request.getParameter("studentId");
        String studentName = request.getParameter("studentName");
        String studentPassword = request.getParameter("studentPassword");
        String studentEmail = request.getParameter("studentEmail");
        String studentMobile = request.getParameter("studentMobile");

        StudentDTO student = new StudentDTO();
        student.setStudentId(studentId);
        student.setStudentName(studentName);
        student.setStudentPassword(studentPassword);
        student.setStudentEmail(studentEmail);
        student.setStudentMobile(studentMobile);
        
        StudentDAO dao = new StudentDAO();
        boolean updated = dao.updateStudent(student);
        if (updated) {
            response.getWriter().println("Student record updated successfully.");
        } else {
            response.getWriter().println("Student record NOT updated.");
        }
}
}