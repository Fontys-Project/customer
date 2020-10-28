package nl.fontys.customer.data.entity;

public class Address {

    public Address(String mail, int floor, String houseNumber, String postalCode, String country, String streetName) {
        this.mail = mail;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.country = country;
        this.streetName = streetName;
    }

    private String mail;
    private String houseNumber;
    private String postalCode;
    private String country;
    private String streetName;
}
