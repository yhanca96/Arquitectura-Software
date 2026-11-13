package apicalendario.apicalendario.infraestructura.mapeadores;

import apicalendario.apicalendario.dominio.entidades.Calendario;
import apicalendario.apicalendario.infraestructura.entidades.CalendarioEntidad;

// Transforma Calendario (dominio) <-> CalendarioEntidad (JPA)
public class CalendarioMapeador {

    public static Calendario aDominio(CalendarioEntidad entidad) {
        if (entidad == null) {
            return null;
        }
        return new Calendario(entidad.getId(), entidad.getFecha(),
                TipoMapeador.aDominio(entidad.getTipo()), entidad.getDescripcion());
    }

    public static CalendarioEntidad aEntidad(Calendario calendario) {
        if (calendario == null) {
            return null;
        }
        return new CalendarioEntidad(calendario.getId(), calendario.getFecha(),
                TipoMapeador.aEntidad(calendario.getTipo()), calendario.getDescripcion());
    }

}
