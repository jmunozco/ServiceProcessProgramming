package com.mymodule.serviceprocessprogramming.utils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrencyUtils {

    // Método para ejecutar una operación con un bloqueo de manera segura
    public static void executeWithLock(Lock lock, Runnable task) {
        lock.lock();
        try {
            task.run();
        } finally {
            lock.unlock();
        }
    }

    // Método para crear un nuevo bloqueo de tipo ReentrantLock
    public static Lock createReentrantLock() {
        return new ReentrantLock();
    }
}
