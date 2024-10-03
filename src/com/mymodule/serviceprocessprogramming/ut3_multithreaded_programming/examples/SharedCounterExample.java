package com.mymodule.serviceprocessprogramming.ut3_multithreaded_programming.examples;

/**
 * Ejemplo de sincronización utilizando la palabra clave `synchronized`.
 * Este programa ilustra cómo varios hilos pueden acceder de manera
 * segura a un contador compartido incrementándolo sin interferencias.
 */
public class SharedCounterExample {

    /**
     * Clase que representa un contador compartido entre varios hilos.
     * La clase usa `synchronized` para garantizar la integridad al incrementar.
     */
    static class SharedCounter {
        private int count = 0;

        // Método sincronizado para incrementar el contador
        public synchronized void increment() {
            count++;
            System.out.println("Incrementado a: " + count + " por " + Thread.currentThread().getName());
        }

        // Método para obtener el valor actual del contador
        public int getCount() {
            return count;
        }
    }

    /**
     * Clase que representa una tarea que incrementa el contador compartido.
     * Implementa Runnable para poder ejecutarse en múltiples hilos.
     */
    static class CounterIncrementTask implements Runnable {
        private SharedCounter sharedCounter;

        // Constructor que inicializa el contador compartido
        public CounterIncrementTask(SharedCounter sharedCounter) {
            this.sharedCounter = sharedCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                sharedCounter.increment(); // Incrementa el contador
                try {
                    Thread.sleep(50); // Pausa para simular tiempo de procesamiento
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Método principal que crea múltiples hilos que incrementan
     * el mismo contador de manera concurrente y segura.
     */
    public static void main(String[] args) {
        SharedCounter counter = new SharedCounter(); // Crea un contador compartido

        // Crea e inicia 3 hilos que ejecutan la tarea de incremento
        Thread t1 = new Thread(new CounterIncrementTask(counter), "Hilo-1");
        Thread t2 = new Thread(new CounterIncrementTask(counter), "Hilo-2");
        Thread t3 = new Thread(new CounterIncrementTask(counter), "Hilo-3");

        t1.start();
        t2.start();
        t3.start();

        // Espera a que todos los hilos finalicen
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor final del contador: " + counter.getCount());
    }
}
