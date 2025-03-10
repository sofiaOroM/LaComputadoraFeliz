<%-- 
    Document   : ensamblaje
    Created on : 3/03/2025, 11:11:19
    Author     : sofia
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ensamblaje de Computadoras</title>
</head>
<body>
    <h2>Registrar Ensamblaje</h2>
    <form action="EnsamblajeServlet" method="POST">
        <label>Selecciona una Computadora:</label>
        <input type="text" name="computadora" required>
        <br>
        <label>Fecha de Ensamblaje:</label>
        <input type="date" name="fecha" required>
        <br>
        <button type="submit">Ensamblar</button>
    </form>
</body>
</html>
