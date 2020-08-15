package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.model.dto.PersonDto;

@Repository
@Qualifier("personDao")
public class PersonDaoImpl implements PersonDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void addPerson(PersonDto person) {
//        jdbcTemplate.update("INSERT INTO uni.persons (id, first_name, last_name) values (?, ?, ?)",
//                person.getId(), person.getFirstName(), person.getLastName());
//        System.out.println("Person Added!!");
    }

    @Override
    public void editPerson(PersonDto person, UUID id) {
//        jdbcTemplate.update("UPDATE uni.persons p SET first_name = ? , last_name = ?  WHERE p.id = ? ",
//                person.getFirstName(), person.getLastName(), id.toString());
//            System.out.println("Person Updated!!");
    }

    @Override
    public void deletePerson(UUID id) {
        jdbcTemplate.update("DELETE from uni.persons p WHERE p.id = ? ", id.toString());
        System.out.println("Person Deleted!!");
    }

    @Override
    public PersonDto findPerson(UUID id) {
        PersonDto person = (PersonDto) jdbcTemplate.queryForObject("SELECT * FROM uni.persons p where p.id = ? ",
                new Object[] { id.toString() }, new BeanPropertyRowMapper(PersonDto.class));
            return person;
    }

    @Override
    public List<PersonDto> findAllPerson() {
        List <PersonDto> persons = jdbcTemplate.query("SELECT * FROM uni.persons", new BeanPropertyRowMapper(PersonDto.class));
        return persons;
    }
}
