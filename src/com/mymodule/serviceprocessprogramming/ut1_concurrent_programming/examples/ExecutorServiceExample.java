package com.mymodule.serviceprocessprogramming.ut1_concurrent_programming.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Example of using ExecutorService to manage a pool of threads.
 * ExecutorService provides an abstraction over thread management and helps in running multiple tasks concurrently.
 */
public class ExecutorServiceExample {

    public static void main(String[] args) {
        // Create a fixed thread pool with 3 threads.
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit multiple tasks to the executor service for concurrent execution.
        for (int i = 1; i <= 5; i++) {
            executor.submit(new Task("Task " + i));
        }

        // Shutdown the executor after all tasks have been submitted.
        executor.shutdown();
    }

    /**
     * Task class that implements the Runnable interface.
     * Each task will be executed by a thread in the executor service.
     */
    static class Task implements Runnable {
        private final String name;  // Name of the task.

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            // Print the name of the task and the name of the current thread.
            System.out.println(name + " is running on thread: " + Thread.currentThread().getName());
            try {
                // Simulate some work by making the thread sleep for 1000 milliseconds.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " finished execution.");
        }
    }
}
