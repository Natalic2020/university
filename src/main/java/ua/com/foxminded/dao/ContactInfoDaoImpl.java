package ua.com.foxminded.dao;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.model.dto.ContactInfo;

@Repository
@Qualifier("contactInfoDao")
public class ContactInfoDaoImpl implements ContactInfoDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void addContactInfo(ContactInfo contactInfo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void editContactInfo(ContactInfo contactInfo, UUID id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteContactInfo(UUID id) {
        // TODO Auto-generated method stub

    }

    @Override
    public ContactInfo findContactInfo(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

}
