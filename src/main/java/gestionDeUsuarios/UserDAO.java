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

public class UserDAO {
	 static DataBaseConnection connectionBD = new DataBaseConnection();
	

	public UserDAO() {

	}



	private static final String SQL_SELECT_ALL = "SELECT * FROM usuarios";

	public void selectAll() {
		try {
			System.out.println("SELECT * FROM usuarios");
			connectionBD.getConn().prepareStatement(SQL_SELECT_ALL).executeQuery();
		} catch (SQLException ex) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void verUsuarios() {

		if (connectionBD.getConn() != null) {

			try {

				String query = "SELECT * FROM usuarios; ";
				PreparedStatement preparedStatement = connectionBD.getConn().prepareStatement(query);

				System.out.println("CONSULTA ====> " + preparedStatement.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					System.out.println("ID : " + rs.getObject("ID") + ",Usuario= " + rs.getObject("id_usuario")
							+ ", Password= " + rs.getObject("password") + ", Ultimo_Acceso_Correcto: "
							+ rs.getObject("hora_ultimo_acceso_correcto") + ", Ultimo_Acceso_Fallido: "
							+ rs.getObject("hora_ultimo_acceso_erroneo") + ", Tipo de Usuario= "
							+ rs.getObject("type_user") + ", Activo= " + rs.getObject("activo"));
				}

				preparedStatement.close();

			} catch (SQLException ex) {
				Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				// cerrarConexion();
			}

		}

	}

	public static ArrayList<UserDTO> recuperarUsuarios(Connection conn) {
		ArrayList<UserDTO> userDTOs = new ArrayList<>();
		UserDTO userDTO = null;

		if (connectionBD.getConn() != null) {

			try {

				String query = "SELECT * FROM usuarios; ";
				PreparedStatement preparedStatement = conn.prepareStatement(query);

				System.out.println("CONSULTA ====> " + preparedStatement.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					// System.out.println("ID : "+rs.getObject("ID")+",Usuario=
					// "+rs.getObject("id_usuario")+", Password= "+rs.getObject("password")+",
					// Ultimo_Acceso_Correcto: "+rs.getObject("hora_ultimo_acceso_correcto")+",
					// Ultimo_Acceso_Fallido: "+rs.getObject("hora_ultimo_acceso_erroneo")+", Tipo
					// de Usuario= "+rs.getObject("type_user")+", Activo= "+rs.getObject("activo"));

					userDTO = new UserDTO((String) rs.getObject("id_usuario"), (String) rs.getObject("password"),
							(String) rs.getObject("type_user"), true);

					userDTOs.add(userDTO);
				}

				preparedStatement.close();

			} catch (SQLException ex) {
				Logger.getLogger(ConsultasSQL.class.getName()).log(Level.SEVERE, null, ex);
			} finally {

			}

		}
		for (int i = 0; i < userDTOs.size(); i++) {
			System.out.println(userDTOs.get(i).toString());
		}
		return userDTOs;
	}


	public void crearUsuario() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Introduce tu nombre de Usuario : ");
		String userIntroducido = sc.nextLine();
		System.out.println("Introduce tu password : ");
		String passIntroducido = sc.nextLine();

		UserDTO usuarioCreado = new UserDTO(userIntroducido, passIntroducido, "Solo Lectura");

		try {
			PreparedStatement preparedStatement = connectionBD.getConn().prepareStatement(
					"INSERT INTO usuarios (id_usuario, password, hora_ultimo_acceso_correcto, hora_ultimo_acceso_erroneo, type_user, activo) VALUES (?,?,?,?,?,?)");
			preparedStatement.setString(1, usuarioCreado.getId_userName());
			preparedStatement.setString(2, usuarioCreado.getStrPasswordMD5());
			preparedStatement.setDate(3, null);
			preparedStatement.setDate(4, null);
			preparedStatement.setString(5, usuarioCreado.getTypeUser());
			preparedStatement.setBoolean(6, usuarioCreado.isActivo());
			preparedStatement.executeUpdate();
			sc.close();
			preparedStatement.close();
		} catch (SQLException ex) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void cambiarPassword(UserDTO userDTO) {

		System.out.println("INTODUCIR NUEVA CONTRASEÑA");
		Scanner sc = new Scanner(System.in);
		String passIntroducido = sc.nextLine();
		if (passIntroducido.length() >= 5) {
			System.out.println("VUELVE A INTRODUCIR NUEVA CONTRASEÑA");
			String repiteNewPass = sc.nextLine();
			if (repiteNewPass.length() >= 5) {

				if (passIntroducido.equals(repiteNewPass)) {
					{
						byte[] bytesOfMessage = null;
						try {
							bytesOfMessage = passIntroducido.getBytes("UTF-8");

							MessageDigest md = MessageDigest.getInstance("MD5");
							byte[] theMD5digest = md.digest(bytesOfMessage);
							String strMD5digest = HexTransform.bytesToHex(theMD5digest);

							PreparedStatement preparedStatement = connectionBD.getConn()
									.prepareStatement("UPDATE usuarios SET password = ? WHERE usuarios.id_usuario = ?");
							preparedStatement.setString(1, strMD5digest);
							preparedStatement.setString(2, userDTO.getId_userName());
							preparedStatement.executeUpdate();

							userDTO.setStrPasswordMD5(strMD5digest);

							System.out.println("CONTRASEÑA CAMBIADA");
							sc.close();
							preparedStatement.close();

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

		} else {
			System.out.println("CONTRASEÑA DEBE DE SER MAYOR DE 5 CARACTERES");

		}
	}

	public void borrarUsuarioPorNombreUsuario(ArrayList<UserDTO> userDTOs, UserDTO userBD) {

		if (userBD.getTypeUser().equalsIgnoreCase("admin")) {
			Scanner sc = new Scanner(System.in);
			System.out.println("USUARIOS :");
			verUsuarios();
			System.out.println("Introduzca el ID del usuario a borrar : ");
			String idDelete = sc.nextLine();

			System.out.println("ESTAS SEGURO QUE DESEA BORRAR EL USUARIO CON ID : [ +" + idDelete + " ]");
			System.out.println("INTRODUCIR (S) o (N)");
			String borrar = sc.nextLine();
			if (borrar.equalsIgnoreCase("S")) {

				if (connectionBD.getConn() != null) {

					PreparedStatement preparedStatement;
					try {
						preparedStatement = connectionBD.getConn().prepareStatement("DELETE FROM usuarios WHERE id_usuario = ?");
						preparedStatement.setString(1, idDelete);
						preparedStatement.executeUpdate();
						System.out.println("USUARIO BORRADO");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					System.out.println("CONEXION FALLIDA CON LA BASE DE DATOS");
				}

			}
		} else {
			System.out.println("NO TIENES PERMISOS PARA BORRAR USUARIOS");
		}
	}

	public static DataBaseConnection getConnectionBD() {
		return connectionBD;
	}

	public static void setConnectionBD(DataBaseConnection connectionBD) {
		UserDAO.connectionBD = connectionBD;
	}
	
	
}
