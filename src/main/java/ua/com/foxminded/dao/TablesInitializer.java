package ua.com.foxminded.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.model.dto.GroupDto;
import ua.com.foxminded.util.FileParser;

@Repository
@Qualifier("tablesInitializer")
public class TablesInitializer {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public void createTables() {
        FileParser file = new FileParser();
        List<String> sqlQueryList = file.readFileToLines("sql.script");
        sqlQueryList.forEach(jdbcTemplate::execute);
    }  
}
