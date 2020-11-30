package com.persons.demo.repository;

import com.persons.demo.enitity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> getPersonByPersonalIdOrBirthDay(String personalId, String birthDay);
}
