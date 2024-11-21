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

            } else {
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

    /// ///////////////////////////////////////////////////////////////////////////////////////////
    /// ///////////////////////////////////////////////////////////////////////////////////////////
    /// /////////////////////METODOS DE ACCESO A LA BASE DE DATOS//////////////////////////////////
    /// ///////////////////////////////////////////////////////////////////////////////////////////
    /// ///////////////////////////////////////////////////////////////////////////////////////////


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
                    System.out.println("ID : " + rs.getObject("ID") + ",Usuario= " + rs.getObject("id_usuario") + ", Password= " + rs.getObject("password") + ", Ultimo_Acceso_Correcto: " + rs.getObject("hora_ultimo_acceso_correcto") + ", Ultimo_Acceso_Fallido: " + rs.getObject("hora_ultimo_acceso_erroneo") + ", Tipo de Usuario= " + rs.getObject("type_user") + ", Activo= " + rs.getObject("activo"));
                }

                preparedStatement.close();

            } catch (SQLException ex) {
                Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
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
                    // System.out.println("ID : "+rs.getObject("ID")+",Usuario= "+rs.getObject("id_usuario")+", Password= "+rs.getObject("password")+", Ultimo_Acceso_Correcto: "+rs.getObject("hora_ultimo_acceso_correcto")+", Ultimo_Acceso_Fallido: "+rs.getObject("hora_ultimo_acceso_erroneo")+", Tipo de Usuario= "+rs.getObject("type_user")+", Activo= "+rs.getObject("activo"));

                    user = new User((String) rs.getObject("id_usuario"), (String) rs.getObject("password"), (String) rs.getObject("type_user"));

                    users.add(user);
                }

                preparedStatement.close();

            } catch (SQLException ex) {
                Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {

            }


        }
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).toString());
        }
        return users;
    }

    public void login() {
        ArrayList<User> users = recuperarUsuarios(this.conn);
        Scanner sc = new Scanner(System.in);
        String userIntroducido;
        String passIntroducido;

        System.out.println("Introduce tu nombre de Usuario: ");
        userIntroducido = sc.nextLine();

        boolean usuarioEncontrado = false;

        for (User user : users) {
            if (userIntroducido.equals(user.getId_userName())) {
                usuarioEncontrado = true;
                System.out.println("Usuario correcto [" + user.getId_userName() + "]");
                System.out.println("Introduce tu password: ");
                passIntroducido = sc.nextLine();

                try {

                    byte[] bytesOfMessage = passIntroducido.getBytes("UTF-8");
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    byte[] theMD5digest = md.digest(bytesOfMessage);
                    String strMD5digest = HexTransform.bytesToHex(theMD5digest);


                    if (strMD5digest.equalsIgnoreCase(user.getStrPasswordMD5())) {
                        System.out.println("Contraseña correcta.");
                        menu(user);
                        return;
                    } else {
                        System.out.println("Contraseña incorrecta.");
                    }
                } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
                    throw new RuntimeException("Error al procesar la contraseña.", e);
                }
            }
        }

        if (!usuarioEncontrado) {
            System.out.println("Usuario no encontrado.");
        }
    }

    public static void logout() {


    }

    public void menu(User user) {
        Scanner sc = new Scanner(System.in);
        String opcion = "";
        UserDTO gestionDeUsuarios = new UserDTO();
        do {
            System.out.println("Bienvenido a la gestion de Usuarios");
            System.out.println("1. Ver usuarios");
            System.out.println("2. Crear Usuario");
            System.out.println("3. Cambiar contraseña");
            System.out.println("-------------------------");
            System.out.println("-------------------------");
            System.out.println("-------------------------");
            System.out.println("-------------------------");
            System.out.println("0. Salir");
            System.out.println();
            System.out.println();
            System.out.println("Ingrese opcion: ");
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    if (user.getTypeUser().equalsIgnoreCase("admin")) {
                        gestionDeUsuarios.verUsuarios();
                    } else {
                        System.out.println("No tienes permisos de administrador");
                    }
                    break;
                case "2":
                    if (user.getTypeUser().equalsIgnoreCase("admin")) {
                        System.out.println("---------2.CREAR USUARIO----------");
                        gestionDeUsuarios.crearUsuario();
                    }
                    else {
                        System.out.println("No tienes permisos de administrador");
                    }

                    break;
                case "3":
                    gestionDeUsuarios.cambiarPassword(user);
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion.equalsIgnoreCase("0"));

        System.out.println("FIN DE LA SESION");
        gestionDeUsuarios.cerrarConexion();

    }

    public void crearUsuario() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce tu nombre de Usuario : ");
        String userIntroducido = sc.nextLine();
        System.out.println("Introduce tu password : ");
        String passIntroducido = sc.nextLine();

        User usuarioCreado = new User(userIntroducido, passIntroducido, "Solo Lectura");

        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO usuarios (id_usuario, password, hora_ultimo_acceso_correcto, hora_ultimo_acceso_erroneo, type_user, activo) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, usuarioCreado.getId_userName());
            preparedStatement.setString(2, usuarioCreado.getStrPasswordMD5());
            preparedStatement.setDate(3, null);
            preparedStatement.setDate(4, null);
            preparedStatement.setString(5, usuarioCreado.getTypeUser());
            preparedStatement.setBoolean(6, usuarioCreado.isActivo());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void cambiarPassword(User user) {

        System.out.println("INTODUCIR NUEVA CONTRASEÑA");
        Scanner sc = new Scanner(System.in);
        String passIntroducido = sc.nextLine();
        System.out.println("VUELVE A INTRODUCIR NUEVA CONTRASEÑA");
        String repiteNewPass = sc.nextLine();

        if (passIntroducido.equals(repiteNewPass)) {
            {
                byte[] bytesOfMessage = null;
                try {
                    bytesOfMessage = passIntroducido.getBytes("UTF-8");

                    MessageDigest md = MessageDigest.getInstance("MD5");
                    byte[] theMD5digest = md.digest(bytesOfMessage);
                    String strMD5digest = HexTransform.bytesToHex(theMD5digest);

                    PreparedStatement preparedStatement = conn.prepareStatement("UPDATE usuarios SET password = ? WHERE usuarios.id_usuario = ?");
                    preparedStatement.setString(1, strMD5digest);
                    preparedStatement.setString(2, user.getId_userName());
                    preparedStatement.executeUpdate();

                    user.setStrPasswordMD5(strMD5digest);

                    System.out.println("CONTRASEÑA CAMBIADA");

                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


        }
    }
}