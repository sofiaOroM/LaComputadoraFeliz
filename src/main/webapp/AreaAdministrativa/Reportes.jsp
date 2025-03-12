<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Generar Reporte</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.9.3/css/bulma.min.css">
</head>
<body>
    <section class="section">
        <div class="container">
            <h1 class="title">Generar Reporte</h1>
            <form action="${pageContext.request.contextPath}/GenerarReportesServlet" method="get">
                <div class="field">
                    <label class="label">Tipo de Reporte</label>
                    <div class="control">
                        <div class="select">
                            <select name="reporteTipo">
                                <option value="ventas">Ventas</option>
                                <option value="devoluciones">Devoluciones</option>
                                <option value="ganancias">Ganancias</option>
                                <option value="usuarioMasVentas">Usuario con más ventas</option>
                                <option value="usuarioMasGanancias">Usuario con más ganancias</option>
                                <option value="computadoraMasVendida">Computadora más vendida</option>
                                <option value="computadoraMenosVendida">Computadora menos vendida</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="field">
                    <label class="label">Fecha de Inicio</label>
                    <div class="control">
                        <input class="input" type="text" name="fechaInicio" placeholder="dd/mm/yyyy" required>
                    </div>
                </div>

                <div class="field">
                    <label class="label">Fecha de Fin</label>
                    <div class="control">
                        <input class="input" type="text" name="fechaFin" placeholder="dd/mm/yyyy" required>
                    </div>
                </div>

                <div class="field">
                    <div class="control">
                        <button class="button is-primary" type="submit">Generar Reporte</button>
                    </div>
                </div>
            </form>
        </div>
    </section>
    <a href="panelAdmin.jsp" class="button is-light">Volver al Panel</a>
</body>
</html>
