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
    
//    @Autowired 
//    public void setDataSource(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//    }    
    
    public void createTables() {
        FileParser file = new FileParser();
        List<String> sqlQueryList = file.readFileToLines("sql.script");
        sqlQueryList.forEach(jdbcTemplate::execute);
    }
  
//    public void insertGroup(Group group) {
//       String sql =  "INSERT INTO uni.groups (id, name) values (:id, :name)"; 
//       SqlParameterSource namedParameters = new MapSqlParameterSource("id", group.getId()).addValue("name", group.getName());
//       namedParameterJdbcTemplate.update(sql, namedParameters) ;  
//    }   
    
//    public JdbcTemplate getJdbcTemplate() {
//        return jdbcTemplate;
//    }
//
//    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public DataSource getDataSource() {
//        return dataSource;
//    }  
}
