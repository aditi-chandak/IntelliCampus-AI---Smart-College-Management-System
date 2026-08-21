package dao;
import db.DBConnector;
import dto.HodDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author HP
 */
public class HodDAO {
 public HodDTO getHodById(String HodId){
  HodDTO Hod =null;
 Connection con =null;
 Statement st =null;
 ResultSet rs =null;
 try{
     con=DBConnector.getConnection();
     st=DBConnector.getStatement();
     String query="SELECT user_id, user_name ,user_password,user_email,user_mobile "+"FROM user WHERE user_id ='"+HodId+"'" ;
     rs=st.executeQuery(query);
     if(rs.next()){
         Hod = new HodDTO();
         Hod.setHodId(rs.getString("user_id"));
         Hod.setHodName(rs.getString("user_name"));
         Hod.setHodPassword(rs.getString("user_password"));
         Hod.setHodEmail(rs.getString("user_email"));
         Hod.setHodMobile(rs.getString("user_mobile"));       
     }
     }
     catch(SQLException e ){
         System.out.println(e);    
             }
     return Hod;
 }
   public boolean updateHod(HodDTO Hod){
       Connection con =null;
 Statement st =null;
 boolean updated=false;
 try{
  con=DBConnector.getConnection();
  st=DBConnector.getStatement();
  String query = "UPDATE user SET "
                       + "user_name = '" + Hod.getHodName() + "', "
                       + "user_password = '" + Hod.getHodPassword() + "', "
                       + "user_email = '" + Hod.getHodEmail() + "', "
                       + "user_mobile = '" + Hod.getHodMobile() + "' "
                       + "WHERE user_id = '" + Hod.getHodId() + "'";
  int rows = st.executeUpdate(query);
            updated = (rows > 0);
        } catch (SQLException e) {
           System.out.println(e);
        }
        return updated;
 }   
}
