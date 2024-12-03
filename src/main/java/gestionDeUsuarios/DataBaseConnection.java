package gestionDeUsuarios;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DataBaseConnection {
	private Connection conn = null;
    private int puerto = 3306;
    private String user = "root";
    private String password = "";
    private String bD = "/gestion_usuarios";

    public DataBaseConnection() {

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:" + puerto + bD, user, password);

            if (conn != null) {
                System.out.println("CONEXION ESTABLECIDA CON LA BASE DE DATOS");

            } else {
                System.out.println("CONEXION FALLIDA CON LA BASE DE DATOS");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
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

   
}
