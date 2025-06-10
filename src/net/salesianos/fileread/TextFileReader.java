package net.salesianos.fileread;

import java.io.*;

public class TextFileReader {

    public static void main(String[] args) throws IOException {
        // Crear archivo con contenido para la actividad
        crearArchivoConTexto();

        // Leer y mostrar caracteres con representación decimal
        leerYMostrarCaracteres();
    }

    // Método para crear un archivo con texto de prueba
    public static void crearArchivoConTexto() throws IOException {
        String fileName = "./files/leer_archivo.txt";
        String textoEjemplo = "Hola, pibe. Este es un texto de ejemplo para la actividad 2.";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(textoEjemplo);
            System.out.println("Archivo creado con texto de ejemplo: " + fileName);
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
            throw e;
        }
    }

    // Método para leer el archivo y mostrar cada carácter con su valor decimal
    public static void leerYMostrarCaracteres() throws IOException {
        String fileName = "./files/archivo_lectura.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            System.out.println("\nContenido del archivo con representación decimal:");

            int caracter;
            StringBuilder resultado = new StringBuilder();

            while ((caracter = br.read()) != -1) {
                char c = (char) caracter;

                // Formatear: carácter_valor_decimal
                if (c == ' ') {
                    resultado.append("*").append(caracter);
                } else {
                    resultado.append(c).append("_").append(caracter);
                }

                // Añadir coma si no es el último carácter
                resultado.append(", ");
            }

            // Eliminar la última coma y espacio
            if (resultado.length() > 2) {
                resultado.setLength(resultado.length() - 2);
            }

            System.out.println(resultado.toString());

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            throw e;
        }
    }

    // Método alternativo que lee un archivo específico
    public static void leerArchivoEspecifico(String rutaArchivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            System.out.println("\nLeyendo archivo: " + rutaArchivo);

            int caracter;
            StringBuilder resultado = new StringBuilder();

            while ((caracter = br.read()) != -1) {
                char c = (char) caracter;

                if (c == ' ') {
                    resultado.append("*").append(caracter);
                } else {
                    resultado.append(c).append("_").append(caracter);
                }
                resultado.append(", ");
            }

            if (resultado.length() > 2) {
                resultado.setLength(resultado.length() - 2);
            }

            System.out.println(resultado.toString());

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            throw e;
        }
    }
}