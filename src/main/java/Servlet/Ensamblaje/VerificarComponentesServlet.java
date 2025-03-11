/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet.Ensamblaje;

import DataBase.ConexionDB;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author sofia
 */
@WebServlet(name = "VerificarComponentesServlet", urlPatterns = {"/VerificarComponentesServlet"})
public class VerificarComponentesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String[]> componentesAgotados = new ArrayList<>();
        List<String[]> componentesPuntoDeAgotarse = new ArrayList<>();
        List<String[]> todosLosComponentes = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection()) {
            if (conn == null) {
                response.getWriter().println("Error: No se pudo conectar a la base de datos.");
                return;
            }

            // Obtener todos los componentes
            String sql = "SELECT id, nombre, cantidad, costo, CATEGORIA FROM componente";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    String id = rs.getString("id");
                    String nombre = rs.getString("nombre");
                    int cantidad = rs.getInt("cantidad");
                    String precio = rs.getString("costo");
                    String categoria = rs.getString("CATEGORIA");
        System.out.println("Componente: ID=" + id + ", Nombre=" + nombre + ", Cantidad=" + cantidad + ", Precio=" + precio + ", Categoría=" + categoria);

                    // Almacenar en lista general
                    todosLosComponentes.add(new String[]{id, nombre, String.valueOf(cantidad), precio, categoria});

                    // Clasificación por cantidad
                    if (cantidad == 0) {
            System.out.println("Agotado: " + nombre);

                        componentesAgotados.add(new String[]{id, nombre});
                    } else if (cantidad > 0 && cantidad <= 5) {
                        componentesPuntoDeAgotarse.add(new String[]{id, nombre, String.valueOf(cantidad)});
                    }
                }
            }

            // Enviar datos a la vista JSP
            request.setAttribute("componentesAgotados", componentesAgotados);
            request.setAttribute("componentesPuntoDeAgotarse", componentesPuntoDeAgotarse);
            request.setAttribute("todosLosComponentes", todosLosComponentes);

            // Redirigir al JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/AreaEnsamblaje/ConsultarComponentes.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error en la base de datos: " + e.getMessage());
        }
    }
}
