<%-- 
    Document   : IniciarSesion
    Created on : 3/03/2025, 11:02:42
    Author     : sofia
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css" rel="stylesheet">
</head>
<body class="has-background-grey-lighter">
    <div class="columns is-centered is-vcentered is-fullheight">
        <div class="column is-half-tablet is-one-quarter-desktop">
            <div class="box has-background-white p-6">
                <h2 class="title is-4 has-text-centered mb-6" style="background-color: #4c1d95; color: white;">Iniciar Sesión</h2>
                
                <!-- Formulario que envía los datos al servlet "IniciarSesion" -->
                <form action="IniciarSesion" method="POST">
                    <div class="field">
                        <label class="label" for="nombre">Usuario</label>
                        <div class="control">
                            <input class="input is-rounded" type="text" name="nombre" id="nombre" required>
                        </div>
                    </div>
                    
                    <div class="field">
                        <label class="label" for="password">Contraseña</label>
                        <div class="control">
                            <input class="input is-rounded" type="password" name="password" id="password" required>
                        </div>
                    </div>

                    <!-- Botón de inicio de sesión -->
                    <div class="field">
                        <div class="control">
                            <button type="submit" class="button is-fullwidth is-medium" style="background-color: #4c1d95; color: white;">
                                Ingresar
                            </button>
                        </div>
                    </div>
                </form>
                
                <!-- Enlace para recuperar contraseña o ir al panel -->
                <div class="field">
                    <p class="has-text-centered">
                        <a href="panel.jsp" class="has-text-link">¿Olvidaste tu contraseña?</a>
                    </p>
                </div>
            </div>
        </div>
    </div>

    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</body>
</html>
