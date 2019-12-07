package io.zipcoder.crudapp.controllers;

import io.zipcoder.crudapp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.zipcoder.crudapp.repositories.PersonRepository;

import java.util.List;

@RestController
public class PersonController{

    private PersonRepository personRepo;

    @Autowired
    public PersonController(PersonRepository personRepo) {

        this.personRepo = personRepo;
    }

    @PostMapping("/people")
    public Person createPerson(@RequestBody Person p){

        return personRepo.save(p);
    }

    @GetMapping("/people/{id}")
    public Person getPerson(@PathVariable Integer id){

        return personRepo.findOne(id);
    }

    @GetMapping("/people")
    public List<Person> getPersonList() {
        return (List<Person>) personRepo.findAll();
    }

    //TODO fix
    @PutMapping("/people/{id}")
    public Person updatePerson(@PathVariable Integer id,@RequestBody Person p){

        if (personRepo.exists(id)){
            Person setItEqualToSomething = personRepo.findOne(id);
            setItEqualToSomething.setFirstName(p.getFirstName());
            setItEqualToSomething.setLastName(p.getLastName());
            personRepo.save(setItEqualToSomething);
            p = setItEqualToSomething;
        }
        return p;
    }


    @DeleteMapping("/people/{id}")
    public void deletePerson(@PathVariable Integer id){
    Person toDelete =  getPerson(id);
    personRepo.delete(toDelete);

    }

}
