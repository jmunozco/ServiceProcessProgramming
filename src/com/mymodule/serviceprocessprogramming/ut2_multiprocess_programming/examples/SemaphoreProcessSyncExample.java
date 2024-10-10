package com.mymodule.serviceprocessprogramming.ut2_multiprocess_programming.examples;

import java.util.concurrent.Semaphore;

/**
 * Example demonstrating the use of Semaphores to synchronize access to a shared resource between multiple processes or threads.
 * A Semaphore controls access by allowing only a specific number of permits to be acquired at a time.
 */
public class SemaphoreProcessSyncExample {

    public static void main(String[] args) {
        // Create a Semaphore with 1 permit, acting as a mutual exclusion lock (mutex).
        Semaphore semaphore = new Semaphore(1);

        // Create two threads representing processes that will compete for access to a shared resource.
        Thread process1 = new Thread(new ProcessTask(semaphore, "Process 1"));
        Thread process2 = new Thread(new ProcessTask(semaphore, "Process 2"));

        // Start both threads.
        process1.start();
        process2.start();
    }

    /**
     * Task that simulates a process trying to access a shared resource controlled by a semaphore.
     */
    static class ProcessTask implements Runnable {
        private final Semaphore semaphore;  // Semaphore used for synchronization.
        private final String processName;   // Name of the process.

        public ProcessTask(Semaphore semaphore, String processName) {
            this.semaphore = semaphore;
            this.processName = processName;
        }

        @Override
        public void run() {
            try {
                // Try to acquire a permit from the semaphore before accessing the resource.
                System.out.println(processName + " is waiting for a permit.");
                semaphore.acquire();
                System.out.println(processName + " acquired a permit.");

                // Simulate some work with the shared resource.
                System.out.println(processName + " is working...");
                Thread.sleep(2000);

                // Release the permit after finishing the work.
                System.out.println(processName + " is releasing the permit.");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
