<%-- 
    Document   : CrearComponente
    Created on : 1/03/2025, 03:18:22
    Author     : sofia
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Crear Componente</title>
    <link href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css" rel="stylesheet">
</head>
<body>
    <section class="section">
        <div class="container">
            <h1 class="title">Registrar Nuevo Componente</h1>

            <form action="CrearComponenteServlet" method="POST">
                <div class="field">
                    <label class="label">Nombre del Componente</label>
                    <div class="control">
                        <input class="input" type="text" name="nombre" required>
                    </div>
                </div>

                <div class="field">
                    <label class="label">Categoría</label>
                    <div class="control">
                        <input class="input" type="text" name="categoria" required>
                    </div>
                </div>

                <div class="field">
                    <label class="label">Costo</label>
                    <div class="control">
                        <input class="input" type="number" step="0.01" name="costo" required>
                    </div>
                </div>

                <div class="field">
                    <label class="label">Cantidad</label>
                    <div class="control">
                        <input class="input" type="number" name="cantidad" required>
                    </div>
                </div>

                <div class="field">
                    <div class="control">
                        <button class="button is-link" type="submit">Guardar Componente</button>
                    </div>
                </div>
            </form>

            <a href="panelEnsamblaje.jsp" class="button is-light">Volver</a>
        </div>
    </section>
</body>
</html>