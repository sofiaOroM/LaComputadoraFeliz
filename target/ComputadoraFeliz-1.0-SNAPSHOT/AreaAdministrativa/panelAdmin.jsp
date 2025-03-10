<%-- 
    Document   : administrador
    Created on : 9/03/2025, 15:58:46
    Author     : sofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css" rel="stylesheet">        
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
        
        <form action="CrearPiezaServlet" method="POST">
            <!-- Nombre de Pieza -->
            <div class="field">
                <label class="label" for="nombrePieza">Nombre de Pieza</label>
                <div class="control">
                    <input class="input" type="text" id="nombrePieza" name="nombrePieza" required>
                </div>
            </div>

            <!-- Costo de Pieza -->
            <div class="field">
                <label class="label" for="costoPieza">Costo de Pieza</label>
                <div class="control">
                    <input class="input" type="number" id="costoPieza" name="costoPieza" required>
                </div>
            </div>

            <div class="field">
                <div class="control">
                    <button type="submit" class="button is-link">Crear Pieza</button>
                </div>
            </div>
        </form>
        
        <form action="EliminarUsuarioServlet" method="POST">
            <!-- Nombre de Usuario a Eliminar -->
            <div class="field">
                <label class="label" for="nombreEliminar">Nombre de Usuario</label>
                <div class="control">
                    <input class="input" type="text" id="nombreEliminar" name="nombreEliminar" required>
                </div>
            </div>

            <div class="field">
                <div class="control">
                    <button type="submit" class="button is-danger">Eliminar Usuario</button>
                </div>
            </div>
        </form>


    </body>
</html>
