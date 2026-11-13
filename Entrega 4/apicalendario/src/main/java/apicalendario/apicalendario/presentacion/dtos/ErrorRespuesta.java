package apicalendario.apicalendario.presentacion.dtos;

// Estructura de la respuesta cuando ocurre un error
public class ErrorRespuesta {

    private int estado;
    private String mensaje;

    public ErrorRespuesta(int estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public int getEstado() {
        return estado;
    }

    public String getMensaje() {
        return mensaje;
    }
}
