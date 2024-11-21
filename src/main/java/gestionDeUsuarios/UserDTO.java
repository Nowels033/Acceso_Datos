package gestionDeUsuarios;

import conectores.ClubSociosApp;
import conectores.ConsultasSQL;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDTO {

    private Connection conn = null;
    private int puerto = 3306;
    private String user = "root";
    private String password = "";
    private String bD = "/gestion_usuarios";

    public UserDTO() {

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:" + puerto + bD, user, password);

            if (conn != null) {
                System.out.println("CONEXION ESTABLECIDA CON LA BASE DE DATOS");

            }
            else {
                System.out.println("CONEXION FALLIDA CON LA BASE DE DATOS");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubSociosApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

    public String getbD() {
        return bD;
    }

    public void setbD(String bD) {
        this.bD = bD;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////METODOS DE ACCESO A LA BASE DE DATOS//////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////



    private static final String SQL_SELECT_ALL = "SELECT * FROM usuarios";

    public void selectAll() {
        try {
            System.out.println("SELECT * FROM usuarios");
            conn.prepareStatement(SQL_SELECT_ALL).executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void verUsuarios() {

        if (this.conn != null) {

            try {


                String query = "SELECT * FROM usuarios; ";
                PreparedStatement preparedStatement = conn.prepareStatement(query);


                System.out.println("CONSULTA ====> " + preparedStatement.toString());
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    System.out.println("ID : "+rs.getObject("ID")+",Usuario= "+rs.getObject("id_usuario")+", Password= "+rs.getObject("password")+", Ultimo_Acceso_Correcto: "+rs.getObject("hora_ultimo_acceso_correcto")+", Ultimo_Acceso_Fallido: "+rs.getObject("hora_ultimo_acceso_erroneo")+", Tipo de Usuario= "+rs.getObject("type_user")+", Activo= "+rs.getObject("activo"));
                }

                preparedStatement.close();

            } catch (SQLException ex) {
                Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
            }finally {
                cerrarConexion();
            }

        }

    }
    public void cerrarConexion() {

        if (this.conn != null) {
            try {
                conn.close();
                System.out.println("CONEXION TERMINADA CON LA BD : " + this.bD);
            } catch (SQLException ex) {
                Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
private static ArrayList<User> recuperarUsuarios(Connection conn) {
    ArrayList<User> users = new ArrayList<>();
    User user = null;


    if (conn != null) {

        try {


            String query = "SELECT * FROM usuarios; ";
            PreparedStatement preparedStatement = conn.prepareStatement(query);


            System.out.println("CONSULTA ====> " + preparedStatement.toString());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("ID : "+rs.getObject("ID")+",Usuario= "+rs.getObject("id_usuario")+", Password= "+rs.getObject("password")+", Ultimo_Acceso_Correcto: "+rs.getObject("hora_ultimo_acceso_correcto")+", Ultimo_Acceso_Fallido: "+rs.getObject("hora_ultimo_acceso_erroneo")+", Tipo de Usuario= "+rs.getObject("type_user")+", Activo= "+rs.getObject("activo"));

                user = new User((String)rs.getObject("id_usuario"),(String)rs.getObject("password"),(String)rs.getObject("type_user"));

                users.add(user);
            }

            preparedStatement.close();

        } catch (SQLException ex) {
            Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
        }finally {

        }


    }
    for (int i = 0; i < users.size(); i++) {
        System.out.println(users.get(i).toString());
    }
    return users;
}
    public  void login() {

        ArrayList<User> users= recuperarUsuarios(this.conn);
        Scanner sc = new Scanner(System.in);
        String userIntroducido ;
        String passIntroducido;

        System.out.println("Introduce tu nombre de Usuario : ");
        userIntroducido = sc.nextLine();


        for (int i = 0; i < users.size(); i++) {
            if (userIntroducido.equals(users.get(i).getId_userName())) {
                System.out.println("Usuario correcto ["+users.get(i).getId_userName()+"]");
                System.out.println("Introduce tu password : ");
                passIntroducido = sc.nextLine();
                byte[] bytesOfMessage = null;
                try {
                    bytesOfMessage = passIntroducido.getBytes("UTF-8");

                    MessageDigest md = MessageDigest.getInstance("MD5");
                    byte[] theMD5digest = md.digest(bytesOfMessage);
                    String strMD5digest = HexTransform.bytesToHex(theMD5digest);

                    for (int j = 0; j < users.size(); j++) {
                         if (strMD5digest.equals(users.get(j).getStrPasswordMD5())) {
                             System.out.println("Contraseña correcta ["+users.get(j).getStrPasswordMD5()+"]");


                         }
                    }
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }

            }
        }


    }

    public static void logout() {


    }
}
