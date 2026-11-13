package apicalendario.apicalendario.infraestructura.mapeadores;

import apicalendario.apicalendario.dominio.entidades.Tipo;
import apicalendario.apicalendario.infraestructura.entidades.TipoEntidad;

// Transforma Tipo (dominio) <-> TipoEntidad (JPA)
public class TipoMapeador {

    public static Tipo aDominio(TipoEntidad entidad) {
        if (entidad == null) {
            return null;
        }
        return new Tipo(entidad.getId(), entidad.getTipo());
    }

    public static TipoEntidad aEntidad(Tipo tipo) {
        if (tipo == null) {
            return null;
        }
        return new TipoEntidad(tipo.getId(), tipo.getTipo());
    }

}
