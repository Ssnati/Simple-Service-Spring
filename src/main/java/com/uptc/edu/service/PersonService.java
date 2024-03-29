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
        people.add(person);
        jsonFileService.saveDataToJson(people);
    }

    private Person validatePerson(Person person) {
        int randomNumber = (int) (Math.random() * 1000);
        person.setRandomNumber(randomNumber);
        person.setNameClient("Client " + randomNumber);
        return person;
    }

    public void editPerson(int id, Person newPerson) {
        List<Person> people = new ArrayList<>(jsonFileService.loadDataFromJson());
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getId() == id) {
                newPerson.setId(id);
                validatePerson(newPerson);
                people.set(i, newPerson);
                jsonFileService.saveDataToJson(people);
                return;
            }
        }
    }

    public void createPerson(Person person) {
        List<Person> people = new ArrayList<>(jsonFileService.loadDataFromJson());
        if (people.isEmpty()) person.setId(1);
        else person.setId(people.get(people.size() - 1).getId() + 1);
        validatePerson(person);
        savePerson(person);
    }

    public Person getById(int id) {
        List<Person> people = new ArrayList<>(jsonFileService.loadDataFromJson());
        for (Person person : people) if (person.getId() == id) return person;
        return null;
    }

    public void deletePerson(int id) {
        List<Person> people = new ArrayList<>(jsonFileService.loadDataFromJson());
        people.removeIf(person -> person.getId() == id);
        jsonFileService.saveDataToJson(people);
    }
}
