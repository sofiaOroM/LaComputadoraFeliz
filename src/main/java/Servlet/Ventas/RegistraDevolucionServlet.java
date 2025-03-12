/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet.Ventas;

import DataBase.ConexionDB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author sofia
 */


@WebServlet(name = "RegistrarDevolucionServlet", urlPatterns = {"/RegistrarDevolucionServlet"})
public class RegistraDevolucionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ventaId = request.getParameter("ventaId");
        String fechaDevolucionStr = request.getParameter("fechaDevolucion");

        response.setContentType("text/plain");

        try (PrintWriter writer = response.getWriter();
             Connection connection = ConexionDB.getConnection()) {

            // Obtener la información de la venta
            String ventaQuery = "SELECT v.id, v.fecha, c.id AS computadora_id, c.costoProduccion FROM venta v INNER JOIN computadora c ON v.computadora_id = c.id WHERE v.id = ?";
            try (PreparedStatement ventaStmt = connection.prepareStatement(ventaQuery)) {
                ventaStmt.setInt(1, Integer.parseInt(ventaId));

                try (ResultSet rs = ventaStmt.executeQuery()) {
                    if (rs.next()) {
                        Date fechaCompra = rs.getTimestamp("fecha");
                        int computadoraId = rs.getInt("computadora_id");
                        double costoProduccion = rs.getDouble("costoProduccion");                        

                        // Verificar que no haya pasado más de una semana desde la compra
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date fechaDevolucion = sdf.parse(fechaDevolucionStr);
                        long diffInMillies = Math.abs(fechaDevolucion.getTime() - fechaCompra.getTime());
                        long diff = diffInMillies / (1000 * 60 * 60 * 24);

                        if (diff > 7) {
                            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se puede realizar la devolución. Ha pasado más de una semana desde la compra.");
                            return;
                        }

                        // Calculo de la pérdida
                        double perdida = costoProduccion / 3;

                        // Registrar la devolución
                        String devolucionQuery = "INSERT INTO devolucion (venta_id, fecha, perdida) VALUES (?, ?, ?)";
                        try (PreparedStatement devolucionStmt = connection.prepareStatement(devolucionQuery)) {
                            devolucionStmt.setInt(1, Integer.parseInt(ventaId));
                            devolucionStmt.setString(2, sdf.format(fechaDevolucion));
                            devolucionStmt.setDouble(3, perdida);
                            devolucionStmt.executeUpdate();
                        }
                        
                        // Actualizar el inventario de computadoras
                        String actualizarInventarioQuery = "UPDATE computadora SET cantidad = cantidad + 1 WHERE id = ?";
                        try (PreparedStatement actualizarInventarioStmt = connection.prepareStatement(actualizarInventarioQuery)) {
                            actualizarInventarioStmt.setInt(1, computadoraId);
                            actualizarInventarioStmt.executeUpdate();
                        }

                        writer.println("Devolución registrada exitosamente.");
                    } else {
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Factura de venta no encontrada.");
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Fecha de devolución inválida.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al registrar la devolución.");
        }
    }
}
