package controller;
import dao.HodDAO;
import dto.HodDTO;
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
public class FetchHodServlet extends HttpServlet 
{
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException
{
    HttpSession s=request.getSession(false);
if( s== null || s.getAttribute("username") == null){
response.sendRedirect("adminLogin.html");
return;
}     
 String HodId = request.getParameter("HodId");     
 HodDAO dao = new HodDAO();
 HodDTO Hod = dao.getHodById(HodId);     
        if (Hod == null) {
            response.getWriter().println("No HOD record found with ID: " + HodId);
            return;
        }
        request.setAttribute("Hod", Hod);
        request.getRequestDispatcher("updateHodForm.jsp").forward(request, response);
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
