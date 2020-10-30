package nl.fontys.customer.api.model.api.v1.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("When creating an address response object")
public class AddressResponseCreationTest {

    private final ResponseAddress address = new ResponseAddress("Netherlands", "1234AB", "My street", "1A");

    @Test
    @DisplayName("It should return the specified country")
    public void getTypeTest() {
        assertEquals("Netherlands", this.address.getCountry());
    }

    @Test
    @DisplayName("It should return the specified postal code")
    public void getPostalCodeTest() {
        assertEquals("1234AB", this.address.getPostalCode());
    }

    @Test
    @DisplayName("It should return the specified street")
    public void getStreetTest() {
        assertEquals("My street", this.address.getStreet());
    }

    @Test
    @DisplayName("It should return the specified house number")
    public void getHouseNumberTest() {
        assertEquals("1A", this.address.getHouseNumber());
    }
}
