package nl.fontys.customer.api.model.api.v1.request;

public class RequestContactMedium {

    private final String type;
    private final String value;
    private final boolean preferred;

    public RequestContactMedium(String type, String value, boolean preferred) {
        this.type = type;
        this.value = value;
        this.preferred = preferred;
    }

    public String getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public boolean isPreferred() {
        return this.preferred;
    }
}
