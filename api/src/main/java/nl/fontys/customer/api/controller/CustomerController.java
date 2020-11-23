package nl.fontys.customer.api.controller;

import nl.fontys.customer.api.exception.EntityNotFoundException;
import nl.fontys.customer.api.model.api.v1.request.RequestAddress;
import nl.fontys.customer.api.model.api.v1.request.RequestCustomer;
import nl.fontys.customer.api.model.api.v1.response.ResponseAddress;
import nl.fontys.customer.api.model.api.v1.response.ResponseCustomer;
import nl.fontys.customer.api.security.jwt.exception.AuthorizationException;
import nl.fontys.customer.api.security.jwt.model.Token;
import nl.fontys.customer.data.entity.Address;
import nl.fontys.customer.data.entity.Customer;
import nl.fontys.customer.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public List<ResponseCustomer> getAllCustomers(HttpServletRequest request) {

        this.assertHasPermission(request, "CUSTOMER_CUSTOMER_GET_ALL");

        return this.customerRepository.findAll().stream()
                .map(this::fromEntityCustomer)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{email}")
    public ResponseCustomer getCustomer(HttpServletRequest request, @PathVariable String email) {

        this.assertHasPermission(request, "CUSTOMER_CUSTOMER_GET_OTHER");

        Optional<Customer> queryResult = this.customerRepository.findById(email);
        if (queryResult.isEmpty()) {
            throw new EntityNotFoundException("Customer not found");
        }

        return this.fromEntityCustomer(queryResult.get());
    }

    @PostMapping(value = "/")
    public ResponseCustomer createCustomer(HttpServletRequest request, @RequestBody RequestCustomer reqCustomer) {

        this.assertHasPermission(request, "CUSTOMER_CUSTOMER_CREATE_OTHER");

        List<Address> addresses = reqCustomer.getAddresses().stream()
                .map(a -> new Address(a.getCountry(), a.getPostalCode(), a.getStreet(), a.getHouseNumber()))
                .collect(Collectors.toList());
        Customer customer = new Customer(reqCustomer.getEmail(), reqCustomer.getName(), reqCustomer.getBirthDate(),
                addresses);

        this.customerRepository.save(customer);

        return this.fromEntityCustomer(customer);
    }

    @PutMapping(value = "/{email}")
    public ResponseCustomer updateCustomer(HttpServletRequest request, @PathVariable String email,
                                           @RequestBody RequestCustomer requestCustomer) {

        this.assertHasPermission(request, "CUSTOMER_CUSTOMER_UPDATE_OTHER");

        Optional<Customer> queryResult = this.customerRepository.findById(email);
        if (queryResult.isEmpty()) {
            throw new EntityNotFoundException("Customer not found");
        }

        Customer customerDataFromRequest = this.fromRequestCustomer(requestCustomer);

        this.customerRepository.save(customerDataFromRequest);

        return this.fromEntityCustomer(customerDataFromRequest);
    }

    @DeleteMapping(value = "/{email}")
    public ResponseCustomer deleteCustomer(HttpServletRequest request, @PathVariable String email) {

        this.assertHasPermission(request, "CUSTOMER_CUSTOMER_DELETE_OTHER");

        Optional<Customer> queryResult = this.customerRepository.findById(email);
        if (queryResult.isEmpty()) {
            throw new EntityNotFoundException("Customer not found");
        }

        this.customerRepository.delete(queryResult.get());

        return this.fromEntityCustomer(queryResult.get());
    }

    private void assertHasPermission(HttpServletRequest request, String permission) {
        Token token = (Token) request.getSession().getAttribute("session");
        if (!token.getUserClaims().getPermissions().contains(permission)) {
            throw new AuthorizationException("Missing permission " + permission);
        }
    }

    private Customer fromRequestCustomer(RequestCustomer requestCustomer) {
        return new Customer(requestCustomer.getEmail(), requestCustomer.getName(), requestCustomer.getBirthDate(),
                this.fromRequestAddresses(requestCustomer.getAddresses()));
    }

    private Address fromRequestAddress(RequestAddress requestAddress) {
        return new Address(requestAddress.getHouseNumber(), requestAddress.getPostalCode(), requestAddress.getCountry(),
                requestAddress.getStreet());
    }

    private List<Address> fromRequestAddresses(List<RequestAddress> requestAddresses) {
        if (requestAddresses == null) return new ArrayList<>();
        return requestAddresses.stream()
                .map(this::fromRequestAddress)
                .collect(Collectors.toList());
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
