package com.persons.demo;

import com.persons.demo.enitity.Person;
import com.persons.demo.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Log4j2
@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(PersonRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Person("John", "Smith", "38602114422", "male", "1980-02-01")));
            log.info("Preloading " + repository.save(new Person("Joan", "Juken", "48602115544", "female", "1995-01-25")));
            log.info("Preloading " + repository.save(new Person("Martins", "Saimuskins", "33303-44343", "male", "1985-06-13")));
        };
    }
}
