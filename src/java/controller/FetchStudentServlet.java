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
public class FetchStudentServlet extends HttpServlet 
{
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException
{
    HttpSession s=request.getSession(false);
if( s== null || s.getAttribute("username") == null){
response.sendRedirect("adminLogin.html");
return;
}     
 String studentId = request.getParameter("studentId");     
 StudentDAO dao = new StudentDAO();
 StudentDTO student = dao.getStudentById(studentId);     
        if (student == null) {
            response.getWriter().println("No student record found with ID: " + studentId);
            return;
        }
        request.setAttribute("student", student);
        request.getRequestDispatcher("updateStudentForm.jsp").forward(request, response);
}
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException
{
    HttpSession s=request.getSession(false);
if( s== null || s.getAttribute("username") == null){
response.sendRedirect("adminLogin.html");
return;
}     
     doGet(request,response); 
    }
}
    

