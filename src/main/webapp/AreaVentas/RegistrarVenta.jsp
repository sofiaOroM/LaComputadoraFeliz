<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Venta</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.9.3/css/bulma.min.css">
    <script>
        function buscarCliente() {
            var nit = document.getElementById("nit").value;
            if (nit === "") {
                return;
            }

            var xhr = new XMLHttpRequest();
            xhr.open("GET", "BuscarCliente?nit=" + nit, true);
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var cliente = JSON.parse(xhr.responseText);
                    if (cliente) {
                        document.getElementById("nombre").value = cliente.nombre;
                        document.getElementById("telefono").value = cliente.telefono;
                        document.getElementById("direccion").value = cliente.direccion;
                        document.getElementById("correoElectronico").value = cliente.correo_electronico;
                    } else {
                        document.getElementById("nombre").value = "";
                        document.getElementById("telefono").value = "";
                        document.getElementById("direccion").value = "";
                        document.getElementById("correoElectronico").value = "";
                    }
                }
            };
            xhr.send();
        }
    </script>
</head> 
<body>
    <section class="section">
                <!-- Formulario para buscar cliente -->
        <form action="${pageContext.request.contextPath}/RegistrarVentaServlet" method="get">
            <div class="field">
                <label class="label">NIT</label>
                <div class="control">
                    <input class="input" type="text" name="nit" value="<%= request.getParameter("nit") != null ? request.getParameter("nit") : "" %>" required>
                </div>
            </div>
            <div class="field">
                <div class="control">
                    <button class="button is-info" type="submit">Buscar Cliente</button>
                </div>
            </div>
        </form>

        <div class="container">
            <h1 class="title">Registrar Venta</h1>
            <form action="${pageContext.request.contextPath}/RegistrarVentaServlet" method="post">
                <div class="field">
                    <label class="label">NIT</label>
                    <div class="control">
                        <input class="input" type="text" id="nit" name="nit" onkeyup="buscarCliente()" required>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Nombre del Cliente</label>
                    <div class="control">
                        <input class="input" type="text" id="nombre" name="nombre" required>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Teléfono</label>
                    <div class="control">
                        <input class="input" type="text" id="telefono" name="telefono" required>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Dirección</label>
                    <div class="control">
                        <input class="input" type="text" id="direccion" name="direccion" required>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Correo Electrónico</label>
                    <div class="control">
                        <input class="input" type="email" id="correoElectronico" name="correoElectronico" required>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Total</label>
                    <div class="control">
                        <input class="input" type="number" step="0.01" name="total" required>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Fecha</label>
                    <div class="control">
                        <input class="input" type="datetime-local" name="fecha" required>
                    </div>
                </div>
                <div class="field">
                    <label class="label">ID de la Computadora</label>
                    <div class="control">
                        <input class="input" type="number" name="computadoraId" required>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Cantidad</label>
                    <div class="control">
                        <input class="input" type="number" name="cantidad" required>
                    </div>
                </div>
                <div class="field">
                    <div class="control">
                        <button class="button is-primary" type="submit">Registrar Venta</button>
                    </div>
                </div>
            </form>
        </div>
    </section>
    <a href="${pageContext.request.contextPath}/panelVentas.jsp" class="button is-light">Volver al Panel</a>
</body>
</html>
                
    