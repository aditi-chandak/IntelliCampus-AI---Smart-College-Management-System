package model;
import db.DBConnector;
import dto.AddhodDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
/**
 *
 * @author HP
 */
public class AddHODAuthenticator {
 public boolean isAdd(AddhodDTO dto)
    {
       String username =
    dto.getUsername();

    String password =
   dto.getPassword();

    String email =
  dto.getEmail();

    String mobile =
   dto.getMobile();

    String id =
    dto.getId();

    String role =
    dto.getRole();

    String branchName =
   dto.getBranchName();

    try
    {

        Connection conn =
        DBConnector.getConnection();

        int branchId =
        getBranchIdByName(branchName);

        System.out.println("branchId = "+branchId);
        String query =
        "INSERT INTO user " +

        "(user_name,user_password,user_email," +

        "user_mobile,user_id,user_role,branch_id) " +

        "VALUES(?,?,?,?,?,?,?)";

        PreparedStatement ps =
        conn.prepareStatement(query);

        ps.setString(1, username);

        ps.setString(2, password);

        ps.setString(3, email);

        ps.setString(4, mobile);

        ps.setString(5, id);

        ps.setString(6, role);

        ps.setInt(7, branchId);

        int i =
        ps.executeUpdate();

        if(i > 0)
        {

            return true;
        }

    }
    catch(SQLException e)
    {

        e.printStackTrace();
    }

    return false; 
    }
 public int getBranchIdByName(
String branchName)
{

    int branchId = 0;

    try
    {

        Connection conn =
        DBConnector.getConnection();

        String query =
        "SELECT branch_id FROM branch " +

        "WHERE branch_name=?";

        PreparedStatement ps =
        conn.prepareStatement(query);

        ps.setString(1, branchName);

        ResultSet rs =
        ps.executeQuery();

        if(rs.next())
        {

            branchId =
            rs.getInt("branch_id");
        }

    }
    catch(SQLException e)
    {

        e.printStackTrace();
    }

    return branchId;
}
}
