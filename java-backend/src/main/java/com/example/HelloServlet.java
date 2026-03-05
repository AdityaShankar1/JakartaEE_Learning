package com.example;
import java.util.Map;
import java.util.HashMap;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // 1. Security Check (Backup for the Filter)
        if (session.getAttribute("user") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String selectedAnimal = req.getParameter("animal");

        // 2. Logic for Session Memory
        if (selectedAnimal == null || selectedAnimal.isEmpty()) {
            selectedAnimal = (String) session.getAttribute("favoriteAnimal");
        }
        if (selectedAnimal == null) selectedAnimal = "Giraffe";

        session.setAttribute("favoriteAnimal", selectedAnimal);

        // 3. JDBC Logic - The "Deep River"
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "")) {

            // Inside HelloServlet.java -> doGet
// Change the List to store objects or use a Map to keep Name + ID
            List<Map<String, String>> animalList = new ArrayList<>();
            ResultSet rsAll = conn.createStatement().executeQuery("SELECT id, name FROM animals");
            while (rsAll.next()) {
                Map<String, String> animalMap = new HashMap<>();
                animalMap.put("id", rsAll.getString("id"));
                animalMap.put("name", rsAll.getString("name"));
                animalList.add(animalMap);
            }
            req.setAttribute("allAnimals", animalList);

            // B. Fetch details for the CURRENT selected animal
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM animals WHERE name = ?");
            pstmt.setString(1, selectedAnimal);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                req.setAttribute("animalName", rs.getString("name"));
                req.setAttribute("animalImage", rs.getString("image_url"));
                req.setAttribute("animalFact", rs.getString("fact"));
            }

        } catch (SQLException e) {
            throw new ServletException("Database access error in HelloServlet", e);
        }

        req.getRequestDispatcher("blog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Handle the Math Calculator
        String n1 = req.getParameter("num1");
        String n2 = req.getParameter("num2");

        try {
            if (n1 != null && n2 != null) {
                int sum = Integer.parseInt(n1) + Integer.parseInt(n2);
                req.setAttribute("calculationResult", "Result: " + sum);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("calculationResult", "Please enter valid numbers!");
        }

        // After math is done, we just run doGet to refresh the animal data and show buttons
        doGet(req, resp);
    }
}