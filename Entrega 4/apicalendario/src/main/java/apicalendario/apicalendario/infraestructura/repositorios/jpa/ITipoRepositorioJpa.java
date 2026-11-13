package apicalendario.apicalendario.infraestructura.repositorios.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apicalendario.apicalendario.infraestructura.entidades.TipoEntidad;

@Repository
public interface ITipoRepositorioJpa extends JpaRepository<TipoEntidad, Long> {

}
