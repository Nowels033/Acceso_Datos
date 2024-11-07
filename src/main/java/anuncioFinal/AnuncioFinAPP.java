package anuncioFinal;

public class AnuncioFinAPP {
    public static void main(String[] args) {

        int numHilos = 6;
        Thread[] hilos = new Thread[numHilos];
        for (int i = 0; i < numHilos; i++) {
            Hilo hilo = new Hilo(numHilos);
            hilos[i] = new Thread(hilo);
            hilos[i].setName(""+i);
            hilos[i].start();
        }
        boolean[] arrayFin = Hilo.getArrayFin();

        boolean finTodos = false;
        int countFin=0;
        while (countFin < 6) {
           // finTodos = true;
            for (int i = 0; i < 6; i++) {
                if (!arrayFin[i]) {
                    finTodos = false;
                   // break;
                }
                else {
                    countFin++;
                    finTodos = true;
                }
                if (countFin==6) {
                    break;
                }
            }
        }
        System.out.println("TODOS LOS HILOS FINALIZARON");



    }
}
