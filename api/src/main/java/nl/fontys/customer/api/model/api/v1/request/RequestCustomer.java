package nl.fontys.customer.api.model.api.v1.request;

import java.util.List;

public class RequestCustomer {

    private final String name;
    private final String email;
    private final String birthDate;
    private final List<RequestAddress> addresses;
    private final List<RequestContactMedium> contactMedia;
    private final List<RequestPaymentMethod> paymentMethods;

    public RequestCustomer(String name, String email, String birthDate, List<RequestAddress> addresses,
                           List<RequestContactMedium> contactMedia, List<RequestPaymentMethod> paymentMethods) {

        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.addresses = addresses;
        this.contactMedia = contactMedia;
        this.paymentMethods = paymentMethods;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public List<RequestAddress> getAddresses() {
        return this.addresses;
    }

    public List<RequestContactMedium> getContactMedia() {
        return this.contactMedia;
    }

    public List<RequestPaymentMethod> getPaymentMethods() {
        return this.paymentMethods;
    }
}
