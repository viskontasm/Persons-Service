package com.persons.demo.controller;

import com.persons.demo.enitity.Person;
import com.persons.demo.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Log4j2
@RestController
public class PersonController {
    private final PersonService personService;

    PersonController(PersonService personService) {
        this.personService = personService;
    }

    @CrossOrigin
    @GetMapping("/persons/search/{value}")
    public List<Person> getPersonByPersonalIdOrBirthDay(@PathVariable String value) {
        log.info("Person searched with input value {}", value);
        return personService.getPersonByPersonalIdOrBirthDay(value);
    }
}
