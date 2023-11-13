package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.CustomerService;
import org.academiadecodigo.javabank.view.BalanceView;

/**
 * The {@link BalanceView} controller
 */
public class BalanceController extends AbstractController {

    private CustomerService customerService;

    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Gets the customer
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return authService.getAccessingCustomer();
    }

    /**
     * Gets the customer balance
     *
     * @return the customer balance
     */
    public double getCustomerBalance() {
        return customerService.getBalance(authService.getAccessingCustomer().getId());
    }
}
