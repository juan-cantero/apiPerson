package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
  private final PersonDao personDao;

  @Autowired
  public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
    this.personDao = personDao;
  }

  public int addPerson(Person person) {
    personDao.insertPerson(person);
    return 1;
  }

  public List<Person> getAllPeople() {
    return personDao.getAllPeople();
  }

  public Optional<Person> selesctPersonById(UUID id) {
    return personDao.selesctPersonById(id);
  }

  public int deletePersonById(UUID id) {
    return personDao.deletePersonById(id);
  }

  public int updatePersonById(UUID id, Person update) {
    return personDao.updatePersonById(id, update);
  }


}
