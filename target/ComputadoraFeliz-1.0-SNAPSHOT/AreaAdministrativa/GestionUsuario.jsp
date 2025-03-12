<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <title>Gestión de Componentes</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
</head>
<body>

    <section class="section">
        <div class="container">
            <h1 class="title has-text-centered">Gestión de Uusuarios</h1>

            <% String mensaje = (String) request.getAttribute("mensaje"); %>
            <% if (mensaje != null) { %>
                <div class="notification is-info">
                    <button class="delete"></button>
                    <%= mensaje %>
                </div>
            <% } %>

            <% List<String[]> usuarios = (List<String[]>) request.getAttribute("usuarios"); 
                if (usuarios == null) {
                usuarios = new ArrayList<>();
            }
            %>
            <div class="columns">
                <!-- Formulario para Crear Componente -->
                <div class="column">
                    <div class="box">
                        <h2 class="subtitle">Registra Nuevo Usuario</h2>
                        <form action="${pageContext.request.contextPath}/GestionUsuario" method="post">
                            <input type="hidden" name="accion" value="crear">
                            <div class="field">
                                <label class="label">Nombre</label>
                                <div class="control">
                                    <input class="input" type="text" name="nombre" required>
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Password</label>
                                <div class="control">
                                    <input class="input" type="text" name="password" required>
                                </div>
                            </div>
                            
                            <div class="field">
                                <label class="label">Rol</label>
                                <div class="control">
                                    <div class="select is-fullwidth">
                                        <select name="rol" required>
                                            <option value="ensamblaje">Ensamblaje</option>
                                            <option value="ventas">Ventas</option>
                                            <option value="admin">Administrador</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="field">
                                <label class="label">Correo Electronico</label>
                                <div class="control">
                                    <input class="input" type="text" name="correo_electronico" required>
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Dirección</label>
                                <div class="control">
                                    <input class="input" type="text" name="direccion" required>
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Celular</label>
                                <div class="control">
                                    <input class="input" type="text" name="celular" required>
                                </div>
                            </div>
                            <button class="button is-primary is-fullwidth">Crear</button>
                        </form>
                    </div>
                </div>

                <!-- Formulario para Modificar Componente -->
                <div class="column">
                    <div class="box">
                        <h2 class="subtitle">Modificar Usuario</h2>
                        <form action="${pageContext.request.contextPath}/GestionUsuario" method="post">
                            <input type="hidden" name="accion" value="modificar">
                            <div class="field">
                                <label class="label">Seleccionar Usuario</label>
                                <div class="control">
                                    <div class="select is-fullwidth">
                                        <select name="id" required>
                                            <% for (String[] u : usuarios) { %>
                                                <option value="<%= u[0] %>"><%= u[1] %> (Rol: <%= u[3] %>)</option>
                                            <% } %>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Nombre</label>
                                <input class="input" type="text" name="nombre" required>
                            </div>
                            <div class="field">
                                <label class="label">Password</label>
                                <div class="control">
                                    <input class="input" type="text" name="password" required>
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Rol</label>
                                <div class="control">
                                    <div class="select is-fullwidth">
                                        <select name="rol" required>
                                            <option value="ensamblaje">Ensamblaje</option>
                                            <option value="ventas">Ventas</option>
                                            <option value="admin">Administrador</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Correo Electronico</label>
                                <div class="control">
                                    <input class="input" type="email" name="correo_electronico" required>
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Dirección</label>
                                <div class="control">
                                    <input class="input" type="text" name="direccion" required>
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Celular</label>
                                <div class="control">
                                    <input class="input" type="tel" name="celular" required>
                                </div>
                            </div>
                            <button class="button is-warning is-fullwidth">Modificar</button>
                        </form>
                    </div>
                </div>

                <!-- Formulario para Eliminar Componente -->
                <div class="column">
                    <div class="box">
                        <h2 class="subtitle">Eliminar Componente</h2>
                        <form action="${pageContext.request.contextPath}/GestionUsuario" method="post">
                            <input type="hidden" name="accion" value="eliminar">
                            <div class="field">
                                <label class="label">Seleccionar Usuario</label>
                                <div class="select is-fullwidth">
                                    <select name="id" required>
                                        <% for (String[] u : usuarios) { %>
                                            <option value="<%= u[0] %>"><%= u[1] %> (Rol: <%= u[3] %>)</option>
                                        <% } %>
                                    </select>
                                </div>
                            </div>
                            <div class="control">
                                <button class="button is-danger is-fullwidth">Eliminar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div> <!-- Fin de las columnas -->

            <% if (usuarios.isEmpty()) { %>
                <div class="notification is-warning has-text-centered">
                    No hay componentes registrados en el sistema.
                </div>
            <% } %>
        </div>
        <a href="panelAdmin.jsp" class="button is-light">Volver al Panel</a>
    </section>

    <script>
        // Funcionalidad para cerrar las notificaciones al hacer clic
        document.addEventListener("DOMContentLoaded", function() {
            document.querySelectorAll(".notification .delete").forEach(button => {
                button.addEventListener("click", function() {
                    this.parentElement.style.display = "none";
                });
            });
        });
    </script>
</body>
</html>