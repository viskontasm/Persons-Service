package com.persons.demo.service;

import com.persons.demo.enitity.Person;
import com.persons.demo.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {

    @Resource
    private PersonRepository personRepository;
    @Autowired
    private PersonService personService;

    @Test
    public void check_person_by_personal_id() {
        personRepository.save(new Person(
                "John", "Smith", "123", "male", "1980-02-01"));

        List<Person> person2 = personService.getPersonByPersonalIdOrBirthDay("123");
        assertEquals("123", person2.get(0).getPersonalId());
    }

    @Test
    public void check_person_by_birthday() {
        personRepository.save(new Person(
                "John", "Smith", "123", "male", "1980-02-02"));

        List<Person> person2 = personService.getPersonByPersonalIdOrBirthDay("1980-02-02");
        assertEquals("123", person2.get(0).getPersonalId());
    }

    @Test
    public void check_person_when_not_found() {
        List<Person> person2 = personService.getPersonByPersonalIdOrBirthDay("1980-02-13");
        assertTrue(person2.isEmpty());
    }
}
