package com.uptc.edu.service;

import com.uptc.edu.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private JsonFileService jsonFileService;

    public List<Person> getAll() {
        return jsonFileService.loadDataFromJson();
    }

    public void savePerson(Person person) {
        List<Person> people = new ArrayList<>(jsonFileService.loadDataFromJson());
        person.setId(people.size() + 1);
        people.add(validatePerson(person));
        jsonFileService.saveDataToJson(people);
    }

    private Person validatePerson(Person person) {
        int randomNumber = (int) (Math.random() * 1000);
        person.setRandomNumber(randomNumber);
        person.setNameClient("Client " + randomNumber);
        return person;
    }
}
