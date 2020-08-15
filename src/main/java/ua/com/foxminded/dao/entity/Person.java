package ua.com.foxminded.dao.entity;

import java.util.UUID;

public class Person {

	private String id;
	private String firstName;
	private String lastName;
	private ContactInfo contactInfo;
	
    public Person() {
        
    }
    
    public Person(Person person) {
        this.firstName = person.firstName;
        this.lastName = person.lastName;
        this.contactInfo = new ContactInfo(person.getContactInfo());
    }

    public String getId() {
        return id;
    }

    public Person setId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public Person setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contactInfo == null) ? 0 : contactInfo.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (contactInfo == null) {
            if (other.contactInfo != null)
                return false;
        } else if (!contactInfo.equals(other.contactInfo))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", contactInfo="
                + contactInfo + "]";
    }	
}
