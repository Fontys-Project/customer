package nl.fontys.customer.api.security.jwt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {

    @JsonProperty("iat")
    private final long iat;

    @JsonProperty("nbf")
    private final long nbf;

    @JsonProperty("jti")
    private final String jti;

    @JsonProperty("exp")
    private final long exp;
    private final int identity;
    private final boolean fresh;
    private final String type;

    @JsonProperty("user_claims")
    private final UserClaims userClaims;

    protected Token() {
        this(0L, 0L, "", 0L, 0, false, "", null);
    }

    public Token(long iat, long nbf, String jti, long exp, int identify, boolean fresh, String type,
                 UserClaims userClaims) {

        this.iat = iat;
        this.nbf = nbf;
        this.jti = jti;
        this.exp = exp;
        this.identity = identify;
        this.fresh = fresh;
        this.type = type;
        this.userClaims = userClaims;
    }

    @JsonProperty("iat")
    public long getIssuedAt() {
        return this.iat;
    }

    @JsonProperty("nbf")
    public long getNotBefore() {
        return this.nbf;
    }

    @JsonProperty("jti")
    public String getTokenID() {
        return this.jti;
    }

    @JsonProperty("exp")
    public long getExpiresAt() {
        return this.exp;
    }

    public int getIdentity() {
        return this.identity;
    }

    public boolean isFresh() {
        return this.fresh;
    }

    public String getType() {
        return this.type;
    }

    @JsonProperty("user_claims")
    public UserClaims getUserClaims() {
        return this.userClaims;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return iat == token.iat &&
                nbf == token.nbf &&
                exp == token.exp &&
                identity == token.identity &&
                fresh == token.fresh &&
                Objects.equals(jti, token.jti) &&
                Objects.equals(type, token.type) &&
                Objects.equals(userClaims, token.userClaims);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iat, nbf, jti, exp, identity, fresh, type, userClaims);
    }
}
