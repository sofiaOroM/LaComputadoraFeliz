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
 * Servlet para crear un componente en la base de datos
 */
@WebServlet(name = "CrearPiezaServlet", urlPatterns = {"/CrearPiezaServlet"})
public class CrearPiezaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoria = request.getParameter("categoria");
        String nombre = request.getParameter("nombre");
        double costo = Double.parseDouble(request.getParameter("costo"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        // Validación de los parámetros
        if (categoria == null || categoria.isEmpty() || nombre == null || nombre.isEmpty() || costo <= 0 || cantidad <= 0) {
            request.setAttribute("error", "Datos inválidos. Verifica la información.");
            request.getRequestDispatcher("AreaAdministrativa/CrearComponentes.jsp").forward(request, response);
            return;
        }

        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = ConexionDB.getConnection();

            // Insertar nuevo componente
            String sql = "INSERT INTO componente (categoria, nombre, costo, cantidad, cantidad_reservada) VALUES (?, ?, ?, ?, 0)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, categoria);
            pst.setString(2, nombre);
            pst.setDouble(3, costo);
            pst.setInt(4, cantidad);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                request.setAttribute("success", "Componente creado con éxito.");
                response.sendRedirect("AreaAdministrativa/panelAdmin.jsp");  // Redirigir al panel
            } else {
                request.setAttribute("error", "Hubo un error al crear el componente.");
                request.getRequestDispatcher("AreaAdministrativa/CrearComponentes.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al conectarse a la base de datos.");
            request.getRequestDispatcher("AreaAdministrativa/CrearComponentes.jsp").forward(request, response);
        } finally {
            try {
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
