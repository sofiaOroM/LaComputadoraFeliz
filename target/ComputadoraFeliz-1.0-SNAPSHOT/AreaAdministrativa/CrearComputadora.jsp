<%-- 
    Document   : CrearComputadora
    Created on : 11/03/2025, 18:31:15
    Author     : sofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Computadora</title>
    </head>
    <body>
        <form action="CrearComputadoraServlet" method="POST">
            <!-- Nombre de Computadora -->
            <div class="field">
                <label class="label" for="nombreComputadora">Nombre de Computadora</label>
                <div class="control">
                    <input class="input" type="text" id="nombreComputadora" name="nombreComputadora" required>
                </div>
            </div>

            <!-- Precio de Computadora -->
            <div class="field">
                <label class="label" for="precio">Precio de Computadora</label>
                <div class="control">
                    <input class="input" type="number" id="precio" name="precio" required>
                </div>
            </div>

            <div class="field">
                <div class="control">
                    <button type="submit" class="button is-link">Crear Computadora</button>
                </div>
            </div>
        </form>
    </body>
</html>
