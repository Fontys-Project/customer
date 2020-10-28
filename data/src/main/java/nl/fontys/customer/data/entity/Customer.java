package nl.fontys.customer.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Customer {

    @Id
    private String mail;
    private String name;
    private String birthdate;
    private List<Address> addresses;

    public Customer(String mail, String name, String birthdate, List<Address> addresses) {
        this.mail = mail;
        this.name = name;
        this.birthdate = birthdate;
        this.addresses = addresses;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }


}
