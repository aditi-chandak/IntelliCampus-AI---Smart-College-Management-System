package controller;
import dao.StudentDAO;
import dao.UserDAO;
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
public class StudentServlet extends HttpServlet
{

   private StudentDAO studentDAO;

    public void init() {
       studentDAO = new StudentDAO();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {

        String action = request.getParameter("action");

        if(action == null) {
            action = "list";
        }

        switch(action) {

            case "list":
                listStudents(request, response);
                break;

            default:
                listStudents(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        doGet(request, response);
    }

    private void listStudents(HttpServletRequest request,
                              HttpServletResponse response)
                              throws ServletException, IOException 
    {

        try {

            System.out.println("StudentServlet listStudents called");

            HttpSession session = request.getSession();

            Integer branchId =
            (Integer) session.getAttribute("branch_id");

            System.out.println("Branch ID : " + branchId);

            if(branchId == null) {

                response.sendRedirect("hodLogin.html");
                return;
            }

           List<Student> studentList =studentDAO.getStudentsByBranch(branchId);

            System.out.println(
            "Student List Size : " + studentList.size());

            request.setAttribute("studentList", studentList);

            RequestDispatcher dispatcher =
            request.getRequestDispatcher(
            "studentOverview.jsp");

            dispatcher.forward(request, response);

        }
        catch(Exception e) {

            e.printStackTrace();
        }

    }
}
