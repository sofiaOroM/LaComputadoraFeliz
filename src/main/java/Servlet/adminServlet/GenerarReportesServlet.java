/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet.adminServlet;

import DataBase.ConexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 *
 * @author sofia
 */
@WebServlet(name = "GenerarReportesServlet", urlPatterns = {"/GenerarReportesServlet"})
public class GenerarReportesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String reporteTipo = request.getParameter("reporteTipo");
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");

        if (reporteTipo == null || fechaInicio == null || fechaFin == null || !isValidDate(fechaInicio) || !isValidDate(fechaFin)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parámetros inválidos");
            return;
        }

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + reporteTipo + ".csv\"");

        try (PrintWriter writer = response.getWriter();
             Connection connection = ConexionDB.getConnection()) {

            writer.println("Reporte de " + reporteTipo);
            writer.println("Fecha de inicio: " + fechaInicio);
            writer.println("Fecha de fin: " + fechaFin);
            writer.println("");

            String query = getQuery(reporteTipo);
            if (query == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tipo de reporte no válido");
                return;
            }

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, convertToSqlDate(fechaInicio));
                statement.setString(2, convertToSqlDate(fechaFin));

                try (ResultSet resultSet = statement.executeQuery();
                     CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(resultSet))) {

                    while (resultSet.next()) {
                        csvPrinter.printRecord(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
                    }

                    if (reporteTipo.equals("computadoraMasVendida") || reporteTipo.equals("computadoraMenosVendida")) {
                        if (resultSet.next()) {
                            String computadoraId = resultSet.getString("computadora_id");
                            query = "SELECT * FROM ventas WHERE computadora_id = ? AND fecha >= ? AND fecha <= ?";
                            try (PreparedStatement detailStatement = connection.prepareStatement(query)) {
                                detailStatement.setString(1, computadoraId);
                                detailStatement.setString(2, convertToSqlDate(fechaInicio));
                                detailStatement.setString(3, convertToSqlDate(fechaFin));

                                try (ResultSet detailResultSet = detailStatement.executeQuery()) {
                                    csvPrinter.printRecord("");
                                    csvPrinter.printRecord("Detalle de las ventas:");

                                    while (detailResultSet.next()) {
                                        csvPrinter.printRecord(detailResultSet.getString(1), detailResultSet.getString(2), detailResultSet.getString(3));
                                    }
                                }
                            } catch (ParseException ex) {
                                Logger.getLogger(GenerarReportesServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            } catch (ParseException ex) {
                Logger.getLogger(GenerarReportesServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al acceder a la base de datos");
        }
    }

    private String getQuery(String reporteTipo) {
        switch (reporteTipo) {
            case "ventas":
                return "SELECT v.id, v.cliente, v.total, v.fecha FROM venta v WHERE v.fecha >= ? AND v.fecha <= ?";            
            case "devoluciones":
                return "SELECT d.id, d.venta_id, d.fecha, d.perdida FROM devolucion d WHERE d.fecha >= ? AND d.fecha <= ?";
            case "ganancias":
                return "SELECT SUM(v.total - (c.costoProduccion * v.cantidad)) AS total_ganancia FROM venta v INNER JOIN computadora c ON v.computadora_id = c.id WHERE v.fecha >= ? AND v.fecha <= ?";
            case "usuarioMasVentas":
                return "SELECT v.usuario, COUNT(*) AS total_ventas FROM venta v WHERE v.fecha >= ? AND v.fecha <= ? GROUP BY v.usuario ORDER BY total_ventas DESC LIMIT 1";
            case "usuarioMasGanancias":
                return "SELECT v.usuario, SUM(v.total - (c.costoProduccion * v.cantidad)) AS total_ganancia FROM venta v INNER JOIN computadora c ON v.computadora_id = c.id WHERE v.fecha >= ? AND v.fecha <= ? GROUP BY v.usuario ORDER BY total_ganancia DESC LIMIT 1";
            case "computadoraMasVendida":
                return "SELECT v.computadora_id, COUNT(*) AS total_ventas FROM venta v WHERE v.fecha >= ? AND v.fecha <= ? GROUP BY v.computadora_id ORDER BY total_ventas DESC LIMIT 1";
            case "computadoraMenosVendida":
                return "SELECT v.computadora_id, COUNT(*) AS total_ventas FROM venta v WHERE v.fecha >= ? AND v.fecha <= ? GROUP BY v.computadora_id ORDER BY total_ventas ASC LIMIT 1";
            default:
                return null;
        }
    }

    private boolean isValidDate(String dateStr) {
        try {
            new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private String convertToSqlDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = sdf.parse(dateStr);
        sdf.applyPattern("yyyy-MM-dd");
        return sdf.format(date);
    }
}

