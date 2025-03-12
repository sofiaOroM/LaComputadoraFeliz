<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultas</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.9.3/css/bulma.min.css">
</head>
<body>
    <section class="section">
        <div class="container">
            <h1 class="title">Consultas</h1>
            <form action="${pageContext.request.contextPath}/ConsultasVentasServlet" method="post">
                <div class="field">
                    <label class="label">Consulta</label>
                    <div class="control">
                        <div class="select">
                            <select name="consulta">
                                <option value="comprasCliente">Compras de Cliente en Intervalo de Tiempo</option>
                                <option value="devolucionesCliente">Devoluciones de Cliente en Intervalo de Tiempo</option>
                                <option value="computadorasDisponibles">Computadoras Disponibles en Sala de Ventas</option>
                                <option value="detallesFactura">Detalles de Factura</option>
                                <option value="ventasDelDia">Ventas del Día</option>
                            </select>
                        </div>
                    </div>
                </div>
                <c:if test="${not empty error}">
                    <div class="notification is-danger">
                        ${error}
                    </div>
                </c:if>
                <div class="field">
                    <label class="label">NIT</label>
                    <div class="control">
                        <input class="input" type="text" name="nit">
                    </div>
                </div>
                <div class="field">
                    <label class="label">Fecha de Inicio</label>
                    <div class="control">
                        <input class="input" type="date" name="fechaInicio">
                    </div>
                </div>
                <div class="field">
                    <label class="label">Fecha de Fin</label>
                    <div class="control">
                        <input class="input" type="date" name="fechaFin">
                    </div>
                </div>
                <div class="field">
                    <label class="label">ID de la Factura</label>
                    <div class="control">
                        <input class="input" type="text" name="facturaId">
                    </div>
                </div>
                <div class="field">
                    <div class="control">
                        <button class="button is-primary" type="submit">Consultar</button>
                    </div>
                </div>
            </form>
        </div>
    </section>
    <a href="panelVentas.jsp" class="button is-light">Volver al Panel</a>                                    
</body>
</html>


<%-- <!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultas</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.9.3/css/bulma.min.css">
</head>
<body>
    <section class="section">
        <div class="container">
            <h1 class="title">Consultas</h1>
            <form action="${pageContext.request.contextPath}/ConsultasVenstasServlet" method="post">
                <div class="field">
                    <label class="label">Consulta</label>
                    <div class="control">
                        <div class="select">
                            <select name="consulta">
                                <option value="comprasCliente">Compras de Cliente en Intervalo de Tiempo</option>
                                <option value="devolucionesCliente">Devoluciones de Cliente en Intervalo de Tiempo</option>
                                <option value="computadorasDisponibles">Computadoras Disponibles en Sala de Ventas</option>
                                <option value="detallesFactura">Detalles de Factura</option>
                                <option value="ventasDelDia">Ventas del Día</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="field">
                    <label class="label">NIT</label>
                    <div class="control">
                        <input class="input" type="text" name="nit" required>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Fecha de Inicio</label>
                    <div class="control">
                        <input class="input" type="date" name="fechaInicio" required>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Fecha de Fin</label>
                    <div class="control">
                        <input class="input" type="date" name="fechaFin" required>
                    </div>
                </div>
                <div class="field">
                    <div class="control">
                        <button class="button is-primary" type="submit">Consultar</button>
                    </div>
                </div>
            </form>
        </div>
    </section>
    <a href="panelVentas.jsp" class="button is-light">Volver al Panel</a>                

</body>
</html>
--%>
