<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalDateTime, java.time.format.DateTimeFormatter" %>
<%
    // This code runs on the server every time the page is requested
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    String timeStr = now.format(formatter);
%>
<html>
<body style="text-align: center; font-family: sans-serif; background: #2c3e50; padding-top: 100px; color: white;">

    <div style="max-width: 350px; margin: 0 auto; background: white; padding: 30px; border-radius: 15px; color: #333; box-shadow: 0 10px 30px rgba(0,0,0,0.5);">
        <h1 style="margin-bottom: 5px;">Kingdom Login</h1>

        <p style="margin-top: 0; color: #e67e22; font-weight: bold;">
            Server Time: <%= timeStr %>
        </p>

        <hr style="border: 0; height: 1px; background: #eee; margin: 20px 0;">

        <form action="login" method="POST">
            <div style="margin-bottom: 15px;">
                <input type="text" name="username" placeholder="Username" required
                       style="width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 5px;">
            </div>
            <div style="margin-bottom: 20px;">
                <input type="password" name="password" placeholder="Password" required
                       style="width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 5px;">
            </div>
            <button type="submit" style="width: 100%; padding: 12px; background: #27ae60; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 1em;">
                Enter Kingdom
            </button>
        </form>

        <p style="color: #c0392b; font-size: 0.9em; height: 1em;">${error}</p>
    </div>

    <p style="margin-top: 20px; font-size: 0.8em; opacity: 0.7;">Java 25 | Jakarta EE | H2 Database</p>
</body>
</html>