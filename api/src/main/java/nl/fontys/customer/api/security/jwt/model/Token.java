package nl.fontys.customer.api.security.jwt.model;

import io.jsonwebtoken.impl.DefaultClaims;

public class Token extends DefaultClaims {

    private final String email;

    public Token(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
}
