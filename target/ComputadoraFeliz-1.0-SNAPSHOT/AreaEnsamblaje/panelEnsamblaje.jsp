
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession sesion = request.getSession(false);
    String rol = (sesion != null) ? (String) sesion.getAttribute("rol") : null;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panel de Ensamblaje</title>
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

    <div class="container">
        <h2>Panel de Ensamblaje</h2>
            
        <%-- Mostrar el botón solo si el usuario es admin --%>
            <% if ("admin".equals(rol)) { %>
                <a href="${pageContext.request.contextPath}/AreaAdministrativa/panelAdmin.jsp" class="button is-light">Volver al Panel de Administración</a>
            <% } %>
        <ul class="menu">
            <li><a href="VerificarComponentes.jsp">Consultar Componentes Agotados</a></li>
            <li><a href="ModificarComponente.jsp">Gestion De Componentes</a></li>
            <li><a href="EnsamblarComputadora.jsp">Ensamblar Computadoras</a></li>
            <li><a href="consultarComponentesYComputadoras.jsp">Consultar Componentes y Computadoras</a></li>
        </ul>
    </div>
<% String error = request.getParameter("error"); %>
<% if (error != null) { %>
    <div class="notification is-danger"><%= error %></div>
<% } %>
</body>
</html>
