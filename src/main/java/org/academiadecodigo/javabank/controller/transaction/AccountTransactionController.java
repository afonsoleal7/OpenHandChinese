package org.academiadecodigo.javabank.controller.transaction;

import org.academiadecodigo.javabank.controller.Controller;

import java.util.Set;

/**
 * Common interface for account transaction controllers,
 * provides methods to perform account transactions and to get account ids
 */
public interface AccountTransactionController extends Controller {

    /**
     * Gets the ids of the customer accounts
     *
     * @return the account ids
     */
    Set<Integer> getAccountIds();

    /**
     * Perform an account transaction
     *
     * @param accountId the destination account id
     * @param amount the amount of the transaction
     */
    void submitTransaction(int accountId, double amount);
}
