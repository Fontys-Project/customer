package nl.fontys.customer.api.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import nl.fontys.customer.api.security.jwt.exception.AuthorizationException;
import nl.fontys.customer.api.security.jwt.model.Token;

import java.security.PublicKey;

public class JwtTokenParser implements TokenParser {

    private final PublicKey publicKey;

    public JwtTokenParser(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public Token parse(String rawToken) throws AuthorizationException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object rawBody = Jwts.parser().setSigningKey(this.publicKey).parse(rawToken).getBody();
            return mapper.convertValue(rawBody, Token.class);
        } catch (Exception ex) {
            throw new AuthorizationException(ex.getMessage());
        }
    }
}
