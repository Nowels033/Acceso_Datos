package inicio_primeros_dias;

import entidades.Producto;

import java.util.ArrayList;

public class PersistenciaObjetos {
    public static void main(String[] args) {

        String path ="producto.dat";
        FileManager2 fm = new FileManager2();
        Producto p1= new Producto(1, 1, "PISTOLA", "DELICUENCIA", "Tipo 1", 10, 10.0f, true);

        ArrayList<Producto> lista = new ArrayList<Producto>();

        lista.add(p1);



       fm.guardaProducto(path,  p1);

        Producto p = fm.cargarProducto(path );

        System.out.println(p.toString());
        System.out.println("----------------------------------------");
        System.out.println("CON ARRAYLIST");

        //CARGAMOS EL LISTADO DE PRODUCTOS
        fm.guardaProducto(path,  lista);
        lista = fm.cargarProductos(path);

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toString());
        }



      //  System.out.println(p.toString());


    }



}
