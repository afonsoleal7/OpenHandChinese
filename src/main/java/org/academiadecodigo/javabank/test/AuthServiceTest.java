package org.academiadecodigo.javabank.test;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.AuthServiceImpl;
import org.academiadecodigo.javabank.services.CustomerService;
import org.academiadecodigo.javabank.services.CustomerServiceImpl;

public class AuthServiceTest {

    public boolean test() {

        AuthServiceImpl authService = new AuthServiceImpl();
        CustomerService customerService = new CustomerServiceImpl();
        authService.setCustomerService(customerService);

        Customer customer = new Customer();
        customerService.add(customer);

        // should authenticate
        authService.authenticate(customer.getId());
        if (authService.getAccessingCustomer() != customer) {
            return false;
        }

        return true;
    }
}
