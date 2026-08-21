package model;
import db.DBConnector;
import dto.AddStudentDTO;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
/**
 *
 * @author HP
 */
public class AddStudentAuthenticator 
{
 public boolean isAdd(AddStudentDTO dto)
    {
        String username=dto.getUsername();
        String password=dto.getPassword();
        String email =dto.getEmail();
        String mobile=dto.getMobile();
        String id=dto.getId();
        String  branchName =dto.getBranchName();
        String  section =dto.getSection();
        
        System.out.println("auth "+dto.getUsername()+dto.getPassword()+dto.getId()+dto.getEmail()+dto.getMobile()+dto.getBranchName()+dto.getSection());
 try{
    Connection con =DBConnector.getConnection();
    //Statement st=DBConnector.getStatement();
     
//String query = "INSERT INTO student(`student_name`,`student_password`,`student_email`,`student_mobile`,`student_id`,`branch_id`,`section`) VALUES('"+username+"', '"+password+"','"+email+"','"+mobile+"','"+id+"','"+branchName+"','"+section+"')";
String query="INSERT INTO student"+"(student_name,student_password,student_email,student_mobile,student_id,branch_id,section)"+"VALUES(?,?,?,?,?,?,?)";
System.out.println("Query is "+query );
PreparedStatement ps =con.prepareStatement(query);
ps.setString(1, username);
ps.setString(2, password);
ps.setString(3, email);
ps.setString(4, mobile);
ps.setString(5, id);
int branchId=getBranchIdByName(branchName);
     System.out.println("Branch ID ="+branchId);
ps.setInt(6, branchId);
ps.setString(7, section);
     
int i=ps.executeUpdate();
if(i>0){
return true;
}
 return false;
 
 }
 
 catch(SQLException e ){
     System.out.println(e);
 }
        return false;
    }  
 public int getBranchIdByName(String branchName){
     int branchId=0; 
    try{
        Connection con = DBConnector.getConnection();
        PreparedStatement ps =con.prepareStatement("SELECT branch_id FROM branch WHERE branch_name=?");
        ps.setString(1, branchName);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            branchId=rs.getInt("branch_id");
        }  
    }
    catch(Exception e){
        System.out.println(e); 
    }
    return branchId;
 }
}
