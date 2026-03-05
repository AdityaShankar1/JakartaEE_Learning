package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/add-animal")
public class AddAnimalServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String image = req.getParameter("image");
        String fact = req.getParameter("fact");
        String author = (String) req.getSession().getAttribute("user");

        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "")) {
            String sql = "INSERT INTO animals (name, fact, image_url, added_by) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, fact);
            pstmt.setString(3, image);
            pstmt.setString(4, author);
            pstmt.executeUpdate();
            resp.sendRedirect("hello");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}