package nl.fontys.customer.api.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
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
    public Token parse(String rawToken) throws TokenValidationException {
        try {
            Jws<Claims> token = Jwts.parser().setSigningKey(this.publicKey).parseClaimsJws(rawToken);

            return null;
        } catch (Exception ex) {
            throw new TokenValidationException(ex);
        }
    }
}
