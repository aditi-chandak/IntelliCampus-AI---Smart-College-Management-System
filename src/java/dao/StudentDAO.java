package dao;
import java.sql.PreparedStatement;
import db.DBConnector;
import dto.StudentDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Student;
/**
 *
 * @author HP
 */
public class StudentDAO
{
    private Connection con;
    public StudentDAO(){
        con=DBConnector.getConnection();
    }
   public StudentDTO getStudentById(String studentId){
  StudentDTO student=null;
 Connection con =null;
 Statement st =null;
 ResultSet rs =null;
 try{
     con=DBConnector.getConnection();
     st=DBConnector.getStatement();
     String query="SELECT student_id, student_name ,student_password,student_email,student_mobile "+"FROM student WHERE student_id ='"+studentId+"'" ;
     rs=st.executeQuery(query);
     if(rs.next()){
         student = new StudentDTO();
         student.setStudentId(rs.getString("student_id"));
         student.setStudentName(rs.getString("student_name"));
         student.setStudentPassword(rs.getString("student_password"));
         student.setStudentEmail(rs.getString("student_email"));
         student.setStudentMobile(rs.getString("student_mobile"));       
     }
     }
     catch(SQLException e ){
         System.out.println(e);    
             }
     return student;
 }
   public boolean updateStudent(StudentDTO student){
       Connection con =null;
 Statement st =null;
 boolean updated=false;
 try{
  con=DBConnector.getConnection();
  st=DBConnector.getStatement();
  String query = "UPDATE student SET "
                       + "student_name = '" + student.getStudentName() + "', "
                       + "student_password = '" + student.getStudentPassword() + "', "
                       + "student_email = '" + student.getStudentEmail() + "', "
                       + "student_mobile = '" + student.getStudentMobile() + "' "
                       + "WHERE student_id = '" + student.getStudentId() + "'";
  int rows = st.executeUpdate(query);
            updated = (rows > 0);
        } catch (SQLException e) {
           System.out.println(e);
        }
        return updated;
 }
   public List<Student> getAllStudents(int branchId){
     List<Student> list = new ArrayList<>();

    try {

        Connection con = DBConnector.getConnection();

        String query = "SELECT s.student_id,s.student_name,s.student_email,s.student_mobile,s.section,s.branch_id,b.branch_name FROM student s JOIN branch b ON s.branch_id=b.branch_id WHERE s.branch_id = ? ORDER BY s.section";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, branchId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Student s = new Student();
String branchName=rs.getString("branch_name");
            s.setStudentId(rs.getInt("student_id"));
            s.setStudentName(rs.getString("student_name"));
            s.setStudentEmail(rs.getString("student_email"));
            s.setStudentMobile(rs.getString("student_mobile"));
            s.setBranchId(rs.getInt("branch_id"));
            s.setStudentSection(rs.getString("section"));
            list.add(s);
        }

    } catch (Exception e) {
        System.out.println(e);
    }

    return list;
   }
   public boolean saveAttendance(int studentId, int branchId, String status) {
    boolean isSaved = false;

    try {
       
        if(con == null) {
            System.out.println("Connection is null!");
            return false;
        }

      
        Statement st = con.createStatement();
        String deleteSql = "DELETE FROM attendance WHERE branch_id = " + branchId;
        st.executeUpdate(deleteSql);
        List<Integer> presentIds = new ArrayList<>();

        
        for(Integer sid :presentIds) {
            String insertSql = "INSERT INTO attendance(student_id, branch_id, present) " +
                               "VALUES(" + studentId + ", " + branchId + ", 1)";
            st.executeUpdate(insertSql);
        }

        isSaved = true;  
        System.out.println("Attendance saved successfully for branch_id = " + branchId);

    } catch(Exception e) {
        System.out.println(e);
        System.out.println("Error saving attendance!");
    }

    return isSaved;
}
   public List<Student> getStudentsByBranch(int branchId) {

    List<Student> list = new ArrayList<>();

    try {

        Connection conn = DBConnector.getConnection();

        String query =
        "SELECT student_id, student_name, " +
        "student_password, student_email, " +
        "student_mobile, section, branch_id " +
        "FROM student WHERE branch_id=?";

        PreparedStatement ps =
        conn.prepareStatement(query);

        ps.setInt(1, branchId);

        ResultSet rs = ps.executeQuery();

        while(rs.next()) {

            Student s = new Student();

            s.setStudentId(
            rs.getInt("student_id"));

            s.setStudentName(
            rs.getString("student_name"));

            s.setStudentPassword(
            rs.getString("student_password"));

            s.setStudentEmail(
            rs.getString("student_email"));

            s.setStudentMobile(
            rs.getString("student_mobile"));

            s.setStudentSection(
            rs.getString("section"));

            s.setBranchId(
            rs.getInt("branch_id"));

            list.add(s);
        }

    }
    catch(Exception e) {

        e.printStackTrace();
    }

    return list;
}
}

   
