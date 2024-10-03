package com.mymodule.serviceprocessprogramming.ut3_multithreaded_programming.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Ejemplo de uso de `ExecutorService` y `ThreadPool` para gestionar la ejecución
 * de múltiples tareas concurrentes.
 * Este programa demuestra la creación de un pool de hilos para procesar varias
 * tareas en paralelo.
 */
public class ThreadPoolExample {

    /**
     * Clase que representa una tarea que se ejecutará en un hilo.
     * Cada tarea simplemente imprime un mensaje y se duerme por un tiempo.
     */
    static class Task implements Runnable {
        private final int taskId;

        public Task(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            System.out.println("Ejecutando Tarea #" + taskId + " en " + Thread.currentThread().getName());
            try {
                Thread.sleep(500); // Simula el tiempo de ejecución de la tarea
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Tarea #" + taskId + " completada en " + Thread.currentThread().getName());
        }
    }

    /**
     * Método principal para ejecutar el ejemplo. Crea un pool de 3 hilos
     * y asigna 5 tareas a ser ejecutadas por el pool.
     */
    public static void main(String[] args) {
        // Crea un pool de hilos con un tamaño fijo de 3 hilos
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Envía 5 tareas al pool de hilos
        for (int i = 1; i <= 5; i++) {
            executorService.submit(new Task(i));
        }

        // Cierra el pool de hilos para evitar que acepte nuevas tareas
        executorService.shutdown();
    }
}
