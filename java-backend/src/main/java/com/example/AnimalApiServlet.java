package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/api/animals")
public class AnimalApiServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1. Set the Content-Type so the browser knows this is JSON, not HTML
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        List<Map<String, String>> animals = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "")) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM animals");
            while (rs.next()) {
                Map<String, String> a = new HashMap<>();
                a.put("id", rs.getString("id"));
                a.put("name", rs.getString("name"));
                a.put("fact", rs.getString("fact"));
                animals.add(a);
            }

            // 2. Use Jackson to turn the List into a JSON String
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(animals);

            // 3. Write the string directly to the response body
            resp.getWriter().write(json);

        } catch (SQLException e) {
            resp.setStatus(500);
            resp.getWriter().write("{\"error\": \"Database connection failed\"}");
        }
    }
}
