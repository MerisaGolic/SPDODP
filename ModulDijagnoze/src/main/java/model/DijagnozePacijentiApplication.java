package model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"model"})
public class DijagnozePacijentiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DijagnozePacijentiApplication.class, args);
	}
}
