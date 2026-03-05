package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/edit-animal")
public class EditAnimalServlet extends HttpServlet {

    // 1. Show the form with existing data
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "")) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM animals WHERE id = ?");
            pstmt.setInt(1, Integer.parseInt(id));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                req.setAttribute("id", rs.getInt("id"));
                req.setAttribute("name", rs.getString("name"));
                req.setAttribute("fact", rs.getString("fact"));
                req.setAttribute("image", rs.getString("image_url"));
                req.getRequestDispatcher("edit-animal.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    // 2. Process the update
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String fact = req.getParameter("fact");
        String image = req.getParameter("image");

        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "")) {
            String sql = "UPDATE animals SET name=?, fact=?, image_url=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, fact);
            pstmt.setString(3, image);
            pstmt.setInt(4, Integer.parseInt(id));
            pstmt.executeUpdate();
            resp.sendRedirect("hello?animal=" + name);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
