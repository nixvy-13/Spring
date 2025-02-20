package com.example.ApiRest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.ApiRest.model.Empleado;
import com.example.ApiRest.repository.EmpleadoRepository;

// Al indicar @Configuration y @Bean SpringBoot sabe que tiene que cargarlo automaticamente 
// Probar hacerlo con un data.sql
@Configuration
public class CargarBaseDeDatos {
	private static final Logger log = LoggerFactory.getLogger(CargarBaseDeDatos.class);

	@Bean
	CommandLineRunner initDatabase(EmpleadoRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new Empleado("Bilbo Baggins", "burglar")));
			log.info("Preloading " + repository.save(new Empleado("Frodo Baggins", "thief")));
		};
	}
}
