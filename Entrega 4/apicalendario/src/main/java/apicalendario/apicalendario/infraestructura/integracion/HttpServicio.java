package apicalendario.apicalendario.infraestructura.integracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// Cliente HTTP para consumir servicios externos
@Configuration
public class HttpServicio {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
