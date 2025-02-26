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


public class ViewStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String enrollment = request.getParameter("enrollment");
        String semester = request.getParameter("semester");

        try (Connection conn = DBConnection.getConnection()) {
            String query;
            PreparedStatement ps;
            
            if (enrollment != null && !enrollment.isEmpty()) {
                query = "SELECT * FROM students WHERE enrollment_no = ?";
                ps = conn.prepareStatement(query);
                ps.setString(1, enrollment);
            } else if (semester != null && !semester.isEmpty()) {
                query = "SELECT * FROM students WHERE semester = ?";
                ps = conn.prepareStatement(query);
                ps.setString(1, semester);
            } else {
                query = "SELECT * FROM students";
                ps = conn.prepareStatement(query);
            }

            ResultSet rs = ps.executeQuery();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h2>Student List</h2>");
            out.println("<table border='1'><tr><th>Name</th><th>Enrollment</th><th>Semester</th><th>Department</th><th>Gmail</th><th>Phone</th><th>Address</th><th>Father's Name</th><th>Father's Contact</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("enrollment_no") + "</td>"); 
                out.println("<td>" + rs.getString("semester") + "</td>");
                out.println("<td>" + rs.getString("department") + "</td>");
                out.println("<td>" + rs.getString("gmail") + "</td>"); 
                out.println("<td>" + rs.getString("phone") + "</td>");
                out.println("<td>" + rs.getString("address") + "</td>");
                out.println("<td>" + rs.getString("father_name") + "</td>");
                out.println("<td>" + rs.getString("father_contact") + "</td>");
                out.println("<td><a href='EditStudentServlet?enrollment=" + rs.getString("enrollment_no") + "'>Edit</a></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
