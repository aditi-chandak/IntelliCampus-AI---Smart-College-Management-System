package controller;
import dao.UserDAO;
import model.User;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
public class FacultyServlet extends HttpServlet 
{
private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {

            case "delete":
                deleteFaculty(request, response);
                break;

            default:
                listFaculty(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addFaculty(request, response);
        }
    }

    // 🔹 LIST FACULTY
    private void listFaculty(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int branchId = (int) session.getAttribute("branch_id");

        List<User> facultyList = userDAO.getFacultyByBranch(branchId);

        request.setAttribute("facultyList", facultyList);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("FacultyManagement.jsp");
        dispatcher.forward(request, response);
    }

    // 🔹 ADD FACULTY
    private void addFaculty(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");

        HttpSession session = request.getSession();
        int branchId = (int) session.getAttribute("branch_id");

        User faculty = new User();
        faculty.setUserName(name);
        faculty.setUserPassword(password);
        faculty.setUserRole("FACULTY");   // 🔥 Important
        faculty.setUserEmail(email);
        faculty.setUserMobile(mobile);
        faculty.setBranchId(branchId);

        userDAO.insertUser(faculty);

        response.sendRedirect("FacultyServlet?action=list");
    }

    // 🔹 DELETE FACULTY
    private void deleteFaculty(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int userId = Integer.parseInt(request.getParameter("id"));

        userDAO.deleteUser(userId);

        response.sendRedirect("FacultyServlet?action=list");
    }
    
}
