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
public class StudentListServlet extends HttpServlet 
{
 private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("StudentListServlet called : ");
        try {
HttpSession session =request.getSession(false);
if(session == null){
    System.out.println("session not found ");
    response.sendRedirect("facultyLogin.html");
    return;
}
Integer branchId=(Integer)session.getAttribute("branch_id");
if(branchId == null){
    System.out.println("BranchId not found in session");  
    response.sendRedirect("facultyLogin.html");
    return;
}

            // fetch student list from DAO
            List<Student> studentList = studentDAO.getAllStudents(branchId);
            //request.setAttribute("studentlist",studentList);
String branchName=studentList.get(0).getBranchName();
//request.setAttribute("branchName",branchName);
String section = studentList.get(0).getStudentSection();
            // send list to JSP
            request.setAttribute("studentList", studentList);
request.setAttribute("branchName", branchName);
            // forward to JSP page
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("studentList.jsp");

            dispatcher.forward(request, response);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
