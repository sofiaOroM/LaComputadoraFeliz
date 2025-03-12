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
            <h1 class="title has-text-centered">Gestión de Componentes</h1>

            <% String mensaje = (String) request.getAttribute("mensaje"); %>
            <% if (mensaje != null) { %>
                <div class="notification is-info">
                    <button class="delete"></button>
                    <%= mensaje %>
                </div>
            <% } %>

            <% List<String[]> componentes = (List<String[]>) request.getAttribute("componentes"); 
                if (componentes == null) {
                componentes = new ArrayList<>();
            }
            %>
            <div class="columns">
                <!-- Formulario para Crear Componente -->
                <div class="column">
                    <div class="box">
                        <h2 class="subtitle">Registra Nuevo Componente</h2>
                        <form action="${pageContext.request.contextPath}/ModificarComponenteServlet" method="post">
                            <input type="hidden" name="accion" value="crear">
                            <div class="field">
                                <label class="label">Nombre</label>
                                <div class="control">
                                    <input class="input" type="text" name="nombre" required>
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Cantidad</label>
                                <div class="control">
                                    <input class="input" type="number" name="cantidad" required>
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Costo</label>
                                <div class="control">
                                    <input class="input" type="number" step="0.01" name="costo" required>
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Categoría</label>
                                <div class="control">
                                    <input class="input" type="text" name="categoria" required>
                                </div>
                            </div>
                            <button class="button is-primary is-fullwidth">Crear</button>
                        </form>
                    </div>
                </div>

                <!-- Formulario para Modificar Componente -->
                <div class="column">
                    <div class="box">
                        <h2 class="subtitle">Modificar Componente</h2>
                        <form action="${pageContext.request.contextPath}/ModificarComponenteServlet" method="post">
                            <input type="hidden" name="accion" value="modificar">
                            <div class="field">
                                <label class="label">Seleccionar Componente</label>
                                <div class="control">
                                    <div class="select is-fullwidth">
                                        <select name="id" required>
                                            <% for (String[] c : componentes) { %>
                                                <option value="<%= c[0] %>"><%= c[1] %> (Cantidad: <%= c[2] %>)</option>
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
                                <label class="label">Cantidad</label>
                                <input class="input" type="number" name="cantidad" required>
                            </div>
                            <div class="field">
                                <label class="label">Costo</label>
                                <input class="input" type="number" step="0.01" name="costo" required>
                            </div>
                            <div class="field">
                                <label class="label">Categoría</label>
                                <input class="input" type="text" name="categoria" required>
                            </div>
                            <button class="button is-warning is-fullwidth">Modificar</button>
                        </form>
                    </div>
                </div>

                <!-- Formulario para Eliminar Componente -->
                <div class="column">
                    <div class="box">
                        <h2 class="subtitle">Eliminar Componente</h2>
                        <form action="${pageContext.request.contextPath}/ModificarComponenteServlet" method="post">
                            <input type="hidden" name="accion" value="eliminar">
                            <div class="field">
                                <label class="label">Seleccionar Componente</label>
                                <div class="select is-fullwidth">
                                    <select name="id" required>
                                        <% for (String[] c : componentes) { %>
                                            <option value="<%= c[0] %>                                        <option value="<%= c[0] %>"><%= c[1] %> (Cantidad: <%= c[2] %>)</option>
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

            <% if (componentes.isEmpty()) { %>
                <div class="notification is-warning has-text-centered">
                    No hay componentes registrados en el sistema.
                </div>
            <% } %>
        </div>
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
    <a href="${pageContext.request.contextPath}/AreaEnsamblaje/panelEnsamblaje.jsp" class="button is-light">Volver</a>
</body>
