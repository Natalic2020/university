package ua.com.foxminded.dao.entity;

public class Person {

	private String id;
	private String firstName;
	private String lastName;
	private ContactInfo contactInfo;
	
    public Person() {
        
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
}
