package ua.com.foxminded.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ua.com.foxminded.util.FileParser;

@Component
public class DatabaseInitializer {

    @Autowired
    FileParser file;
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public void createDB() {
        jdbcTemplate.batchUpdate( file.readFileToLines("sql_db.script"));
    }
}
