package com.mymodule.serviceprocessprogramming.ut1_concurrent_programming.exercises;

/**
 * Example demonstrating the difference between synchronized and non-synchronized access to shared resources.
 */
public class SynchronizedThreadExampleModified {

    public static void main(String[] args) {
        // Shared resource that will be accessed by multiple threads.
        Counter counter = new Counter();

        // Create two threads that will increment the counter without synchronization.
        Thread thread1 = new Thread(new CounterIncrementTask(counter, false));  // Non-synchronized increment
        Thread thread2 = new Thread(new CounterIncrementTask(counter, false));  // Non-synchronized increment

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
        System.out.println("Final Counter Value without synchronization: " + counter.getValue());

        // Reset counter for synchronized test.
        counter.reset();

        // Create two threads that will increment the counter with synchronization.
        Thread thread3 = new Thread(new CounterIncrementTask(counter, true));  // Synchronized increment
        Thread thread4 = new Thread(new CounterIncrementTask(counter, true));  // Synchronized increment

        // Start both threads.
        thread3.start();
        thread4.start();

        try {
            // Wait for both threads to complete their execution.
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final value of the counter after both threads have finished.
        System.out.println("Final Counter Value with synchronization: " + counter.getValue());
    }

    /**
     * Task that increments a shared counter, with an option to use synchronization.
     */
    static class CounterIncrementTask implements Runnable {
        private final Counter counter;
        private final boolean useSynchronizedMethod;

        public CounterIncrementTask(Counter counter, boolean useSynchronizedMethod) {
            this.counter = counter;
            this.useSynchronizedMethod = useSynchronizedMethod;
        }

        @Override
        public void run() {
            // Loop to increment the counter 1000 times.
            for (int i = 0; i < 10000; i++) {
                if (useSynchronizedMethod) {
                    counter.incrementSynchronized();  // Use synchronized method.
                } else {
                    counter.incrementNonSynchronized();  // Use non-synchronized method.
                }
            }
        }
    }

    /**
     * Counter class representing a shared resource.
     * Provides both synchronized and non-synchronized increment methods for comparison.
     */
    static class Counter {
        private int value = 0;

        /**
         * Synchronized method to increment the counter value safely.
         */
        public synchronized void incrementSynchronized() {
            value++;
        }

        /**
         * Non-synchronized method to increment the counter value.
         * This method may cause race conditions.
         */
        public void incrementNonSynchronized() {
            value++;
        }

        /**
         * Method to get the current value of the counter.
         */
        public int getValue() {
            return value;
        }

        /**
         * Method to reset the counter value to 0.
         */
        public void reset() {
            value = 0;
        }
    }
}
