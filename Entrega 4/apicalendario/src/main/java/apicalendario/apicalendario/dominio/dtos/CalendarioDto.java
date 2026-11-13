package apicalendario.apicalendario.dominio.dtos;

import java.time.LocalDate;

// Estructura de cada dia del calendario que responde esta API
public class CalendarioDto {

    private LocalDate fecha;
    private String tipo;
    private String descripcion;

    public CalendarioDto() {
    }

    public CalendarioDto(LocalDate fecha, String tipo, String descripcion) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
