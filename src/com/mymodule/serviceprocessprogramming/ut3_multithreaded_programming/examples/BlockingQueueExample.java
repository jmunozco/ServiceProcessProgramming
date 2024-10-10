package com.mymodule.serviceprocessprogramming.ut3_multithreaded_programming.examples;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Example demonstrating the use of BlockingQueue to enable communication between threads.
 * This example simulates a Producer-Consumer pattern using BlockingQueue to exchange data between threads.
 */
public class BlockingQueueExample {

    public static void main(String[] args) {
        // Create a BlockingQueue with a capacity of 5 items.
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(5);

        // Create and start a producer thread.
        Thread producer = new Thread(new Producer(queue));
        producer.start();

        // Create and start a consumer thread.
        Thread consumer = new Thread(new Consumer(queue));
        consumer.start();
    }

    /**
     * Producer class that adds items to the BlockingQueue.
     */
    static class Producer implements Runnable {
        private final BlockingQueue<String> queue;

        public Producer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                // Produce and add items to the queue.
                for (int i = 1; i <= 5; i++) {
                    String item = "Item " + i;
                    System.out.println("Produced: " + item);
                    queue.put(item); // Add the item to the queue.
                    Thread.sleep(500); // Simulate time taken to produce an item.
                }
                // Signal the end of production by adding a special item.
                queue.put("END");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Consumer class that takes items from the BlockingQueue.
     */
    static class Consumer implements Runnable {
        private final BlockingQueue<String> queue;

        public Consumer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                // Consume items from the queue until "END" is encountered.
                String item;
                while (!(item = queue.take()).equals("END")) {
                    System.out.println("Consumed: " + item);
                    Thread.sleep(1000); // Simulate time taken to consume an item.
                }
                System.out.println("Consumer finished consuming items.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
