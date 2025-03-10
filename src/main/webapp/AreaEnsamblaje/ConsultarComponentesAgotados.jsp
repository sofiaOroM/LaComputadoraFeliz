<%-- 
    Document   : ConsultarComponentesAgotados
    Created on : 10/03/2025, 04:03:31
    Author     : sofia
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Componentes Agotados o a Punto de Agotarse</title>
    <link href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css" rel="stylesheet">
</head>
<body>
    <section class="section">
        <div class="container">
            <h1 class="title">Componentes Agotados o a Punto de Agotarse</h1>

            <table class="table is-striped">
                <thead>
                    <tr>
                        <th>Componente</th>
                        <th>Cantidad Restante</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        List<String[]> componentes = (List<String[]>) request.getAttribute("componentesAgotados");
                        for (String[] componente : componentes) {
                            out.print("<tr>");
                            out.print("<td>" + componente[0] + "</td>");
                            out.print("<td>" + componente[1] + "</td>");
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

