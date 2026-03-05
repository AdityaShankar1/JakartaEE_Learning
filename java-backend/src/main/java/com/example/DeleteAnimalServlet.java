package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/delete-animal")
public class DeleteAnimalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (id != null) {
            try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "")) {
                String sql = "DELETE FROM animals WHERE id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(id));
                pstmt.executeUpdate();
            } catch (Exception e) {
                throw new ServletException("Error deleting animal", e);
            }
        }
        // Redirect back to the main list
        resp.sendRedirect("hello");
    }
}
