/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.clientejuegos.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Rosa
 */
public class Conexion {
     private static final String SERVIDOR = "jdbc:mysql://127.0.0.1/";
    private static final String NOMBRE_BASE_DATOS = "clientejuegos";
    private static final String USER = "jose";
    private static final String PASS = "josefa";
    public static final String DRIVER = "com.mysql.jdbc.Driver";

    private static Connection instancia = null;

    private Conexion() {

    }

    // Método de clase para acceder a la instancia del objeto Connection
    public static Connection getInstance() {
        // Si el objeto Connection no está creado, se crea
        if (instancia == null) {
            try {
                Class.forName(DRIVER);
                // Se crea el objeto Connection	
                instancia = DriverManager.getConnection(SERVIDOR + NOMBRE_BASE_DATOS, USER, PASS);

                System.out.println("Conexión realizada con éxito.");

            } catch (SQLException e) {
                // Error en la conexión
                System.out.println("Conexión fallida: " + e.getMessage());
            } catch (ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return instancia;
    }

}
