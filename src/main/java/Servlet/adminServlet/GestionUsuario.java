/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet.adminServlet;

import DataBase.ConexionDB;
import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author sofia
 */
@WebServlet(name = "GestionUsuario", urlPatterns = {"/GestionUsuario"})
public class GestionUsuario extends HttpServlet {
    
    @Override    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cargarUsuarios(request);  // Se llama al método para obtener la lista de usuarios
        request.getRequestDispatcher("AreaAdministrativa/GestionUsuario.jsp").forward(request, response);
    }
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String mensaje = null;

        try (Connection conn = ConexionDB.getConnection()) {
            if (conn == null) {
                mensaje = "Error: No se pudo conectar a la base de datos.";
            } else {
                if ("crear".equals(accion)) {
                    mensaje = crearUsuario(request, conn);
                } else if ("modificar".equals(accion)) {
                    mensaje = modificarUsuario(request, conn);
                } else if ("eliminar".equals(accion)) {
                    mensaje = eliminarUsuario(request, conn);
                }
            }

//            List<String[]> usuarios = obtenerUsuarios(conn);
//            if (usuarios == null){
//                usuarios = new ArrayList<>();
//            }
            //request.setAttribute("usuarios", usuarios);
        } catch (SQLException | NumberFormatException e) {
            mensaje = "Error: " + e.getMessage();
        }
        cargarUsuarios(request);

        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("AreaAdministrativa/GestionUsuario.jsp").forward(request, response);
    }

    private String crearUsuario(HttpServletRequest request, Connection conn) throws SQLException {
        String nombre = request.getParameter("nombre");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");
        String correo = request.getParameter("correo_electronico");
        String direccion = request.getParameter("direccion");
        String celular = request.getParameter("celular");

        String sql = "INSERT INTO usuario (nombre, password, rol, correo_electronico, direccion, celular) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, password);
            stmt.setString(3, rol);
            stmt.setString(4, correo);
            stmt.setString(5, direccion);
            stmt.setString(6, celular);
            stmt.executeUpdate();
        }
        return "Usuario creado exitosamente.";
    }

    private String modificarUsuario(HttpServletRequest request, Connection conn) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");
        String correo = request.getParameter("correo_electronico");
        String direccion = request.getParameter("direccion");
        String celular = request.getParameter("celular");

        String sql = "UPDATE usuario SET nombre=?, password=?, rol=?, correo_electronico=?, direccion=?, celular=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, password);
            stmt.setString(3, rol);
            stmt.setString(4, correo);
            stmt.setString(5, direccion);
            stmt.setString(6, celular);
            stmt.setInt(7, id);
            stmt.executeUpdate();
        }
        return "Usuario modificado exitosamente.";
    }

    private String eliminarUsuario(HttpServletRequest request, Connection conn) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));

        String sql = "DELETE FROM usuario WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        return "Usuario eliminado exitosamente.";
    }

    private List<String[]> obtenerUsuarios(Connection conn) throws SQLException {
        List<String[]> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String[] usuario = {
                    String.valueOf(rs.getInt("id")),
                    rs.getString("nombre"),
                    rs.getString("password"),
                    rs.getString("rol"),
                    rs.getString("correo_electronico"),
                    rs.getString("direccion"),
                    rs.getString("celular")
                };
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
        }
        return usuarios;
    }
    
    private void cargarUsuarios(HttpServletRequest request) {
        try (Connection conn = ConexionDB.getConnection()) {
            List<String[]> usuarios = obtenerUsuarios(conn);
            request.setAttribute("usuarios", usuarios);
        } catch (SQLException e) {
            request.setAttribute("mensaje", "Error al obtener usuarios: " + e.getMessage());
        }
    }
}