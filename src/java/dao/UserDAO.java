package dao;
import model.User;
import java.sql.*;
import java.util.*;
/**
 *
 * @author HP
 */
public class UserDAO {
  private String jdbcURL = "jdbc:mysql://localhost:3306/db05";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // 🔹 Get Faculty By Branch
    public List<User> getFacultyByBranch(int branchId) {

        List<User> facultyList = new ArrayList<>();

        String sql = "SELECT * FROM user WHERE user_role='FACULTY' AND branch_id=?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, branchId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setUserPassword(rs.getString("user_password"));
                user.setUserRole(rs.getString("user_role"));
                user.setUserEmail(rs.getString("user_email"));
                user.setUserMobile(rs.getString("user_mobile"));
                user.setBranchId(rs.getInt("branch_id"));

                facultyList.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return facultyList;
    }

    // 🔹 Insert User
    public void insertUser(User user) {

        String sql = "INSERT INTO user (user_name, user_password, user_role, user_email, user_mobile, branch_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getUserPassword());
            stmt.setString(3, user.getUserRole());
            stmt.setString(4, user.getUserEmail());
            stmt.setString(5, user.getUserMobile());
            stmt.setInt(6, user.getBranchId());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 Delete User
    public void deleteUser(int userId) {

        String sql = "DELETE FROM user WHERE user_id=?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
}
