package nl.fontys.customer.api.security.jwt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {

    private List<String> permissions = new ArrayList<>();
    private String email = "";

    protected Token() {

    }

    public List<String> getPermissions() {
        return this.permissions;
    }

    public String getEmail() {
        return this.email;
    }
}
