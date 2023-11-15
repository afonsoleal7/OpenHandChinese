package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;

import java.util.List;
import java.util.Set;

/**
 * Common interface for customer services, provides methods to manage customers
 */
public interface CustomerService {

    /**
     * Gets the customer
     *
     * @param id the customer id
     * @return the customer with the given id
     */
    Customer get(Integer id);

    /**
     * Gets the list of customers
     *
     * @return the customer list
     */
    List<Customer> list();

    /**
     * Gets the set of customer account ids
     *
     * @param id the customer id
     * @return the accounts of the given customer id
     */
    Set<Integer> listCustomerAccountIds(Integer id);

    /**
     * Gets the balance of the customer
     *
     * @param id the customer id
     * @return the balance of the customer with the given id
     */
    double getBalance(int id);

    /**
     * Adds a customer to the service
     *
     * @param customer the customer to add
     */
    void add(Customer customer);
}
