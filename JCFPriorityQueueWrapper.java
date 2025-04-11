package jcf;

import java.util.PriorityQueue;
import modelo.Paciente;

public class JCFPriorityQueueWrapper {
    private PriorityQueue<Paciente> queue;

    public JCFPriorityQueueWrapper() {
        // La cola se ordena usando el orden natural definido en Paciente.
        queue = new PriorityQueue<>();
    }

    public void agregar(Paciente p) {
        queue.offer(p);
    }

    public Paciente extraer() {
        return queue.poll();
    }

    public Paciente verSiguiente() {
        return queue.peek();
    }

    public boolean estaVacia() {
        return queue.isEmpty();
    }
}
