package inicio_primeros_dias;

public class EjemploPrintF {
    public static void main(String[] args) {

        //IMPRIMIR CON PRINTF
        //%d ==> entero
        //%f ==> flotante
        //%c ==> caracter
        //%s ==> String
        //%b ==> boolean
        //%o ==> octal
        //%x ==> hexadecimal
        //%n ==> salto de linea
        //%t ==> tabulador
        //%r ==> retorno de carro
        //%e ==> exponente
        //%E ==> exponente en mayúsculas
        //%g ==> gramos
        //%G ==> gramos en mayúsculas
        //%a ==> notaciÃ³n binaria
        //%A ==> notaciÃ³n binaria en mayúsculas
        //

        System.out.printf("%d ==> %c ==> %c\n",65,65,65+('s'-'S'));

        // %7d ==> relleno de 7 espacios

        System.out.printf("%7d ==> %7c ==> %7c\n",65,65,65+('s'-'S'));

        // %7d ==> relleno de 7 espacios desde la derecha
        // %-7d ==> relleno de 7 espacios en el lado izquierdo
        System.out.printf("%7d ==> %7c ==> %-7c\n",65,65,65+('s'-'S'));

        //%7.2f ==> relleno de 7 espacios con dos decimales
        System.out.printf("%7.2f ==> %7c ==> %-7c\n",65.F,65,65+('s'-'S'));
    }
}
