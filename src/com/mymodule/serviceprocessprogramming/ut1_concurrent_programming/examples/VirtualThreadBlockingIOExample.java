package com.mymodule.serviceprocessprogramming.ut1_concurrent_programming.examples;

import java.util.concurrent.Executors;

public class VirtualThreadBlockingIOExample {
    public static void main(String[] args) {
        // Crear un pool de hilos usando un ExecutorService con hilos virtuales
        var executor = Executors.newVirtualThreadPerTaskExecutor();

        // Enviar 10 tareas que simulan bloqueos de I/O a los hilos virtuales
        for (int i = 0; i < 10; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " started.");
                try {
                    // Simular una tarea que requiere tiempo, como una operación de I/O
                    Thread.sleep(2000);
                    System.out.println("Task " + taskId + " finished.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Cerrar el executor al terminar todas las tareas
        executor.shutdown();
    }
}

