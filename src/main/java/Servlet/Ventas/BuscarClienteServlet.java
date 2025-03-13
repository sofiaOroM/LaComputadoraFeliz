package Servlet.Ventas;

import DataBase.ConexionDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet(name = "BuscarClienteServlet", urlPatterns = {"/BuscarClienteServlet"})
public class BuscarClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nit = request.getParameter("nit");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (Connection conn = ConexionDB.getConnection()) {
            if (conn == null) {
                response.getWriter().write("{\"error\": \"No se pudo conectar a la base de datos.\"}");
                return;
            }

            String sql = "SELECT nombre, telefono, direccion, correo_electronico FROM cliente WHERE nit = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nit);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        JSONObject cliente = new JSONObject();
                        cliente.put("nombre", rs.getString("nombre"));
                        cliente.put("telefono", rs.getString("telefono"));
                        cliente.put("direccion", rs.getString("direccion"));
                        cliente.put("correo_electronico", rs.getString("correo_electronico"));
                        response.getWriter().write(cliente.toString());
                    } else {
                        response.getWriter().write("{\"error\": \"Cliente no encontrado\"}");
                    }
                }
            }
        } catch (SQLException e) {
            response.getWriter().write("{\"error\": \"Error en la base de datos: " + e.getMessage() + "\"}");
        }
    }
}
//package Servlet.Ventas;

//import DataBase.ConexionDB;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.json.JSONObject;
/**
 *
 * @author sofia
 */
//@WebServlet(name = "BuscarClienteServlet", urlPatterns = {"/BuscarClienteServlet"})
//public class BuscarClienteServlet extends HttpServlet {

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//        String nit = request.getParameter("nit");
//
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//
//        try (PrintWriter writer = response.getWriter();
//             Connection connection = ConexionDB.getConnection()) {
//
//            String query = "SELECT nombre, telefono, direccion, correo_electronico FROM cliente WHERE nit = ?";
//            try (PreparedStatement statement = connection.prepareStatement(query)) {
//                statement.setString(1, nit);
//
//                try (ResultSet rs = statement.executeQuery()) {
//                    if (rs.next()) {
//                        JSONObject cliente = new JSONObject();
//                        cliente.put("nombre", rs.getString("nombre"));
//                        cliente.put("telefono", rs.getString("telefono"));
//                        cliente.put("direccion", rs.getString("direccion"));
//                        cliente.put("correo_electronico", rs.getString("correo_electronico"));
//                        writer.print(cliente.toString());
//                    } else {
////                        writer.print("{}");
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//} 
