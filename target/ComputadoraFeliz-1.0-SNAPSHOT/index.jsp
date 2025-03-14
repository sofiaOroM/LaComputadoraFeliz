<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css" rel="stylesheet">
</head>
<body class="has-background-grey-lighter">
<%@ page import="DataBase.ConexionDB" %>
<%
    String estadoConexion = ConexionDB.verificarConexion();
%>

<pre>
    Estado de la conexión: <%= estadoConexion %>
</pre>

    <!-- Contenedor principal centrado -->
    <div class="columns is-centered is-vcentered is-fullheight">
        <div class="column is-half-tablet is-one-quarter-desktop">
            <div class="box has-background-white p-6">

                <!-- Título centrado con color -->
                <h2 class="title is-4 has-text-centered has-text-primary mb-6">Bienvenido de Nuevo</h2>

                <!-- Mostrar mensaje si hay un error -->
                <% 
                    String error = request.getParameter("error");
                    if (error != null) { 
                        String mensaje = "";
                        if (error.equals("credenciales_invalidas")) {
                            mensaje = "Usuario o contraseña incorrectos.";
                        } else if (error.equals("conexion_fallida")) {
                            mensaje = "Error: No se pudo conectar a la base de datos.";
                        } else if (error.equals("rol_desconocido")) {
                            mensaje = "Error: Rol de usuario desconocido.";
                        }
                %>
                <p class="has-text-danger has-text-centered"><%= mensaje %></p>
                <% } %>

                <!-- Formulario de inicio de sesión -->
                <form action="LoginServlet" method="POST">
                    <!-- Campo de nombre de usuario con icono -->
                    <div class="field">
                        <label class="label has-text-link" for="nombre">Nombre de Usuario</label>
                        <div class="control has-icons-left">
                            <input class="input is-rounded is-medium" type="text" id="nombre" name="nombre" required>
                            <span class="icon is-small is-left">
                                <i class="fas fa-user"></i>
                            </span>
                        </div>
                    </div>

                    <!-- Campo de contraseña con icono -->
                    <div class="field">
                        <label class="label has-text-link" for="password">Contraseña</label>
                        <div class="control has-icons-left">
                            <input class="input is-rounded is-medium" type="password" id="password" name="password" required>
                            <span class="icon is-small is-left">
                                <i class="fas fa-lock"></i>
                            </span>
                        </div>
                    </div>

                    <!-- Recordar usuario -->
                    <div class="field">
                        <div class="control">
                            <label class="checkbox">
                                <input type="checkbox">
                                Recordarme
                            </label>
                        </div>
                    </div>

                    <!-- Enlace para recuperar contraseña -->
                    <div class="field is-grouped is-grouped-right">
                        <p class="control">
                            <a href="#" class="has-text-link">¿Olvidaste tu contraseña?</a>
                        </p>
                    </div>

                    <!-- Botón de iniciar sesión -->
                    <div class="field">
                        <div class="control">
                            <button type="submit" class="button is-link is-fullwidth is-medium">Iniciar Sesión</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</body>
</html>