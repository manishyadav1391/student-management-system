package com.studentmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AddStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String enrollment = request.getParameter("enrollment");
        String semester = request.getParameter("semester");
        String department = request.getParameter("department");
        String dob = request.getParameter("dob");
        String gmail = request.getParameter("gmail");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String fatherName = request.getParameter("fatherName");
        String fatherContact = request.getParameter("fatherContact");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
          
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagement", "root", "root");

         
            String sql = "INSERT INTO students (name, enrollment_no, semester, department, dob, gmail, phone, address, father_name, father_contact) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, enrollment);
            stmt.setString(3, semester);
            stmt.setString(4, department);
            stmt.setString(5, dob);
            stmt.setString(6, gmail);
            stmt.setString(7, phone);
            stmt.setString(8, address);
            stmt.setString(9, fatherName);
            stmt.setString(10, fatherContact);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                out.println("<h3>Student Added Successfully! <a href='welcome.html'>Go to home page</a></h3>");
            } else {
                out.println("<h3>Failed to Add Student.</h3>");
            }

        } catch (ClassNotFoundException e) {
            out.println("<h3>Error: JDBC Driver not found!</h3>");
            e.printStackTrace();
        } catch (SQLException e) {
            out.println("<h3>Error: Database issue!</h3>");
            e.printStackTrace();
        } 
    }
}
