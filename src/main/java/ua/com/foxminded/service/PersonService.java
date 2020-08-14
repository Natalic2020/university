package ua.com.foxminded.service;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.Person;

public interface PersonService {
    
    public void addPerson(Person person);
    public void editPerson(Person person, UUID id);
    public void deletePerson(UUID id);
    public Person findPerson(UUID id);
    public List<Person> findAllPerson();
    
}
