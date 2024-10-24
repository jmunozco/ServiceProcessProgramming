package com.mymodule.serviceprocessprogramming.ut1_concurrent_programming.exercises;

/**
 * Modified example to make thread concurrency more observable by using different sleep times.
 */
public class BasicThreadExampleModified {

    public static void main(String[] args) {
        // Create a thread using the Runnable interface.
        Thread runnableThread = new Thread(new RunnableTask(1));
        // Start the thread to execute the task in parallel.
        runnableThread.start();

        // Create and start a thread using the Thread class directly.
        Thread directThread = new DirectThread(2);
        directThread.start();
    }

    /**
     * RunnableTask class that implements the Runnable interface.
     * This task will be executed in a separate thread.
     */
    static class RunnableTask implements Runnable {
        private int threadId;

        public RunnableTask(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            System.out.println("Runnable Task " + threadId + " is running...");
            // Loop to simulate some repetitive work in the thread.
            for (int i = 1; i <= 5; i++) {
                System.out.println("Runnable Task " + threadId + " - Count: " + i);
                try {
                    // Pause the thread for 300 milliseconds (shorter sleep time).
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Runnable Task " + threadId + " finished.");
        }
    }

    /**
     * DirectThread class that extends the Thread class directly.
     * This class overrides the run() method to define its own behavior.
     */
    static class DirectThread extends Thread {
        private int threadId;

        public DirectThread(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            System.out.println("Direct Thread " + threadId + " is running...");
            // Loop to simulate some repetitive work in the thread.
            for (int i = 1; i <= 5; i++) {
                System.out.println("Direct Thread " + threadId + " - Count: " + i);
                try {
                    // Pause the thread for 700 milliseconds (longer sleep time).
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Direct Thread " + threadId + " finished.");
        }
    }
}
