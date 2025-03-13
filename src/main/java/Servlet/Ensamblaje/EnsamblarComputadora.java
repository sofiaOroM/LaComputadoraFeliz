
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet.Ensamblaje;

import DataBase.ConexionDB;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.Date;

@WebServlet(name = "EnsamblarComputadora", urlPatterns = {"/EnsamblarComputadora"})
public class EnsamblarComputadora extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map<String, String>> computadoras = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM computadora"); 
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Map<String, String> computadora = new HashMap<>();
                computadora.put("id", String.valueOf(rs.getInt("id")));
                computadora.put("nombre", rs.getString("nombre"));
                computadora.put("precio", String.valueOf(rs.getDouble("precio")));
                computadoras.add(computadora);
            }
        }
//            request.setAttribute("computadoras", computadoras);
         catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error cargando computadoras: " + e.getMessage());
        }
        request.setAttribute("computadoras", computadoras);
        request.getRequestDispatcher("/AreaEnsamblaje/EnsamblarComputadora.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String computadoraId = request.getParameter("computadoraId");

        if (computadoraId == null || computadoraId.isEmpty()) {
            request.setAttribute("mensaje", "Seleccione una computadora válida.");
            doGet(request, response);
            return;
        }

        try (Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO ensamblaje (computadora_id, estado, fecha_ensamblaje) VALUES (?, 'pendiente', NOW())")) {

            stmt.setInt(1, Integer.parseInt(computadoraId));
            int filas = stmt.executeUpdate();

            if (filas > 0) {
                request.setAttribute("mensaje", "¡Computadora ensamblada correctamente!");
            } else {
                request.setAttribute("mensaje", "Error ensamblando la computadora.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error durante el ensamblaje: " + e.getMessage());
        }
        doGet(request, response);
    }
}
