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
@WebServlet(name = "ComponentesAgotadosServlet", urlPatterns = {"/ComponentesAgotadosServlet"})
public class ComponentesAgotadosServlet extends HttpServlet {

    // Método doGet que maneja la solicitud para obtener los componentes agotados
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<String[]> componentesAgotados = new ArrayList<>();

        // Establece la conexión con la base de datos y obtiene los componentes agotados
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM componente WHERE cantidad <= 5";  // Consulta los componentes con cantidad <= 5
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Procesar los resultados de la consulta y agregar los componentes a la lista
            while (rs.next()) {
                componentesAgotados.add(new String[] {
                    rs.getString("nombre"),          // Nombre del componente
                    String.valueOf(rs.getInt("cantidad")) // Cantidad restante
                });
            }
            
            // Establecer el atributo 'componentesAgotados' en la solicitud
            request.setAttribute("componentesAgotados", componentesAgotados);
            
            // Redirigir a la vista JSP que muestra los componentes agotados
            request.getRequestDispatcher("AreaEnsamblaje/consultarComponentesAgotados.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            // En caso de error, redirigir al panel de ensamblaje con un mensaje de error
            response.sendRedirect("AreaEnsamblaje/panelEnsamblaje.jsp?error=ErrorAlConsultarComponentes");
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
            out.println("<title>Servlet ComponentesAgotadosServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComponentesAgotadosServlet at " + request.getContextPath() + "</h1>");
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
