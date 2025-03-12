package Servlet;

import DataBase.ConexionDB;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TestConexionServlet", urlPatterns = {"/TestConexionServlet"})
public class TestConexionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String mensaje;
        Connection conn = ConexionDB.getConnection();
        
        if (conn != null) {
            mensaje = "Conexión exitosa con la base de datos.";
        } else {
            mensaje = "Error: No se pudo conectar a la base de datos.";
        }
        
        // Redirigir con el mensaje como parámetro
        response.sendRedirect("index.jsp?mensaje=" + mensaje);
    }
}

