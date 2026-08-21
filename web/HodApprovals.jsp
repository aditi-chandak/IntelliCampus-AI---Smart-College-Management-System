<%-- 
    Document   : HodApprovals
    Created on : 6 Feb 2026, 3:38:48 pm
    Author     : HP
--%>
<%@page import="dto.LeaveDTO"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOD-Leave Approvals </title>
    </head>
    <body>
       
        <h1>Pending Leave Approvals...</h1>
        <table border="1" cellpadding="10" cellspacing="0">
            <tr>
                <td>Leave ID:</td>
                <td>Faculty-Name:</td>
                <td>Leave-Type:</td>
                <td>From-Date:</td>
                <td>To-Date:</td>
                <td>Reason:</td>
                <td>Applied-Date:</td>
                <td>Action:</td>
                
            </tr> 
            <%
List<LeaveDTO> pendingLeaves = (List<LeaveDTO>) request.getAttribute("PendingLeaves");
if(pendingLeaves != null && !pendingLeaves.isEmpty()) {
    for(LeaveDTO leave : pendingLeaves) {
        %>
            <tr>
                <td><%= leave.getLeaveId() %></td>
                <td><%= leave.getUserName()%></td>
                <td><%= leave.getLeaveType()%></td>
                <td><%= leave.getFromDate()%></td>
                <td><%= leave.getToDate()%></td>
                <td><%= leave.getReason()%></td>
                <td><%= leave.getAppliedDate()%></td>
                <td>
                    <form action="HodApprovalServlet" method="post">
                        <input type="hidden" name="leave_id" value="<%= leave.getLeaveId() %>">
                        <input type="submit" name="action" value="Approve">
                        <input type="submit" name="action" value="Reject">
                    </form>
                </td>
            </tr>
            <%
                }
}else{
            %>
            <tr>
                <td colspan="8">No pending leave request</td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
