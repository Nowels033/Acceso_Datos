/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ALUMNO CCC - TARDE
 */
public class ClubSociosApp {

    public static void main(String[] args) {

        //insertSQL();
        //update();
        deleteSQL();

    }

    public static void insertSQL() {
        int puerto = 3306;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:" + puerto + "/club_socios", "root", "");

            if (conn != null) {
                System.out.println("CONEXION ESTABLECIDA CON LA BASE DE DATOS");

                String query = "insert into socios (nombre,apellidos,dni,domicilio,localidad,tipo_socio,fecha_alta,fecha_baja,paga_ult_recibo,anotaciones)values (?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, "Noel");
                preparedStatement.setString(2, "Dominguez");
                preparedStatement.setString(3, "222222222");
                preparedStatement.setString(4, "Calle no te importa");
                preparedStatement.setString(5, "Madrid");
                preparedStatement.setString(6, "A");
                preparedStatement.setString(7, "2010-11-25");
                preparedStatement.setString(8, "2022-11-25");
                preparedStatement.setString(9, "S");
                preparedStatement.setString(10, "Se dio de baja:/");

                System.out.println("CONSULTA ====> " + preparedStatement.toString());
                int conteo = preparedStatement.executeUpdate();
                System.out.println("Se insertaron: " + conteo + " registros.");

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubSociosApp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(ClubSociosApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void update() {

        int puerto = 3306;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:" + puerto + "/club_socios", "root", "");

            if (conn != null) {
                System.out.println("CONEXION ESTABLECIDA CON LA BASE DE DATOS");

                String updateQuery = "UPDATE socios SET domicilio = ? WHERE id_socio = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
                preparedStatement.setString(1, "LA DIRECCIÓN corregida");
                preparedStatement.setInt(2, 1);

                System.out.println("CONSULTA ====> " + preparedStatement.toString());
                int conteo = preparedStatement.executeUpdate();
                System.out.println("Se actualizaron: " + conteo + " registros.");

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubSociosApp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {

                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClubSociosApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public static void deleteSQL() {

        int puerto =3306;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:"+puerto+"/club_socios","root","");
            
            if (conn != null) {
                System.out.println("CONEXION ESTABLECIDA CON LA BASE DE DATOS");
                
                String updateQuery = "DELETE FROM socios WHERE id_socio = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
                preparedStatement.setInt(1, 13);// El 13 es el id del que quiero borrar
                
                
                System.out.println("CONSULTA ====> "+preparedStatement.toString());
                int conteo = preparedStatement.executeUpdate();
                System.out.println("Se borraron : "+conteo+" registros.");
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubSociosApp.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (conn != null) {
                
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClubSociosApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
