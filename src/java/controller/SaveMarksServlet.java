package controller;
import dao.MarksDAO;
import dao.StudentDAO;
import java.io.IOException;
import java.util.List;
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
public class SaveMarksServlet extends HttpServlet 
{
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            HttpSession session = request.getSession(false);

            if(session == null){
                response.sendRedirect("faculty-login.html");
                return;
            }

            Integer branchId = (Integer) session.getAttribute("branch_id");

            String subject = request.getParameter("subject");

            StudentDAO studentDAO = new StudentDAO();
            List<Student> studentList = studentDAO.getAllStudents(branchId);

            MarksDAO marksDAO = new MarksDAO();

            for(Student s : studentList){

                String markStr = request.getParameter("marks_" + s.getStudentId());
String examType=request.getParameter("examType_"+s.getStudentId());
                System.out.println("Exam types is :"+examType);
                if(markStr != null && !markStr.isEmpty()){

                    int marks = Integer.parseInt(markStr);

                    marksDAO.saveMarks(s.getStudentId(), branchId, subject, marks,examType);

                    System.out.println("Marks saved for student: " + s.getStudentId());
                }
            }

            System.out.println("All marks saved successfully");

            response.sendRedirect("facultyDashBoard.jsp");

        } 
        catch(Exception e){
            System.out.println(e);
        }
    }
   
}
