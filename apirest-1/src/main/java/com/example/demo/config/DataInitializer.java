package com.example.demo.config;

import com.example.demo.model.Personaje;
import com.example.demo.repository.PersonajeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(PersonajeRepository repository) {
        return args -> {

            repository.deleteAll();
            
            repository.save(new Personaje("Gandalf", "Mago", 20));
            repository.save(new Personaje("Aragorn", "Guerrero", 15));
            repository.save(new Personaje("Legolas", "Arquero", 14));
            repository.save(new Personaje("Frodo", "Ladrón", 8));
            repository.save(new Personaje("Gimli", "Guerrero", 13));
            repository.save(new Personaje("Galadriel", "Mago", 25));
            repository.save(new Personaje("Boromir", "Guerrero", 12));
            repository.save(new Personaje("Sam", "Aventurero", 7));
            repository.save(new Personaje("Elrond", "Mago", 22));
            repository.save(new Personaje("Arwen", "Ranger", 16));
          
        };
    }
}
