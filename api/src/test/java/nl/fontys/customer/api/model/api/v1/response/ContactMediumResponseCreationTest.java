package nl.fontys.customer.api.model.api.v1.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("When creating a contact medium response object")
public class ContactMediumResponseCreationTest {

    private final ResponseContactMedium contactMedium = new ResponseContactMedium("SMS", "0612345678", true);

    @Test
    @DisplayName("It should return the specified type")
    public void getTypeTest() {
        assertEquals("SMS", this.contactMedium.getType());
    }

    @Test
    @DisplayName("It should return the specified value")
    public void getValue() {
        assertEquals("0612345678", this.contactMedium.getValue());
    }

    @Test
    @DisplayName("It should return the specified preferred value")
    public void getBirthDate() {
        assertTrue(this.contactMedium.isPreferred());
    }
}
