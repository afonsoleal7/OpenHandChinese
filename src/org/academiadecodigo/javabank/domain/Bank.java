package org.academiadecodigo.javabank.domain;

import org.academiadecodigo.javabank.managers.AccountManager;

import java.util.HashMap;
import java.util.Set;

/**
 * The bank entity
 */
public class Bank {

    private AccountManager accountManager;
    private HashMap<Integer, Customer> customers;

    /**
     * Creates a new instance of {@code Bank}
     */
    public Bank() {
        this.customers = new HashMap<>();
    }

    /**
     * Gets the customer
     *
     * @param id the customer id
     * @return the customer
     */
    public Customer getCustomer(int id) {
        return customers.get(id);
    }

    /**
     * Gets the ids of the bank customers
     *
     * @return customer ids
     */
    public Set<Integer> getCustomerIds() {
        return customers.keySet();
    }

    /**
     * Gets the total balance of the bank
     *
     * @return the bank total balance
     */
    public double getBalance() {

        double balance = 0;

        for (Customer customer : customers.values()) {
            balance += customer.getBalance();
        }

        return balance;
    }

    /**
     * Gets the account manager
     *
     * @return the account manager
     */
    public AccountManager getAccountManager() {
        return accountManager;
    }

    /**
     * Sets the account manager
     *
     * @param accountManager the account manager to set
     */
    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * Adds a new customer to the bank
     *
     * @param customer the new bank customer
     * @see Customer#setAccountManager(AccountManager)
     */
    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
        customer.setAccountManager(accountManager);
    }
}
