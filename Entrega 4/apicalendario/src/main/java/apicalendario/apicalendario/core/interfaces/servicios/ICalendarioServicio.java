package apicalendario.apicalendario.core.interfaces.servicios;

import java.util.List;

import apicalendario.apicalendario.dominio.dtos.CalendarioDto;

public interface ICalendarioServicio {

    public boolean generar(int anio);

    public List<CalendarioDto> listar(int anio);

}
