package prueba2Ev;

import java.io.*;
import java.util.Scanner;

public class prueba {
    static File fichero = new File("sumas.txt");
    static FileWriter fw = null;
    static FileReader fr = null;
    static Scanner sc = new Scanner(System.in);


    public static void sumar() {
        comprobarFichero(fichero);
        try {
            fr = new FileReader(fichero);
            int lecturaFich = fr.read();
            System.out.println("INTRODUCE UN NUMERO PARA SUMARLO AL FICHERO");
            int num = sc.nextInt();
            lecturaFich = lecturaFich + num;
            System.out.println("La suma total es: " + lecturaFich);
            fr.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void guardarEnMemoria() {

        comprobarFichero(fichero);

        try {
            fr = new FileReader(fichero);
            int lecturaFich = fr.read();
            System.out.println("INTRODUCE UN NUMERO PARA SUMARLO AL FICHERO");
            int num = sc.nextInt();
            lecturaFich = lecturaFich + num;
            System.out.println("La suma total es: " + lecturaFich);
            fr.close();

            fw = new FileWriter(fichero);
            fw.write(lecturaFich);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void comprobarFichero(File fichero) {
        if (!fichero.exists()) {

            try {
                fichero.createNewFile();
                fw = new FileWriter(fichero);
                fw.write(0);
                fw.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

