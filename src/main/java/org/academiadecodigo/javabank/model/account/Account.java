package org.academiadecodigo.javabank.model.account;

import org.academiadecodigo.javabank.model.Model;

/**
 * Common interface for bank accounts, provides methods to access account
 * information and perform account transactions
 */
public interface Account extends Model {

    /**
     * Gets the account balance
     *
     * @return the account balance
     */
    double getBalance();

    /**
     * Gets the account type
     *
     * @return the account type
     */
    AccountType getAccountType();

    /**
     * Credits the account
     *
     * @param amount the amount to credit
     * @see Account#canCredit(double)
     */
    void credit(double amount);

    /**
     * Debits the account
     *
     * @param amount the amount to debit
     * @see Account#canDebit(double)
     */
    void debit(double amount);

    /**
     * Checks if a specific amount can be credited on the account
     *
     * @param amount the amount to check
     * @return {@code true} if the account can be credited
     */
    boolean canCredit(double amount);

    /**
     * Checks if a specific amount can be debited from the account
     *
     * @param amount the amount to check
     * @return {@code true} if the account can be debited
     */
    boolean canDebit(double amount);

    /**
     * Checks if the account can be withdrawn
     *
     * @return {@code true} if withdraw can be done
     */
    boolean canWithdraw();

    /**
     * Returns the owning customer's Id
     *
     * @return the owning customer's Id
     */
    Integer getCustomerId();

    /**
     * Sets the owning customer id
     *
     * @param id the owner customer id
     */
    void setCustomerId(Integer id);
}
