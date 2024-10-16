package inicio_primeros_dias;



import java.io.*;
import java.util.ArrayList;

public class FileManager2 {
    public static void main(String[] args) {

    }

    public void guardaProducto(String ruta, Producto producto){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(ruta));
            oos.writeObject(producto);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(oos != null){
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void guardaProducto(String ruta, ArrayList<Producto> lista){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(ruta));
            oos.writeObject(lista);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(oos != null){
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param ruta
     * @return
     */
    public Producto cargarProducto(String ruta){

        ObjectInputStream ois = null;
        Producto p = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(ruta));
            p = (Producto) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if(ois != null){
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return p;

    }
    public ArrayList<Producto> cargarProductos(String ruta){
        ArrayList<Producto> lista = null;
        ObjectInputStream ois = null;
        Producto p = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(ruta));

            lista = (ArrayList<Producto>) ois.readObject();

           // p = (Producto) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if(ois != null){
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}
