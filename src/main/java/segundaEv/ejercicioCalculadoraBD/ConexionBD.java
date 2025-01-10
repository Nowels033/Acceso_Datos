package segundaEv.ejercicioCalculadoraBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {
    private static Connection conn;
    private static final String URL = "jdbc:mysql://localhost:3306/ejerciciosuma";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConexion() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                // System.out.println("Conexion establecida.");
            } catch (SQLException ex) {
                Logger.getLogger(gestionDeUsuarioV2.ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
    }

    public static void cerrarConexion() {
        if (conn != null) {
            try {
                conn.close();
                // System.out.println("Conexion cerrada.");
            } catch (SQLException ex) {
                Logger.getLogger(gestionDeUsuarioV2.ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static Connection getConn() {
        return conn;
    }
}
