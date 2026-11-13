package apicalendario.apicalendario.infraestructura.repositorios;

import org.springframework.stereotype.Repository;

import apicalendario.apicalendario.core.interfaces.repositorios.ITipoRepositorio;
import apicalendario.apicalendario.dominio.entidades.Tipo;
import apicalendario.apicalendario.infraestructura.mapeadores.TipoMapeador;
import apicalendario.apicalendario.infraestructura.repositorios.jpa.ITipoRepositorioJpa;

@Repository
public class TipoRepositorio implements ITipoRepositorio {

    private ITipoRepositorioJpa repositorioJpa;

    public TipoRepositorio(ITipoRepositorioJpa repositorioJpa) {
        this.repositorioJpa = repositorioJpa;
    }

    @Override
    public Tipo obtener(long id) {
        return repositorioJpa.findById(id).map(TipoMapeador::aDominio).orElse(null);
    }

}
