package com.mymodule.serviceprocessprogramming.ut3_multithreaded_programming.examples;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Example demonstrating the use of ReentrantLock for advanced thread synchronization.
 * This example shows how to use a lock to control access to a shared resource.
 */
public class LockSynchronizationExample {

    public static void main(String[] args) {
        // Shared resource that needs synchronization using locks.
        SharedResource resource = new SharedResource();

        // Create multiple threads that will access the shared resource.
        Thread thread1 = new Thread(new ResourceAccessTask(resource), "Thread 1");
        Thread thread2 = new Thread(new ResourceAccessTask(resource), "Thread 2");
        Thread thread3 = new Thread(new ResourceAccessTask(resource), "Thread 3");

        // Start all threads.
        thread1.start();
        thread2.start();
        thread3.start();
    }

    /**
     * Task that accesses a shared resource using a lock for synchronization.
     */
    static class ResourceAccessTask implements Runnable {
        private final SharedResource resource;

        public ResourceAccessTask(SharedResource resource) {
            this.resource = resource;
        }

        @Override
        public void run() {
            // Attempt to access the shared resource.
            resource.performTask();
        }
    }

    /**
     * Shared resource class that uses a ReentrantLock to synchronize access to its methods.
     */
    static class SharedResource {
        // Create a ReentrantLock instance to control access.
        private final Lock lock = new ReentrantLock();

        /**
         * Method that performs a task while controlling access using a lock.
         */
        public void performTask() {
            lock.lock(); // Acquire the lock before accessing the resource.
            try {
                System.out.println(Thread.currentThread().getName() + " is performing the task...");
                try {
                    // Simulate some work with the shared resource.
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " finished the task.");
            } finally {
                lock.unlock(); // Release the lock after the task is complete.
            }
        }
    }
}
