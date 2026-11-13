package apicalendario.apicalendario.presentacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "apicalendario.apicalendario")
@EnableJpaRepositories(basePackages = "apicalendario.apicalendario.infraestructura.repositorios.jpa")
@EntityScan(basePackages = "apicalendario.apicalendario.infraestructura.entidades")
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
