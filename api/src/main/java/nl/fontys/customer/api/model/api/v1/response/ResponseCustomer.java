package nl.fontys.customer.api.model.api.v1.response;

import java.util.List;

public class ResponseCustomer {

    private final String name;
    private final String email;
    private final String birthDate;
    private final List<ResponseAddress> addresses;
    private final List<ResponseContactMedium> contactMedia;
    private final List<ResponsePaymentMethod> paymentMethods;

    public ResponseCustomer(String name, String email, String birthDate, List<ResponseAddress> addresses,
                            List<ResponseContactMedium> contactMedia, List<ResponsePaymentMethod> paymentMethods) {

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

    public List<ResponseAddress> getAddresses() {
        return this.addresses;
    }

    public List<ResponseContactMedium> getContactMedia() {
        return this.contactMedia;
    }

    public List<ResponsePaymentMethod> getPaymentMethods() {
        return this.paymentMethods;
    }
}
