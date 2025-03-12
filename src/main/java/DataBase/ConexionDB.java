/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sofia
 */
public class ConexionDB {
private static final String URL = "jdbc:mysql://localhost:3306/computadorafeliz?useSSL=true";    
private static final String USER = "root"; // Ajusta según tu configuración
private static final String PASSWORD = "sofia2808"; // Ajusta según tu configuración

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos.");
            return conn;
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver de MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error de conexión a la base de datos.");
            e.printStackTrace();
        }
        return null; // Si hay error, devuelve null
    }
    
    public static String verificarConexion() {
        try (Connection conn = getConnection()) {
            return (conn != null) ? "Conexión exitosa" : "Error en la conexión";
        } catch (Exception e) {
            return "Error en la conexión: " + e.getMessage();
        }
    }
}
