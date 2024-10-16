package inicio_primeros_dias;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class ListarDirectorio {
    public static void main(String[] args) {


        File directorio = new File("C:\\Users\\ALUMNO CCC - TARDE\\Documents");

        if (directorio.exists()){
            File [] archivos = directorio.listFiles();
            System.out.println("Directorio:---"+directorio.getAbsolutePath());
            for (int i = 0; i < archivos.length; i++) {

                System.out.println(archivos[i].getName());
            }


        }
        else {
            System.err.println("El directorio no existe");
        }


    }

    public static void escribeFihceroRAF(String ruta,String contenido){

        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(ruta,"w");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (raf != null) {
                    raf.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }
}
