package controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
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
public class ChangePasswordServlet extends HttpServlet 
{
protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException 
{
    System.out.println("Change Password Servlet Called");

      
        HttpSession session = request.getSession(false);

        if(session == null)
        {
            System.out.println("Session is NULL");
            response.sendRedirect("studentLogin.html");
            return;
        }

        Object obj = session.getAttribute("studentID");

        if(obj == null)
        {
            System.out.println("Student ID is NULL in session");
            response.sendRedirect("studentLogin.html");
            return;
        }

        int studentId = Integer.parseInt(obj.toString());

        System.out.println("Student ID = " + studentId);

       
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        System.out.println("Old Password = " + oldPassword);
        System.out.println("New Password = " + newPassword);
        System.out.println("Confirm Password = " + confirmPassword);

      
        if(!newPassword.equals(confirmPassword))
        {
            System.out.println("New & Confirm Password mismatch");
            response.sendRedirect("changePassword.jsp");
            return;
        }

        try
        {
          
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db05",
                "root",
                "root"
            );

           
            PreparedStatement ps1 = con.prepareStatement(
                "SELECT * FROM student WHERE student_id=? AND student_password=?"
            );

            ps1.setInt(1, studentId);
            ps1.setString(2, oldPassword);

            ResultSet rs = ps1.executeQuery();

            if(rs.next())
            {
                System.out.println("Old password verified");

               
                PreparedStatement ps2 = con.prepareStatement(
                    "UPDATE student SET student_password=? WHERE student_id=?"
                );

                ps2.setString(1, newPassword);
                ps2.setInt(2, studentId);

                int row = ps2.executeUpdate();

                System.out.println("Rows Updated = " + row);

                if(row > 0)
                {
                    System.out.println("🎉 Password Updated Successfully");

                    session.invalidate();

                    response.sendRedirect("passwordChanged.html");
                }
            }
            else
            {
                System.out.println("❌ Old password incorrect");
                response.sendRedirect("changePassword.jsp");
            }

            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
