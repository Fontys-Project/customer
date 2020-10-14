package nl.fontys.customer.api.security.jwt.model;

public class Token {

    private final String email;

    public Token(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
}
