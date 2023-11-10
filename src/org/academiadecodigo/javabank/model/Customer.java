package org.academiadecodigo.javabank.model;

import org.academiadecodigo.javabank.model.account.Account;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The customer domain entity
 */
public class Customer {

    private int id;
    private String name;

    private Map<Integer, Account> accounts = new HashMap<>();

    /**
     * Creates a new instance of Customer and initializes it with given id and name
     *
     * @param id the customer id
     * @param name the customer name
     */
    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the customer id
     *
     * @return the customer id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the customer name
     *
     * @return the customer name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the customer accounts
     *
     * @return the accounts
     */
    public Set<Account> getAccounts() {
        return new HashSet<>(accounts.values());
    }

    /**
     * Gets the customer account ids
     *
     * @return the accounts ids
     */
    public Set<Integer> getAccountIds() {
        return accounts.keySet();
    }

    /**
     * Gets the balance of an {@link Account}
     *
     * @param id the id of the account
     * @return the account balance
     */
    public double getBalance(int id) {
        return accounts.get(id).getBalance();
    }

    /**
     * Gets the total customer balance
     *
     * @return the balance
     */
    public double getBalance() {

        double balance = 0;
        for (Account account : accounts.values()) {
            balance += account.getBalance();
        }

        return balance;
    }

    /**
     * Adds a new account to the customer
     *
     * @param account the account to add
     */
    public void addAccount(Account account) {
        accounts.put(account.getId(), account);
    }
}


