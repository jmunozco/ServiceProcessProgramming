package com.mymodule.serviceprocessprogramming.ut1_concurrent_programming.exercises;

// Clase base para la cuenta bancaria
class CuentaBancaria {
    private int saldo = 1000;
    private int saldoMax=1500;

    public synchronized void retirar(int cantidad,String cajero) {
        if (saldo >= cantidad) {
            saldo -= cantidad;
            System.out.println("Retirado: " + cantidad + " de "+cajero+". Saldo actual: " + saldo);
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public synchronized void depositar(int cantidad,String cajero) {
        saldo += cantidad;

        if (saldo >= saldoMax) {
            saldo -= cantidad;
            System.out.println("Se ha excedido el límite en la cuenta. Cantidad actual: "+saldo+". Puedes ingresar hasta "+(saldoMax-saldo)+" euros.");

        } else
        System.out.println("Depositado: " + cantidad +" de "+cajero+". Saldo actual: " + saldo);
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
    private String nombreCajero;



    public Cajero(CuentaBancaria cuenta, int cantidad, boolean esDeposito, String nombreCajero) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
        this.esDeposito = esDeposito;
        this.nombreCajero = nombreCajero;
    }


    public void run() {
        try {
            // Simulamos un pequeño retardo en la operación
            Thread.sleep(1000);
            if (esDeposito) {
                Thread.sleep(1000);
                cuenta.depositar(cantidad,nombreCajero);
            } else {
                cuenta.retirar(cantidad,nombreCajero);
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
        Cajero cajero1 = new Cajero(cuenta, 2000, false, "Santander");
        Cajero cajero2 = new Cajero(cuenta, 150, false, "IberCaja");
        Cajero cajero3 = new Cajero(cuenta, 50, false,"Caixa");
        Cajero cajero4 = new Cajero(cuenta, 500, false,"Unicaja");
        Cajero cajero5 = new Cajero(cuenta, 600, true,"BBVA");

        // Iniciamos los hilos de cada cajero

        cajero1.start();
        cajero2.start();
        cajero3.start();
        cajero4.start();
        cajero5.start();

        // Esperamos a que los hilos terminen su ejecución
        try {
            cajero1.join();
            cajero2.join();
            cajero3.join();
            cajero4.join();
            cajero5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Mostramos el saldo final
        System.out.println("Saldo final de la cuenta: " + cuenta.getSaldo() + " euros.");
    }
}
