package modelo;

import java.time.LocalDateTime;

public class Paciente implements Comparable<Paciente> {
    private String nombre;
    private String sintoma;
    private char codigoEmergencia;
    private LocalDateTime timestamp;

    public Paciente(String nombre, String sintoma, char codigoEmergencia, LocalDateTime timestamp) {
        this.nombre = nombre;
        this.sintoma = sintoma;
        this.codigoEmergencia = codigoEmergencia;
        this.timestamp = timestamp;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSintoma() {
        return sintoma;
    }

    public char getCodigoEmergencia() {
        return codigoEmergencia;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public int compareTo(Paciente otro) {
        // Comparar por código de emergencia. 'A' es el de mayor prioridad.
        if (this.codigoEmergencia != otro.codigoEmergencia) {
            return Character.compare(this.codigoEmergencia, otro.codigoEmergencia);
        } else {
            // Si el código es igual, se compara por el timestamp (el que llegó primero tiene prioridad)
            return this.timestamp.compareTo(otro.timestamp);
        }
    }

    @Override
    public String toString() {
        return nombre + ", " + sintoma + ", " + codigoEmergencia;
    }
}

