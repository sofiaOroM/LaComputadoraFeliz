/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

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
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author sofia
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String password = request.getParameter("password");

        Connection conn = ConexionDB.getConnection();
        if (conn == null) {
            response.getWriter().println("Error: No se pudo conectar a la base de datos.");
            return;
        }

        String sql = "SELECT * FROM usuario WHERE nombre = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String rol = rs.getString("rol");

                // Iniciar sesión
                HttpSession session = request.getSession();
                session.setAttribute("usuario", nombre);
                session.setAttribute("rol", rol);

                // Redirigir según el rol
                switch (rol) {
                    case "admin":
                        response.sendRedirect("AreaAdministrativa/panelAdmin.jsp");
                        break;
                    case "ventas":
                        response.sendRedirect("AreaVentas/panelVentas.jsp");
                        break;
                    case "ensamblaje":
                        response.sendRedirect("AreaEnsamblaje/panelEnsamblaje.jsp");
                        break;
                    default:
                        response.sendRedirect("index.jsp?error=rol_desconocido");
                        break;
                }
            } else {
                response.sendRedirect("index.jsp?error=credenciales_invalidas");
            }    conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error en el login: " + e.getMessage());
        }
    }
}
