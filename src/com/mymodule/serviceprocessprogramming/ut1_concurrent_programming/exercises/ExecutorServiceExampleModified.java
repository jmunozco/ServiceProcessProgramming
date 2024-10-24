package com.mymodule.serviceprocessprogramming.ut1_concurrent_programming.exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExampleModified {

    public static void main(String[] args) {
        System.out.println("=== Ejecución secuencial ===");
        runTasksSequentially();

        System.out.println("\n=== Ejecución concurrente ===");
        runTasksConcurrently();
    }

    // Método que ejecuta las tareas de forma secuencial (una tras otra)
    public static void runTasksSequentially() {
        for (int i = 1; i <= 5; i++) {
            new Task("Task " + i).run();  // Ejecuta directamente la tarea en el mismo hilo
        }
    }

    // Método que ejecuta las tareas de forma concurrente usando ExecutorService
    public static void runTasksConcurrently() {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            executor.submit(new Task("Task " + i));  // Ejecuta las tareas en hilos separados
        }

        executor.shutdown();
    }

    static class Task implements Runnable {
        private final String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + " is running on thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);  // Simula trabajo con una pausa de 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " finished execution.");
        }
    }
}
