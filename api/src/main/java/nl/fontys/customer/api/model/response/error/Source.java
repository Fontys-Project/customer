package nl.fontys.customer.api.model.response.error;

public class Source {

    private final String pointer;

    public Source(String pointer) {
        this.pointer = pointer;
    }

    public String getPointer() {
        return this.pointer;
    }
}
