package Servlet.Ensamblaje;

import DataBase.ConexionDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Ruta del servlet
@WebServlet(name = "ModificarComponenteServlet", urlPatterns = {"/ModificarComponenteServlet"})
public class ModificarComponenteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String mensaje = null;

        try (Connection conn = ConexionDB.getConnection()) {
            if (conn == null) {
                mensaje = "Error: No se pudo conectar a la base de datos.";
            } else {
                if ("crear".equals(accion)) {
                    mensaje = crearComponente(request, conn);
                } else if ("modificar".equals(accion)) {
                    mensaje = modificarComponente(request, conn);
                } else if ("eliminar".equals(accion)) {
                    mensaje = eliminarComponente(request, conn);
                }
            }

            List<String[]> componentes = obtenerComponentes(conn);
            if (componentes == null){
                componentes = new ArrayList<>();
            }
            request.setAttribute("componentes", componentes);

        } catch (SQLException | NumberFormatException e) {
            mensaje = "Error: " + e.getMessage();
        }

        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("AreaEnsamblaje/ModificarComponente.jsp").forward(request, response);
    }

    private String crearComponente(HttpServletRequest request, Connection conn) throws SQLException {
        String nombre = request.getParameter("nombre");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        double costo = Double.parseDouble(request.getParameter("costo"));
        String categoria = request.getParameter("categoria");

        String sql = "INSERT INTO componente (nombre, cantidad, costo, categoria) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setInt(2, cantidad);
            stmt.setDouble(3, costo);
            stmt.setString(4, categoria);
            stmt.executeUpdate();
        }
        return "Componente creado exitosamente.";
    }

    private String modificarComponente(HttpServletRequest request, Connection conn) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        double costo = Double.parseDouble(request.getParameter("costo"));
        String categoria = request.getParameter("categoria");

        String sql = "UPDATE componente SET nombre=?, cantidad=?, costo=?, categoria=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setInt(2, cantidad);
            stmt.setDouble(3, costo);
            stmt.setString(4, categoria);
            stmt.setInt(5, id);
            stmt.executeUpdate();
        }
        return "Componente modificado exitosamente.";
    }

    private String eliminarComponente(HttpServletRequest request, Connection conn) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));

        String sql = "DELETE FROM componente WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        return "Componente eliminado exitosamente.";
    }

    private List<String[]> obtenerComponentes(Connection conn) throws SQLException {
        List<String[]> componentes = new ArrayList<>();
        String sql = "SELECT * FROM componente";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String[] componente = {
                    String.valueOf(rs.getInt("id")),
                    rs.getString("nombre"),
                    String.valueOf(rs.getInt("cantidad")),
                    String.valueOf(rs.getDouble("costo"))
                };
                componentes.add(componente);
            }
        }
        return componentes;
    }
}

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package Servlet.Ensamblaje;
//
//import DataBase.ConexionDB;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author sofia
// */
//@WebServlet(name = "ModificarComponenteServlet", urlPatterns = {"/ModificarComponente"})
//public class ModificarComponenteServlet extends HttpServlet {
//    
//protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String accion = request.getParameter("accion");
//        
//        try (Connection conn = ConexionDB.getConnection()) {
//            if (conn == null) {
//                request.setAttribute("mensaje", "Error: No se pudo conectar a la base de datos.");
//                //request.getRequestDispatcher("/AreaEnsamblaje/GestionarComponentes.jsp").forward(request, response);
//                return;
//            }
//
//            if ("crear".equals(accion)) {
//                String nombre = request.getParameter("nombre");
//                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
//                double costo = Double.parseDouble(request.getParameter("costo"));
//                String categoria = request.getParameter("categoria");
//
//                String sql = "INSERT INTO componente (nombre, cantidad, costo, categoria) VALUES (?, ?, ?, ?)";
//                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//                    stmt.setString(1, nombre);
//                    stmt.setInt(2, cantidad);
//                    stmt.setDouble(3, costo);
//                    stmt.setString(4, categoria);
//                    stmt.executeUpdate();
//                    request.setAttribute("mensaje", "Componente creado exitosamente.");
//                }
//            } else if ("modificar".equals(accion)) {
//                int id = Integer.parseInt(request.getParameter("id"));
//                String nombre = request.getParameter("nombre");
//                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
//                double costo = Double.parseDouble(request.getParameter("costo"));
//                String categoria = request.getParameter("categoria");
//
//                String sql = "UPDATE componente SET nombre=?, cantidad=?, costo=?, categoria=? WHERE id=?";
//                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//                    stmt.setString(1, nombre);
//                    stmt.setInt(2, cantidad);
//                    stmt.setDouble(3, costo);
//                    stmt.setString(4, categoria);
//                    stmt.setInt(5, id);
//                    stmt.executeUpdate();
//                    request.setAttribute("mensaje", "Componente modificado exitosamente.");
//                }
//            } else if ("eliminar".equals(accion)) {
//                int id = Integer.parseInt(request.getParameter("id"));
//
//                String sql = "DELETE FROM componente WHERE id=?";
//                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//                    stmt.setInt(1, id);
//                    stmt.executeUpdate();
//                    request.setAttribute("mensaje", "Componente eliminado exitosamente.");
//                }
//            }
//        } catch (SQLException | NumberFormatException e) {
//            request.setAttribute("mensaje", "Error: " + e.getMessage());
//        }
//
////        request.getRequestDispatcher("/AreaEnsamblaje/GestionarComponentes.jsp").forward(request, response);
//    }
//}
