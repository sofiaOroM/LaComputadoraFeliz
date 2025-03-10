<%-- 
    Document   : panel
    Created on : 3/03/2025, 11:04:07
    Author     : sofia
--%>

<%@ page import="com.mycompany.model.Usuario" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {   
        response.sendRedirect("IniciarSesion.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <header>
        <h1>Bienvenido, <%= usuario.getNombre() %></h1>
    </header>
    <nav>
        <a href="ventas.jsp">Ventas</a>
        <a href="devoluciones.jsp">Devoluciones</a>
        <a href="reportes.jsp">Reportes</a>
        <a href="usuarios.jsp">Usuarios</a>
        <a href="ensamblaje.jsp">Ensamblaje</a>
        <a href="LogoutServlet">Cerrar Sesión</a>
    </nav>
</body>
</html>