import heap.VectorHeap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import jcf.JCFPriorityQueueWrapper;
import modelo.Paciente;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        while (true) {
            System.out.println("=== SISTEMA DE COLAS PARA EMERGENCIAS ===");
            System.out.println("1. Usar implementación con VectorHeap");
            System.out.println("2. Usar implementación con Java Collection Framework");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intente nuevamente.\n");
                continue;
            }
            
            if (opcion == 3) {
                System.out.println("Saliendo del sistema...");
                break;
            }
            
            // Leer los pacientes del archivo "pacientes.txt"
            List<Paciente> pacientes = leerPacientes("pacientes.txt");
            if (pacientes.isEmpty()) {
                System.out.println("No se cargaron pacientes o el archivo está vacío.\n");
                continue;
            }
            
            if (opcion == 1) {
                System.out.println("Implementación con VectorHeap");
                VectorHeap<Paciente> vectorHeap = new VectorHeap<>();
                for (Paciente p : pacientes) {
                    vectorHeap.insertar(p);
                }
                System.out.println("Atendiendo pacientes:");
                while (!vectorHeap.isEmpty()) {
                    Paciente p = vectorHeap.remover();
                    System.out.println(p);
                }
            } else if (opcion == 2) {
                System.out.println("Implementación con Java Collection Framework");
                JCFPriorityQueueWrapper jcfQueue = new JCFPriorityQueueWrapper();
                for (Paciente p : pacientes) {
                    jcfQueue.agregar(p);
                }
                System.out.println("Atendiendo pacientes:");
                while (!jcfQueue.estaVacia()) {
                    Paciente p = jcfQueue.extraer();
                    System.out.println(p);
                }
            } else {
                System.out.println("Opción no válida, intente nuevamente.");
            }
            
            System.out.println(); // Salto de línea para separar ejecuciones.
        }
        scanner.close();
    }
    
    private static List<Paciente> leerPacientes(String fileName) {
        List<Paciente> lista = new java.util.ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(fileName));
            for (String linea : lineas) {
                String[] partes = linea.split(",");
                if (partes.length < 3) continue;
                String nombre = partes[0].trim();
                String sintoma = partes[1].trim();
                char codigo = partes[2].trim().charAt(0);
                // Se utiliza LocalDateTime.now() para marcar el tiempo de llegada.
                Paciente paciente = new Paciente(nombre, sintoma, codigo, LocalDateTime.now());
                lista.add(paciente);
                // Pequeña pausa para diferenciar los timestamps de llegada.
                Thread.sleep(10); // 10 milisegundos
            }
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo: " + ex.getMessage());
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        return lista;
    }
}

