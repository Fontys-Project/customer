package nl.fontys.customer.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "nl.fontys.customer.data")
public class Customer {

    public static void main(String[] args) {
        SpringApplication.run(Customer.class, args);
    }
}
