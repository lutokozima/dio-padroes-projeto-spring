package one.digitalinnovation.gof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Replicando padroes de projetos spring
 * Projeto Spring Boot gerado via Spring Initializr
 * Foi selecionado os seguintes módulos:
 * - Spring Data JPA
 * - Spring Web
 * - H2 Database
 * - OpenFeign
 *
 */

@EnableFeignClients
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
