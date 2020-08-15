package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.PersonDto;

public interface PersonDao {

    public void addPerson(PersonDto person);
    public void editPerson(PersonDto person, UUID id);
    public void deletePerson(UUID id);
    public PersonDto findPerson(UUID id);
    public List<PersonDto> findAllPerson();
    
}
