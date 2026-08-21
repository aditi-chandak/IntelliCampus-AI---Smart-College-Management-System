package dao;
import db.DBConnector;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.Marks;
import java.sql.ResultSet;
/**
 *
 * @author HP
 */
public class MarksDAO {
   public void saveMarks(int studentID, int branchID, String subject, int marks,String examType)
   {

        try{

            Connection conn = DBConnector.getConnection();

            String query = "INSERT INTO marks(student_id, branch_id, subject, marks,exam_type) VALUES(?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, studentID);
            ps.setInt(2, branchID);
            ps.setString(3, subject);
            ps.setInt(4, marks);
           ps.setString(5,examType);
            ps.executeUpdate();

            System.out.println("Marks saved for student: " + studentID);

        }
        catch(Exception e){
            System.out.println(e);
        }

    } 
   public List<Marks> getMarks(int studentID,String examType){

List<Marks> list=new ArrayList<>();

try{

Connection con=DBConnector.getConnection();

String query="SELECT subject,marks FROM marks WHERE student_id=? AND exam_type=?";

PreparedStatement ps=con.prepareStatement(query);

ps.setInt(1,studentID);
ps.setString(2,examType);

ResultSet rs=ps.executeQuery();

while(rs.next()){

Marks m=new Marks();

m.setSubjectName(rs.getString("subject"));
m.setMarks(rs.getInt("marks"));
    System.out.println(rs.getInt("marks"));

list.add(m);

}

}catch(Exception e){
    System.out.println(e);
}

return list;

}
}
