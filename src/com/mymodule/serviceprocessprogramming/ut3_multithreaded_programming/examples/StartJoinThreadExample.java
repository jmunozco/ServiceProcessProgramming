package com.mymodule.serviceprocessprogramming.ut3_multithreaded_programming.examples;

/**
 * Example demonstrating the use of start() and join() methods for thread synchronization.
 * This example shows how to start threads and wait for their completion using the join() method.
 */
public class StartJoinThreadExample {

    public static void main(String[] args) {
        // Create multiple threads.
        Thread thread1 = new CustomThread("Thread 1");
        Thread thread2 = new CustomThread("Thread 2");
        Thread thread3 = new CustomThread("Thread 3");

        // Start all threads.
        thread1.start();
        thread2.start();
        thread3.start();

        try {
            // Wait for all threads to complete using join().
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All threads have finished execution.");
    }

    /**
     * CustomThread class that extends Thread and prints its name during execution.
     */
    static class CustomThread extends Thread {
        private final String threadName;

        public CustomThread(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            System.out.println(threadName + " is running...");
            for (int i = 1; i <= 3; i++) {
                System.out.println(threadName + " - Count: " + i);
                try {
                    // Pause the thread for 500 milliseconds.
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(threadName + " has finished execution.");
        }
    }
}
