<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<body style="text-align: center; font-family: sans-serif; background: #f4f7f6; padding-top: 50px;">
    <div style="max-width: 500px; margin: 0 auto; background: white; padding: 30px; border-radius: 15px; box-shadow: 0 4px 15px rgba(0,0,0,0.1);">
        <h1>Edit ${name}</h1>
        <form action="edit-animal" method="POST">
            <input type="hidden" name="id" value="${id}">
            <div style="margin-bottom: 15px; text-align: left;">
                <label>Animal Name:</label>
                <input type="text" name="name" value="${name}" required style="width: 100%; padding: 10px;">
            </div>
            <div style="margin-bottom: 15px; text-align: left;">
                <label>Image Filename:</label>
                <input type="text" name="image" value="${image}" required style="width: 100%; padding: 10px;">
            </div>
            <div style="margin-bottom: 20px; text-align: left;">
                <label>Interesting Fact:</label>
                <textarea name="fact" rows="4" required style="width: 100%; padding: 10px;">${fact}</textarea>
            </div>
            <button type="submit" style="width: 100%; padding: 12px; background: #3498db; color: white; border: none; border-radius: 5px; cursor: pointer; font-weight: bold;">
                Save Changes
            </button>
        </form>
        <br><a href="hello" style="color: #666; text-decoration: none;">Cancel</a>
    </div>
</body>
</html>
