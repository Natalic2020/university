package ua.com.foxminded.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.dao.PersonDao;
import ua.com.foxminded.model.dto.PersonDto;

@Service("personService")
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDao personDao;
    
    @Override
    public void addPerson(PersonDto person) {
        personDao.addPerson(person);
    }

    @Override
    public void editPerson(PersonDto person, UUID id) {
        personDao.editPerson(person, id);
    }

    @Override
    public void deletePerson(UUID id) {
        personDao.deletePerson(id);
    }

    @Override
    public PersonDto findPerson(UUID id) {  
        return personDao.findPerson(id);
    }

    @Override
    public List<PersonDto> findAllPerson() {   
        return personDao.findAllPerson();
    }
}
