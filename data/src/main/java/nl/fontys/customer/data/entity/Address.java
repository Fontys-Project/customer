package nl.fontys.customer.data.entity;

public class Address {

    private String houseNumber;
    private String postalCode;
    private String country;
    private String streetName;

    public Address(String houseNumber, String postalCode, String country, String streetName) {
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.country = country;
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return this.houseNumber;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public String getCountry() {
        return this.country;
    }

    public String getStreetName() {
        return this.streetName;
    }
}
