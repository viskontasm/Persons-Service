package com.persons.demo.service;

import com.persons.demo.enitity.Person;
import com.persons.demo.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

@Log4j2
@Service
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersonByPersonalIdOrBirthDay(String value) {
        List<Person> persons = personRepository.getPersonByPersonalIdOrBirthDay(value, value);
        if (persons.isEmpty()) {
            log.error("No persons found with input value {}", value);
        }
        return persons;
    }
}
