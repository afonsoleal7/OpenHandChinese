package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;

/**
 * An {@link AuthService} implementation
 */
public class AuthServiceImpl implements AuthService {

    private CustomerService customerService;
    private Integer accessingCustomer;

    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * @see AuthService#authenticate(Integer)
     */
    @Override
    public boolean authenticate(Integer id) {

        Customer customer = customerService.get(id);
        accessingCustomer = customer == null ? null : customer.getId();
        return accessingCustomer != null;
    }

    /**
     * @see AuthService#getAccessingCustomer()
     */
    @Override
    public Customer getAccessingCustomer() {
        return customerService.get(accessingCustomer);
    }
}
