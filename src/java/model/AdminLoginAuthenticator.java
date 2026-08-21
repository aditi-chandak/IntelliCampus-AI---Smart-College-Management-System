package model;

import db.DBConnector;
import dto.AdminDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author HP
 */
public class AdminLoginAuthenticator 
{
    public boolean isLogin(AdminDTO admin )
    {
       String username = admin.getUsername();
       String password =  admin.getPassword();
         String tablePassword="";
       try
       {
       Statement st=DBConnector.getStatement();
       String query="SELECT user_password from user where user_name = '"+username+"' AND user_role='ADMIN' ";
       
           System.out.println("Query is " + query);
           ResultSet rs = st.executeQuery(query);
           if(rs.next()){
               tablePassword=rs.getString(1);   
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
}
