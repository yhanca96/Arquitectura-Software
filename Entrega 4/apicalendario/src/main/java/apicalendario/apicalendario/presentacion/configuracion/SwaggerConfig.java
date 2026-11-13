package apicalendario.apicalendario.presentacion.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

// Documentacion de la API disponible en http://localhost:8080/swagger-ui.html
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiCalendario() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Calendario Laboral")
                        .description("Genera y lista el calendario laboral de un año usando la API Festivos")
                        .version("1.0"));
    }

}
