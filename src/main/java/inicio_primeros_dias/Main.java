package inicio_primeros_dias;

import java.io.File;
import java.io.IOException;
import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        File fichero = new File("fichero_ej.txt");

        if(!fichero.exists()){
            System.out.println("EL FICHERO NO EXISTEE");
            try {
                fichero.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        File directorio = new File("directorio_prueba");
        if (!directorio.exists()){

            if (!directorio.mkdir()){
                System.err.println("fallo la creacion del directorio");
            }
            else {
                System.out.println("\tDirectorio creado");
            }

        }
        else {
            //YA EXISTE

            long time_stamp = directorio.lastModified();
            Date date = new Date(time_stamp);

        }


    }
}