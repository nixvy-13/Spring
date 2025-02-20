package com.example.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MasApisApplication {
	
	private static final Logger log = LoggerFactory.getLogger(MasApisApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MasApisApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	@Profile("!test")
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			List<Pet> pets = restTemplate.getForObject(
					"http://petstore-demo-endpoint.execute-api.com/petstore/pets", List.class);
			log.info(pets.toString());
		};
	}
	
}
