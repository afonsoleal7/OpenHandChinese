package org.academiadecodigo.javabank.model;

import org.academiadecodigo.javabank.model.account.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * The customer model entity
 */
public class Customer extends AbstractModel {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    private List<Account> accounts = new ArrayList<>();

    /**
     * Gets the firstName of the customer
     *
     * @return the customer firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the firstName of the customer
     *
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the customer accounts
     *
     * @return the accounts
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * Adds a new account to the customer
     *
     * @param account the account to add
     */
    public void addAccount(Account account) {
        account.setCustomerId(getId());
        accounts.add(account);
    }

    /**
     * Removes an account from the customer
     *
     * @param account the account to remove
     */
    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}


