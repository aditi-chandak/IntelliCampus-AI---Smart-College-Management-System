
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
public class UploadMarksServlet extends HttpServlet 
{
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    HttpSession session = request.getSession(false);
    Integer branchId = (Integer) session.getAttribute("branch_id");

    StudentDAO dao = new StudentDAO();
    List<Student> studentList = dao.getAllStudents(branchId);

    request.setAttribute("studentList", studentList);

    RequestDispatcher rd = request.getRequestDispatcher("upload-marks.jsp");
    rd.forward(request, response);
}
}
