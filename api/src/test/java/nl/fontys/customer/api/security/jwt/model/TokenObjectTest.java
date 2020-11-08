package nl.fontys.customer.api.security.jwt.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.fontys.customer.api.ResourceDrivenTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Token object")
public class TokenObjectTest extends ResourceDrivenTest {

    @Test
    @DisplayName("Constructor should pass all values")
    public void constructShouldPassAllValues() {
        Token act = new Token(1604602507L, 1604602507L, "196c6871-e62b-40bd-ae97-3f4aaa0300c9", 1604603407L, 1, true,
                "access", null);

        assertEquals(1604602507L, act.getIssuedAt());
        assertEquals(1604602507L, act.getNotBefore());
        assertEquals("196c6871-e62b-40bd-ae97-3f4aaa0300c9", act.getTokenID());
        assertEquals(1604603407L, act.getExpiresAt());
        assertEquals(1, act.getIdentity());
        assertTrue(act.isFresh());
        assertEquals("access", act.getType());
        assertNull(act.getUserClaims());
    }

    @Test
    @DisplayName("Two instances with equal values must be seen as equal")
    public void equalObjectsMustBeSeenAsEqual() {
        Token one = new Token(1604602507L, 1604602507L, "196c6871-e62b-40bd-ae97-3f4aaa0300c9", 1604603407L, 1, true, "access", null);
        Token two = new Token(1604602507L, 1604602507L, "196c6871-e62b-40bd-ae97-3f4aaa0300c9", 1604603407L, 1, true, "access", null);
        Token three = new Token(1604602507L, 1604602517L, "196c6871-e62b-40bd-ae97-3f4aaa0300c9", 1604603407L, 1, true, "access", null);

        assertEquals(one, two);
        assertNotEquals(one, three);
        assertNotEquals(two, three);
    }

    @Test
    @DisplayName("Two instances with equal values must generate the same hashcode")
    public void equalObjectsMustGenerateSameHashcode() {
        Token one = new Token(1604602507L, 1604602507L, "196c6871-e62b-40bd-ae97-3f4aaa0300c9", 1604603407L, 1, true, "access", null);
        Token two = new Token(1604602507L, 1604602507L, "196c6871-e62b-40bd-ae97-3f4aaa0300c9", 1604603407L, 1, true, "access", null);
        Token three = new Token(1604602507L, 1604602517L, "196c6871-e62b-40bd-ae97-3f4aaa0300c9", 1604603407L, 1, true, "access", null);

        assertEquals(one.hashCode(), two.hashCode());
        assertNotEquals(one.hashCode(), three.hashCode());
        assertNotEquals(two.hashCode(), three.hashCode());
    }

    @Test
    @DisplayName("Can deserialize from token JSON")
    public void canDeserializeFromTokenJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Token act = mapper.readValue(this.getClass().getClassLoader()
                .getResource(packageAsPath() + "correct_token.json"),
                Token.class);

        assertEquals(1604602507L, act.getIssuedAt());
        assertEquals(1604602507L, act.getNotBefore());
        assertEquals("196c6871-e62b-40bd-ae97-3f4aaa0300c9", act.getTokenID());
        assertEquals(1604603407L, act.getExpiresAt());
        assertEquals(1, act.getIdentity());
        assertTrue(act.isFresh());
        assertEquals("access", act.getType());
        assertNotNull(act.getUserClaims());
    }

    @Test
    @DisplayName("Must ignore additional unknown fields")
    public void mustIgnoreAdditionalUnknownFields() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // Will throw an exception if it does not properly ignore unknown fields
        mapper.readValue(this.getClass().getClassLoader()
                        .getResource(packageAsPath() + "unknown_fields_token.json"),
                Token.class);
    }
}
