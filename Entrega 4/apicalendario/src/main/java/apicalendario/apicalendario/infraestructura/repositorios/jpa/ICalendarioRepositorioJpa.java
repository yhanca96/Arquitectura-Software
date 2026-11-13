package apicalendario.apicalendario.infraestructura.repositorios.jpa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apicalendario.apicalendario.infraestructura.entidades.CalendarioEntidad;

@Repository
public interface ICalendarioRepositorioJpa extends JpaRepository<CalendarioEntidad, Long> {

    @Query("SELECT c FROM CalendarioEntidad c WHERE c.fecha >= ?1 AND c.fecha <= ?2 ORDER BY c.fecha")
    List<CalendarioEntidad> listarPorPeriodo(LocalDate desde, LocalDate hasta);

    @Modifying
    @Query("DELETE FROM CalendarioEntidad c WHERE c.fecha >= ?1 AND c.fecha <= ?2")
    void eliminarPorPeriodo(LocalDate desde, LocalDate hasta);

}
