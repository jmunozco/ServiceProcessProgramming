package com.mymodule.serviceprocessprogramming.ut1_concurrent_programming.exercises;

// Clase base para la cuenta bancaria
class CuentaBancaria {
    private int saldo = 1000;

    public synchronized void retirar(int cantidad) {
        if (saldo >= cantidad) {
            saldo -= cantidad;
            System.out.println("Retirado: " + cantidad + ". Saldo actual: " + saldo);
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public synchronized void depositar(int cantidad) {
        saldo += cantidad;
        System.out.println("Depositado: " + cantidad + ". Saldo actual: " + saldo);
    }
   public int getSaldo() {
        return saldo;
    }
}

// Clase que representa a un cajero automático
class Cajero extends Thread {
    private CuentaBancaria cuenta;
    private int cantidad;
    private boolean esDeposito;

    public Cajero(CuentaBancaria cuenta, int cantidad, boolean esDeposito) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
        this.esDeposito = esDeposito;
    }

    public void run() {
        try {
            // Simulamos un pequeño retardo en la operación
            Thread.sleep(1000);
            if (esDeposito) {
                cuenta.depositar(cantidad);
            } else {
                cuenta.retirar(cantidad);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Clase principal con la ejecución de hilos
public class BankOperationSimulation {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria();
        Cajero cajero1 = new Cajero(cuenta, 200, true);  // Depósito de 200
        Cajero cajero2 = new Cajero(cuenta, 150, false); // Retiro de 150
        Cajero cajero3 = new Cajero(cuenta, 50, false);  // Retiro de 50

        // Iniciamos los hilos de cada cajero
        cajero1.start();
        cajero2.start();
        cajero3.start();

        // Esperamos a que los hilos terminen su ejecución
        try {
            cajero1.join();
            cajero2.join();
            cajero3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Mostramos el saldo final
        System.out.println("Saldo final de la cuenta: " + cuenta.getSaldo() + " euros.");
    }
}
