public class CuentaBancaria {
    private int saldo;

    public CuentaBancaria(int saldo) {
        this.saldo = saldo;
    }

    public synchronized void retirar(int cantidad) throws InterruptedException {
        System.out.println("Realizando retiro.");
        this.saldo -= cantidad;
        Thread.sleep(1000);
        System.out.println("Retiro realizado.");
        System.out.println("Saldo actual: "+this.saldo);
        Thread.sleep(1000);
    };

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "saldo=" + saldo +
                '}';
    }

    public synchronized void depositar(int cantidad) throws InterruptedException {
        System.out.println("Realizando deposito.");
        this.saldo += cantidad;
        Thread.sleep(1000);
        System.out.println("Deposito realizado.");
        System.out.println("Saldo actual: "+this.saldo);
        Thread.sleep(1000);
    };
}
