package com.mymodule.serviceprocessprogramming.ut3_multithreaded_programming.examples;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Ejemplo clásico del problema de los Filósofos Comensales en concurrencia.
 * Representa la sincronización de cinco filósofos que comparten cinco palillos (recursos).
 */
public class DiningPhilosophers {

    /**
     * Clase que representa un palillo compartido entre los filósofos.
     * Utiliza un `Lock` para controlar el acceso de los filósofos.
     */
    static class Palillo {
        private final Lock lock = new ReentrantLock();

        /**
         * Método para tomar el palillo. El filósofo bloqueará el recurso.
         */
        public void tomar() {
            lock.lock();
        }

        /**
         * Método para soltar el palillo. El filósofo libera el recurso.
         */
        public void soltar() {
            lock.unlock();
        }
    }

    /**
     * Clase que representa un filósofo.
     * Cada filósofo intenta tomar los dos palillos a su izquierda y derecha para comer.
     */
    static class Filosofo implements Runnable {
        private final Palillo palilloIzquierdo;
        private final Palillo palilloDerecho;
        private final int numero;
        private final int comidasPorRealizar = 3;

        public Filosofo(int numero, Palillo palilloIzquierdo, Palillo palilloDerecho) {
            this.numero = numero;
            this.palilloIzquierdo = palilloIzquierdo;
            this.palilloDerecho = palilloDerecho;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < comidasPorRealizar; i++) {
                    pensar();
                    tomarPalillos();
                    comer();
                    soltarPalillos();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Filósofo " + numero + " fue interrumpido.");
            }
        }

        /**
         * Método que simula el pensamiento del filósofo antes de intentar comer.
         */
        private void pensar() throws InterruptedException {
            System.out.println("Filósofo " + numero + " está pensando.");
            Thread.sleep(((int) (Math.random() * 100))); // Simula tiempo de pensamiento
        }

        /**
         * Método para que el filósofo intente tomar los palillos.
         * Toma primero el palillo izquierdo y luego el derecho.
         */
        private void tomarPalillos() {
            palilloIzquierdo.tomar();
            System.out.println("Filósofo " + numero + " tomó el palillo izquierdo.");
            palilloDerecho.tomar();
            System.out.println("Filósofo " + numero + " tomó el palillo derecho.");
        }

        /**
         * Método para que el filósofo coma durante un tiempo simulado.
         */
        private void comer() throws InterruptedException {
            System.out.println("Filósofo " + numero + " está comiendo.");
            Thread.sleep(((int) (Math.random() * 100))); // Simula tiempo de comida
        }

        /**
         * Método para que el filósofo suelte ambos palillos después de comer.
         */
        private void soltarPalillos() {
            palilloIzquierdo.soltar();
            palilloDerecho.soltar();
            System.out.println("Filósofo " + numero + " soltó ambos palillos.");
        }
    }

    /**
     * Método principal para ejecutar el problema de los Filósofos Comensales.
     * Crea los cinco filósofos y los cinco palillos y gestiona la sincronización.
     */
    public static void main(String[] args) {
        // Crear los palillos (recursos compartidos)
        Palillo[] palillos = new Palillo[5];
        for (int i = 0; i < palillos.length; i++) {
            palillos[i] = new Palillo();
        }

        // Crear los filósofos y asignarles los palillos
        Filosofo[] filosofos = new Filosofo[5];
        for (int i = 0; i < filosofos.length; i++) {
            Palillo palilloIzquierdo = palillos[i];
            Palillo palilloDerecho = palillos[(i + 1) % palillos.length];
            filosofos[i] = new Filosofo(i + 1, palilloIzquierdo, palilloDerecho);
            new Thread(filosofos[i], "Filósofo " + (i + 1)).start();
        }
    }
}
