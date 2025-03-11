<%-- 
    Document   : VerificarComponentes
    Created on : 10/03/2025, 12:11:05
    Author     : sofia
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Verificación de Componentes</title>
    <link href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css" rel="stylesheet">
    <style>
        body { font-family: Arial, sans-serif; text-align: center; }
        table { width: 80%; margin: 20px auto; border-collapse: collapse; }
        th, td { border: 1px solid black; padding: 8px; text-align: center; }
        th { background-color: #4CAF50; color: white; }
        .agotado { background-color: #ff4c4c; color: white; }
        .punto-agotarse { background-color: #ffb84c; }
    </style>
</head>
<body>
    <pre>
        Componentes Agotados: <%= request.getAttribute("componentesAgotados") %>
        Componentes por Agotarse: <%= request.getAttribute("componentesPuntoDeAgotarse") %>
    </pre>
    <h2>Componentes Agotados</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
        </tr>
        <%
            List<String[]> componentesAgotados = (List<String[]>) request.getAttribute("componentesAgotados");
            if (componentesAgotados != null && !componentesAgotados.isEmpty()) {
                for (String[] componente : componentesAgotados) {
        %>
                    <tr class="agotado">
                        <td><%= componente[0] %></td>
                        <td><%= componente[1] %></td>
                    </tr>
        <%
                }
            } else {
        %>
                <tr><td colspan="2">No hay componentes agotados.</td></tr>
        <%
            }
        %>
    </table>

    <h2>Componentes por Agotarse</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Cantidad</th>
        </tr>
        <%
            List<String[]> componentesPuntoDeAgotarse = (List<String[]>) request.getAttribute("componentesPuntoDeAgotarse");
            if (componentesPuntoDeAgotarse != null && !componentesPuntoDeAgotarse.isEmpty()) {
                for (String[] componente : componentesPuntoDeAgotarse) {
        %>
                    <tr class="punto-agotarse">
                        <td><%= componente[0] %></td>
                        <td><%= componente[1] %></td>
                        <td><%= componente[2] %></td>
                    </tr>
        <%
                }
            } else {
        %>
                <tr><td colspan="3">No hay componentes en riesgo de agotarse.</td></tr>
        <%
            }
        %>
    </table>

    <a href="panelEnsamblaje.jsp">Volver al menú</a>
</body>
</html>

