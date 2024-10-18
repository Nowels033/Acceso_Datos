/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio_Gestion_De_Jugadores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author ALUMNO CCC - TARDE
 */
public class FileManager {

    private FileWriter fw;
    private FileReader fr;
    private String rutaFichero;
    
//    public static void main(String[] args) {
//        
//    }

    /**
     *
     * @param rutaFichero
     */
    public FileManager(String rutaFichero) {
        try {
            this.fw = new FileWriter(rutaFichero,true);
            this.fr = new FileReader(rutaFichero);
            this.rutaFichero = rutaFichero;

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (fr != null) {
                    fr.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public FileWriter getFw() {
        return fw;
    }

    public void setFw(FileWriter fw) {
        this.fw = fw;
    }

    public FileReader getFr() {
        return fr;
    }

    public void setFr(FileReader fr) {
        this.fr = fr;
    }

    public String getRutaFichero() {
        return rutaFichero;
    }

    public void setRutaFichero(String rutaFichero) {
        this.rutaFichero = rutaFichero;
    }

    /**
     *
     * @param linea
     */
    public void escribirLinea(String linea) {

        try {
            this.fw = new FileWriter(rutaFichero,true);
            this.fw.write(linea);
            this.fw.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     *
     */
    public void leerFichero() {
        try {
            this.fr = new FileReader(this.rutaFichero);
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     *
     * @return
     */
    public String getInformacionFichero() {
        String informacion = "";
        BufferedReader br=null;
        try {
            this.fr = new FileReader(this.rutaFichero);
            br = new BufferedReader(fr);
            String linea = br.readLine();
            while (linea != null) {
                informacion += linea + "\n";
                linea = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fr != null) {
                    fr.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return informacion;
    }
    public void eliminarContenidoFichero() {
        try {
            this.fr = new FileReader(this.rutaFichero);
            this.fw = new FileWriter(this.rutaFichero, false);
            System.out.println("Contenido borrado del fichero: " + this.rutaFichero);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (fr != null) {
                    fr.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    public void eliminarFichero() {
        File fichero = new File(this.rutaFichero);
        if (fichero.exists()) {
            fichero.delete();
            System.out.println("Fichero eliminado: " + this.rutaFichero);
        }
        else {
            System.out.println("Fichero no existe: " + this.rutaFichero);
        }
    }


}
