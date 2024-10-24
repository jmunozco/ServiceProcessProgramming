public class Cajero extends Thread{
    private CuentaBancaria cuenta;
    private int cantidad;
    private boolean operacion;
    public Cajero(CuentaBancaria cuenta, int cantidad, boolean operacion) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
        this.operacion = operacion;
    }
    public void run() {
        try {
            if (operacion) cuenta.depositar(cantidad);
            else cuenta.retirar(cantidad);
        }catch (InterruptedException e) {
            System.out.println(e.getMessage());
            System.err.println("Error de concurrencia");
        }
    }
}
