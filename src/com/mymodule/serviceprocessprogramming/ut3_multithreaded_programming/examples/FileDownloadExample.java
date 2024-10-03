package com.mymodule.serviceprocessprogramming.ut3_multithreaded_programming.examples;

/**
 * Ejemplo que simula la descarga de archivos en paralelo.
 * Cada descarga se ejecuta en un hilo separado y se espera a que todas
 * finalicen antes de realizar el siguiente paso.
 */
public class FileDownloadExample {

    /**
     * Clase que representa una tarea de descarga de archivos.
     * Implementa Runnable para ejecutarse en un hilo separado.
     */
    static class DownloadTask implements Runnable {
        private final String fileName;

        public DownloadTask(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            System.out.println("Iniciando descarga: " + fileName);
            try {
                Thread.sleep(2000); // Simula el tiempo de descarga
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Descarga completa: " + fileName);
        }
    }

    public static void main(String[] args) {
        // Lista de archivos a descargar
        String[] files = {"archivo1.pdf", "archivo2.jpg", "archivo3.mp4"};

        // Crear hilos para cada tarea de descarga
        Thread[] downloadThreads = new Thread[files.length];
        for (int i = 0; i < files.length; i++) {
            downloadThreads[i] = new Thread(new DownloadTask(files[i]));
            downloadThreads[i].start(); // Iniciar cada hilo
        }

        // Esperar a que todas las descargas finalicen antes de continuar
        for (Thread t : downloadThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Todos los archivos han sido descargados
        System.out.println("Todas las descargas han finalizado. ¡Listo para procesar los archivos!");
    }
}
