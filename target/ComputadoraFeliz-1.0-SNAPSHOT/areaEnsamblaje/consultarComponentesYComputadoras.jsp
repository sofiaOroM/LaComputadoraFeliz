<%-- 
    Document   : consultarComponentesYComputadoras
    Created on : 10/03/2025, 04:02:19
    Author     : sofia
--%>

<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Consultar Componentes y Computadoras</title>
    <link href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css" rel="stylesheet">
</head>
<body>
    <section class="section">
        <div class="container">
            <h1 class="title">Consulta de Componentes y Computadoras Ensambladas</h1>

            <!-- Consultar Componentes -->
            <h2 class="subtitle">Componentes</h2>
            <div class="field">
                <div class="control">
                    <a href="ConsultarComponentesServlet?ordenarPor=asc" class="button is-link">Ordenar Componentes Ascendente</a>
                    <a href="ConsultarComponentesServlet?ordenarPor=desc" class="button is-link">Ordenar Componentes Descendente</a>
                </div>
            </div>
            <table class="table is-striped">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Categoría</th>
                        <th>Costo</th>
                        <th>Cantidad</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        List<String[]> componentes = (List<String[]>) request.getAttribute("componentes");
                        for (String[] componente : componentes) {
                            out.print("<tr>");
                            out.print("<td>" + componente[1] + "</td>");
                            out.print("<td>" + componente[2] + "</td>");
                            out.print("<td>" + componente[3] + "</td>");
                            out.print("<td>" + componente[4] + "</td>");
                            out.print("</tr>");
                        }
                    %>
                </tbody>
            </table>

            <!-- Consultar Computadoras Ensambladas -->
            <h2 class="subtitle">Computadoras Ensambladas</h2>
            <div class="field">
                <div class="control">
                    <a href="ConsultarComputadorasServlet?ordenarPor=asc" class="button is-link">Ordenar Computadoras Ascendente</a>
                    <a href="ConsultarComputadorasServlet?ordenarPor=desc" class="button is-link">Ordenar Computadoras Descendente</a>
                </div>
            </div>
            <table class="table is-striped">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Fecha de Ensamble</th>
                        <th>Costo</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        List<String[]> computadoras = (List<String[]>) request.getAttribute("computadoras");
                        for (String[] computadora : computadoras) {
                            out.print("<tr>");
                            out.print("<td>" + computadora[1] + "</td>");
                            out.print("<td>" + computadora[2] + "</td>");
                            out.print("<td>" + computadora[3] + "</td>");
                            out.print("</tr>");
                        }
                    %>
                </tbody>
            </table>

            <a href="panelEnsamblaje.jsp" class="button is-light">Volver</a>
        </div>
    </section>
</body>
</html>

