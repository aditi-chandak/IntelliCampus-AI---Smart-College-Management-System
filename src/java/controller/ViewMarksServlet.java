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
import model.Marks;
/**
 *
 * @author HP
 */
public class ViewMarksServlet extends HttpServlet 
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        System.out.println("ViewMarksServlet called");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("studentID") == null) {
            response.sendRedirect("studentLogin.html");
            return;
        }
        String studentIDstr = session.getAttribute("studentID").toString();
int studentIDInt=Integer.parseInt(studentIDstr);
        System.out.println("Student ID : = "+studentIDstr);
        String examType = request.getParameter("examType");
        System.out.println("Exam type is : "+examType);
        System.out.println("exam type recived from jsp :"+examType);
        if (examType == null || examType.trim().isEmpty()) {
          response.getWriter().println("ExamTpe not Selected");
          return;
        }

        try {
         
            MarksDAO marksDAO = new MarksDAO();
            System.out.println("Calling DAO method ");
            List<Marks> marksList = marksDAO.getMarks(studentIDInt, examType);

          
            request.setAttribute("marksList", marksList);
          
RequestDispatcher dispatcher = request.getRequestDispatcher("marks.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
            response.getWriter().println("Error fetching marks: " + e.getMessage());
        } 
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response); // POST को GET में forward कर दिया
    }
}
