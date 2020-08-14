package ua.com.foxminded.dao;

import java.util.List;
import java.util.UUID;

import ua.com.foxminded.model.dto.ContactInfo;

public interface ContactInfoDao {

    public void addContactInfo(ContactInfo contactInfo);
    public void editContactInfo(ContactInfo contactInfo, UUID id);
    public void deleteContactInfo(UUID id);
    public ContactInfo findContactInfo(UUID id);
    
}
