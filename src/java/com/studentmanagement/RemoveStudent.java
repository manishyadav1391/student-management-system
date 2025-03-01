
 
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


public class RemoveStudent extends HttpServlet {
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String enrollment = request.getParameter("enrollment");

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    try (Connection conn = DBConnection.getConnection()) {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM students WHERE enrollment_no = ?");
        ps.setString(1, enrollment);
        int rows = ps.executeUpdate();

        if (rows > 0) {
            out.println("<h3 style='color:green;'>Student Removed Successfully!</h3>");
        } else {
            out.println("<h3 style='color:red;'>No student found with this enrollment number!</h3>");
        }
        out.println("<a href='ViewStudentServlet'>Back to Student List</a>");

    } catch (SQLException e) {
        out.println("<h3 style='color:red;'>Database Error!</h3>");
        e.printStackTrace();
    }
}

}



   