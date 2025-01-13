package segundaEv.ejercicioCalculadoraBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import conectores.ConsultasSQL;


public class Calculadora {



    public static void sumar() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce un numero para sumarlo a la base de datos");
        int num = sc.nextInt();
        int numBD = 0;

        

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM suma;")) {

            System.out.println("CONSULTA ====> " + preparedStatement.toString());
            try (ResultSet rs = preparedStatement.executeQuery()) { // Manejo de ResultSet
                while (rs.next()) {
                    System.out.println("ID : " + rs.getInt("id") + ", NUMERO EN BD = " + rs.getInt("numero"));
                    numBD = rs.getInt("numero");
                }
            }

            //ConexionBD.cerrarConexion();
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

        try (Connection conn = ConexionBD.getConexion()) { 


            String query = "SELECT * FROM suma;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            System.out.println("CONSULTA ====> " + preparedStatement.toString());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("ID : " + rs.getInt("id") + ",NUMERO EN BD= " + rs.getInt("numero"));
                numBD = rs.getInt("numero");
            }
            System.out.println("EL RESULTADO DEL NUMERO INTRODUCIDO MAS EL QUE ESTA EN LA BD ES : " + (num + numBD));

           
            PreparedStatement preparedStatement2 = conn.prepareStatement("UPDATE suma SET numero = ? WHERE id = 1");
            preparedStatement2.setInt(1, (numBD + num));
            preparedStatement2.executeUpdate();

            System.out.println("NUMERO ACTUALIZADO EN LA BASE DE DATOS");

           
            preparedStatement.close();
            preparedStatement2.close();
           // ConexionBD.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }


