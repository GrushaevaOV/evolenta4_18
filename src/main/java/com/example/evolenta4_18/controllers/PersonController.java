package com.example.evolenta4_18.controllers;


import com.example.evolenta4_18.models.Message;
import com.example.evolenta4_18.models.Person;
import com.example.evolenta4_18.repositories.PersonRepository;
import com.example.evolenta4_18.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping()
    public Iterable<Person> getPersons() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return service.findOne(id);
    }

    @PostMapping()
    public Person addPerson(@RequestBody Person person) {
        return service.savePerson(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        return service.update(id, person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable int id) {
        service.delete(id);
    }

    @PostMapping("/{id}/messages")
    public ResponseEntity<Person> addMessage(@PathVariable int id, @RequestBody Message message) {
        return service.addMeesageToPerson(id, message);
    }

    @PutMapping("/{id}/delete-messages")
    public ResponseEntity<Person> deleteMessagePerson (@PathVariable int id){
        return service.deleteMessage(id);
    }

    @GetMapping("/{id}/show-messages")
    public Iterable<Message> showMessagePerson (@PathVariable int id) {
        return service.showMessage(id);
    }

}
