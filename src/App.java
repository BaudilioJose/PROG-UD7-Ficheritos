import net.salesianos.filesave.TextFileSaver;
import net.salesianos.fileread.TextFileReader;
import net.salesianos.mergefile.FileMerger;
import net.salesianos.table.Table;
import net.salesianos.table.TableManager;
import java.io.*;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        Console console = System.console();

        if (console == null) {
            System.out.println("Error: No se puede obtener la consola. Ejecuta desde terminal.");
            return;
        }

        int option;

        do {
            showMainMenu();
            String input = console.readLine("Selecciona una opción: ");

            try {
                option = Integer.parseInt(input);

                switch (option) {
                    case 1:
                        executeActivity1();
                        break;
                    case 2:
                        executeActivity2();
                        break;
                    case 3:
                        executeActivity3();
                        break;
                    case 4:
                        executeActivity4(console);
                        break;
                    case 0:
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número válido.");
                option = -1; // Para continuar el bucle
            }

            System.out.println(); // Línea en blanco para separar

        } while (option != 0);
    }

    private static void showMainMenu() {
        System.out.println("=== MENÚ PRINCIPAL - ACTIVIDADES DE FICHEROS ===");
        System.out.println("1. Actividad 1: Guardar texto formateado");
        System.out.println("2. Actividad 2: Leer archivo específico");
        System.out.println("3. Actividad 3: Fusionar contenido de ficheros");
        System.out.println("4. Actividad 4: Gestión de mesas (persistencia de objetos)");
        System.out.println("0. Salir");
        System.out.println("================================================");
    }

    private static void executeActivity1() throws IOException {
        System.out.println("\n=== ACTIVIDAD 1: GUARDAR TEXTO FORMATEADO ===");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // Solicitar texto al usuario
        String text = TextFileSaver.solicitarTexto(bufferedReader);

        // Formatear texto
        String formattedText = TextFileSaver.formatearTexto(text);
        System.out.println("Texto formateado: " + formattedText);

        // Guardar texto formateado
        TextFileSaver.guardarTextoFormateado(formattedText);

        System.out.println("Actividad 1 completada.");
    }

    private static void executeActivity2() throws IOException {
        System.out.println("\n=== ACTIVIDAD 2: LEER ARCHIVO ESPECÍFICO ===");
        TextFileReader.leerArchivoEspecifico("./files/leer_archivo.txt");
        System.out.println("Actividad 2 completada.");
    }

    private static void executeActivity3() throws IOException {
        System.out.println("\n=== ACTIVIDAD 3: FUSIONAR CONTENIDO DE VARIOS FICHEROS ===");

        // a) Crear fichero para esta actividad (se crea automáticamente al fusionar)
        String activity3File = "./files/actividad3_fusion.txt";

        // b) Fusionar contenido de los ficheros de las actividades anteriores
        FileMerger.mergeFiles(activity3File);

        // c) Añadir mensaje de firma
        FileMerger.addSignature(activity3File);

        System.out.println("Actividad 3 completada. Archivo creado: " + activity3File);
    }

    private static void executeActivity4(Console console) {
        System.out.println("\n=== ACTIVIDAD 4: GESTIÓN DE MESAS (PERSISTENCIA DE OBJETOS) ===");

        int option;

        do {
            showTablesMenu();
            String input = console.readLine("Selecciona una opción: ");

            try {
                option = Integer.parseInt(input);

                switch (option) {
                    case 1:
                        createNewTable(console);
                        break;
                    case 2:
                        showAllTables();
                        break;
                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número válido.");
                option = -1; // Para continuar el bucle
            }

            if (option != 0) {
                System.out.println(); // Línea en blanco para separar
            }

        } while (option != 0);
    }

    private static void showTablesMenu() {
        System.out.println("=== GESTIÓN DE MESAS ===");
        System.out.println("1. Nueva mesa");
        System.out.println("2. Mostrar todas las mesas almacenadas");
        System.out.println("0. Volver al menú principal");
        System.out.println("========================");
    }

    private static void createNewTable(Console console) {
        try {
            // Solicitar color
            String color = console.readLine("Introduce el color de la mesa: ");

            // Validar que el color no esté vacío
            while (color == null || color.trim().isEmpty()) {
                System.out.println("El color no puede estar vacío.");
                color = console.readLine("Introduce el color de la mesa: ");
            }

            // Solicitar número de patas
            int legCount = 0;
            boolean validLegs = false;

            while (!validLegs) {
                String legsInput = console.readLine("Introduce el número de patas: ");
                try {
                    legCount = Integer.parseInt(legsInput);
                    if (legCount > 0) {
                        validLegs = true;
                    } else {
                        System.out.println("El número de patas debe ser mayor que 0.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, introduce un número válido.");
                }
            }

            // Crear y guardar la mesa
            Table table = new Table(color.trim(), legCount);
            TableManager.saveTableToFile(table);

            System.out.println("Mesa creada: " + table);

        } catch (IOException e) {
            System.out.println("Error al crear la mesa: " + e.getMessage());
        }
    }

    private static void showAllTables() {
        List<Table> tables = TableManager.getAllTablesFromFile();

        if (tables.isEmpty()) {
            System.out.println("No hay mesas almacenadas en el fichero.");
        } else {
            System.out.println("=== MESAS ALMACENADAS ===");
            for (int i = 0; i < tables.size(); i++) {
                System.out.println((i + 1) + ". " + tables.get(i));
            }
            System.out.println("Total de mesas: " + tables.size());
        }
    }
}