package com.mymodule.serviceprocessprogramming.utils;

public class ThreadUtils {

    // Método para iniciar un hilo con una tarea Runnable y nombre específico
    public static Thread createAndStartThread(Runnable task, String threadName) {
        Thread thread = new Thread(task, threadName);
        thread.start();
        return thread;
    }

    // Método para detener un hilo de manera segura
    public static void stopThread(Thread thread) {
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
            LoggerUtility.logInfo("Thread " + thread.getName() + " has been stopped.");
        }
    }

    // Método para pausar la ejecución de un hilo durante el tiempo especificado
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            LoggerUtility.logError("Thread sleep interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    // Método para esperar hasta que un hilo específico haya finalizado
    public static void join(Thread thread) {
        try {
            if (thread != null && thread.isAlive()) {
                thread.join();
            }
        } catch (InterruptedException e) {
            LoggerUtility.logError("Thread join interrupted: " + e.getMessage());
        }
    }
}
