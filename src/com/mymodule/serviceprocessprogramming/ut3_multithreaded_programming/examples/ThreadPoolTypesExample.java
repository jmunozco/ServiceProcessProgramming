package com.mymodule.serviceprocessprogramming.ut3_multithreaded_programming.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Ejemplo de uso de diferentes tipos de `Thread Pool` en Java.
 * Este programa demuestra la creación de varios tipos de pools de hilos
 * (FixedThreadPool, CachedThreadPool, SingleThreadExecutor) y sus comportamientos.
 */
public class ThreadPoolTypesExample {

    /**
     * Clase que representa una tarea que se ejecutará en un hilo.
     * Cada tarea simplemente imprime un mensaje y se duerme por un tiempo.
     */
    static class SimpleTask implements Runnable {
        private final int taskId;

        public SimpleTask(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            System.out.println("Ejecutando Tarea #" + taskId + " en " + Thread.currentThread().getName());
            try {
                Thread.sleep(300); // Simula tiempo de procesamiento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Tarea #" + taskId + " completada en " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        System.out.println("---- Fixed Thread Pool ----");
        executeWithFixedThreadPool();

        System.out.println("---- Cached Thread Pool ----");
        executeWithCachedThreadPool();

        System.out.println("---- Single Thread Executor ----");
        executeWithSingleThreadExecutor();
    }

    /**
     * Demuestra el uso de un `FixedThreadPool` con un tamaño fijo de 3 hilos.
     * Este tipo de pool reutiliza una cantidad fija de hilos para ejecutar tareas.
     */
    private static void executeWithFixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 5; i++) {
            fixedThreadPool.submit(new SimpleTask(i));
        }
        fixedThreadPool.shutdown(); // No acepta nuevas tareas
    }

    /**
     * Demuestra el uso de un `CachedThreadPool` que crea nuevos hilos según sea necesario.
     * Este tipo de pool es ideal para ejecutar muchas tareas cortas de manera eficiente.
     */
    private static void executeWithCachedThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 1; i <= 5; i++) {
            cachedThreadPool.submit(new SimpleTask(i));
        }
        cachedThreadPool.shutdown(); // No acepta nuevas tareas
    }

    /**
     * Demuestra el uso de un `SingleThreadExecutor` que utiliza un único hilo para ejecutar tareas.
     * Este tipo de pool asegura que las tareas se ejecuten de forma secuencial.
     */
    private static void executeWithSingleThreadExecutor() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 5; i++) {
            singleThreadExecutor.submit(new SimpleTask(i));
        }
        singleThreadExecutor.shutdown(); // No acepta nuevas tareas
    }
}
