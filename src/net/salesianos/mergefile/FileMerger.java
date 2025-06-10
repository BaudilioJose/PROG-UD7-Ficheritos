package net.salesianos.mergefile;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileMerger {

    public static void main(String[] args) throws IOException {
        // a) Crear fichero para esta actividad
        String activity3File = "./files/actividad3_fusion.txt";

        // b) Fusionar contenido de los ficheros de las actividades anteriores
        mergeFiles(activity3File);

        // c) Añadir mensaje de firma
        addSignature(activity3File);

        System.out.println("Actividad 3 completada. Archivo creado: " + activity3File);
    }

    public static void mergeFiles(String destinationFile) throws IOException {
        String file1 = "./files/archivo.txt";
        String file2 = "./files/leer_archivo.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(destinationFile))) {
            // Leer y escribir contenido del primer archivo
            String content1 = readFile(file1);
            bw.write("- Contenido del Fichero Uno: " + content1);
            bw.newLine();

            // Leer y escribir contenido del segundo archivo
            String content2 = readFile(file2);
            bw.write("- Contenido del Fichero Dos: " + content2);
            bw.newLine();

            System.out.println("Archivos fusionados correctamente.");

        } catch (IOException e) {
            System.out.println("Error al fusionar archivos: " + e.getMessage());
            throw e;
        }
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
                // Si no es la última línea, añadir salto de línea
                if (br.ready()) {
                    content.append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo " + filePath + ": " + e.getMessage());
            throw e;
        }

        return content.toString();
    }

    public static void addSignature(String filePath) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.newLine();
            bw.write("---");
            bw.newLine();

            // Crear mensaje de firma con fecha y hora
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String signature = String.format("Archivo fusionado automáticamente el %s - Actividad 3 PROG-UD7",
                    now.format(format));

            bw.write(signature);
            bw.newLine();
            bw.write("Autor: Sistema de Fusión de Archivos");

            System.out.println("Firma añadida correctamente.");

        } catch (IOException e) {
            System.out.println("Error al añadir firma: " + e.getMessage());
            throw e;
        }
    }
}