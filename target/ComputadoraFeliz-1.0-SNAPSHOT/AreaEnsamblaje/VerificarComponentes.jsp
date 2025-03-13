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
        th { background-color: #0056b3; color: #f4f4f4; }
        .agotado { background-color: #f4f4f4; color: white; }
        .punto-agotarse { background-color: #f4f4f4; }
    </style>
</head>
<body>
    <%--<jsp:include page="/VerificarComponentesServlet"></jsp:include>
    --%>
    
    <section class="section">
        <div class="container">
            <h1 class="title">Verificación de Componentes</h1>
            <form action="${pageContext.request.contextPath}/VerificarComponentesServlet" method="get">
                <div class="field">
                    <label class="label">Ordenar por Cantidad</label>
                    <div class="control">
                        <div class="select">
                            <select name="orden">
                                <option value="asc">Menor a Mayor</option>
                                <option value="desc">Mayor a Menor</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="field">
                    <div class="control">
                        <button class="button is-primary" type="submit">Ordenar</button>
                    </div>
                </div>
            </form>
        </div>
    </section>
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

    <a href="${pageContext.request.contextPath}/AreaEnsamblaje/panelEnsamblaje.jsp">Volver al menú</a>
</body>
</html>

