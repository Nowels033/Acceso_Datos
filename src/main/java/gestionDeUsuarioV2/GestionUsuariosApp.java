package gestionDeUsuarioV2;

import java.util.List;
import java.util.Scanner;

public class GestionUsuariosApp {
	public static void main(String[] args) {
	    UsuarioServicio usuarioServicio = new UsuarioServicio();
	    Scanner sc = new Scanner(System.in);
	    boolean usuarioAutenticado = false;

	    while (true) {
	        if (!usuarioAutenticado) {
	            System.out.println("---- Inicio de Sesion ----");
	            System.out.print("Usuario: ");
	            String idUsuario = sc.nextLine();
	            System.out.print("Contraseña: ");
	            String password = sc.nextLine();

	            if (usuarioServicio.iniciarSesion(idUsuario, password)) {
	                System.out.println("Inicio de sesiun validado. Bienvenido!");
	                usuarioAutenticado = true;
	            } else {
	                System.out.println("Usuario o contraseña incorrectos. Intente de nuevo.");
	            }
	        } else {
	            System.out.println("---- Menu Principal ----");
	            System.out.println("1. Listar Usuarios");
	            System.out.println("2. Crear Usuario");
	            System.out.println("3. Cambiar Contraseña");
	            System.out.println("4. Eliminar Usuario");
	            System.out.println("5. Cerrar Sesión");
	            System.out.println("0. Salir");
	            System.out.print("Seleccione una opcion: ");
	            String opcion = sc.nextLine();

	            switch (opcion) {
	            case "1":
	                
	                List<UsuarioDTO> usuarios = usuarioServicio.listarUsuarios();

	               
	                if (usuarios.size() == 0) {
	                    System.out.println("No hay usuarios registrados.");
	                } else {
	                    System.out.println("Lista de Usuarios:");
	                   
	                    for (int i = 0; i < usuarios.size(); i++) {
	                        UsuarioDTO usuario = usuarios.get(i);
	                        System.out.println("Usuario " + (i + 1) + ":");
	                        System.out.println("ID Usuario: " + usuario.getIdUsuario());
	                        System.out.println("Tipo de Usuario: " + usuario.getTipoUsuario());
	                        System.out.println("Activo: " + (usuario.isActivo() ? "Sí" : "No"));
	                        System.out.println("------------------------");
	                    }
	                }
	                break;
	                case "2":
	                    System.out.print("ID Usuario: ");
	                    String idUsuario = sc.nextLine();
	                    System.out.print("Contraseña: ");
	                    String password = sc.nextLine();
	                    System.out.print("Tipo Usuario: ");
	                    String tipoUsuario = "solo lectura";
	                    usuarioServicio.crearUsuario(idUsuario, password, tipoUsuario);
	                    break;
	                case "3":
	                    System.out.print("ID Usuario: ");
	                    idUsuario = sc.nextLine();
	                    System.out.print("Nueva Contraseña: ");
	                    String nuevaPassword = sc.nextLine();
	                    usuarioServicio.cambiarPassword(idUsuario, nuevaPassword);
	                    break;
	                case "4":
	                    System.out.print("ID Usuario a Eliminar: ");
	                    idUsuario = sc.nextLine();
	                    usuarioServicio.eliminarUsuario(idUsuario);
	                    break;
	                case "5":
	                    System.out.println("Cerrando sesión...");
	                    usuarioAutenticado = false;
	                    break;
	                case "0":
	                    System.out.println("Saliendo...");
	                    return;
	                default:
	                    System.out.println("Opción inválida.");
	            }
	        }
	    }
	}
}