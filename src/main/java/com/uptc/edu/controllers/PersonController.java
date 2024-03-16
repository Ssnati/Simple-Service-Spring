package com.uptc.edu.controllers;

import com.uptc.edu.models.Person;
import com.uptc.edu.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping()
    public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.ok(personService.getAll());
    }
}
