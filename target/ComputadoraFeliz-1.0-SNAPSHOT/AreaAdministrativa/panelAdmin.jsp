<%-- 
    Document   : administrador
    Created on : 9/03/2025, 15:58:46
    Author     : sofia
--%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panel Administración</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 600px;
            margin: auto;
            padding: 20px;
            background: white;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
        }
        .menu {
            list-style: none;
            padding: 0;
        }
        .menu li {
            margin: 10px 0;
        }
        .menu a {
            display: block;
            padding: 10px;
            background: #007bff;
            color: white;
            text-decoration: none;
            text-align: center;
            border-radius: 5px;
        }
        .menu a:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
    <%@ page import="DataBase.ConexionDB" %>
        <%
            String estadoConexion = ConexionDB.verificarConexion();
        %>

        <pre>
            Estado de la conexión: <%= estadoConexion %>
        </pre>
    <div class="container">
        <h2>Panel de Administración</h2>
        <ul class="menu">
            <li><a href="GestionUsuario.jsp">Gestion De Usuarios</a></li>
            <li><a href="CrearComponentes.jsp">Crear Componente</a></li>
            <li><a href="CrearComputadora.jsp">Crear Computadora</a></li>
            <li><a href="Reportes.jsp">Reportes</a></li>
        </ul>
    </div>
<% String error = request.getParameter("error"); %>
<% if (error != null) { %>
    <div class="notification is-danger"><%= error %></div>
<% } %>
</body>
</html>