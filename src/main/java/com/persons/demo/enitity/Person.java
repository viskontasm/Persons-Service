package com.persons.demo.enitity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String personalId;
    private String gender;
    private String birthDay;


    public Person(String firstName, String lastName, String personalId, String gender, String birthDay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.gender = gender;
        this.birthDay = birthDay;
    }

}

