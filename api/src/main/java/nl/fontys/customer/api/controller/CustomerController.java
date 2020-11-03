package nl.fontys.customer.api.controller;

import nl.fontys.customer.api.model.api.v1.request.RequestCustomer;
import nl.fontys.customer.api.model.api.v1.response.ResponseAddress;
import nl.fontys.customer.api.model.api.v1.response.ResponseCustomer;
import nl.fontys.customer.api.security.jwt.exception.AuthorizationException;
import nl.fontys.customer.api.security.jwt.model.Token;
import nl.fontys.customer.data.entity.Address;
import nl.fontys.customer.data.entity.Customer;
import nl.fontys.customer.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(value = "/")
    public List<ResponseCustomer> getCustomer(HttpServletRequest request) {

        Customer customer = new Customer("mail", "name", "asdf",null);
        this.customerRepository.save(customer);

        Token token = (Token) request.getSession().getAttribute("session");
        if (!token.getPermissions().contains("CUSTOMER_GET_CUSTOMER_ALL")) {
            throw new AuthorizationException("Missing permission CUSTOMER_GET_CUSTOMER");
        }

        return this.customerRepository.findAll().stream()
                .map(this::fromEntityCustomer)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/")
    public ResponseCustomer createCustomer(@RequestBody RequestCustomer reqCustomer) {
        // Purely a blank endpoint, returning nothing yet.

        List<Address> addresses = reqCustomer.getAddresses().stream()
                .map(a -> new Address(a.getCountry(), a.getPostalCode(), a.getStreet(), a.getHouseNumber()))
                .collect(Collectors.toList());
        Customer customer = new Customer(reqCustomer.getEmail(), reqCustomer.getName(), reqCustomer.getBirthDate(),
                addresses);

        this.customerRepository.save(customer);

        return this.fromEntityCustomer(customer);
    }

    @PutMapping(value = "/{customerId}")
    public ResponseCustomer updateCustomer(@PathVariable String customerId, RequestCustomer requestCustomer) {
        // Purely a blank endpoint, returning nothing yet.
        return null;
    }

    private ResponseAddress fromEntityAddress(Address address) {
        return new ResponseAddress(address.getCountry(), address.getPostalCode(), address.getStreetName(),
                address.getHouseNumber());
    }

    private List<ResponseAddress> fromEntityAddresses(List<Address> addresses) {
        if (addresses == null) return new ArrayList<>();
        return addresses.stream()
                .map(this::fromEntityAddress)
                .collect(Collectors.toList());
    }

    private ResponseCustomer fromEntityCustomer(Customer customer) {
        return new ResponseCustomer(customer.getName(), customer.getMail(), customer.getBirthdate(),
                fromEntityAddresses(customer.getAddresses()), new ArrayList<>(), new ArrayList<>());
    }
}
