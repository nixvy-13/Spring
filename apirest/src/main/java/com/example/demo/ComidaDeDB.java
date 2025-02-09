package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ComidaDeDB implements CommandLineRunner {

    private final PersonRepository personRepository;

    public ComidaDeDB(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) {
        if (personRepository.count() == 0) {
            Person p1 = new Person("Agustin", "Aguilera");
            Person p2 = new Person("Javier", "Puche");
            Person p3 = new Person("Carlos", "Villaroel");

            personRepository.saveAll(List.of(p1, p2, p3));
        }
    }
}
