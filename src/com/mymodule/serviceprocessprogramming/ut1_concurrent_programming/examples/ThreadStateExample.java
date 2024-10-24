package com.mymodule.serviceprocessprogramming.ut1_concurrent_programming.examples;

// The ThreadStateExample class extends the Thread class to demonstrate thread states during execution
class ThreadStateExample extends Thread {

    // The run() method contains the code that will be executed when the thread is started
    @Override
    public void run() {
        System.out.println("Thread running");
        try {
            // Simulate a running process by putting the thread to sleep for 1 second (1000 milliseconds)
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Handle the case where the thread is interrupted while sleeping
            e.printStackTrace();
        }
        // After finishing its task, the thread prints a message indicating it has completed execution
        System.out.println("Thread finished");
    }

    // The main method demonstrates the lifecycle of a thread, showing different states
    public static void main(String[] args) {
        // Create a new instance of ThreadStateExample, but the thread has not started yet
        ThreadStateExample thread = new ThreadStateExample();

        // Print the initial state of the thread, which should be NEW because it hasn't started
        System.out.println("Initial state: " + thread.getState());

        // Start the thread, which transitions the state to RUNNABLE (the thread is ready to run)
        thread.start();

        // Print the state after starting the thread, which will likely be RUNNABLE or RUNNING depending on the system
        System.out.println("State after starting: " + thread.getState());

        // The join() method is used to wait for this thread to finish its execution before proceeding with the main thread
        try {
            thread.join();  // Wait for the thread to complete
        } catch (InterruptedException e) {
            // Handle the case where the main thread is interrupted while waiting for the thread to finish
            e.printStackTrace();
        }

        // Once the thread has finished, its state will be TERMINATED
        System.out.println("State after finishing: " + thread.getState());
    }
}
