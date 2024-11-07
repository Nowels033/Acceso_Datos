package anuncioFinal;

import java.util.Random;

public class Hilo implements Runnable {


    private static boolean arrayFin[] = new boolean[20];
    private static int numHilos;



    public Hilo(int numHilos) {


        if (arrayFin == null) {

            arrayFin = new boolean[numHilos];

            for (int i = 0; i < numHilos; i++) {
                arrayFin[i] = false;
            }
        }


    }
    public static boolean[] getArrayFin() {
        return arrayFin;
    }

    @Override
    public void run() {
        String id = Thread.currentThread().getName();
        System.out.println("Hilo " + "[" + id + "] ====> voy a dormir");


        try {
            Thread.sleep(Math.abs(new Random().nextInt() % 3 * 1000));
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        System.out.println("Hilo " + "[ " + id + " ] " + " <<<<<<<<<desperte>>>>>>>>>");
        arrayFin[Integer.parseInt(id)] = true;
    }


}
