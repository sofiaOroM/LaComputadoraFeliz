/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet.Ensamblaje;

import DataBase.ConexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
@WebServlet(name = "ConsultarComputadorasServlet", urlPatterns = {"/ConsultarComputadorasServlet"})
public class ConsultarComputadorasServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String ordenarPor = request.getParameter("ordenarPor");  // "asc" o "desc"
        List<String[]> computadoras = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM computadora_ensamblada";
            if ("asc".equals(ordenarPor)) {
                sql += " ORDER BY fecha_ensamble ASC";
            } else if ("desc".equals(ordenarPor)) {
                sql += " ORDER BY fecha_ensamble DESC";
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                computadoras.add(new String[] {
                    String.valueOf(rs.getInt("id")),
                    rs.getString("nombre"),
                    String.valueOf(rs.getDate("fecha_ensamble")),
                    String.valueOf(rs.getDouble("costo"))
                });
            }
            request.setAttribute("computadoras", computadoras);
            request.getRequestDispatcher("AreaEnsamblaje/consultarComponentesYComputadoras.jsp").forward(request, response);
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("AreaEnsamblaje/panelEnsamblaje.jsp?error=ErrorAlConsultarComputadoras");
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
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }

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
