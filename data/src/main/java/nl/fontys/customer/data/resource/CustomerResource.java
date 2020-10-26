package nl.fontys.customer.data.resource;

import nl.fontys.customer.data.entity.Customer;
import nl.fontys.customer.data.repository.CustomerRepository;

import java.util.List;

public class CustomerResource {

    private CustomerRepository customerRepository;

    public CustomerResource(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

}
