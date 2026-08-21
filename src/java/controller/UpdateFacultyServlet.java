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
public class UpdateFacultyServlet extends HttpServlet 
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
String facultyId = request.getParameter("facultyId");
        String facultyName = request.getParameter("facultyName");
        String facultyPassword = request.getParameter("facultyPassword");
        String facultyEmail = request.getParameter("facultyEmail");
        String facultyMobile = request.getParameter("facultyMobile");

        FacultyDTO faculty = new FacultyDTO();
        faculty.setFacultyId(facultyId);
       faculty.setFacultyName(facultyName);
        faculty.setFacultyPassword(facultyPassword);
      faculty.setFacultyEmail(facultyEmail);
        faculty.setFacultyMobile(facultyMobile);
        
        FacultyDAO dao = new FacultyDAO();
        boolean updated = dao.updateFaculty(faculty);
        if (updated) {
            response.getWriter().println("Faculty record updated successfully.");
        } else {
            response.getWriter().println("Faculty record NOT updated.");
        }
}
    
}
