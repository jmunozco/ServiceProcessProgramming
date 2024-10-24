package com.mymodule.serviceprocessprogramming.ut1_concurrent_programming.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadPoolExample {
    public static void main(String[] args) {
        // Crear un ExecutorService que utiliza hilos virtuales para cada tarea
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        // Enviar múltiples tareas para ser ejecutadas por el pool de hilos virtuales
        for (int i = 0; i < 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " is being executed by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simular tarea
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Esperar a que terminen todas las tareas y cerrar el Executor
        executor.shutdown();
    }
}

