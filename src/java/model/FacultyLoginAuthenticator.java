package model;
import db.DBConnector;
import dto.FacultyLoginDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author HP
 */
public class FacultyLoginAuthenticator {
   private int facultyId;
    public boolean isLogin(FacultyLoginDTO Faculty)
    {
       String username = Faculty.getUsername();
       String password =  Faculty.getPassword();
       String tablePassword="";
       try
       {
       Statement st=DBConnector.getStatement();
       String query="SELECT user_id, user_password from user where user_name = '"+username+"' AND user_role='FACULTY' ";
       
           System.out.println("Query is " + query);
           ResultSet rs = st.executeQuery(query);
           if(rs.next()){
               tablePassword=rs.getString(2);   
               facultyId=rs.getInt(1);   
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
public int getBranchID(String username){
    int branchId=0;
    try{
   Connection con=DBConnector.getConnection();
   Statement st= con.createStatement();
   String query="SELECT branch_id FROM user WHERE user_name='"+username+"'";
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
    
    
    public int getFacultyId(){
        return facultyId;
        
    }
     
}
