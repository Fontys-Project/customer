package nl.fontys.customer.api.security.jwt.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.fontys.customer.api.ResourceDrivenTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserClaims object")
public class UserClaimsObjectTest extends ResourceDrivenTest {

    @Test
    @DisplayName("Constructor should pass all values")
    public void constructShouldPassAllValues() {
        UserClaims act = new UserClaims(List.of("testrole"), true, 1, "testuser", List.of("testpermission"));

        assertEquals(List.of("testrole"), act.getRoles());
        assertTrue(act.isActive());
        assertEquals(1, act.getId());
        assertEquals("testuser", act.getUsername());
        assertEquals(List.of("testpermission"), act.getPermissions());
    }

    @Test
    @DisplayName("Two instances with equal values must be seen as equal")
    public void equalObjectsMustBeSeenAsEqual() {
        UserClaims one = new UserClaims(List.of("testrole"), true, 1, "testuser", List.of("testpermission"));
        UserClaims two = new UserClaims(List.of("testrole"), true, 1, "testuser", List.of("testpermission"));
        UserClaims three = new UserClaims(List.of("testrole"), true, 1, "other-testuser", List.of("testpermission"));

        assertEquals(one, two);
        assertNotEquals(one, three);
        assertNotEquals(two, three);
    }

    @Test
    @DisplayName("Two instances with equal values must generate the same hashcode")
    public void equalObjectsMustGenerateSameHashcode() {
        UserClaims one = new UserClaims(List.of("testrole"), true, 1, "testuser", List.of("testpermission"));
        UserClaims two = new UserClaims(List.of("testrole"), true, 1, "testuser", List.of("testpermission"));
        UserClaims three = new UserClaims(List.of("testrole"), true, 1, "other-testuser", List.of("testpermission"));

        assertEquals(one.hashCode(), two.hashCode());
        assertNotEquals(one.hashCode(), three.hashCode());
        assertNotEquals(two.hashCode(), three.hashCode());
    }

    @Test
    @DisplayName("Can deserialize from token JSON")
    public void canDeserializeFromTokenJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        UserClaims act = mapper.readValue(this.getClass().getClassLoader()
                .getResource(packageAsPath() + "correct_user_claims.json"),
                UserClaims.class);

        assertEquals(List.of("Admin"), act.getRoles());
        assertTrue(act.isActive());
        assertEquals(1, act.getId());
        assertEquals("admin@wmstest.nl", act.getUsername());
        assertEquals(List.of("CUSTOMER_CUSTOMER_CREATE_OTHER"), act.getPermissions());
    }

    @Test
    @DisplayName("Must ignore additional unknown fields")
    public void mustIgnoreAdditionalUnknownFields() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // Will throw an exception if it does not properly ignore unknown fields
        mapper.readValue(this.getClass().getClassLoader()
                        .getResource(packageAsPath() + "unknown_fields_user_claims.json"),
                UserClaims.class);
    }
}
