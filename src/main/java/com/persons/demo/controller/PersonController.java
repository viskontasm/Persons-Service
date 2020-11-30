package com.persons.demo.controller;

import com.persons.demo.enitity.Person;
import com.persons.demo.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Log4j2
@RestController
public class PersonController {
    private final PersonRepository repository;

    PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/persons/search/{value}")
    public List<Person> getPersonByPersonalIdOrBirthDay(@PathVariable String value) {
        log.info("Person searched with input value {}", value);
        List<Person> persons = repository.getPersonByPersonalIdOrBirthDay(value, value);
        if (persons.isEmpty()) {
            log.error("No persons found with input value {}", value);
        }
        return persons;
    }
}
