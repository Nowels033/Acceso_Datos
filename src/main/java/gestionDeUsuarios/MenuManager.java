package gestionDeUsuarios;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;


public class MenuManager {
	public static ArrayList<UserDTO> userDTOs = UserDAO.recuperarUsuarios(UserDAO.connectionBD.getConn());	
	
	
	
	public MenuManager() {
		
	}
	
	public static void logout() {

	}

	public static void menu(UserDTO userDTO, ArrayList<UserDTO> userDTOs) {
	    Scanner sc = new Scanner(System.in);
	    String opcion = "";
	    UserDAO gestionDeUsuarios = new UserDAO();
	    do {
	        System.out.println("Bienvenido a la gestion de Usuarios");
	        System.out.println("1. Ver usuarios");
	        System.out.println("2. Crear Usuario");
	        System.out.println("3. Cambiar contraseña");
	        System.out.println("4. Borrar Usuario(SOLO ADMIN)");
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
	                if (userDTO.getTypeUser().equalsIgnoreCase("admin")) {
	                    gestionDeUsuarios.verUsuarios();
	                } else {
	                    System.out.println("No tienes permisos de administrador");
	                }
	                break;
	            case "2":
	                if (userDTO.getTypeUser().equalsIgnoreCase("admin")) {
	                    System.out.println("---------2.CREAR USUARIO----------");
	                    gestionDeUsuarios.crearUsuario();
	                }
	                else {
	                    System.out.println("No tienes permisos de administrador");
	                }

	                break;
	            case "3":
	                gestionDeUsuarios.cambiarPassword(userDTO);
	                break;
	            case "4":
	                gestionDeUsuarios.borrarUsuarioPorNombreUsuario(userDTOs,userDTO);
	                break;
	            case "0":
	                System.out.println("FIN");
	                UserDAO.getConnectionBD().cerrarConexion();
	                break;
	            default:
	                System.out.println("Opcion no valida");
	                break;
	        }
	    } while (!opcion.equalsIgnoreCase("0"));

	    System.out.println("FIN DE LA SESION");
	   

	}
public void login() {
		
		Scanner sc = new Scanner(System.in);
		String userIntroducido;
		String passIntroducido;

		System.out.println("Introduce tu nombre de Usuario: ");
		userIntroducido = sc.nextLine();

		boolean usuarioEncontrado = false;

		for (UserDTO userDTO : userDTOs) {
			if (userIntroducido.equals(userDTO.getId_userName())) {
				usuarioEncontrado = true;
				System.out.println("Usuario correcto [" + userDTO.getId_userName() + "]");
				System.out.println("Introduce tu password: ");
				passIntroducido = sc.nextLine();

				try {

					byte[] bytesOfMessage = passIntroducido.getBytes("UTF-8");
					MessageDigest md = MessageDigest.getInstance("MD5");
					byte[] theMD5digest = md.digest(bytesOfMessage);
					String strMD5digest = HexTransform.bytesToHex(theMD5digest);

					if (strMD5digest.equals(userDTO.getStrPasswordMD5())) {
						System.out.println("Contraseña correcta.");
						MenuManager.menu(userDTO, userDTOs);
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

}


