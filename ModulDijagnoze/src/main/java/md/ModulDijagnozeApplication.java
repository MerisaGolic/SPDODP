package md;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"md"})

@SpringBootApplication
public class ModulDijagnozeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModulDijagnozeApplication.class, args);
	}
}
