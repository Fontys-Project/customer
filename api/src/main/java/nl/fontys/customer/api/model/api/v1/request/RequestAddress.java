package nl.fontys.customer.api.model.api.v1.request;

public class RequestAddress {

    private final String country;
    private final String postalCode;
    private final String street;
    private final String houseNumber;

    public RequestAddress(String country, String postalCode, String street, String houseNumber) {
        this.country = country;
        this.postalCode = postalCode;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public String getCountry() {
        return this.country;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public String getStreet() {
        return this.street;
    }

    public String getHouseNumber() {
        return this.houseNumber;
    }
}
