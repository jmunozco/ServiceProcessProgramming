package com.mymodule.serviceprocessprogramming.utils;

import java.io.*;
import java.net.*;

public class NetworkUtils {

    // Método para crear una conexión de socket con un servidor
    public static Socket createSocketConnection(String host, int port) throws IOException {
        return new Socket(host, port);
    }

    // Método para enviar un mensaje a través de un socket
    public static void sendMessage(Socket socket, String message) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(message);
        LoggerUtility.logInfo("Sent message: " + message);
    }

    // Método para recibir un mensaje desde un socket
    public static String receiveMessage(Socket socket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String message = in.readLine();
        LoggerUtility.logInfo("Received message: " + message);
        return message;
    }

    // Método para cerrar un socket de manera segura
    public static void closeSocket(Socket socket) {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
                LoggerUtility.logInfo("Socket connection closed.");
            } catch (IOException e) {
                LoggerUtility.logError("Error closing socket: " + e.getMessage());
            }
        }
    }
}
