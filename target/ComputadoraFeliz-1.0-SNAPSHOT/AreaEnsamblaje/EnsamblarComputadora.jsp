<%-- 
    Document   : EnsamblarComputadora
    Created on : 12/03/2025, 04:46:50
    Author     : sofia
--%>

<%@page import="java.util.Map"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <title>Ensamblaje de Computadoras</title>
</head>
<body>
    <section class="section">
        <div class="container">
            <h1 class="title">Ensamblaje de Computadoras</h1>
            
            <% String mensaje = (String) request.getAttribute("mensaje"); %>
            <% if (mensaje != null) { %>
                <div class="notification is-info">
                    <noscript>⚠️ <%= mensaje %></noscript>
                </div>
            <% } %>
            <% 
            List<Map<String, String>> computadoras = (List<Map<String, String>>) request.getAttribute("computadoras");
            if (computadoras == null){
                computadoras = new ArrayList<>();
                }
            %>

            <form action="${pageContext.request.contextPath}/EnsamblarComputadora" method="post">
                <div class="select is-fullwidth">
                    <select name="computadoraId" required>
                        <option value="" disabled selected>Selecciona una computadora</option>
                        <% 
                            for (Map<String, String> pc : computadoras) {
                        %>
                            <option value="<%= pc.get("id") %>"><%= pc.get("nombre") %> - Q<%= pc.get("precio") %></option>
                        <% } %>
                    </select>
                </div>
                <br>
                <button type="submit" class="button is-primary is-fullwidth">Ensamblar Computadora</button>
            </form>
        </div>
    </section>
    <a href="${pageContext.request.contextPath}/AreaEnsamblaje/panelEnsamblaje.jsp" class="button is-light">Volver</a>                    
</body>
</html>

