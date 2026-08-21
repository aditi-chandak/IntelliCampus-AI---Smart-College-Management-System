package controller;
import dao.MarksDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
public class MarksServlet extends HttpServlet 
{

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

String examType = request.getParameter("examType");

HttpSession session = request.getSession();
int studentId = (int) session.getAttribute("studentId");

MarksDAO dao = new MarksDAO();

List marksList = dao.getMarks(studentId, examType);

request.setAttribute("marksList", marksList);

RequestDispatcher rd = request.getRequestDispatcher("marks.jsp");
rd.forward(request,response);

}  
}
