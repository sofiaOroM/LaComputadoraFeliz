package Servlet.Ventas;


import DataBase.ConexionDB;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegistrarVentaServlet", urlPatterns = {"/RegistrarVentaServlet"})
public class RegistrarVentaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nit = request.getParameter("nit");
        String total = request.getParameter("total");
        String fecha = request.getParameter("fecha");
        String computadoraId = request.getParameter("computadoraId");
        String cantidad = request.getParameter("cantidad");

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=factura.pdf");

        try (Connection connection = ConexionDB.getConnection()) {
            // Verificar si el cliente ya existe y obtener sus datos
            String selectClienteQuery = "SELECT id, nombre, telefono, direccion, correo_electronico FROM cliente WHERE nit = ?";
            int clienteId = -1;
            String nombre = "", telefono = "", direccion = "", correoElectronico = "";

            try (PreparedStatement selectClienteStmt = connection.prepareStatement(selectClienteQuery)) {
                selectClienteStmt.setString(1, nit);

                try (ResultSet rs = selectClienteStmt.executeQuery()) {
                    if (rs.next()) {
                        // Cliente existente
                        clienteId = rs.getInt("id");
                        nombre = rs.getString("nombre");
                        telefono = rs.getString("telefono");
                        direccion = rs.getString("direccion");
                        correoElectronico = rs.getString("correo_electronico");
                    } else {
                        // Cliente no encontrado, devolver formulario vacío
                        request.setAttribute("error", "Cliente no encontrado. Complete los datos del cliente.");
                        request.getRequestDispatcher("/RegistrarVenta.jsp").forward(request, response);
                        return;
                    }
                }
            }

            // Registrar la venta
            String insertVentaQuery = "INSERT INTO venta (cliente_id, total, fecha, computadora_id, cantidad) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement insertVentaStmt = connection.prepareStatement(insertVentaQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                insertVentaStmt.setInt(1, clienteId);
                insertVentaStmt.setBigDecimal(2, new java.math.BigDecimal(total));
                insertVentaStmt.setString(3, fecha);
                insertVentaStmt.setInt(4, Integer.parseInt(computadoraId));
                insertVentaStmt.setInt(5, Integer.parseInt(cantidad));
                insertVentaStmt.executeUpdate();

                try (ResultSet generatedKeys = insertVentaStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int ventaId = generatedKeys.getInt(1);
                    
                        try{
                            Document document = new Document();
                            PdfWriter.getInstance(document,response.getOutputStream());
                            document.open();        

                            document.add(new Paragraph("Factura de Venta"));
                            document.add(new Paragraph("ID de Venta: " + ventaId));
                            document.add(new Paragraph("Cliente: " + nombre));
                            document.add(new Paragraph("NIT: " + nit));
                            document.add(new Paragraph("Teléfono: " + telefono));
                            document.add(new Paragraph("Dirección: " + direccion));
                            document.add(new Paragraph("Correo Electrónico: " + correoElectronico));
                            document.add(new Paragraph("Total: " + total));
                            document.add(new Paragraph("Fecha: " + fecha));
                            document.add(new Paragraph("ID de la Computadora: " + computadoraId));
                            document.add(new Paragraph("Cantidad: " + cantidad));

                            document.close();
                        } catch (DocumentException e) {
                            e.printStackTrace();
                            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al generar la factura");
                        }    
                    } else {
                        throw new SQLException("Error al registrar la venta.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al registrar la venta y generar la factura");
        }
    }
}

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package Servlet.Ventas;
//
//import DataBase.ConexionDB;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
///**
// *
// * @author sofia
// */
//@WebServlet(name = "RegistrarVentaServlet", urlPatterns = {"/RegistrarVentaServlet"})
//public class RegistrarVentaServlet extends HttpServlet {
//;
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String nombre = request.getParameter("nombre");
//        String nit = request.getParameter("nit");
//        String telefono = request.getParameter("telefono");
//        String direccion = request.getParameter("direccion");
//        String correoElectronico = request.getParameter("correoElectronico");
//        String total = request.getParameter("total");
//        String fecha = request.getParameter("fecha");
//        String computadoraId = request.getParameter("computadoraId");
//        String cantidad = request.getParameter("cantidad");
//
//        response.setContentType("text/plain");
//
//        try (PrintWriter writer = response.getWriter();
//             Connection connection = ConexionDB.getConnection()) {
//
//            // Verificar si el cliente ya existe
//            String selectClienteQuery = "SELECT id FROM cliente WHERE nit = ?";
//            int clienteId;
//
//            try (PreparedStatement selectClienteStmt = connection.prepareStatement(selectClienteQuery)) {
//                selectClienteStmt.setString(1, nit);
//
//                try (ResultSet rs = selectClienteStmt.executeQuery()) {
//                    if (rs.next()) {
//                        // Cliente existente
//                        clienteId = rs.getInt("id");
//                    } else {
//                        // Cliente nuevo
//                        String insertClienteQuery = "INSERT INTO cliente (nombre, nit, telefono, direccion, correo_electronico) VALUES (?, ?, ?, ?, ?)";
//                        try (PreparedStatement insertClienteStmt = connection.prepareStatement(insertClienteQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
//                            insertClienteStmt.setString(1, nombre);
//                            insertClienteStmt.setString(2, nit);
//                            insertClienteStmt.setString(3, telefono);
//                            insertClienteStmt.setString(4, direccion);
//                            insertClienteStmt.setString(5, correoElectronico);
//                            insertClienteStmt.executeUpdate();
//
//                            try (ResultSet generatedKeys = insertClienteStmt.getGeneratedKeys()) {
//                                if (generatedKeys.next()) {
//                                    clienteId = generatedKeys.getInt(1);
//                                } else {
//                                    throw new SQLException("Error al obtener el ID del nuevo cliente.");
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            // Registrar la venta
//            String insertVentaQuery = "INSERT INTO venta (cliente_id, total, fecha, computadora_id, cantidad) VALUES (?, ?, ?, ?, ?)";
//            try (PreparedStatement insertVentaStmt = connection.prepareStatement(insertVentaQuery)) {
//                insertVentaStmt.setInt(1, clienteId);
//                insertVentaStmt.setBigDecimal(2, new java.math.BigDecimal(total));
//                insertVentaStmt.setString(3, fecha);
//                insertVentaStmt.setInt(4, Integer.parseInt(computadoraId));
//                insertVentaStmt.setInt(5, Integer.parseInt(cantidad));
//                insertVentaStmt.executeUpdate();
//            }
//
//            writer.println("Venta registrada exitosamente.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al registrar la venta");
//        }
//    }
//}