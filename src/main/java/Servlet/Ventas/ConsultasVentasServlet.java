/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet.Ventas;

import DataBase.ConexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sofia
 */
@WebServlet(name = "ConsultasVentasServlet", urlPatterns = {"/ConsultasVentasServlet"})
public class ConsultasVentasServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String consulta = request.getParameter("consulta");
        String nit = request.getParameter("nit");
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");
        String facturaId = request.getParameter("facturaId");

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter writer = response.getWriter();
             Connection connection = ConexionDB.getConnection()) {

            String query = "";
            switch (consulta) {
                case "comprasCliente":
                    query = "SELECT * FROM venta WHERE cliente_id = (SELECT id FROM cliente WHERE nit = ?) AND fecha >= ? AND fecha <= ?";
                    if (nit == null || fechaInicio == null || fechaFin == null) {
                        request.setAttribute("error", "Por favor, complete todos los campos requeridos para esta consulta.");
                        request.getRequestDispatcher("/ConsultasVentas.jsp").forward(request, response);
                        return;
                    }
                    break;
                case "devolucionesCliente":
                    query = "SELECT * FROM devolucion WHERE venta_id IN (SELECT id FROM venta WHERE cliente_id = (SELECT id FROM cliente WHERE nit = ?)) AND fecha >= ? AND fecha <= ?";
                    if (nit == null || fechaInicio == null || fechaFin == null) {
                        request.setAttribute("error", "Por favor, complete todos los campos requeridos para esta consulta.");
                        request.getRequestDispatcher("/ConsultasVentas.jsp").forward(request, response);
                        return;
                    }
                    break;
                case "computadorasDisponibles":
                    query = "SELECT * FROM computadora";
                    break;
                case "detallesFactura":
                    query = "SELECT * FROM venta WHERE id = ?";
                    if (facturaId == null) {
                        request.setAttribute("error", "Por favor, complete el ID de la factura.");
                        request.getRequestDispatcher("/ConsultasVentas.jsp").forward(request, response);
                        return;
                    }
                    break;
                case "ventasDelDia":
                    query = "SELECT * FROM venta WHERE DATE(fecha) = CURDATE()";
                    break;
                default:
                    request.setAttribute("error", "Consulta no válida.");
                    request.getRequestDispatcher("/ConsultasVentas.jsp").forward(request, response);
                    return;
            }

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                switch (consulta) {
                    case "comprasCliente":
                    case "devolucionesCliente":
                        statement.setString(1, nit);
                        statement.setString(2, fechaInicio);
                        statement.setString(3, fechaFin);
                        break;
                    case "detallesFactura":
                        statement.setString(1, facturaId);
                        break;
                }

                try (ResultSet rs = statement.executeQuery()) {
                    writer.println("<html><head><link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/bulma/0.9.3/css/bulma.min.css\"></head><body><section class=\"section\"><div class=\"container\"><table class=\"table is-striped is-bordered is-hoverable is-fullwidth\">");
                    writer.println("<thead><tr>");

                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        writer.println("<th>" + rs.getMetaData().getColumnName(i) + "</th>");
                    }

                    writer.println("</tr>");

                    while (rs.next()) {
                        writer.println("<tr>");
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                            writer.println("<td>" + rs.getString(i) + "</td>");
                        }
                        writer.println("</tr>");
                    }

                    writer.println("</tbody></table></div></section></body></html>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al realizar la consulta");
        }
    }
}
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String consulta = request.getParameter("consulta");
//        String nit = request.getParameter("nit");
//        String fechaInicio = request.getParameter("fechaInicio");
//        String fechaFin = request.getParameter("fechaFin");
//
//        response.setContentType("text/html");
//        response.setCharacterEncoding("UTF-8");
//
//        try (PrintWriter writer = response.getWriter();
//             Connection connection = ConexionDB.getConnection()) {
//
//            String query = "";
//            switch (consulta) {
//                case "comprasCliente":
//                    query = "SELECT * FROM venta WHERE cliente = ? AND fecha >= ? AND fecha <= ?";
//                    break;
//                case "devolucionesCliente":
//                    query = "SELECT * FROM devolucion WHERE venta_id IN (SELECT id FROM venta WHERE cliente = ?) AND fecha >= ? AND fecha <= ?";
//                    break;
//                case "computadorasDisponibles":
//                    query = "SELECT * FROM computadora";
//                    break;
//                case "detallesFactura":
//                    query = "SELECT * FROM venta WHERE id = ?";
//                    break;
//                case "ventasDelDia":
//                    query = "SELECT * FROM venta WHERE DATE(fecha) = CURDATE()";
//                    break;
//            }
//
//            try (PreparedStatement statement = connection.prepareStatement(query)) {
//                if (consulta.equals("comprasCliente") || consulta.equals("devolucionesCliente")) {
//                    statement.setString(1, nit);
//                    statement.setString(2, fechaInicio);
//                    statement.setString(3, fechaFin);
//                } else if (consulta.equals("detallesFactura")) {
//                    statement.setString(1, nit);
//                }
//
//                try (ResultSet rs = statement.executeQuery()) {
//                    writer.println("<html><body><table>");
//                    writer.println("<tr>");
//
//                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
//                        writer.println("<th>" + rs.getMetaData().getColumnName(i) + "</th>");
//                    }
//
//                    writer.println("</tr>");
//
//                    while (rs.next()) {
//                        writer.println("<tr>");
//                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
//                            writer.println("<td>" + rs.getString(i) + "</td>");
//                        }
//                        writer.println("</tr>");
//                    }
//
//                    writer.println("</table></body></html>");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al realizar la consulta");
//        }
//    }
//}