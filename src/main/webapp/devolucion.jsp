<%-- 
    Document   : devolucion
    Created on : 3/03/2025, 10:49:45
    Author     : sofia
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Devolución</title>
</head>
<body>
    <h2>Registrar Devolución</h2>
    <form action="DevolucionServlet" method="POST">
        <label>ID de Venta:</label>
        <input type="number" name="venta_id" required>
        <br>
        <button type="submit">Registrar Devolución</button>
    </form>
</body>
</html>

