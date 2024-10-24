import com.sun.security.jgss.GSSUtil;

public class Main {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria(1000);
        Cajero cajero1 = new Cajero(cuenta,100,true);
        Cajero cajero2 = new Cajero(cuenta,342,false);
        Cajero cajero3 = new Cajero(cuenta,122,true);
        Cajero cajero4 = new Cajero(cuenta,546,false);

        cajero1.start();
        cajero2.start();
        cajero3.start();
        cajero4.start();
        try {
            cajero1.join();
            cajero2.join();
            cajero3.join();
            cajero4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}