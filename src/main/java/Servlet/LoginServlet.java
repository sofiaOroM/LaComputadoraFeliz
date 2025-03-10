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
                        response.sendRedirect("index.html?error=rol_desconocido");
                        break;
                }
            } else {
                response.sendRedirect("index.html?error=credenciales_invalidas");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error en el login: " + e.getMessage());
        }
    }
}
//    // Método para manejar la validación y redirección
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String nombre = request.getParameter("nombre");
//        String password = request.getParameter("password");
//        
//        // Verificar si los campos están vacíos
//        if (nombre == null || password == null || nombre.isEmpty() || password.isEmpty()) {
//            request.setAttribute("error", "Nombre de usuario o contraseña incorrectos");
//            request.getRequestDispatcher("index.html").forward(request, response);
//            return;
//        }
//        Connection conn = null;
//        PreparedStatement pst = null;
//        ResultSet rs = null;
//
//        try {
//            conn = ConexionDB.getConnection();
//            String sql = "SELECT id, nombre, password, rol FROM usuario WHERE nombre = ?";
//            pst = conn.prepareStatement(sql);
//            pst.setString(1, nombre);
//            rs = pst.executeQuery();
//
//            // Verificar si el usuario existe
//            if (rs.next()) {
//                String dbPassword = rs.getString("password");
//                String rol = rs.getString("rol");
//
//            if (BCrypt.checkpw(password, dbPassword)) {
//                // Contraseña correcta, iniciar sesión
//                HttpSession session = request.getSession();
//                session.setAttribute("nombre", nombre);
//                session.setAttribute("rol", rol);
//
//                    // Redirigir según el rol
//                    switch (rol) {
//                        case "admin":
//                            response.sendRedirect("AreaAdmin/panelAdmin.jsp");
//                            break;
//                        case "ventas":
//                            response.sendRedirect("AreaVentas/panelVentas.jsp");
//                            break;
//                        case "ensamblaje":
//                            response.sendRedirect("AreaEnsamblaje/panelEnsamblaje.jsp");
//                            break;
//                        default:
//                            response.sendRedirect("index.html");
//                            break;
//                    }
//                } else {
//                    // Contraseña incorrecta
//                    request.setAttribute("error", "Contraseña incorrecta");
//                    request.getRequestDispatcher("index.html").forward(request, response);
//                }
//            } else {
//                // Usuario no encontrado
//                request.setAttribute("error", "Usuario no encontrado");
//                request.getRequestDispatcher("index.html").forward(request, response);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            request.setAttribute("error", "Error en la conexión a la base de datos");
//            request.getRequestDispatcher("index.html").forward(request, response);
//        } finally {
//            // Cerrar recursos
//            try {
//                if (rs != null) rs.close();
//                if (pst != null) pst.close();
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        // Redirigir al login en caso de que se haga un GET (no es habitual en el login)
//        response.sendRedirect("index.html");
//    }
//
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
////    @Override
////    protected void doGet(HttpServletRequest request, HttpServletResponse response)
////            throws ServletException, IOException {
////        processRequest(request, response);
////    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
////    @Override
////    protected void doPost(HttpServletRequest request, HttpServletResponse response)
////            throws ServletException, IOException {
////        processRequest(request, response);
////    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
