package nl.fontys.customer.api.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/customer")
public class CustomerController {

    @PutMapping(value = "/{customerId}")
    public ResponseBody createCustomer(@PathVariable String customerId) {
        // Purely a blank endpoint, returning nothing yet.
        return null;
    }
}
