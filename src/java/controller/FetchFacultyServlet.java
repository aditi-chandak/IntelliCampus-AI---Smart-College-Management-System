package controller;
import dao.FacultyDAO;
import dto.FacultyDTO;
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
public class FetchFacultyServlet extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException
{
    HttpSession s=request.getSession(false);
if( s== null || s.getAttribute("username") == null){
response.sendRedirect("adminLogin.html");
return;
}     
 String facultyId = request.getParameter("facultyId");     
FacultyDAO dao = new FacultyDAO();
 FacultyDTO faculty = dao.getFacultyById(facultyId);     
        if (faculty == null) {
            response.getWriter().println("No Faculty record found with ID: " + facultyId);
            return;
        }
        request.setAttribute("faculty", faculty);
        request.getRequestDispatcher("updateFacultyForm.jsp").forward(request, response);
        
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
