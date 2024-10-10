package com.mymodule.serviceprocessprogramming.ut1_concurrent_programming.examples;

/**
 * Basic example demonstrating the creation and execution of threads using the Thread and Runnable interfaces.
 * This example shows two approaches:
 * 1. Using the Runnable interface.
 * 2. Extending the Thread class directly.
 */
public class BasicThreadExample {

    public static void main(String[] args) {
        // Create a thread using the Runnable interface.
        Thread runnableThread = new Thread(new RunnableTask());
        // Start the thread to execute the task in parallel.
        runnableThread.start();

        // Create and start a thread using the Thread class directly.
        Thread directThread = new DirectThread();
        directThread.start();
    }

    /**
     * RunnableTask class that implements the Runnable interface.
     * This task will be executed in a separate thread.
     */
    static class RunnableTask implements Runnable {
        @Override
        public void run() {
            System.out.println("Runnable Task is running...");
            // Loop to simulate some repetitive work in the thread.
            for (int i = 1; i <= 5; i++) {
                System.out.println("Runnable Task - Count: " + i);
                try {
                    // Pause the thread for 500 milliseconds.
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Runnable Task finished.");
        }
    }

    /**
     * DirectThread class that extends the Thread class directly.
     * This class overrides the run() method to define its own behavior.
     */
    static class DirectThread extends Thread {
        @Override
        public void run() {
            System.out.println("Direct Thread is running...");
            // Loop to simulate some repetitive work in the thread.
            for (int i = 1; i <= 5; i++) {
                System.out.println("Direct Thread - Count: " + i);
                try {
                    // Pause the thread for 500 milliseconds.
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Direct Thread finished.");
        }
    }
}
