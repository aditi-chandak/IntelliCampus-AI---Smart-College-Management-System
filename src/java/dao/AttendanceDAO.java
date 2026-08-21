
package dao;
import db.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import model.Attendance;
/**
 *
 * @author HP
 */
public class AttendanceDAO {
  public boolean saveAttendance(int studentId,int branchId,java.sql.Date date,String status,String subject) {
boolean result=false;
        try {

            Connection con = DBConnector.getConnection();

            String query = "INSERT INTO attendance(student_id, branch_id, date, status,subject) VALUES (?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, studentId);
            ps.setInt(2, branchId);
            ps.setDate(3, date);
            ps.setString(4, status);
            ps.setString(5, subject);
            System.out.println("Before Execute Update : ");
         int i= ps.executeUpdate();
          System.out.println("after Execute Update : ");
            System.out.println("No. of rows inserted is: "+i);
if(i>0) result=true;
            System.out.println("Attendance saved for student: " + studentId);

        } catch (Exception e) {
           e.printStackTrace();
        }
        return result;
    }  
  public List<Attendance> getAttendanceByBranch(int branchId,String subject){

    List<Attendance> list = new ArrayList<>();

    try{

        Connection con = DBConnector.getConnection();

        String query = "SELECT student_id,branch_id,date,status,subject"+" FROM attendance WHERE branch_id=? AND subject=?";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, branchId);
        ps.setString(2, subject);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            Attendance a = new Attendance();

            a.setStudentID(rs.getInt("student_id"));
            a.setBranchID(rs.getInt("branch_id"));
            a.setDate(rs.getDate("date"));
            a.setStatus(rs.getString("status"));
            a.setSubject(rs.getString("subject"));
            a.setSubject(rs.getString("subject"));
            list.add(a);
        }

    }catch(Exception e){
        System.out.println(e);
    }

    return list;
}
}
