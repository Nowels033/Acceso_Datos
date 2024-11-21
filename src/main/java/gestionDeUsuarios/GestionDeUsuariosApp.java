package gestionDeUsuarios;

public class GestionDeUsuariosApp {
    public static void main(String[] args) {

        menu();

    }
    public static void menu() {

      //  User usuario = new User("Alumno", "", "A");

   //     System.out.println(usuario.toString()+" "+usuario.getStrPasswordMD5().length());
        UserDTO user = new UserDTO();

        //user.recuperarUsuarios();

        user.login();


    }

}