package inicio_primeros_dias;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManager {

    public static void main(String[] args) {

        FileManager fileManager = new FileManager();

        String nombreArchivo = "el_quijote.txt";

        String nombreCopia = "el_quijote_copia.txt";


        if (fileManager.copiaFichero(nombreArchivo, nombreCopia)) {
            File f = new File(nombreCopia);

            System.out.println("El fichero ha sido copiado en la ruta :"+f.getAbsolutePath());
            System.out.println();

        }
        else {
            System.out.println("El fichero no se ha podido copiar");
        }


        //COPIAR CON EL OTRO METODO DE COPIAR


        String copia = fileManager.copiaFicheroInfo(nombreArchivo, nombreCopia);

        if (copia != null) {
            System.out.println("COPIA REALIZADA CON EXITO CON EL SEGUNDO METODO DE COPIAR");
            System.out.println("El fichero ha sido copiado en la ruta :"+copia);
        }
        else {
            System.out.println("El fichero "+nombreArchivo+" no se ha podido copiar");
        }

        //COPIAR PARCIAL

        if (fileManager.copiaParcialFichero(nombreArchivo, nombreCopia)) {

            System.out.println("El fichero ha sido copiado COrrectamente");
        }

        FileManagerCaracteres.escribeMensajeFichero("salida.txt","ESTOY ESCRIBIENDO UN FICHERO\nESTA ES LA SEGUNDA LIENA");

        System.out.println("----------------------------------");
        System.out.println("ESCRIBIENDO UN FICHERO CON RANDOM ACCESS");
        fileManager.escribeFihceroRAF("RAF.dat","ESTOY ESCRIBIENDO UN FICHERO\r\nES UN ARCHIVO DE TEXTO RAF");


    }
    /**
     *  Copia un fichero
     *  @param rutaOrigen Ruta del fichero origen
     *  @param rutaDestino Ruta del fichero_destino
     *  @return <true> si se ha copiado, <false> si no
     */
    public boolean copiaFichero(String rutaOrigen, String rutaDestino) {

        File ficheroOrigen = new File(rutaOrigen);
        File ficheroDestino = new File(rutaDestino);

        try {
            FileInputStream fis = new FileInputStream(ficheroOrigen);
            FileOutputStream fos = new FileOutputStream(ficheroDestino);

         //  byte[] bytesLeidos = fis.readAllBytes();
            byte[] bytesLeidos = null;

            do {
                bytesLeidos = fis.readNBytes(1024);
                if (bytesLeidos.length > 0) {
                    fos.write(bytesLeidos);
                }

            } while (bytesLeidos.length > 0);

            fos.write(bytesLeidos);
            fis.close();
            fos.close();


        }catch (FileNotFoundException e) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, e);
            return false;

        } catch (IOException e) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

        return true;
    }

    /**
     * Copia el fichero de la rutaOrigen a la rutaDestino indicada
     * @param rutaOrigen
     * @param rutaDestino
     * @ return <String> ruta del fichero copiado
     */
    public String copiaFicheroInfo(String rutaOrigen, String rutaDestino) {
        File f = new File(rutaDestino);

        if(this.copiaFichero(rutaOrigen, rutaDestino)){
            return f.getAbsolutePath();


        }
        return null;
    }

    public boolean copiaParcialFichero(String rutaOrigen, String rutaDestino) {
        InputStream in = null;
        OutputStream out = null;
        try {
             in = new FileInputStream(rutaOrigen);
             out = new FileOutputStream(rutaDestino);
            byte[] buf = new byte[1024];

            int numBytesLeidos = in.read(buf,40,500);
            System.out.println("SE HAN LEIDO "+numBytesLeidos+" BYTES DEL FICHERO");
            System.out.printf("SE HAN LEIDO "+numBytesLeidos+" BYTES DEL FICHERO");
            out.write(buf,40,numBytesLeidos);

            // numBytesLeidos = in.readNBytes(buf,541,500);
            //System.out.println("SE HAN LEIDO "+numBytesLeidos+" BYTES DEL FICHERO");
            //out.write(buf);

        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;

        } catch (IOException e) {
            e.printStackTrace();
            return false;

        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    public void escribeFihceroRAF(String ruta,String contenido){

        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(ruta,"rw");
            raf.writeBytes(contenido);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
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

