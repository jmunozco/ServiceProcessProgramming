package com.mymodule.serviceprocessprogramming.ut1_concurrent_programming.examples;

import java.util.Random;

/**
 * Basic example demonstrating the creation and execution of threads using the Thread and Runnable interfaces.
 * This example shows two approaches:
 * 1. Using the Runnable interface.
 * 2. Extending the Thread class directly.
 */
public class BasicThreadExample {

    public static void main(String[] args) {
        // Create a thread using the Runnable interface.
        Thread runnableThread = new Thread(new RunnableTask("Con interface"));
        // Start the thread to execute the task in parallel.
        runnableThread.start();

        // Create and start a thread using the Thread class directly.
        Thread directThread = new DirectThread("De forma directa");
        directThread.start();
    }

    /**
     * RunnableTask class that implements the Runnable interface.
     * This task will be executed in a separate thread.
     */
    static class RunnableTask implements Runnable {
        private String identificador; //CREO UN IDENTIFICADOR, PARA COMPROBAR MEJOR QUE HILO SE ESTÁ EJECUTANDO
        public RunnableTask(String identificador){
            this.identificador = identificador;
        }
        @Override
        public void run() {
            System.out.println("Runnable Task is running...");
            // Loop to simulate some repetitive work in the thread.
            for (int i = 1; i <= 5; i++) {
                if (i != 1){ //COMPUEBO QUE NO SEA UNO, PORQUE SE INTERCALAN AL COMIENZO, Y SALDRÍAN LOS IDENTIFICADORES DE FORMA ERRONEA EN LA PRIMERA INTERACCIÓN
                    System.out.print(identificador + " "); //Creo un identificador
                }

                System.out.println("Runnable Task - Count: " + i);
                try {
                    // Pause the thread for 500 milliseconds.
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Runnable Task finished.");
        }
    }

    /**
     * DirectThread class that extends the Thread class directly.
     * This class overrides the run() method to define its own behavior.
     */
    static class DirectThread extends Thread {
        private String identificador; //CREO UN IDENTIFICADOR, PARA COMPROBAR MEJOR QUE HILO SE ESTÁ EJECUTANDO

        public DirectThread(String identificador){
            this.identificador = identificador;
        }


        @Override
        public void run() {
            System.out.println("Direct Thread is running...");
            // Loop to simulate some repetitive work in the thread.
            for (int i = 1; i <= 6; i++) { //Aquí puedo cambiar el rango del for, para que se ejecute más veces, y comprobar la concurrencia
                if (i != 1){
                    System.out.print(identificador + " "); //Creo un identificador
                }
                System.out.println("Direct Thread - Count: " + i);
                try {
                    // Pause the thread for 500 milliseconds.
                    Thread.sleep(1500); //También puedo ver la concurrencia cambiando el tiempo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Direct Thread finished.");
        }
    }
}
