<%@ page import="DataBase.ConexionDB" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel Administración</title>
    <!-- Vinculación de Bulma (si no está referenciado externamente) -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 900px;
            margin: auto;
            padding: 30px;
            background: white;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            font-size: 2.5rem;
        }
        .menu {
            list-style: none;
            padding: 0;
        }
        .menu li {
            margin: 15px 0;
        }
        .menu a {
            display: block;
            padding: 15px;
            background: #007bff;
            color: white;
            text-decoration: none;
            text-align: center;
            border-radius: 5px;
            font-size: 1.2rem;
            transition: background-color 0.3s ease;
        }
        .menu a:hover {
            background: #0056b3;
        }
        .box {
            padding: 20px;
            background: #f9f9f9;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .box h2.subtitle {
            margin-bottom: 20px;
            font-size: 1.5rem;
            color: #4a4a4a;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Panel de Administración</h2>
        <ul class="menu">
            <li><a href="GestionUsuario.jsp">Gestion De Usuarios</a></li>
            <li><a href="CrearComponentes.jsp">Crear Componente</a></li>
            <li><a href="CrearComputadora.jsp">Crear Computadora</a></li>
            <li><a href="Reportes.jsp">Reportes</a></li>
        </ul>

        <!-- Accesos a otros paneles -->
        <div class="columns is-centered">
            <div class="column is-one-third">
                <div class="box has-text-centered">
                    <h2 class="subtitle">Acceso a Otras Áreas</h2>
                    <a href="${pageContext.request.contextPath}/AreaVentas/panelVentas.jsp" class="button is-success is-fullwidth is-large">Ir al Panel de Ventas</a>
                    <br><br>
                    <a href="${pageContext.request.contextPath}/AreaEnsamblaje/panelEnsamblaje.jsp" class="button is-warning is-fullwidth is-large">Ir al Panel de Ensamblaje</a>
                </div>
            </div>
        </div>

        <% String error = request.getParameter("error"); %>
        <% if (error != null) { %>
            <div class="notification is-danger"><%= error %></div>
        <% } %>

    </div>

</body>
</html>