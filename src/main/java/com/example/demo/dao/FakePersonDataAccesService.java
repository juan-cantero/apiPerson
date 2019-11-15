package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


/*Spring @Repository annotation is used to indicate
that the class provides the mechanism for storage, retrieval,
search, update and delete operation on objects.
 */
@Repository("fakeDao")
public class FakePersonDataAccesService implements PersonDao {
  private static List<Person> DB = new ArrayList<>();

  @Override
  public int insertPerson(UUID id, Person person) {
    DB.add(new Person(id,person.getName()));
    return 1;
  }

  @Override
  public List<Person> getAllPeople() {
    return DB;
  }

  @Override
  public Optional<Person> selesctPersonById(UUID id) {
    return DB.stream()
            .filter(person -> person.getId().equals(id))
            .findFirst();

  }

  @Override
  public int deletePersonById(UUID id) {
    Optional<Person> personMaybe = selesctPersonById(id);
    if (personMaybe.isEmpty()) {
      return 0;
    }else {
      DB.remove(personMaybe.get());
      return 1;
    }

  }

  @Override
  public int updatePersonById(UUID id, Person update) {
    return selesctPersonById(id)
            .map(person -> {
              int indexOfPersonToUpdate = DB.indexOf(person);
              if (indexOfPersonToUpdate >= 0) {
                DB.set(indexOfPersonToUpdate, new Person(id,update.getName()));
                return 1;
              }
              return 0;
            })
            .orElse(0);
  }


}
