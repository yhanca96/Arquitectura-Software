package apicalendario.apicalendario.presentacion.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import apicalendario.apicalendario.presentacion.dtos.ErrorRespuesta;

@RestControllerAdvice
public class ExcepcionesGlobalesHandler {

    // El año de la ruta no es numerico
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorRespuesta> parametroNoValido(MethodArgumentTypeMismatchException ex) {
        return respuesta(HttpStatus.BAD_REQUEST, "El año debe ser un número");
    }

    // La API Festivos rechazo la solicitud (por ejemplo, año fuera de rango)
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorRespuesta> solicitudRechazada(HttpClientErrorException ex) {
        return respuesta(HttpStatus.BAD_REQUEST, "La API Festivos no aceptó el año solicitado");
    }

    // La API Festivos no esta disponible
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ErrorRespuesta> servicioNoDisponible(ResourceAccessException ex) {
        return respuesta(HttpStatus.SERVICE_UNAVAILABLE, "No fue posible conectar con la API Festivos");
    }

    private ResponseEntity<ErrorRespuesta> respuesta(HttpStatus estado, String mensaje) {
        return ResponseEntity.status(estado).body(new ErrorRespuesta(estado.value(), mensaje));
    }

}
