package heap;

import modelo.Paciente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class VectorHeapTest {

    @Test
    public void testInsertarYRemoverUnSoloPaciente() {
        VectorHeap<Paciente> vectorHeap = new VectorHeap<>();
        Paciente paciente = new Paciente("Paciente Uno", "Síntoma 1", 'B', LocalDateTime.now());
        vectorHeap.insertar(paciente);
        // Al remover, debe obtenerse el mismo paciente insertado
        assertEquals(paciente, vectorHeap.remover());
        assertTrue(vectorHeap.isEmpty());
    }

    @Test
    public void testOrdenDePrioridad() throws InterruptedException {
        VectorHeap<Paciente> vectorHeap = new VectorHeap<>();
        
        LocalDateTime t1 = LocalDateTime.now();
        Paciente p1 = new Paciente("Paciente A", "Síntoma A", 'B', t1);
        Thread.sleep(10); // Pequeño retraso para diferenciar el timestamp.
        
        LocalDateTime t2 = LocalDateTime.now();
        Paciente p2 = new Paciente("Paciente B", "Síntoma B", 'A', t2);
        Thread.sleep(10);
        
        LocalDateTime t3 = LocalDateTime.now();
        Paciente p3 = new Paciente("Paciente C", "Síntoma C", 'C', t3);
        
        vectorHeap.insertar(p1);
        vectorHeap.insertar(p2);
        vectorHeap.insertar(p3);
        
        // Orden esperado según prioridad: primero p2 (código A), luego p1 (código B) y finalmente p3 (código C)
        assertEquals(p2, vectorHeap.remover());
        assertEquals(p1, vectorHeap.remover());
        assertEquals(p3, vectorHeap.remover());
        assertTrue(vectorHeap.isEmpty());
    }

    @Test
    public void testDesempatePorTimestamp() throws InterruptedException {
        VectorHeap<Paciente> vectorHeap = new VectorHeap<>();
        
        LocalDateTime t1 = LocalDateTime.now();
        Paciente p1 = new Paciente("Paciente Uno", "Síntoma Uno", 'A', t1);
        Thread.sleep(10);
        
        LocalDateTime t2 = LocalDateTime.now();
        Paciente p2 = new Paciente("Paciente Dos", "Síntoma Dos", 'A', t2);
        
        // Insertar en orden inverso para verificar que se respeta el orden de llegada
        vectorHeap.insertar(p2);
        vectorHeap.insertar(p1);
        
        // Debe extraer primero al que llegó primero (p1)
        assertEquals(p1, vectorHeap.remover());
        assertEquals(p2, vectorHeap.remover());
        assertTrue(vectorHeap.isEmpty());
    }
}

