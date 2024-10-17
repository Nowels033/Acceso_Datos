package ejercicio_Gestion_De_Jugadores;

import javax.swing.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // Realizar un programa en java que permita gestionar los datos de un equipo de baloncesto.
    // Crea un DAO y el DTO correspondiente
    //La app contará con un menu para seleccionar la funcionalidad entre los casos de uso CRUD,
    // una opción para generar un volcado a
    //fichero CSV de los datos de los jugadores, y una opción para salir.

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Programa de gestion de jugadores");
        menu();
        //filewritter

       // JugadorDAO.generarFicheroJugadores();
       // JugadorDAO.generarFicheroIDS();
        //System.out.println(JugadorDAO.generarIDDesdeFichero());

    }

    public static void menu() {

        //GENERAR FICHEROS
        JugadorDAO.generarFicheroIDS();
        JugadorDAO.generarFicheroJugadores();
        ArrayList<JugadorDTO> arrayJugadores = JugadorDAO.meterJugadoresDesdeFicheroEnArrayList();
        Scanner sc = new Scanner(System.in);

        int op = -1;

        do {
            System.out.println("---------------MENU---------------------");
            System.out.println("---------1.Dar de alta jugador");
            System.out.println("---------2.Mostrar todos los jugadores");
            System.out.println("---------3.Cargar Jugadores desde fichero en el programa");
            System.out.println("---------4. ");
            System.out.println("---------0. Salir");
            System.out.println("Ingrese  opcion: ");


            try {
                op = sc.nextInt();


                switch (op) {
                    case 1:
                        System.out.println("Insertar nuevo jugador");
                        JugadorDAO.crearJugador();
                        break;
                    case 2:
                        System.out.println("Mostrar todos los jugadores");
                        JugadorDAO.leerJugadoresFichero();
                        break;
                    case 3:
                        System.out.println("Buscar jugador por nombre");
                        arrayJugadores=JugadorDAO.meterJugadoresDesdeFicheroEnArrayList();
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
