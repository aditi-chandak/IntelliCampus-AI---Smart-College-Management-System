package model;
import java.sql.Connection;
import db.DBConnector;
import dto.HODLoginDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author HP
 */
public class HodLoginAuthenticator {
    private int hodId;
    public boolean isLogin(HODLoginDTO Hod)
    {
       String username = Hod.getUsername();
       String password =  Hod.getPassword();
       String tablePassword="";
       try
       {
       Statement st=DBConnector.getStatement();
       String query="SELECT user_id, user_password from user where user_name = '"+username+"' AND user_role='HOD' ";
       
           System.out.println("Query is " + query);
           ResultSet rs = st.executeQuery(query);
           if(rs.next()){
               tablePassword=rs.getString(2);   
               hodId=rs.getInt(1);   
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
    
    
    public int getHodId(){
        return hodId;
        
    }
    
    }

