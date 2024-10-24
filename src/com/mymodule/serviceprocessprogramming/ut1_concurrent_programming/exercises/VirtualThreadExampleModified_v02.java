package com.mymodule.serviceprocessprogramming.ut1_concurrent_programming.exercises;

public class VirtualThreadExampleModified_v02 {
    public static void main(String[] args) throws InterruptedException {
        // Create 10 virtual threads
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            Thread vThread = Thread.startVirtualThread(() -> {
                // Print the thread name and its unique task ID
                System.out.println("Virtual thread " + taskId + " is running.");

                // Calculate the square of the task ID
                int square = taskId * taskId;
                System.out.println("Thread " + taskId + " calculated: " + square);

                try {
                    Thread.sleep(500); // Simulate a time-consuming task
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            // Ensure the main program waits for the thread to finish
            vThread.join();
        }
    }
}
