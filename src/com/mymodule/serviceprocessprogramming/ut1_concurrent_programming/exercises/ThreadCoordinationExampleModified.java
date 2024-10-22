package com.mymodule.serviceprocessprogramming.ut1_concurrent_programming.exercises;

/**
 * Example demonstrating the use of wait(), notify(), and notifyAll() for coordinating threads.
 * This example illustrates a simple Producer-Consumer pattern where one thread writes a message and another thread reads it.
 */
public class ThreadCoordinationExampleModified {

    public static void main(String[] args) {
        // Shared Message object used for communication between the producer and consumer threads.
        Message message = new Message();

        // Create and start a writer thread (Producer).
        Thread writerThread = new Thread(new MessageWriter(message));
        writerThread.start();

        // Create and start a reader thread (Consumer).
        Thread readerThread = new Thread(new MessageReader(message));
        readerThread.start();
    }

    /**
     * Writer task that sends a message to the shared object.
     * It notifies the waiting thread (Reader) that a message is available.
     */
    static class MessageWriter implements Runnable {
        private final Message message;  // Shared message object.

        public MessageWriter(Message message) {
            this.message = message;
        }

        @Override
        public void run() {
            synchronized (message) {
                System.out.println("Writing message: Hello from Writer!");
                message.setMessage("Hello from Writer!");
                // Notify the reader thread that the message is ready.
                message.notify(); // Notify the waiting thread.
            }
        }
    }

    /**
     * Reader task that waits for a message to be available and then reads it.
     * It waits using the wait() method until the writer thread notifies it.
     */
    static class MessageReader implements Runnable {
        private final Message message;  // Shared message object.

        public MessageReader(Message message) {
            this.message = message;
        }

        @Override
        public void run() {
            synchronized (message) {
                try {
                    System.out.println("Reader is waiting for the message...");
                    // Wait until the writer thread notifies that the message is available.
                    while (message.getMessage() == null) {  // Use a loop to recheck the condition.
                        message.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Print the received message.
                System.out.println("Read message: " + message.getMessage());
            }
        }
    }

    /**
     * Shared message class used for coordination between the writer and reader threads.
     */
    static class Message {
        private String content;

        /**
         * Synchronized method to set a new message.
         */
        public synchronized void setMessage(String content) {
            this.content = content;
        }

        /**
         * Synchronized method to get the current message.
         */
        public synchronized String getMessage() {
            return content;
        }
    }
}
