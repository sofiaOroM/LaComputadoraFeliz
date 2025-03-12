<%-- 
    Document   : CrearComponentes
    Created on : 11/03/2025, 18:32:46
    Author     : sofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manejo Componentes</title>
    </head>
    <body>
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
    </body>
</html>
