package segundaEv.ejercicioCalculadoraBD;

import conectores.ConsultasSQL;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Calculadora {



    public static void sumar() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce un numero para sumarlo a la base de datos");
        int num = sc.nextInt();
        int numBD = 0;

        // Utilizamos try-with-resources para manejar la conexión y los recursos

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM suma;")) {

            System.out.println("CONSULTA ====> " + preparedStatement.toString());
            try (ResultSet rs = preparedStatement.executeQuery()) { // Manejo de ResultSet
                while (rs.next()) {
                    System.out.println("ID : " + rs.getInt("id") + ", NUMERO EN BD = " + rs.getInt("numero"));
                    numBD = rs.getInt("numero");
                }
            }

            System.out.println("EL RESULTADO DEL NUMERO INTRODUCIDO MAS EL QUE ESTA EN LA BD ES : " + (num + numBD));

        } catch (SQLException ex) {
            Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void guardarEnMemoria() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce un numero para sumarlo a la base de datos");
        int num = sc.nextInt();
        int numBD = 0;

        try (Connection conn = ConexionBD.getConexion()) { // Abrir una nueva conexión
            // Consulta para obtener el valor actual en la BD
            String query = "SELECT * FROM suma;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            System.out.println("CONSULTA ====> " + preparedStatement.toString());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("ID : " + rs.getInt("id") + ",NUMERO EN BD= " + rs.getInt("numero"));
                numBD = rs.getInt("numero");
            }
            System.out.println("EL RESULTADO DEL NUMERO INTRODUCIDO MAS EL QUE ESTA EN LA BD ES : " + (num + numBD));

            // Preparar el UPDATE
            PreparedStatement preparedStatement2 = conn.prepareStatement("UPDATE suma SET numero = ? WHERE id = 1");
            preparedStatement2.setInt(1, (numBD + num));
            preparedStatement2.executeUpdate();

            System.out.println("NUMERO ACTUALIZADO EN LA BASE DE DATOS");

            // Cerrar recursos automáticamente con try-with-resources
            preparedStatement.close();
            preparedStatement2.close();
           // conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }


