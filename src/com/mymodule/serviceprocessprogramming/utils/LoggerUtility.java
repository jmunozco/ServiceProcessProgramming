package com.mymodule.serviceprocessprogramming.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerUtility {

    // Método para registrar mensajes informativos
    public static void logInfo(String message) {
        System.out.println("[INFO] [" + getCurrentTime() + "] " + message);
    }

    // Método para registrar mensajes de advertencia
    public static void logWarning(String message) {
        System.out.println("[WARNING] [" + getCurrentTime() + "] " + message);
    }

    // Método para registrar errores
    public static void logError(String message) {
        System.err.println("[ERROR] [" + getCurrentTime() + "] " + message);
    }

    // Método para obtener la hora actual en formato legible
    private static String getCurrentTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
}
