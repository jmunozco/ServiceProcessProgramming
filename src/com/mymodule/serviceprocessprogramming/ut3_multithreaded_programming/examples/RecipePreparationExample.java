package com.mymodule.serviceprocessprogramming.ut3_multithreaded_programming.examples;

/**
 * Ejemplo que simula la preparación de ingredientes para una receta.
 * Cada tarea se ejecuta en un hilo separado y se espera a que todas las
 * tareas finalicen antes de comenzar a cocinar.
 */
public class RecipePreparationExample {

    /**
     * Clase que representa una tarea de preparación de ingredientes.
     * Implementa Runnable para ejecutarse en un hilo separado.
     */
    static class PreparationTask implements Runnable {
        private final String ingredient;

        public PreparationTask(String ingredient) {
            this.ingredient = ingredient;
        }

        @Override
        public void run() {
            System.out.println("Preparando: " + ingredient);
            try {
                Thread.sleep(1000); // Simula el tiempo de preparación de cada ingrediente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Ingrediente preparado: " + ingredient);
        }
    }

    public static void main(String[] args) {
        // Crear hilos para cada tarea de preparación de ingredientes
        Thread cutVegetables = new Thread(new PreparationTask("Verduras cortadas"));
        Thread marinateMeat = new Thread(new PreparationTask("Carne marinada"));
        Thread makeSauce = new Thread(new PreparationTask("Salsa preparada"));

        // Iniciar cada hilo
        cutVegetables.start();
        marinateMeat.start();
        makeSauce.start();

        // Esperar a que todas las tareas finalicen antes de continuar
        try {
            cutVegetables.join();
            marinateMeat.join();
            makeSauce.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ahora que todos los ingredientes están listos, comenzamos a cocinar
        System.out.println("Todos los ingredientes están listos. ¡Comencemos a cocinar!");
    }
}
