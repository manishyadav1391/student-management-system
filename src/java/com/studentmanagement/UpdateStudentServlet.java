package com.studentmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String enrollment = request.getParameter("enrollment");
        String name = request.getParameter("name");
        String semester = request.getParameter("semester");
        String department = request.getParameter("department");
        String dob = request.getParameter("dob");
        String gmail = request.getParameter("gmail");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String fatherName = request.getParameter("fatherName");
        String fatherContact = request.getParameter("fatherContact");

        try (Connection conn = DBConnection.getConnection()) {
            String query = "UPDATE students SET name=?, semester=?, department=?, dob=?, gmail=?, phone=?, address=?, father_name=?, father_contact=? WHERE enrollment_no=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, semester);
            ps.setString(3, department);
            ps.setString(4, dob);
            ps.setString(5, gmail);
            ps.setString(6, phone);
            ps.setString(7, address);
            ps.setString(8, fatherName);
            ps.setString(9, fatherContact);
            ps.setString(10, enrollment);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                out.println("<h3>Student details updated successfully! <a href='welcome.html'>Go to home page</a></h3>");
            } else {
                out.println("<h3>Failed to update student details.</h3>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h3>Error updating student details.</h3>");
        }
    }
}
