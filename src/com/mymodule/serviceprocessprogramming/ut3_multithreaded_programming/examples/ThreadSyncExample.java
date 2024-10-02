// Definición del paquete correspondiente a la estructura del proyecto
package com.mymodule.serviceprocessprogramming.ut3_multithreaded_programming.examples;

/**
 * Ejemplo de sincronización de hilos utilizando wait() y notify().
 * Este programa simula la comunicación entre un hilo productor (Entrega)
 * y un hilo consumidor (Recibe) que comparten un recurso (Bandeja).
 */
public class ThreadSyncExample {

    /**
     * Método auxiliar para pausar la ejecución de un hilo durante un
     * tiempo específico, simulando un retraso en la operación.
     *
     * @param amount Tiempo en milisegundos que se desea pausar.
     */
    static void esperar(long amount) {
        try {
            Thread.sleep(amount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clase que representa una bandeja compartida entre el hilo productor
     * y el hilo consumidor. Utiliza un indicador `disponible` para saber
     * si la bandeja tiene un elemento disponible o está vacía.
     */
    static class Bandeja {
        private boolean disponible = false;

        /**
         * Método sincronizado para colocar un elemento en la bandeja.
         * Si la bandeja ya tiene un elemento (disponible == true), el hilo
         * se suspenderá utilizando wait() hasta que el elemento sea
         * retirado por el consumidor.
         */
        public synchronized void poner() {
            while (disponible) {
                try {
                    wait(); // Suspende el hilo hasta que el consumidor notifique
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Una vez que la bandeja está vacía, se coloca un nuevo elemento
            disponible = true;
            System.out.println("Entrega: Elemento puesto en la bandeja.");
            notify(); // Notifica al consumidor que se ha colocado un nuevo elemento
        }

        /**
         * Método sincronizado para retirar un elemento de la bandeja.
         * Si la bandeja está vacía (disponible == false), el hilo se
         * suspenderá utilizando wait() hasta que el productor coloque
         * un nuevo elemento.
         */
        public synchronized void tomar() {
            while (!disponible) {
                try {
                    wait(); // Suspende el hilo hasta que el productor notifique
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Una vez que hay un elemento disponible, se retira de la bandeja
            disponible = false;
            System.out.println("Recibe: Elemento recogido de la bandeja.");
            notify(); // Notifica al productor que la bandeja está vacía nuevamente
        }
    }

    /**
     * Clase que representa la acción de entrega. Implementa la interfaz
     * Runnable para ejecutarse en un hilo separado. El productor coloca
     * un nuevo elemento en la bandeja cada cierto intervalo de tiempo.
     */
    static class Entrega implements Runnable {
        private Bandeja bandeja;

        // Constructor que inicializa la bandeja compartida
        Entrega(Bandeja b) {
            this.bandeja = b;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                bandeja.poner();  // Coloca un nuevo elemento en la bandeja
                esperar(1000);    // Pausa de 1 segundo entre colocaciones
            }
        }
    }

    /**
     * Clase que representa la acción de recepción. Implementa la interfaz
     * Runnable para ejecutarse en un hilo separado. El consumidor retira
     * un elemento de la bandeja cada cierto intervalo de tiempo.
     */
    static class Recibe implements Runnable {
        private Bandeja bandeja;

        // Constructor que inicializa la bandeja compartida
        Recibe(Bandeja b) {
            this.bandeja = b;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                bandeja.tomar();  // Retira un elemento de la bandeja
                esperar(1000);    // Pausa de 1 segundo entre recepciones
            }
        }
    }

    /**
     * Método principal para ejecutar el programa. Crea las instancias de
     * la bandeja compartida, el productor y el consumidor, y los inicia
     * en hilos separados para demostrar la sincronización con wait/notify.
     */
    public static void main(String[] args) {
        Bandeja b = new Bandeja();                     // Bandeja compartida
        Thread e = new Thread(new Entrega(b));         // Hilo del productor
        Thread r = new Thread(new Recibe(b));          // Hilo del consumidor
        e.start();  // Inicia el hilo del productor
        r.start();  // Inicia el hilo del consumidor
    }
}
