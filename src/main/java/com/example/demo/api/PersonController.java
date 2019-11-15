package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/person")
public class PersonController {
  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @PostMapping
  public void addPerson(@RequestBody Person person) {
    personService.addPerson(person);
  }

  @GetMapping
  public List<Person> getAllPeople() {
    return personService.getAllPeople();
  }

  @GetMapping("{id}")
  //@PathVariable Annotation which indicates that a method parameter
  // should be bound to a URI template variable. Supported for RequestMapping
  // annotated handler methods.
  public Person selectPersonById(@PathVariable("id") UUID id) {
    return personService.selesctPersonById(id)
            .orElse(null);
  }

  @DeleteMapping("{id}")
  public int deletePersonById(@PathVariable("id") UUID id) {
    return personService.deletePersonById(id);
  }


  @PutMapping("{id}")
  public int updatePersonById(@PathVariable("id") UUID id,@Valid @NotNull @RequestBody Person update) {
    return personService.updatePersonById(id, update);
  }

}
