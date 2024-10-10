package com.mymodule.serviceprocessprogramming.ut2_multiprocess_programming.examples;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Example demonstrating inter-process communication using pipes in Java.
 * A PipedOutputStream is connected to a PipedInputStream, simulating a message being sent between two processes.
 */
public class InterProcessCommunicationExample {

    public static void main(String[] args) {
        // Create a PipedInputStream and PipedOutputStream to establish a communication link.
        try (PipedOutputStream outputStream = new PipedOutputStream();
             PipedInputStream inputStream = new PipedInputStream(outputStream)) {

            // Create a writer thread to send a message through the output stream.
            Thread writerThread = new Thread(new WriterTask(outputStream));
            // Create a reader thread to read the message from the input stream.
            Thread readerThread = new Thread(new ReaderTask(inputStream));

            // Start both threads.
            writerThread.start();
            readerThread.start();

            // Wait for both threads to finish.
            writerThread.join();
            readerThread.join();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writer task that writes a message to the PipedOutputStream.
     */
    static class WriterTask implements Runnable {
        private final PipedOutputStream outputStream;

        public WriterTask(PipedOutputStream outputStream) {
            this.outputStream = outputStream;
        }

        @Override
        public void run() {
            try {
                // Write a message to the output stream.
                String message = "Hello from Writer!";
                outputStream.write(message.getBytes());
                System.out.println("Message sent: " + message);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Reader task that reads the message from the PipedInputStream.
     */
    static class ReaderTask implements Runnable {
        private final PipedInputStream inputStream;

        public ReaderTask(PipedInputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public void run() {
            try {
                // Read the message from the input stream.
                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);
                String message = new String(buffer, 0, bytesRead);
                System.out.println("Message received: " + message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
