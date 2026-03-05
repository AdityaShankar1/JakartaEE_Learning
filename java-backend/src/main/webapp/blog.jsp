<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${animalName} Blog</title>
</head>
<body style="text-align: center; font-family: sans-serif; background-color: #f9f9f9; padding-top: 50px;">
    <div style="max-width: 650px; margin: 0 auto; background: white; padding: 30px; border-radius: 20px; box-shadow: 0 10px 25px rgba(0,0,0,0.1);">

        <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
            <span style="color: #888;">Welcome, <b>${user}</b></span>
            <div>
                <a href="add-animal.jsp" style="text-decoration: none; background: #27ae60; color: white; padding: 8px 15px; border-radius: 5px; font-weight: bold; margin-right: 10px;">+ Add Animal</a>
                <a href="logout" style="color: #c0392b; text-decoration: none; font-weight: bold;">Logout 🚪</a>
            </div>
        </div>

        <div style="margin-bottom: 30px; border-top: 1px solid #eee; padding-top: 20px;">
            <c:forEach var="animal" items="${allAnimals}">
                <div style="display: inline-flex; margin: 5px; vertical-align: middle;">
                    <a href="hello?animal=${animal.name}"
                       style="text-decoration: none; padding: 10px 15px; background: ${animalName == animal.name ? '#3498db' : '#eee'};
                              color: ${animalName == animal.name ? 'white' : '#333'}; border-radius: 5px 0 0 5px; border: 1px solid #ccc; border-right: none;">
                       🐾 ${animal.name}
                    </a>
                    <a href="edit-animal?id=${animal.id}"
                       style="text-decoration: none; padding: 10px 10px; background: #f39c12; color: white; border-top: 1px solid #e67e22; border-bottom: 1px solid #e67e22; font-weight: bold;">
                       ✎
                    </a>
                    <a href="delete-animal?id=${animal.id}"
                       onclick="return confirm('Are you sure you want to delete ${animal.name}?')"
                       style="text-decoration: none; padding: 10px 12px; background: #c0392b; color: white; border-radius: 0 5px 5px 0; border: 1px solid #c0392b; font-weight: bold;">
                       ✕
                    </a>
                </div>
            </c:forEach>
        </div>

        <hr style="border:0; height:1px; background:#eee;">

        <h1>The Majestic ${animalName}</h1>
        <img src="${animalImage}" alt="${animalName}" width="400" style="border-radius: 15px; border: 8px solid #f0f0f0;">
        <p style="font-size: 1.1em; line-height: 1.6; padding: 20px; color: #444;">${animalFact}</p>

        <c:forEach var="animal" items="${allAnimals}">
            <c:if test="${animal.name == animalName}">
                <a href="edit-animal?id=${animal.id}" style="color: #3498db; text-decoration: none; font-size: 0.9em;">Edit this description</a>
            </c:if>
        </c:forEach>

        <hr style="border:0; height:1px; background:#eee; margin-top: 20px;">

        <h3>Math Corner</h3>
        <form action="hello" method="POST">
            <input type="number" name="num1" style="width:50px; padding: 5px;"> +
            <input type="number" name="num2" style="width:50px; padding: 5px;">
            <button type="submit" style="padding: 5px 15px; cursor: pointer;">Sum</button>
        </form>
        <h2 style="color: #2196F3;"> ${calculationResult} </h2>
    </div>
</body>
</html>