package ua.com.foxminded.dao.entity;

import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "contact_infos", schema = "uni")
public class ContactInfo {

    @Id
    @Column(name = "id_cont_info")
    private String id;
    @OneToOne()
    @JoinColumn(name = "id_person")  
    private Person person;
	private int index;
	private String country;
	private String city;
	private String street;
	private String house;
	private int apartment;
	private String phone1;
	private String phone2;
	private String email;
	
    public ContactInfo() {
       
    }

    public String getId() {
        return id;
    }

    public ContactInfo setId(String id) {
        this.id = id;
        return this;
    }

   
   

    public int getIndex() {
        return index;
    }

    public ContactInfo setIndex(int index) {
        this.index = index;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public ContactInfo setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public ContactInfo setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public ContactInfo setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getHouse() {
        return house;
    }

    public ContactInfo setHouse(String house) {
        this.house = house;
        return this;
    }

    public int getApartment() {
        return apartment;
    }

    public ContactInfo setApartment(int apartment) {
        this.apartment = apartment;
        return this;
    }

    public String getPhone1() {
        return phone1;
    }

    public ContactInfo setPhone1(String phone1) {
        this.phone1 = phone1;
        return this;
    }

    public String getPhone2() {
        return phone2;
    }

    public ContactInfo setPhone2(String phone2) {
        this.phone2 = phone2;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ContactInfo setEmail(String email) {
        this.email = email;
        return this;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

   
}
