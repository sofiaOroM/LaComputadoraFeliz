<%-- 
    Document   : ventas
    Created on : 3/03/2025, 10:48:15
    Author     : sofia
--%>

<%@page import="java.sql.*, com.mycompany.model.Usuario"%>
<%--<%@ page import="java.sql.*, model.Usuario" %>--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null || !usuario.getRol().equals("ventas")) {
        response.sendRedirect("IniciarSesion.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Venta</title>
</head>
<body>
    <h2>Registrar Venta</h2>
    <form action="VentaServlet" method="POST">
        <label>Cliente (NIT o Nombre):</label>
        <input type="text" name="cliente" required>
        <br>
        <label>Computadora:</label>
        <input type="text" name="computadora" required>
        <br>
        <label>Cantidad:</label>
        <input type="number" name="cantidad" min="1" required>
        <br>
        <button type="submit">Registrar Venta</button>
    </form>
</body>
</html>
