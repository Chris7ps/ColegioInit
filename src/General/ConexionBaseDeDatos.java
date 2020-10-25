/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author chris
 */
public class ConexionBaseDeDatos {

    public static Connection CONNECTION = connectDatabase();
    
    static Connection connectDatabase() {
        try {

            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection;

            connection = DriverManager.getConnection("jdbc:postgresql://localhost/colegio",
                    "postgres", "UMGPROGRA2020");

            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "Conexión a BD exitosa" : "Error al conectar a base de datos");
            return connection;
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error: " + sqle);
            return null;
        }
    }
}
