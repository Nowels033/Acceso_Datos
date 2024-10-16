package ejercicio_Gestion_De_Jugadores;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // Realizar un programa en java que permita gestionar los datos de un equipo de baloncesto.
    // Crea un DAO y el DTO correspondiente
    //La app contará con un menu para seleccionar la funcionalidad entre los casos de uso CRUD,
    // una opción para generar un volcado a
    //fichero CSV de los datos de los jugadores, y una opción para salir.

    public static void main(String[] args) {

        menu();
        //filewritter


    }

    public static void menu() {

        Scanner sc = new Scanner(System.in);
        int op = -1;

        do {
            System.out.println("---------------MENU---------------------");
            System.out.println("---------1. Insertar nuevo jugador");
            System.out.println("---------2. Mostrar todos los jugadores");
            System.out.println("---------3. Buscar jugador por nombre");
            System.out.println("---------4. Eliminar jugador");
            System.out.println("---------0. Salir");
            System.out.println("Ingrese  opcion: ");


            try {
                op = sc.nextInt();


                switch (op) {
                    case 1:
                        System.out.println("Insertar nuevo jugador");
                        break;
                    case 2:
                        System.out.println("Mostrar todos los jugadores");
                        break;
                    case 3:
                        System.out.println("Buscar jugador por nombre");
                        break;
                    case 4:
                        System.out.println("Eliminar jugador");
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opcion invalida");

                }
            } catch (InputMismatchException e) {
                System.out.println("opcion invalida");
                op = -1;
            }finally {
                sc.nextLine();


            }



        } while (op != 0);

    }
}
