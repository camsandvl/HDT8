package heap;

import java.util.ArrayList;
import java.util.List;

public class VectorHeap<E extends Comparable<E>> {
    private List<E> heap;

    public VectorHeap() {
        heap = new ArrayList<>();
    }

    public void insertar(E elemento) {
        heap.add(elemento);
        upHeap(heap.size() - 1);
    }

    public E remover() {
        if (isEmpty()) {
            return null;
        }
        E resultado = heap.get(0);
        E ultimo = heap.remove(heap.size() - 1);
        if (!isEmpty()) {
            heap.set(0, ultimo);
            downHeap(0);
        }
        return resultado;
    }

    public E peek() {
        return isEmpty() ? null : heap.get(0);
    }
    
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void upHeap(int index) {
        E valor = heap.get(index);
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (valor.compareTo(heap.get(parent)) >= 0) {
                break;
            }
            heap.set(index, heap.get(parent));
            index = parent;
        }
        heap.set(index, valor);
    }

    private void downHeap(int index) {
        int size = heap.size();
        E valor = heap.get(index);
        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;
            if (leftChild < size && heap.get(leftChild).compareTo(heap.get(smallest)) < 0) {
                smallest = leftChild;
            }
            if (rightChild < size && heap.get(rightChild).compareTo(heap.get(smallest)) < 0) {
                smallest = rightChild;
            }
            if (smallest == index) {
                break;
            }
            heap.set(index, heap.get(smallest));
            index = smallest;
        }
        heap.set(index, valor);
    }
}

