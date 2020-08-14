package ua.com.foxminded.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.dao.PersonDao;
import ua.com.foxminded.model.dto.Person;

@Service("personService")
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDao personDao;
    
    @Override
    public void addPerson(Person person) {
        personDao.addPerson(person);
    }

    @Override
    public void editPerson(Person person, UUID id) {
        personDao.editPerson(person, id);
    }

    @Override
    public void deletePerson(UUID id) {
        personDao.deletePerson(id);
    }

    @Override
    public Person findPerson(UUID id) {  
        return personDao.findPerson(id);
    }

    @Override
    public List<Person> findAllPerson() {   
        return personDao.findAllPerson();
    }
}
