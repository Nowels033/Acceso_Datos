package ejercicio_Gestion_De_Jugadores;

import javax.swing.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    //ESTO ES UNA PRUEBA
    //ESTOY DESDE NETBEANS
    // Realizar un programa en java que permita gestionar los datos de un equipo de baloncesto.
    // Crea un DAO y el DTO correspondiente
    //La app contará con un menu para seleccionar la funcionalidad entre los casos de uso CRUD,
    // una opción para generar un volcado a
    //fichero CSV de los datos de los jugadores, y una opción para salir.

    public static void main(String[] args) {

       // gestionDeJugadores();
        //filewritter


        gestionDeJugadores();

//        FileManager fileManager = new FileManager("el_quijote.txt");
//        fileManager.leerFichero();
//        fileManager.escribirLinea("Hola Mundo");
//        System.out.println("----------------------");
//        fileManager.leerFichero();
//        fileManager.eliminarContenidoFichero();
//        fileManager.eliminarFichero();

    }

    public static void gestionDeJugadores() {

        JOptionPane.showMessageDialog(null, "Programa de gestion de jugadores");

        //GENERAR FICHEROS
        JugadorDAO.generarFicheroIDS();
        JugadorDAO.generarFicheroJugadores();
        //CARGAR JUGADORES DESDE FICHERO EN EL PROGRAMA CON ARRAYLIST
        ArrayList<JugadorDTO> arrayJugadores = JugadorDAO.cargarJugadoresDesdeFicheroEnArrayList();
        menu(arrayJugadores);

    }
    public static void menu(ArrayList<JugadorDTO>arrayJugadores){
        Scanner sc = new Scanner(System.in);

        int op = -1;

        do {
            System.out.println("---------------MENU---------------------");
            System.out.println("---------1.Dar de alta jugador");
            System.out.println("---------2.Mostrar todos los jugadores en el fichero: jugadores.csv");
            System.out.println("---------3.Cargar Jugadores desde fichero en el programa");
            System.out.println("---------4.Mostrar Jugadores Ordenados Por Nombre");
            System.out.println("---------5.Mostrar Jugadores Ordenados Por Edad");
            System.out.println("---------6.Mostrar Jugadores Ordenados Por ID");
            System.out.println("---------7.Volver a Generar el Fichero de : jugadores.csv (con los jugadores cargados en el programa)");
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
                        System.out.println("Cargar Jugadores desde fichero en el programa");
                        arrayJugadores=JugadorDAO.cargarJugadoresDesdeFicheroEnArrayList();
                        break;
                    case 4:
                        System.out.println("Jugadores Ordenados Por Nombre :");
                        JugadorDAO.mostrarJugadoresOrdenadosPorNombre(arrayJugadores);
                        break;
                    case 5:
                        System.out.println("Jugadores Ordenados Por Edad :");
                        JugadorDAO.mostrarJugadoresOrdenadosPorEdad(arrayJugadores);
                        break;
                    case 6:
                        System.out.println("Jugadores Ordenados Por ID : ");
                        JugadorDAO.mostrarJugadoresPorId(arrayJugadores);
                        break;
                    case 7:
                        System.out.println("Volver a Generar el Fichero de : jugadores.csv (con los jugadores cargados en el programa)");
                        JugadorDAO.volverAGenerarFicheroDeJugadoresDesdeArrayList(arrayJugadores);
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
