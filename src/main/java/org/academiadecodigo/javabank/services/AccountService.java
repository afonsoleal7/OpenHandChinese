package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.account.Account;

/**
 * Common interface for account services, provides methods to manage accounts and perform account transactions
 */
public interface AccountService {

    /**
     * gets an account by its id number
     *
     * @param id the id of the account to get
     * @return the account with the given id
     */
    Account get(Integer id);

    /**
     * Adds an account to the service
     *
     * @param account the account to add
     */
    void add(Account account);

    /**
     * Perform an {@link Account} deposit
     *
     * @param id     the id of the account
     * @param amount the amount to deposit
     */
    void deposit(int id, double amount);

    /**
     * Perform an {@link Account} withdrawal
     *
     * @param id     the id of the account
     * @param amount the amount to withdraw
     */
    void withdraw(int id, double amount);

    /**
     * Performs a transfer between two {@link Account} if possible
     *
     * @param srcId  the source account id
     * @param dstId  the destination account id
     * @param amount the amount to transfer
     */
    void transfer(int srcId, int dstId, double amount);

}
