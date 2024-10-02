package com.mymodule.serviceprocessprogramming.utils;

import java.io.*;
import java.nio.file.*;

public class FileUtils {

    // Método para leer el contenido de un archivo
    public static String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    // Método para escribir contenido a un archivo
    public static void writeFile(String filePath, String content) throws IOException {
        Files.write(Paths.get(filePath), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        LoggerUtility.logInfo("File written: " + filePath);
    }
}
