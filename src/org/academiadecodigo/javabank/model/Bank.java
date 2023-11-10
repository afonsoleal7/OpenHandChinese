package org.academiadecodigo.javabank.model;

import org.academiadecodigo.javabank.managers.AccountManager;

import java.util.HashMap;
import java.util.Set;

/**
 * The bank entity
 */
public class Bank {

    private AccountManager accountManager;
    private HashMap<Integer, Customer> customers;

    private int loginCustomer;

    /**
     * Creates a new instance of Bank
     */
    public Bank() {
        this.customers = new HashMap<>();
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
     * Gets the ids of the bank customers
     *
     * @return customer ids
     */
    public Set<Integer> getCustomerIds() {
        return customers.keySet();
    }

    /**
     * Gets the logged in customer
     *
     * @return the customer
     */
    public Customer getLoginCustomer() {
        return customers.get(loginCustomer);
    }

    /**
     * Sets the logged in customer
     *
     * @param id the customer id
     */
    public void setLoginCustomer(int id) {
        this.loginCustomer = id;
    }

    /**
     * Adds a new customer to the bank
     *
     * @param customer the new bank customer
     */
    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }
}
