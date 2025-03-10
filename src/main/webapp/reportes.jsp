<%-- 
    Document   : reportes
    Created on : 3/03/2025, 10:47:35
    Author     : sofia
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Generar Reportes Financieros</title>
</head>
<body>
    <h2>Generar Reportes</h2>
    <form action="ReporteServlet" method="GET">
        <label>Fecha Inicio:</label>
        <input type="date" name="fechaInicio" required>
        <br>
        <label>Fecha Fin:</label>
        <input type="date" name="fechaFin" required>
        <br>
        <button type="submit">Descargar Reporte CSV</button>
        <button type="submit" formaction="ReportePDFServlet">Descargar Reporte PDF</button>
    </form>
</body>
</html>

