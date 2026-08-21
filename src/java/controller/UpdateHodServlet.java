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
public class UpdateHodServlet extends HttpServlet 
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
String HodId = request.getParameter("HodId");
        String HodName = request.getParameter("HodName");
        String HodPassword = request.getParameter("HodPassword");
        String HodEmail = request.getParameter("HodEmail");
        String HodMobile = request.getParameter("HodMobile");

        HodDTO Hod = new HodDTO();
        Hod.setHodId(HodId);
        Hod.setHodName(HodName);
        Hod.setHodPassword(HodPassword);
        Hod.setHodEmail(HodEmail);
        Hod.setHodMobile(HodMobile);
        
        HodDAO dao = new HodDAO();
        boolean updated = dao.updateHod(Hod);
        if (updated) {
            response.getWriter().println("HOD record updated successfully.");
        } else {
            response.getWriter().println("HOD record NOT updated.");
        }
}
    
}
