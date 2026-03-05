package com.example;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebListener
public class DbInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Under the hood: Loading the H2 Driver into memory
            Class.forName("org.h2.Driver");

            // DB_CLOSE_DELAY=-1 keeps the in-memory database alive as long as Tomcat is running
            Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
            Statement stmt = conn.createStatement();

            // 1. Create and Seed Users Table
            stmt.execute("CREATE TABLE IF NOT EXISTS users (username VARCHAR(255) PRIMARY KEY, password VARCHAR(255))");

            // Check if user exists before inserting to prevent Primary Key violations on reload
            ResultSet rsUser = stmt.executeQuery("SELECT COUNT(*) FROM users WHERE username = 'aditya'");
            if (rsUser.next() && rsUser.getInt(1) == 0) {
                stmt.execute("INSERT INTO users VALUES ('aditya', 'java25')");
                System.out.println("H2: User 'aditya' created.");
            }

            // 2. Create and Seed Animals Table
            stmt.execute("CREATE TABLE IF NOT EXISTS animals (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "fact TEXT, " +
                    "image_url VARCHAR(255), " +
                    "added_by VARCHAR(255))");

            // Seed initial animals if table is empty
            ResultSet rsAnimals = stmt.executeQuery("SELECT COUNT(*) FROM animals");
            if (rsAnimals.next() && rsAnimals.getInt(1) == 0) {
                stmt.execute("INSERT INTO animals (name, fact, image_url, added_by) VALUES " +
                        "('Giraffe', 'Tallest mammals on Earth.', 'giraffe.png', 'system'), " +
                        "('Fox', 'Canines that behave like cats.', 'fox.png', 'system')");
                System.out.println("H2: Initial animals seeded.");
            }

            System.out.println("H2 Database initialized successfully.");

        } catch (Exception e) {
            System.err.println("Database initialization failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup happens automatically for in-memory DBs
    }
}