package apicalendario.apicalendario.aplicacion;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apicalendario.apicalendario.core.interfaces.integracion.IFestivoServicioExterno;
import apicalendario.apicalendario.core.interfaces.repositorios.ICalendarioRepositorio;
import apicalendario.apicalendario.core.interfaces.repositorios.ITipoRepositorio;
import apicalendario.apicalendario.core.interfaces.servicios.ICalendarioServicio;
import apicalendario.apicalendario.dominio.dtos.CalendarioDto;
import apicalendario.apicalendario.dominio.dtos.FestivoDto;
import apicalendario.apicalendario.dominio.entidades.Calendario;
import apicalendario.apicalendario.dominio.entidades.Tipo;

@Service
public class CalendarioServicio implements ICalendarioServicio {

    // Identificadores de la tabla Tipo (DML - Calendario Laboral.sql)
    private static final long TIPO_DIA_LABORAL = 1;
    private static final long TIPO_FIN_SEMANA = 2;
    private static final long TIPO_DIA_FESTIVO = 3;

    private ICalendarioRepositorio repositorio;
    private ITipoRepositorio repositorioTipo;
    private IFestivoServicioExterno servicioFestivos;

    public CalendarioServicio(ICalendarioRepositorio repositorio,
            ITipoRepositorio repositorioTipo,
            IFestivoServicioExterno servicioFestivos) {
        this.repositorio = repositorio;
        this.repositorioTipo = repositorioTipo;
        this.servicioFestivos = servicioFestivos;
    }

    @Override
    @Transactional
    public boolean generar(int anio) {
        Tipo diaLaboral = repositorioTipo.obtener(TIPO_DIA_LABORAL);
        Tipo finSemana = repositorioTipo.obtener(TIPO_FIN_SEMANA);
        Tipo diaFestivo = repositorioTipo.obtener(TIPO_DIA_FESTIVO);
        if (diaLaboral == null || finSemana == null || diaFestivo == null) {
            return false;
        }

        // Festivos del anio obtenidos de la API Festivos (si dos caen el mismo dia se unen los nombres)
        Map<LocalDate, String> festivos = new HashMap<>();
        for (FestivoDto festivo : servicioFestivos.obtenerFestivos(anio)) {
            festivos.merge(LocalDate.parse(festivo.getFecha()), festivo.getFestivo(), (a, b) -> a + " - " + b);
        }

        // Clasificar cada dia del anio: Dia festivo / Fin de Semana / Dia laboral
        LocalDate desde = LocalDate.of(anio, 1, 1);
        LocalDate hasta = LocalDate.of(anio, 12, 31);
        List<Calendario> dias = new ArrayList<>();
        for (LocalDate fecha = desde; !fecha.isAfter(hasta); fecha = fecha.plusDays(1)) {
            if (festivos.containsKey(fecha)) {
                dias.add(new Calendario(0, fecha, diaFestivo, festivos.get(fecha)));
            } else if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
                dias.add(new Calendario(0, fecha, finSemana, null));
            } else {
                dias.add(new Calendario(0, fecha, diaLaboral, null));
            }
        }

        // Reemplazar el calendario del anio (la fecha es unica en la tabla)
        repositorio.eliminarPorPeriodo(desde, hasta);
        repositorio.guardarTodos(dias);
        return true;
    }

    @Override
    public List<CalendarioDto> listar(int anio) {
        List<CalendarioDto> calendario = new ArrayList<>();
        for (Calendario dia : repositorio.listarPorPeriodo(LocalDate.of(anio, 1, 1), LocalDate.of(anio, 12, 31))) {
            calendario.add(new CalendarioDto(dia.getFecha(), dia.getTipo().getTipo().trim(), dia.getDescripcion()));
        }
        return calendario;
    }

}
