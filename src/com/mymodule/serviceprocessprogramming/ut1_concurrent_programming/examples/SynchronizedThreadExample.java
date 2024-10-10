package com.mymodule.serviceprocessprogramming.ut1_concurrent_programming.examples;

/**
 * Example demonstrating the use of synchronized blocks to control access to shared resources between threads.
 * This example shows how to prevent race conditions when multiple threads attempt to access and modify a shared resource.
 */
public class SynchronizedThreadExample {

    public static void main(String[] args) {
        // Shared resource that will be accessed by multiple threads.
        Counter counter = new Counter();

        // Create two threads that will increment the counter.
        Thread thread1 = new Thread(new CounterIncrementTask(counter));
        Thread thread2 = new Thread(new CounterIncrementTask(counter));

        // Start both threads.
        thread1.start();
        thread2.start();

        try {
            // Wait for both threads to complete their execution.
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final value of the counter after both threads have finished.
        System.out.println("Final Counter Value: " + counter.getValue());
    }

    /**
     * Task that increments a shared counter using synchronization to ensure thread safety.
     */
    static class CounterIncrementTask implements Runnable {
        private final Counter counter;  // Reference to the shared counter resource.

        public CounterIncrementTask(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            // Loop to increment the counter 1000 times.
            for (int i = 0; i < 1000; i++) {
                counter.increment();  // Synchronized increment to prevent race conditions.
            }
        }
    }

    /**
     * Counter class representing a shared resource that needs synchronization.
     * The increment() method is synchronized to ensure that only one thread can access it at a time.
     */
    static class Counter {
        private int value = 0;

        /**
         * Synchronized method to increment the counter value safely.
         */
        public synchronized void increment() {
            value++;
        }

        /**
         * Method to get the current value of the counter.
         */
        public int getValue() {
            return value;
        }
    }
}
