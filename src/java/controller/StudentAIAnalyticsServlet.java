package controller;
import db.DBConnector;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
/**
 *
 * @author HP
 */
public class StudentAIAnalyticsServlet extends HttpServlet {
protected void doPost
(
HttpServletRequest request,
HttpServletResponse response
)

throws ServletException, IOException
{
System.out.println("Student AI Analytics Servlet Called : ");
HttpSession session =
request.getSession(false);
Object sid=session.getAttribute("studentID");
Object bid=session.getAttribute("branchID");
System.out.println("student session value : "+sid);
System.out.println("branch session value : "+bid);

int studentID =
(Integer)session.getAttribute("studentID");

int branchID=
(Integer)session.getAttribute("branchID");

Connection con = null;

String weakSubjectMessage = "";

String improvementMessage = "";

String semesterInsight = "";

try
{

con = DBConnector.getConnection();

System.out.println("Connected");

String weakQuery =

"SELECT subject, marks " +

"FROM marks " +

"WHERE student_id = ? " +

"AND exam_type = 'MST1' " +

"ORDER BY marks ASC " +

"LIMIT 1";


PreparedStatement psWeak =
con.prepareStatement(weakQuery);

psWeak.setInt(1, studentID);

ResultSet rsWeak =
psWeak.executeQuery();
    System.out.println("Weak Query Executed : ");
String weakSubject = "";

int weakMarks = 0;

if(rsWeak.next())
{

weakSubject =
rsWeak.getString("subject");

weakMarks =
rsWeak.getInt("marks");

weakSubjectMessage =

"Attention Needed! " +

"Your MST-1 marks in " +

weakSubject +

" are below average. " +

"Focus more on important concepts " +

"and practice previous year questions.";

}

System.out.println
(
"Weak Subject = " +
weakSubject
);


String improvementQuery =

"SELECT m1.subject, " +

"m1.marks AS mst1, " +

"m2.marks AS mst2 " +

"FROM marks m1 " +

"JOIN marks m2 " +

"ON m1.subject = m2.subject " +

"WHERE m1.student_id = ? " +

"AND m2.student_id = ? " +

"AND m1.exam_type = 'MST1' " +

"AND m2.exam_type = 'MST2'";


PreparedStatement psImprove =
con.prepareStatement(improvementQuery);

psImprove.setInt(1,studentID);

psImprove.setInt(2, studentID);

ResultSet rsImprove =
psImprove.executeQuery();

while(rsImprove.next())
{

String subject =
rsImprove.getString("subject");

int mst1 =
rsImprove.getInt("mst1");

int mst2 =
rsImprove.getInt("mst2");

int improvement =
mst2 - mst1;

if(improvement > 0)
{

improvementMessage =

"Great Improvement! " +

"Your MST-2 performance in " +

subject +

" improved by " +

improvement +

" marks. " +

"Keep maintaining consistency.";

}

}

System.out.println
(
"Improvement Message = " +
improvementMessage
);

String insightQuery =

"SELECT subject, marks " +

"FROM marks " +

"WHERE student_id = ? " +

"AND exam_type = 'ENDSEM'";


PreparedStatement psInsight =
con.prepareStatement(insightQuery);

psInsight.setInt(1, studentID);

ResultSet rsInsight =
psInsight.executeQuery();

String strongSubject = "";

String weakEndSubject = "";

int maxMarks = -1;

int minMarks = 999;

while(rsInsight.next())
{

String subject =
rsInsight.getString("subject");

int marks =
rsInsight.getInt("marks");

if(marks > maxMarks)
{

maxMarks = marks;

strongSubject = subject;

}

if(marks < minMarks)
{

minMarks = marks;

weakEndSubject = subject;

}

}


semesterInsight =

"Final Semester Insight: " +

"Your strongest subject this semester was " +

strongSubject +

". Weakest subject was " +

weakEndSubject + ".";


System.out.println
(
"Semester Insight = " +
semesterInsight
);

request.setAttribute
(
"weakSubject",
weakSubjectMessage
);

request.setAttribute
(
"improvementMessage",
improvementMessage
);

request.setAttribute
(
"semesterInsight",
semesterInsight
);

RequestDispatcher rd =
request.getRequestDispatcher
(
"studentDashBoard.jsp"
);

rd.forward(request, response);

}

catch(Exception e)
{

e.printStackTrace();

}

}

}