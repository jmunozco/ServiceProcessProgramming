package com.mymodule.serviceprocessprogramming.ut1_concurrent_programming.exercises;

public class VirtualThreadExampleModified_v01 {
    public static void main(String[] args) throws InterruptedException {
        // Create a virtual thread that executes a simple task
        Thread vThread1 = Thread.startVirtualThread(() -> {
            System.out.println("Virtual thread is running.");
        });

        // Create another virtual thread that performs a more complex task
        Thread vThread2 = Thread.startVirtualThread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Task " + i + " executed by virtual thread.");
                try {
                    Thread.sleep(1000); // Simulate a task in execution
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Ensure that the main program waits for the threads to finish
        vThread1.join(); // Wait for the first thread to finish
        vThread2.join(); // Wait for the second thread to finish
    }
}
