<%-- 
    Document   : view_marks_popup
    Created on : 13 Mar 2026, 6:49:26 pm
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View-Marks</title>
        <style>
        /* Popup container */
        .popup-container {
            width: 400px;
            margin: 100px auto;
            padding: 20px;
            border: 2px solid #4CAF50;
            border-radius: 10px;
            background-color: #f9f9f9;
            box-shadow: 0 0 15px rgba(0,0,0,0.2);
            text-align: center;
            font-family: Arial, sans-serif;
        }

        h2 {
            color: #4CAF50;
            margin-bottom: 20px;
        }

        select {
            padding: 8px;
            width: 80%;
            margin-bottom: 20px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        input[type="submit"] {
            padding: 10px 20px;
            border: none;
            background-color: #4CAF50;
            color: white;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
    </head>
    <body>
    <div class="popup-container">
        <h2>Select Examination</h2>
        <form action="ViewMarksServlet" method="get">
            <select name="examType" required>
                <option value="" disabled selected>Select Exam</option>
                <option value="MST1">Mid Semester Test 1</option>
                <option value="MST2">Mid Semester Test 2</option>
                <option value="EndSemester">End Semester</option>
            </select>
            <br>
            <input type="submit" value="View Marks">
        </form>
    </div>
</body>
</html>
