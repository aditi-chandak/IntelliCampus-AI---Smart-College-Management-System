package dao;
import db.DBConnector;
import dto.FacultyDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author HP
 */
public class FacultyDAO 
{
 public FacultyDTO getFacultyById(String facultyId){
  FacultyDTO faculty=null;
 Connection con =null;
 Statement st =null;
 ResultSet rs =null;
 try{
     con=DBConnector.getConnection();
     st=DBConnector.getStatement();
     String query="SELECT u.user_id," +"u.user_name," +"u.user_password," +"u.user_email," +"u.user_mobile," +"b.branch_name" +"FROM user u" +"JOIN branch b" +"ON u.branch_id = b.branch_id" +"WHERE u.user_role='faculty'" ;
     
     rs=st.executeQuery(query);
     if(rs.next()){
          faculty = new FacultyDTO();
        // System.out.println("NAME="+rs.getString("user_name"));
         // System.out.println("Email="+rs.getString("user_email"));
         //  System.out.println("Mobile="+rs.getString("user_mobile"));
     
         faculty.setFacultyId(rs.getString("user_id"));
         faculty.setFacultyName(rs.getString("user_name"));
         faculty.setFacultyPassword(rs.getString("user_password"));
         faculty.setFacultyEmail(rs.getString("user_email"));
         faculty.setFacultyMobile(rs.getString("user_mobile")); 
         faculty.setBranchName(rs.getString("branchName"));
          return faculty;    
     }
     else{
         return null;    
     }
     }
     catch(SQLException e ){
         System.out.println(e);    
             }
     return  faculty;
 }
   public boolean updateFaculty(FacultyDTO faculty){
       Connection con =null;
 Statement st =null;
 boolean updated=false;
 try{
  con=DBConnector.getConnection();
  st=DBConnector.getStatement();
  String query = "UPDATE user SET "
                       + "user_name = '" + faculty.getFacultyName() + "', "
                       + "user_password = '" + faculty.getFacultyPassword() + "', "
                       + "user_email = '" + faculty.getFacultyEmail() + "', "
                       + "user_mobile = '" + faculty.getFacultyMobile() + "' "
                       + "WHERE user_role ='FACULTY' AND user_id = '" + faculty.getFacultyId() + "'";
  int rows = st.executeUpdate(query);
            updated = (rows > 0);
        } catch (SQLException e) {
           System.out.println(e);
        }
        return updated;
 }   
}
