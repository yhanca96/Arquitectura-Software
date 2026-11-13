package apicalendario.apicalendario.core.interfaces.integracion;

import java.util.List;

import apicalendario.apicalendario.dominio.dtos.FestivoDto;

// Contrato de integracion con la API Festivos (ExpressJS + MongoDB)
public interface IFestivoServicioExterno {

    public List<FestivoDto> obtenerFestivos(int anio);

}
