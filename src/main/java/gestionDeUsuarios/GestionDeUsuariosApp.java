package gestionDeUsuarios;

public class GestionDeUsuariosApp {
    public static void main(String[] args) {

        menu();

    }
    public static void menu() {

        User usuario = new User("Alumno", "123456", "A");

        System.out.println(usuario.toString());


    }
}
