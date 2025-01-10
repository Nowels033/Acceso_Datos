package prueba2Ev;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {

    menu();

    }

    public static void menu(){
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("---------------MENU---------------------");
            System.out.println("1. Sumar ");
            System.out.println("2. Guardar en memoria ");
            System.out.println("3. Salir ");
            System.out.println("Ingrese opcion: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    prueba.sumar();
                    break;
                case 2:
                    prueba.guardarEnMemoria();
                    break;
                case 3:
                    System.out.println("Adios");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        }while (opcion != 3);


    }
}
