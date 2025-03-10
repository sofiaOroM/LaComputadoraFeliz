<%-- 
    Document   : usuarios
    Created on : 3/03/2025, 10:48:53
    Author     : sofia
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Administrar Usuarios</title>
</head>
<body>
    <h2>Administrar Usuarios</h2>
    <form action="UsuarioServlet" method="POST">
        <label>Nombre:</label>
        <input type="text" name="nombre" required>
        <br>
        <label>Contraseña:</label>
        <input type="password" name="password" required>
        <br>
        <label>Rol:</label>
        <select name="rol">
            <option value="ensamblaje">Ensamblaje</option>
            <option value="ventas">Ventas</option>
            <option value="admin">Administrador</option>
        </select>
        <br>
        <button type="submit">Agregar Usuario</button>
    </form>
</body>
</html>