package nl.fontys.customer.api.model.api.v1.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("When creating a customer request object")
public class CustomerRequestCreationTest {

    private final RequestCustomer customer = new RequestCustomer("Dennis van der Veeke", "dennis@example.org",
            "18-12-1997", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    @Test
    @DisplayName("It should return the specified name")
    public void getNameTest() {
        assertEquals("Dennis van der Veeke", this.customer.getName());
    }

    @Test
    @DisplayName("It should return the specified email address")
    public void getEmailTest() {
        assertEquals("dennis@example.org", this.customer.getEmail());
    }

    @Test
    @DisplayName("It should return the specified birth date")
    public void getBirthDate() {
        assertEquals("18-12-1997", this.customer.getBirthDate());
    }

    @Test
    @DisplayName("It should return the specified addresses")
    public void getAddresses() {
        assertEquals(new ArrayList<>(), this.customer.getAddresses());
    }

    @Test
    @DisplayName("It should return the specified contact media")
    public void getContactMedia() {
        assertEquals(new ArrayList<>(), this.customer.getContactMedia());
    }

    @Test
    @DisplayName("It should return the specified payment methods")
    public void getPaymentMethods() {
        assertEquals(new ArrayList<>(), this.customer.getPaymentMethods());
    }
}
