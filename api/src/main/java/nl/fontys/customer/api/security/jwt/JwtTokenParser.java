package nl.fontys.customer.api.security.jwt;

import nl.fontys.customer.api.security.jwt.exception.TokenValidationException;
import nl.fontys.customer.api.security.jwt.model.Token;

import java.security.PublicKey;

public class JwtTokenParser implements TokenParser {

    private final static String TOKEN_TYPE = "Bearer";

    private final PublicKey publicKey;

    public JwtTokenParser(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public Token parse(String requestToken) throws TokenValidationException {
        return null;
    }
}
