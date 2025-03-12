@WebSe<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Devolución</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.9.3/css/bulma.min.css">
</head>
<body>
    <section class="section">
        <div class="container">
            <h1 class="title">Registrar Devolución</h1>
            <form action="${pageContext.request.contextPath}/RegistrarDevolucionServlet" method="post">
                <div class="field">
                    <label class="label">ID de la Venta (Factura)</label>
                    <div class="control">
                        <input class="input" type="number" name="ventaId" required>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Fecha de Devolución</label>
                    <div class="control">
                        <input class="input" type="date" name="fechaDevolucion" required>
                    </div>
                </div>
                <div class="field">
                    <div class="control">
                        <button class="button is-primary" type="submit">Registrar Devolución</button>
                    </div>
                </div>
            </form>
        </div>
    </section>
    <a href="panelVentas.jsp" class="button is-light">Volver al Panel</a>                
</body>
</html>
