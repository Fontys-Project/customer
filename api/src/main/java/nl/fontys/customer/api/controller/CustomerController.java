package nl.fontys.customer.api.controller;

import nl.fontys.customer.api.model.api.v1.request.RequestCustomer;
import nl.fontys.customer.api.model.api.v1.response.ResponseCustomer;
import nl.fontys.customer.api.security.jwt.exception.AuthorizationException;
import nl.fontys.customer.api.security.jwt.model.Token;
import nl.fontys.customer.data.entity.Customer;
import nl.fontys.customer.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/v1/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(value = "/")
    public ResponseCustomer getCustomer(HttpServletRequest request) {

        Customer customer = new Customer("mail", "name", "asdf");
        this.customerRepository.save(customer);

        Token token = (Token) request.getSession().getAttribute("session");
        if (!token.getPermissions().contains("CUSTOMER_GET_CUSTOMER")) {
            throw new AuthorizationException("Missing permission CUSTOMER_GET_CUSTOMER");
        }
        // Purely a blank endpoint, returning nothing yet.
        return null;
    }

    @PostMapping(value = "/")
    public ResponseCustomer createCustomer(RequestCustomer requestCustomer) {
        // Purely a blank endpoint, returning nothing yet.

        Customer customer = new Customer("mail", "name", "asdf");
        this.customerRepository.save(customer);

        return null;
    }

    @PutMapping(value = "/{customerId}")
    public ResponseCustomer updateCustomer(@PathVariable String customerId, RequestCustomer requestCustomer) {
        // Purely a blank endpoint, returning nothing yet.
        return null;
    }
}
