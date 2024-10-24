package com.mymodule.serviceprocessprogramming.ut1_concurrent_programming.examples;

// A class that demonstrates thread interruption
public class InterruptThreadExample extends Thread {

    // The run() method contains the code to be executed when the thread starts
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (Thread.interrupted()) {
                System.out.println("Thread was interrupted, stopping execution...");
                return;
            }
            System.out.println("Thread running, count: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted during sleep.");
                return;
            }
        }
        System.out.println("Thread finished successfully.");
    }

    // Main method demonstrating the creation, start, and interruption of a thread
    public static void main(String[] args) throws InterruptedException {
        InterruptThreadExample thread = new InterruptThreadExample();
        thread.start();
        Thread.sleep(2000);  // Allow the thread to run for a short time
        thread.interrupt();  // Interrupt the thread
    }
}
