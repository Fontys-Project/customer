package nl.fontys.customer.api.model.api.v1.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("When creating a payment method request object")
public class PaymentMethodRequestCreationTest {

    private final RequestPaymentMethod paymentMethod = new RequestPaymentMethod("iDEAL", "NL83INGB6583086565", true);

    @Test
    @DisplayName("It should return the specified type")
    public void getTypeTest() {
        assertEquals("iDEAL", this.paymentMethod.getType());
    }

    @Test
    @DisplayName("It should return the specified value")
    public void getValue() {
        assertEquals("NL83INGB6583086565", this.paymentMethod.getValue());
    }

    @Test
    @DisplayName("It should return the specified preferred value")
    public void getBirthDate() {
        assertTrue(this.paymentMethod.isPreferred());
    }
}
