package net.salesianos.filesave;

import java.io.*;

public class TextFileSaver {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String texto = solicitarTexto(br);

        // Formatear el texto
        String textoFormateado = formatearTexto(texto);
        System.out.println("Texto formateado: " + textoFormateado);

        // Guardar en archivo
        guardarTextoFormateado(textoFormateado);

        br.close();
    }

    public static String solicitarTexto(BufferedReader br) throws IOException {
        String texto;
        final int LENGTH_MINIMUM = 30; // Corregido: MINIMUM

        do {
            System.out.println("Introduce un texto mínimo de 30 caracteres:");
            texto = br.readLine(); // Leer el texto en cada iteración

            if (texto.length() < LENGTH_MINIMUM) {
                int missing = LENGTH_MINIMUM - texto.length();
                System.out.println(
                        "El texto es demasiado corto, te faltan " + missing + " caracteres. Inténtalo de nuevo.");
            }

        } while (texto.length() < 9); // Continúa hasta que sea válido

        return texto;
    }

    public static String formatearTexto(String texto) {
        // Convertir el texto a mayúsculas y espacios a barras bajas
        return texto.toUpperCase().replace(" ", "_");
    }

    public static void guardarTextoFormateado(String texto) throws IOException {

        String fileName = "./files/archivo.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(texto);
            System.out.println("Texto guardado en: " + fileName);
        } catch (IOException e) {
            System.out.println("Error al guardar el texto formateado en el archivo: " + e.getMessage());
        }

    }
}