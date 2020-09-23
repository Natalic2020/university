package ua.com.foxminded.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.util.FileParser;

@Repository
@Qualifier("tablesInitializer")
public class TablesInitializer {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Autowired
    FileParser file;
    
    public void createTables() {
        jdbcTemplate.batchUpdate( file.readFileToLines("sql.script"));
    }  
    
    public void fillTables() {
        jdbcTemplate.batchUpdate( file.readFileToLines("tables.script"));
    }  
    
}
