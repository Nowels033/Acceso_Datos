/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ALUMNO CCC - TARDE
 */
public class ConsultasSQL {

    private Connection conn = null;
    private int puerto = 3306;
    private String user = "root";
    private String password = "";
    private String bD = "/club_socios";

    public ConsultasSQL() {

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:" + puerto + bD, user, password);

            if (conn != null) {
                System.out.println("CONEXION ESTABLECIDA CON LA BASE DE DATOS");

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubSociosApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //GETTERS Y SETTERS
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getbD() {
        return bD;
    }

    public void setbD(String bD) {
        this.bD = bD;
    }

    public void insertSQL() {

        Scanner sc = new Scanner(System.in);

        if (this.conn != null) {
            try {
                System.out.println("Insertar");

                String query = "insert into socios (nombre,apellidos,dni,domicilio,localidad,tipo_socio,fecha_alta,fecha_baja,paga_ult_recibo,anotaciones)values (?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                System.out.println("Introduce el nombre :");
                String nombre = sc.nextLine();
                preparedStatement.setString(1, nombre);
                System.out.println("Introduce los apellidos :");
                String apellidos = sc.nextLine();
                preparedStatement.setString(2, apellidos);
                System.out.println("Introduce el DNI :");
                String dNI = sc.nextLine();
                preparedStatement.setString(3, dNI);
                System.out.println("Introduce la direccion :");
                String direccion = sc.nextLine();
                preparedStatement.setString(4, direccion);
                System.out.println("Introduce la ciudad :");
                String ciudad = sc.nextLine();
                preparedStatement.setString(5, ciudad);
                preparedStatement.setString(6, "A");
                System.out.println("Introduce la fecha de alta (FORMATO yyyy-mm-dd) :");
                String fechaAlta = sc.nextLine();
                preparedStatement.setString(7, fechaAlta);
                System.out.println("Introduce la fecha de baja (FORMATO yyyy-mm-dd) :");
                String fechaBaja = sc.nextLine();
                preparedStatement.setString(8, fechaBaja);
                System.out.println("Introduce si pago el ultimo recibo (FORMATO 'S' o 'N') :");
                String pagaUltRecibo = sc.nextLine();
                preparedStatement.setString(9, pagaUltRecibo);
                System.out.println("Introduce anotaciones :");
                String anotacion = sc.nextLine();
                preparedStatement.setString(10, anotacion);

                System.out.println("CONSULTA ====> " + preparedStatement.toString());
                int conteo = preparedStatement.executeUpdate();
                System.out.println("Se insertaron: " + conteo + " registros.");

            } catch (SQLException ex) {
                Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void updateDomicilio() {
        Scanner sc = new Scanner(System.in);
        if (this.conn != null) {

            try {
                System.out.println("INTRODUCE LA DIRECCION NUEVA : ");
                String direccion = sc.nextLine();
                System.out.println("INTRODUCE EL ID DEL SOCIO : ");
                int iDSocio = sc.nextInt();
                sc.nextLine();
                String updateQuery = "UPDATE socios SET domicilio = ? WHERE id_socio = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
                preparedStatement.setString(1, direccion);
                preparedStatement.setInt(2, iDSocio);

                System.out.println("CONSULTA ====> " + preparedStatement.toString());
                int conteo = preparedStatement.executeUpdate();
                System.out.println("Se actualizaron: " + conteo + " registros.");

            } catch (SQLException ex) {
                Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void deleteSQLPorId() {
        Scanner sc = new Scanner(System.in);
        if (this.conn != null) {

            try {

                System.out.println("INTRODUCE EL ID DEL SOCIO A ELIMINAR : ");
                int iDSocio= sc.nextInt();
                sc.nextLine();
                String updateQuery = "DELETE FROM socios WHERE id_socio = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
                preparedStatement.setInt(1, iDSocio);// El 13 es el id del que quiero borrar
                System.out.println("CONSULTA ====> " + preparedStatement.toString());
                int conteo = preparedStatement.executeUpdate();
                System.out.println("Se borraron : " + conteo + " registros.");
            } catch (SQLException ex) {
                Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
    public void verSocios() {
       
        if (this.conn != null) {

            try {
                
                
                String query = "SELECT id_socio,nombre,apellidos,dni FROM socios; ";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                

                System.out.println("CONSULTA ====> " + preparedStatement.toString());
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {                    
                    System.out.println("ID : "+rs.getObject("id_socio")+", Nombre= "+rs.getObject("nombre")+", Apellidos : "+rs.getObject("apellidos")+", DNI: "+rs.getObject("dni"));
                }
                rs.close();
                preparedStatement.close();

            } catch (SQLException ex) {
                Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
////////careverga wrap  o bocata ???????????????????????????????????????????????????????????????????????????????????????????
    public void cerrarConexion() {

        if (this.conn != null) {

            try {
                conn.close();
                System.out.println("CONEXION TERMINADA CON LA BD : " + this.bD);
            } catch (SQLException ex) {
                Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
