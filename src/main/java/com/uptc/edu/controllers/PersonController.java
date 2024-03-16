package com.uptc.edu.controllers;

import com.uptc.edu.models.Person;
import com.uptc.edu.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable int id) {
        return ResponseEntity.ok(personService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Person person) {
        personService.createPerson(person);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Person person) {
        personService.editPerson(id, person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        personService.deletePerson(id);
        return ResponseEntity.ok().build();
    }
}
