package net.salesianos.table;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TableManager {
    private static final String TABLES_FILE = "./files/actividad4_mesas.txt";

    // Método para guardar una mesa en el fichero
    public static void saveTableToFile(Table table) throws IOException {
        List<Table> tables = getAllTablesFromFile();
        tables.add(table);

        try (BufferedOutputStream bufferedOutput = new BufferedOutputStream(new FileOutputStream(TABLES_FILE));
                ObjectOutputStream objectOutput = new ObjectOutputStream(bufferedOutput)) {

            // Escribir todas las mesas (incluyendo la nueva)
            for (Table t : tables) {
                objectOutput.writeObject(t);
            }

            System.out.println("Mesa guardada correctamente en el fichero.");

        } catch (IOException e) {
            System.out.println("Error al guardar la mesa: " + e.getMessage());
            throw e;
        }
    }

    // Método para obtener todas las mesas guardadas del fichero
    public static List<Table> getAllTablesFromFile() {
        List<Table> tables = new ArrayList<>();
        File file = new File(TABLES_FILE);

        // Si el archivo no existe, devolver lista vacía
        if (!file.exists()) {
            return tables;
        }

        try (BufferedInputStream bufferedInput = new BufferedInputStream(new FileInputStream(TABLES_FILE));
                ObjectInputStream objectInput = new ObjectInputStream(bufferedInput)) {

            // Leer todas las mesas del fichero
            while (true) {
                try {
                    Table table = (Table) objectInput.readObject();
                    tables.add(table);
                } catch (EOFException e) {
                    // Final del archivo alcanzado
                    break;
                } catch (ClassNotFoundException e) {
                    System.out.println("Error al deserializar objeto Mesa: " + e.getMessage());
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo de mesas no encontrado. Se creará uno nuevo al guardar la primera mesa.");
        } catch (IOException e) {
            System.out.println("Error al leer las mesas del fichero: " + e.getMessage());
        }

        return tables;
    }
}