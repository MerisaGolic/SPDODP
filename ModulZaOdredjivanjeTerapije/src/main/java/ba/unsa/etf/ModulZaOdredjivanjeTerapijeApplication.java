package ba.unsa.etf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"ba.unsa.etf"})
public class ModulZaOdredjivanjeTerapijeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModulZaOdredjivanjeTerapijeApplication.class, args);
	}
}
