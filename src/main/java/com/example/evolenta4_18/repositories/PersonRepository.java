package com.example.evolenta4_18.repositories;


import com.example.evolenta4_18.models.Message;
import com.example.evolenta4_18.models.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
}