package nl.fontys.customer.api.security.jwt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserClaims {

    private final List<String> roles;
    private final boolean active;
    private final int id;
    private final String username;
    private final List<String> permissions;

    protected UserClaims() {
        this(new ArrayList<>(), false, 0, "", new ArrayList<>());
    }

    public UserClaims(List<String> roles, boolean active, int id, String username, List<String> permissions) {
        this.roles = roles;
        this.active = active;
        this.id = id;
        this.username = username;
        this.permissions = permissions;
    }

    public List<String> getRoles() {
        return this.roles;
    }

    public boolean isActive() {
        return this.active;
    }

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public List<String> getPermissions() {
        return this.permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserClaims claims = (UserClaims) o;
        return active == claims.active &&
                id == claims.id &&
                Objects.equals(roles, claims.roles) &&
                Objects.equals(username, claims.username) &&
                Objects.equals(permissions, claims.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roles, active, id, username, permissions);
    }
}
