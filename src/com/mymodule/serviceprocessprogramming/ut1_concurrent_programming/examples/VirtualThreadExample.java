package com.mymodule.serviceprocessprogramming.ut1_concurrent_programming.examples;

public class VirtualThreadExample {
    public static void main(String[] args) {
        // Crear un hilo virtual que ejecuta una tarea simple
        Thread.startVirtualThread(() -> {
            System.out.println("Virtual thread is running.");
        });

        // Crear otro hilo virtual que realice una tarea más compleja
        Thread.startVirtualThread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Task " + i + " executed by virtual thread.");
                try {
                    Thread.sleep(1000); // Simular una tarea en ejecución
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
