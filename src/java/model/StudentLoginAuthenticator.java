package model;
import db.DBConnector;
import dto.StudentLoginDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author HP
 */
public class StudentLoginAuthenticator 
{
    private int studentID;
    private int branchID;
    public boolean isLogin(StudentLoginDTO student)
    {
       String username = student.getUsername();
       String password =  student.getPassword();
       String tablePassword="";
       try
       {
           Connection con=DBConnector.getConnection();
     //  Statement st=DBConnector.getStatement();
       String query="SELECT student_id,branch_id, student_password from student where student_name = ?  ";
      PreparedStatement ps=con.prepareStatement(query);
       ps.setString(1,student.getUsername());
       
           System.out.println("Query is " + query);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
               studentID=rs.getInt("student_id");
               branchID=rs.getInt("branch_id");
               
               String dbPassword = rs.getString("student_password");

        if(dbPassword.equals(student.getPassword())) {
            return true;
        }
           }
       }
       catch(SQLException e ){
           System.out.println(e);
       }
 if(username!= null && password != null && !username.trim().equals("")&& password.equals(tablePassword))
 {
     return true;
 }      
 return false;
    }
public int getBranchId(String username){
    int branchId=0;
    try{
   Connection con=DBConnector.getConnection();
   Statement st= con.createStatement();
   String query="SELECT branch_id FROM student WHERE student_name='"+username+"'";
   ResultSet rs =st.executeQuery(query);
   if(rs.next()){
       branchId=rs.getInt("branch_id");
       
   }
  // rs.close();
  // st.close();
 //  con.close();
    }
    catch(SQLException e ){
        System.out.println(e);    
    }
 
return branchId;
}   
    public int getStudentID(){
        return studentID;
        
    }
  public int getBranchID(){
        return branchID;
        
    }  
}
