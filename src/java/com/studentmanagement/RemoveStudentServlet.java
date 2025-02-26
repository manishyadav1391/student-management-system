package com.studentmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RemoveStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String enrollment = request.getParameter("enrollment");

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM students WHERE enrollment_no = ?");
            ps.setString(1, enrollment);
            int rows = ps.executeUpdate();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            if (rows > 0) {
                out.println("Student Removed Successfully! <a href='welcome.html'>Go Back</a>");
            } else {
                out.println("No student found with this enrollment number! <a href='welcome.html'>Try Again</a>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

