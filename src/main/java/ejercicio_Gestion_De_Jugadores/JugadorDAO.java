package ejercicio_Gestion_De_Jugadores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class JugadorDAO {


    public static void crearJugador() {

        Scanner sc = new Scanner(System.in);


        String nombre = "";
        String apellidos = "";
        int edad = 0;
        int dorsal = 0;
        String posicion = "";
        String fNacimiento = "";
        float estatura = 0;
        float peso = 0;
        boolean activo = true;

        //do{
        System.out.println("Introduce el nombre del jugador");
        nombre = sc.nextLine();
        System.out.println("Introduce el apellido del jugador : " + nombre);
        apellidos = sc.nextLine();
        System.out.println("Introduce la edad del jugador : " + nombre);
        edad = sc.nextInt();

        System.out.println("Introduce el dorsal del jugador : " + nombre);
        dorsal = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduce la posicion donde juega el jugador : " + nombre);
        posicion = sc.nextLine();
        System.out.println("Introduce la fecha de nacimiento del jugador : " + nombre);
        fNacimiento = sc.nextLine();
        System.out.println("Introduce la estatura del jugador : " + nombre);
        estatura = sc.nextFloat();
        System.out.println("Introduce el peso del jugador : " + nombre);
        peso = sc.nextFloat();
        sc.nextLine();
//        System.out.println("Introduce si el jugador esta activo : " + nombre);
//        System.out.println("INTRODUCE TRUE / FALSE");
//        activo = sc.nextBoolean();
//        sc.nextLine();

        int id = JugadorDAO.generarIDDesdeFichero();

        JugadorDTO jugador = new JugadorDTO(id, nombre, apellidos, edad, dorsal, posicion, fNacimiento, estatura, peso, activo);


        // }while(!nombre.matches("[A-Z][a-z]+"));

        if (JugadorDAO.insertarJugadorFichero(jugador)) {

            System.out.println("Jugador insertado correctamente en el fichero : jugadores.csv");
        } else {
            System.out.println("OCURRIO UN PROBLEMA AL INSERTAR EL JUGADOR EN EL FICHERO : jugadores.csv");
        }


    }

    public static void generarFicheroJugadores() {

        FileWriter fw = null;
        try {
            File archivo = new File("jugadores.csv");
            if (!archivo.exists()) {

                fw = new FileWriter("jugadores.csv");
            } else {
                fw = new FileWriter("jugadores.csv", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void generarFicheroIDS() {

        FileWriter fw = null;
        try {
            File archivo = new File("IDS.txt");
            if (!archivo.exists()) {

                fw = new FileWriter("IDS.txt");
                fw.write(0 + "\n");
            } else {
                fw = new FileWriter("IDS.txt", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static String generarStringJugador(JugadorDTO jugador) {

        String jugadorString = String.valueOf(jugador.getIdJugador());

        jugadorString += "*" + jugador.getNombre();

        jugadorString += "*" + jugador.getApellidos();

        jugadorString += "*" + jugador.getEdad();

        jugadorString += "*" + jugador.getDorsal();

        jugadorString += "*" + jugador.getPosicion();

        jugadorString += "*" + jugador.getfNacimiento();

        jugadorString += "*" + jugador.getEstatura();

        jugadorString += "*" + jugador.getPeso();

        jugadorString += "*" + jugador.isActivo();

        return jugadorString;

    }

    public static boolean insertarJugadorFichero(JugadorDTO jugador) {
        FileWriter fw = null;
        boolean insertado = false;
        try {
            fw = new FileWriter("jugadores.csv", true);
            fw.write(JugadorDAO.generarStringJugador(jugador) + "\n");
            insertado = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                insertado = false;
            }
        }

        return insertado;
    }

    public static int generarIDDesdeFichero() {
        int id;

        FileWriter fw = null;
        FileReader fr = null;
        try {

            fr = new FileReader("IDS.txt");
            BufferedReader br = new BufferedReader(fr);
            id = Integer.parseInt(br.readLine());
            id++;
            fw = new FileWriter("IDS.txt");
            fw.write(id + "\n");
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 0;


    }

    public static void leerJugadoresFichero() {

        FileReader fr = null;
        try {
            fr = new FileReader("jugadores.csv");
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static ArrayList<JugadorDTO> cargarJugadoresDesdeFicheroEnArrayList() {

        JugadorDTO jugador = null;
        ArrayList<JugadorDTO> arrayJugadores = new ArrayList<JugadorDTO>();
        FileReader fr = null;
        try {
            fr = new FileReader("jugadores.csv");
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            while (linea != null) {
                String[] datos = linea.split("\\*");


                jugador = new JugadorDTO(Integer.parseInt(datos[0]), datos[1], datos[2], Integer.parseInt(datos[3]), Integer.parseInt(datos[4]), datos[5], datos[6], Float.valueOf(datos[7]), Float.valueOf(datos[8]), Boolean.parseBoolean(datos[9]));

                System.out.println(jugador.toString());


                arrayJugadores.add(jugador);
                linea = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        System.out.println("JUGADORES CARGADOS CORRECTAMENTE EN EL SISTEMA");
        return arrayJugadores;

    }
    public static void mostrarJugadoresOrdenadosPorNombre(ArrayList<JugadorDTO> arrayJugadores) {

       //mostrarJugadoresOrdenadosPorNombre

        Collections.sort(arrayJugadores, new Comparator<JugadorDTO>() {
            @Override
            public int compare(JugadorDTO o1, JugadorDTO o2) {
                return o1.getNombre().compareToIgnoreCase(o2.getNombre());
            }
        });

        for (JugadorDTO jugador : arrayJugadores) {
            System.out.println(jugador.toString());
        }
    }
    public static void mostrarJugadoresOrdenadosPorEdad(ArrayList<JugadorDTO> arrayJugadores) {

        Collections.sort(arrayJugadores, new Comparator<JugadorDTO>() {
            @Override
            public int compare(JugadorDTO o1, JugadorDTO o2) {
                return o1.getEdad() - o2.getEdad();
            }
        });

        for (JugadorDTO jugador : arrayJugadores) {
            System.out.println(jugador.toString());
        }
    }
    public static void mostrarJugadoresPorId(ArrayList<JugadorDTO> arrayJugadores) {

        Collections.sort(arrayJugadores, new Comparator<JugadorDTO>() {
            @Override
            public int compare(JugadorDTO o1, JugadorDTO o2) {
                return o1.getIdJugador() - o2.getIdJugador();
            }
        });

        for (JugadorDTO jugador : arrayJugadores) {
            System.out.println(jugador.toString());
        }
    }
    public static void volverAGenerarFicheroDeJugadoresDesdeArrayList(ArrayList<JugadorDTO> arrayJugadores) {

        FileWriter fw = null;
        try {
            File archivo = new File("jugadores.csv");
            if (!archivo.exists()) {
                archivo.createNewFile();
            } else {
                archivo.delete();
                archivo.createNewFile();
            }
            fw = new FileWriter("jugadores.csv");
            for (JugadorDTO jugador : arrayJugadores) {
                fw.write(JugadorDAO.generarStringJugador(jugador) + "\n");
            }
            //PONEMOS EL ID DEL ULTIMO JUGADOR CREADO EN EL FICHERO DE IDS PARA SABER DONDE EMPIEZA EL FICHERO DE JUGADORES
            JugadorDAO.modificarIDFicheroID(arrayJugadores.getLast().getIdJugador());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    public static void modificarIDFicheroID(int id) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("IDS.txt");
            fw.write(id + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
