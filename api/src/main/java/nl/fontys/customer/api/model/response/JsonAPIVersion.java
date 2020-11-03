package nl.fontys.customer.api.model.response;

public class JsonAPIVersion {

    private final String version;

    public JsonAPIVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return this.version;
    }
}
