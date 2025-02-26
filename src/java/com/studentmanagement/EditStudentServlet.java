package com.studentmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String enrollment = request.getParameter("enrollment");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM students WHERE enrollment_no = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, enrollment);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                
                out.println("<h2>Edit Student Details</h2>");
                out.println("<form action='UpdateStudentServlet' method='post'>");
                out.println("Enrollment No: <input type='text' name='enrollment' value='" + rs.getString("enrollment_no") + "' readonly><br>");
                out.println("Name: <input type='text' name='name' value='" + rs.getString("name") + "'><br>");
                out.println("Semester: <input type='text' name='semester' value='" + rs.getString("semester") + "'><br>");
                out.println("Department: <input type='text' name='department' value='" + rs.getString("department") + "'><br>");
                out.println("DOB: <input type='date' name='dob' value='" + rs.getString("dob") + "'><br>");
                out.println("Gmail: <input type='email' name='gmail' value='" + rs.getString("gmail") + "'><br>");
                out.println("Phone: <input type='text' name='phone' value='" + rs.getString("phone") + "'><br>");
                out.println("Address: <input type='text' name='address' value='" + rs.getString("address") + "'><br>");
                out.println("Father's Name: <input type='text' name='fatherName' value='" + rs.getString("father_name") + "'><br>");
                out.println("Father's Contact: <input type='text' name='fatherContact' value='" + rs.getString("father_contact") + "'><br>");
                out.println("<input type='submit' value='Update'>");
                out.println("</form>");
            } else {
                out.println("<h3>Student not found!</h3>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h3>Error fetching student details.</h3>");
        }
    }
}
