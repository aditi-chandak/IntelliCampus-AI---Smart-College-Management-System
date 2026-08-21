package controller;
import db.DBConnector;
import dto.LeaveDTO;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author HP
 */
public class HodApprovalServlet extends HttpServlet 
{
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException
{
    Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = DBConnector.getConnection();
            st = con.createStatement();

            String query = "SELECT l.leave_id, u.user_name, l.leave_type, l.from_date, l.to_date,l.reason, l.applied_date FROM leave_request l JOIN user u ON l.user_id = u.user_id WHERE l.status = 'pending' ORDER BY l.applied_date ASC";

            rs = st.executeQuery(query);

            List<LeaveDTO> pendingLeaves = new ArrayList<>();
            while (rs.next()) {
                LeaveDTO leave = new LeaveDTO();
                leave.setLeaveId(rs.getInt("leave_id"));
                leave.setUserName(rs.getString("user_name"));
                leave.setLeaveType(rs.getString("leave_type"));
                leave.setFromDate(rs.getDate("from_date"));
                leave.setToDate(rs.getDate("to_date"));
                leave.setReason(rs.getString("reason"));
                leave.setAppliedDate(rs.getTimestamp("applied_date"));
                pendingLeaves.add(leave);
            }
            
           

            request.setAttribute("PendingLeaves", pendingLeaves);
            request.getRequestDispatcher("HODApprovals.jsp").forward(request, response);

        } catch (SQLException e) {
            System.out.println(e);
            response.getWriter().println("Database Error: " + e.getMessage());
        }
}
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException
{
   int leaveId = Integer.parseInt(request.getParameter("Leave_ID"));
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        int hodId = (int) session.getAttribute("HOD_ID");

        Connection con = null;
        Statement st = null;

        try {
            con = DBConnector.getConnection();
            st = con.createStatement();

            String newStatus = action.equalsIgnoreCase("Approve") ? "Approved" : "Rejected";

            String queryUpdate = "UPDATE leave_request " +
                                 "SET status='" + newStatus + "', approved_by=" + hodId + ", decision_date=NOW() " +
                                 "WHERE leave_id=" + leaveId;

            int rowsAffected = st.executeUpdate(queryUpdate);

            System.out.println("Rows updated: " + rowsAffected);

            
            doGet(request, response);

        } catch (SQLException e) {
            System.out.println(e);
            response.getWriter().println("Database Error: " + e.getMessage());
        } 
    } 
}

