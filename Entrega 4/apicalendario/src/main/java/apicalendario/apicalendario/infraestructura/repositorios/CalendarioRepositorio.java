package apicalendario.apicalendario.infraestructura.repositorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import apicalendario.apicalendario.core.interfaces.repositorios.ICalendarioRepositorio;
import apicalendario.apicalendario.dominio.entidades.Calendario;
import apicalendario.apicalendario.infraestructura.entidades.CalendarioEntidad;
import apicalendario.apicalendario.infraestructura.mapeadores.CalendarioMapeador;
import apicalendario.apicalendario.infraestructura.repositorios.jpa.ICalendarioRepositorioJpa;

@Repository
public class CalendarioRepositorio implements ICalendarioRepositorio {

    private ICalendarioRepositorioJpa repositorioJpa;

    public CalendarioRepositorio(ICalendarioRepositorioJpa repositorioJpa) {
        this.repositorioJpa = repositorioJpa;
    }

    @Override
    public List<Calendario> listarPorPeriodo(LocalDate desde, LocalDate hasta) {
        List<Calendario> dias = new ArrayList<>();
        for (CalendarioEntidad entidad : repositorioJpa.listarPorPeriodo(desde, hasta)) {
            dias.add(CalendarioMapeador.aDominio(entidad));
        }
        return dias;
    }

    @Override
    public void eliminarPorPeriodo(LocalDate desde, LocalDate hasta) {
        repositorioJpa.eliminarPorPeriodo(desde, hasta);
    }

    @Override
    public void guardarTodos(List<Calendario> dias) {
        List<CalendarioEntidad> entidades = new ArrayList<>();
        for (Calendario dia : dias) {
            entidades.add(CalendarioMapeador.aEntidad(dia));
        }
        repositorioJpa.saveAll(entidades);
    }

}
