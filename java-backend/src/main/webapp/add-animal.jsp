<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Animal</title>
</head>
<body style="text-align: center; font-family: sans-serif; background-color: #f4f7f6; padding-top: 50px;">
    <div style="max-width: 500px; margin: 0 auto; background: white; padding: 30px; border-radius: 15px; box-shadow: 0 4px 15px rgba(0,0,0,0.1);">
        <h1>🐾 Add to the Zoo</h1>
        <form action="add-animal" method="POST">
            <div style="margin-bottom: 15px; text-align: left;">
                <label>Animal Name:</label>
                <input type="text" name="name" placeholder="e.g. Lion" required style="width: 100%; padding: 10px; margin-top: 5px;">
            </div>
            <div style="margin-bottom: 15px; text-align: left;">
                <label>Image Filename:</label>
                <input type="text" name="image" placeholder="e.g. fox.png" required style="width: 100%; padding: 10px; margin-top: 5px;">
            </div>
            <div style="margin-bottom: 20px; text-align: left;">
                <label>Interesting Fact:</label>
                <textarea name="fact" rows="4" required style="width: 100%; padding: 10px; margin-top: 5px;"></textarea>
            </div>
            <button type="submit" style="width: 100%; padding: 12px; background: #27ae60; color: white; border: none; border-radius: 5px; cursor: pointer;">Publish Animal</button>
        </form>
        <br><a href="hello">← Back to Zoo</a>
    </div>
</body>
</html>