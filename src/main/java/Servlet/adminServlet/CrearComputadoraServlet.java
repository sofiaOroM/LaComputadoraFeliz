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
@WebServlet(name = "CrearComputadoraServlet", urlPatterns = {"/CrearComputadoraServlet"})
public class CrearComputadoraServlet extends HttpServlet {
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreComputadora = request.getParameter("nombreComputadora");
        double precio = Double.parseDouble(request.getParameter("precio"));

        if (nombreComputadora == null || nombreComputadora.isEmpty() || precio <= 0) {
            request.setAttribute("error", "Nombre o precio inválido.");
            request.getRequestDispatcher("panelAdmin.jsp").forward(request, response);
            return;
        }

        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = ConexionDB.getConnection();

            // Insertar la nueva computadora
            String sql = "INSERT INTO computadora (nombre, precio) VALUES (?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, nombreComputadora);
            pst.setDouble(2, precio);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                response.sendRedirect("panelAdmin.jsp");  // Redirigir al panel
            } else {
                request.setAttribute("error", "Hubo un error al crear la computadora.");
                request.getRequestDispatcher("panelAdmin.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al conectarse a la base de datos.");
            request.getRequestDispatcher("panelAdmin.jsp").forward(request, response);
        } finally {
            try {
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet crearComputadoraServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet crearComputadoraServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
