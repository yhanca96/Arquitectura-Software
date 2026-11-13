package apicalendario.apicalendario.presentacion.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import apicalendario.apicalendario.core.interfaces.servicios.ICalendarioServicio;
import apicalendario.apicalendario.dominio.dtos.CalendarioDto;

@RestController
@RequestMapping("/api/calendario")
public class CalendarioControlador {

    private ICalendarioServicio servicio;

    public CalendarioControlador(ICalendarioServicio servicio) {
        this.servicio = servicio;
    }

    @RequestMapping(value = "/generar/{anio}", method = RequestMethod.GET)
    public boolean generar(@PathVariable("anio") int anio) {
        return servicio.generar(anio);
    }

    @RequestMapping(value = "/listar/{anio}", method = RequestMethod.GET)
    public List<CalendarioDto> listar(@PathVariable("anio") int anio) {
        return servicio.listar(anio);
    }

}
