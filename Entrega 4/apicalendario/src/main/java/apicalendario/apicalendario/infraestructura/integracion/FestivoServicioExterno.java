package apicalendario.apicalendario.infraestructura.integracion;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import apicalendario.apicalendario.core.interfaces.integracion.IFestivoServicioExterno;
import apicalendario.apicalendario.dominio.dtos.FestivoDto;

// Consume la API Festivos (ExpressJS + MongoDB): GET {festivos.url}/obtener/{anio}
@Service
public class FestivoServicioExterno implements IFestivoServicioExterno {

    private RestTemplate restTemplate;

    @Value("${festivos.url}")
    private String urlFestivos;

    public FestivoServicioExterno(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<FestivoDto> obtenerFestivos(int anio) {
        FestivoDto[] festivos = restTemplate.getForObject(urlFestivos + "/obtener/" + anio, FestivoDto[].class);
        return festivos == null ? List.of() : Arrays.asList(festivos);
    }

}
