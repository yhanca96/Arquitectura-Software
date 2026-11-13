package apicalendario.apicalendario.core.interfaces.repositorios;

import java.time.LocalDate;
import java.util.List;

import apicalendario.apicalendario.dominio.entidades.Calendario;

public interface ICalendarioRepositorio {

    public List<Calendario> listarPorPeriodo(LocalDate desde, LocalDate hasta);

    public void eliminarPorPeriodo(LocalDate desde, LocalDate hasta);

    public void guardarTodos(List<Calendario> dias);

}
