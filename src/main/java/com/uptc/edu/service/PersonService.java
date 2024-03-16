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
        List<Person> persons = new ArrayList<>(jsonFileService.loadDataFromJson());
        persons.add(person);
        jsonFileService.saveDataToJson(persons);
    }
}
