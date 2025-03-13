<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Crear Componente</title>
    <!-- Vinculación de Bulma (para mejorar el diseño) -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
</head>
<body>
    <div class="container">
        <h1 class="title">Crear Nuevo Componente</h1>
        <form action="${pageContext.request.contextPath}/CrearPiezaServlet" method="POST">
            
            <!-- Categoría -->
            <div class="field">
                <label class="label" for="categoria">Categoría</label>
                <div class="control">
                    <input class="input" type="text" id="categoria" name="categoria" required>
                </div>
            </div>

            <!-- Nombre -->
            <div class="field">
                <label class="label" for="nombre">Nombre del Componente</label>
                <div class="control">
                    <input class="input" type="text" id="nombre" name="nombre" required>
                </div>
            </div>

            <!-- Costo -->
            <div class="field">
                <label class="label" for="costo">Costo</label>
                <div class="control">
                    <input class="input" type="number" id="costo" name="costo" step="0.01" required>
                </div>
            </div>

            <!-- Cantidad -->
            <div class="field">
                <label class="label" for="cantidad">Cantidad</label>
                <div class="control">
                    <input class="input" type="number" id="cantidad" name="cantidad" required>
                </div>
            </div>

            <div class="field">
                <div class="control">
                    <button type="submit" class="button is-primary">Crear Componente</button>
                </div>
            </div>
        </form>

        <!-- Mostrar errores si ocurren -->
        <% String error = request.getAttribute("error") != null ? (String) request.getAttribute("error") : ""; %>
        <% if (!error.isEmpty()) { %>
            <div class="notification is-danger">
                <p><%= error %></p>
            </div>
        <% } %>
    </div>
</body>
</html>
