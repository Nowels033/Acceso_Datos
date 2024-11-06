/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conectores;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author ALUMNO CCC - TARDE
 */
public class ClubSociosApp {

    public static void main(String[] args) {
        menu();

    }

    public static void menu() {
        ConsultasSQL sql = new ConsultasSQL();

        Scanner sc = new Scanner(System.in);

        int op = -1;

        do {
            System.out.println("---------------MENU---------------------");
            System.out.println("---------1.DAR DE ALTA SOCIO");
            System.out.println("---------2.ACTUALIZAR DIRECCION DE SOCIO POR ID");
            System.out.println("---------3.ELIMINAR SOCIO POR ID");
            System.out.println("---------4.VER SOCIOS");
            System.out.println("---------0. Salir");
            System.out.println("Ingrese opcion: ");

            try {
                op = sc.nextInt();

                switch (op) {
                    case 1:
                        System.out.println("---------1.DAR DE ALTA SOCIO");
                        sql.insertSQL();
                        break;
                    case 2:
                        System.out.println("---------2.ACTUALIZAR DIRECCION DE SOCIO POR ID");
                        sql.updateDomicilio();
                        break;
                    case 3:
                        System.out.println("---------3.ELIMINAR SOCIO POR ID");
                        sql.deleteSQLPorId();
                        break;
                    case 4:
                        System.out.println("---------4.VER SOCIOS");
                         sql.verSocios();
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        sql.cerrarConexion();
                        break;
                    default:
                        System.out.println("Opcion invalida");

                }
            } catch (InputMismatchException e) {
                System.out.println("opcion invalida");
                op = -1;
                sc.nextLine();
            } finally {
                sc.nextLine();

            }

        } while (op != 0);

    }

//    public static void insertSQL() {
//        int puerto = 3306;
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:" + puerto + "/club_socios", "root", "");
//
//            if (conn != null) {
//                System.out.println("CONEXION ESTABLECIDA CON LA BASE DE DATOS");
//
//                String query = "insert into socios (nombre,apellidos,dni,domicilio,localidad,tipo_socio,fecha_alta,fecha_baja,paga_ult_recibo,anotaciones)values (?,?,?,?,?,?,?,?,?,?)";
//                PreparedStatement preparedStatement = conn.prepareStatement(query);
//                preparedStatement.setString(1, "Noel");
//                preparedStatement.setString(2, "Dominguez");
//                preparedStatement.setString(3, "222222222");
//                preparedStatement.setString(4, "Calle no te importa");
//                preparedStatement.setString(5, "Madrid");
//                preparedStatement.setString(6, "A");
//                preparedStatement.setString(7, "2010-11-25");
//                preparedStatement.setString(8, "2022-11-25");
//                preparedStatement.setString(9, "S");
//                preparedStatement.setString(10, "Se dio de baja:/");
//
//                System.out.println("CONSULTA ====> " + preparedStatement.toString());
//                int conteo = preparedStatement.executeUpdate();
//                System.out.println("Se insertaron: " + conteo + " registros.");
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ClubSociosApp.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//
//            } catch (SQLException ex) {
//                Logger.getLogger(ClubSociosApp.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//    }
//
//    public static void update() {
//
//        int puerto = 3306;
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:" + puerto + "/club_socios", "root", "");
//
//            if (conn != null) {
//                System.out.println("CONEXION ESTABLECIDA CON LA BASE DE DATOS");
//
//                String updateQuery = "UPDATE socios SET domicilio = ? WHERE id_socio = ?";
//                PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
//                preparedStatement.setString(1, "LA DIRECCIÓN corregida");
//                preparedStatement.setInt(2, 1);
//
//                System.out.println("CONSULTA ====> " + preparedStatement.toString());
//                int conteo = preparedStatement.executeUpdate();
//                System.out.println("Se actualizaron: " + conteo + " registros.");
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ClubSociosApp.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            if (conn != null) {
//
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ClubSociosApp.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//
//    }
//
//    public static void deleteSQL() {
//
//        int puerto =3306;
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:"+puerto+"/club_socios","root","");
//            
//            if (conn != null) {
//                System.out.println("CONEXION ESTABLECIDA CON LA BASE DE DATOS");
//                
//                String updateQuery = "DELETE FROM socios WHERE id_socio = ?";
//                PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
//                preparedStatement.setInt(1, 13);// El 13 es el id del que quiero borrar
//                
//                
//                System.out.println("CONSULTA ====> "+preparedStatement.toString());
//                int conteo = preparedStatement.executeUpdate();
//                System.out.println("Se borraron : "+conteo+" registros.");
//                
//                
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ClubSociosApp.class.getName()).log(Level.SEVERE, null, ex);
//        }finally{
//            if (conn != null) {
//                
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ClubSociosApp.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//    }
}
