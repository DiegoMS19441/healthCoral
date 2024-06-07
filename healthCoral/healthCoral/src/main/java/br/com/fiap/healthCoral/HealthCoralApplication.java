package br.com.fiap.healthCoral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HealthCoralApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthCoralApplication.class, args);
	}

}
