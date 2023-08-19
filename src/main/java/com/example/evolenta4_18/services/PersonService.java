package com.example.evolenta4_18.services;

import com.example.evolenta4_18.models.Message;
import com.example.evolenta4_18.models.Person;
import com.example.evolenta4_18.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    PersonRepository repository;

    public Iterable<Person> findAll() {
        return repository.findAll();
    }

    public Optional<Person> findOne(int personId) {
        return repository.findById(personId);
    }

    public Person savePerson(Person person) {
        return repository.save(person);
    }

    public void delete(int personId) {
        repository.deleteById(personId);
    }

    public ResponseEntity<Person> update(int personId, Person person) {
        Optional<Person> foundPerson = repository.findById(personId);
        if (foundPerson.isPresent()) {
            person.setId(personId);
            return new ResponseEntity<>(repository.save(person), HttpStatus.OK);
        } else
            return new ResponseEntity<>(repository.save(person), HttpStatus.CREATED);
    }


    public ResponseEntity<Person> addMeesageToPerson(int personId, Message message) {
        Optional<Person> foundPerson = repository.findById(personId);
        if (foundPerson.isPresent()) {
            message.setPerson(foundPerson.get());
            message.setTime(LocalDateTime.now());
            foundPerson.get().addMessage(message);
            return new ResponseEntity<>(repository.save(foundPerson.get()), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Person> deleteMessage (int personId){
        Optional<Person> foundPerson = repository.findById(personId);
        if (foundPerson.isPresent()){
            foundPerson.get().getMessages().clear();
            return new ResponseEntity<>(repository.save(foundPerson.get()), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public Iterable<Message> showMessage (int personId) {
        Optional<Person> foundPerson = repository.findById(personId);
        return foundPerson.get().getMessages();
    }
}
